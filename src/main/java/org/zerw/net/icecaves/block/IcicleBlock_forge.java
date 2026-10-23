package org.zerw.net.icecaves.block;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import org.zerw.net.icecaves.registry.ModEffects_forge;
import org.zerw.net.icecaves.registry.ModSounds_forge;

public final class IcicleBlock_forge extends PointedDripstoneBlock {
   private static final int MELT_LIGHT_LEVEL = 8;
   private static final int MELT_CHECK_DELAY = 20;
   private static final float RANDOM_FALL_CHANCE = 0.05F;
   private static final int HIT_FREEZING_TICKS = 100;
   private static final float FALL_DAMAGE_PER_DISTANCE = 1.0F;
   private static final int FALL_DAMAGE_MAX = 16;

   public IcicleBlock_forge(Properties properties) {
      super(properties);
   }

   protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
      if (!level.isClientSide() && this.columnExposedToMelt(level, pos, state)) {
         this.scheduleColumnFall((ServerLevel)level, pos, state, 20);
      }
   }

   protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
      if (this.columnExposedToMelt(level, pos, state)) {
         this.scheduleColumnFall(level, pos, state, 2);
      } else {
         if (state.getValue(TIP_DIRECTION) == Direction.DOWN && state.getValue(THICKNESS) == DripstoneThickness.TIP && random.nextFloat() < 0.05F) {
            this.scheduleColumnFall(level, pos, state, 2);
         }
      }
   }

   protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
      boolean melt = this.columnExposedToMelt(level, pos, state);
      boolean unsupported = !this.canSurvive(state, level, pos);
      if (melt || unsupported) {
         if (state.getValue(TIP_DIRECTION) == Direction.UP) {
            level.destroyBlock(pos, true);
         } else {
            this.fallHangingColumn(level, this.findHangingColumnTop(level, pos));
         }
      }
   }

   protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos fromPos, boolean movedByPiston) {
      if (!level.isClientSide() && this.columnExposedToMelt(level, pos, state)) {
         this.scheduleColumnFall((ServerLevel)level, pos, state, 2);
      }
   }

   protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
      Direction tipDirection = (Direction)state.getValue(TIP_DIRECTION);
      BlockPos supportPos = pos.relative(tipDirection.getOpposite());
      BlockState support = level.getBlockState(supportPos);
      return support.is(this) && support.hasProperty(TIP_DIRECTION) && support.getValue(TIP_DIRECTION) == tipDirection ? true : isValidIceCaveSupport(support);
   }

   protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
      if (direction != Direction.UP && direction != Direction.DOWN) {
         return state;
      } else {
         Direction tipDirection = (Direction)state.getValue(TIP_DIRECTION);
         if (direction == tipDirection.getOpposite() && !this.canSurvive(state, level, pos)) {
            level.scheduleTick(pos, this, tipDirection == Direction.DOWN ? 2 : 1);
            return state;
         } else {
            boolean mergeOpposingTips = state.getValue(THICKNESS) == DripstoneThickness.TIP_MERGE;
            DripstoneThickness thickness = this.calculateThickness(level, pos, tipDirection, mergeOpposingTips);
            return (BlockState)state.setValue(THICKNESS, thickness);
         }
      }
   }

   @Nullable
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      LevelAccessor level = context.getLevel();
      BlockPos pos = context.getClickedPos();
      Direction clicked = context.getClickedFace();
      Direction preferred;
      if (clicked.getAxis().isVertical()) {
         preferred = clicked;
      } else {
         preferred = context.getNearestLookingVerticalDirection().getOpposite();
      }

      Direction tipDirection = null;
      if (this.isValidIceOrIcicleSupport(level, pos, preferred)) {
         tipDirection = preferred;
      } else if (this.isValidIceOrIcicleSupport(level, pos, preferred.getOpposite())) {
         tipDirection = preferred.getOpposite();
      }

      if (tipDirection == null) {
         return null;
      }

      boolean mergeOpposingTips = !context.isSecondaryUseActive();
      DripstoneThickness thickness = this.calculateThickness(level, pos, tipDirection, mergeOpposingTips);
      return (BlockState)((BlockState)((BlockState)this.defaultBlockState().setValue(TIP_DIRECTION, tipDirection)).setValue(THICKNESS, thickness))
         .setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER);
   }

   public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
      if (state.getValue(TIP_DIRECTION) == Direction.UP && state.getValue(THICKNESS) == DripstoneThickness.TIP) {
         entity.causeFallDamage(fallDistance + 2.5F, 2.0F, level.damageSources().stalagmite());
         if (entity instanceof LivingEntity living) {
            applyFreezing(level, living);
         }
      } else {
         super.fallOn(level, state, pos, entity, fallDistance);
      }
   }

   public void onLand(Level level, BlockPos pos, BlockState state, BlockState replacedBlock, FallingBlockEntity entity) {
      applyFreezingNearby(level, entity);
      playLandingImpactSound(level, pos, entity);
      super.onLand(level, pos, state, replacedBlock, entity);
   }

   public void onBrokenAfterFall(Level level, BlockPos pos, FallingBlockEntity entity) {
      applyFreezingNearby(level, entity);
      playLandingImpactSound(level, pos, entity);
   }

   private static void playLandingImpactSound(Level level, BlockPos pos, FallingBlockEntity entity) {
      if (!entity.isSilent()) {
         level.playSound(null, pos, ModSounds_forge.ICE_BREAK, SoundSource.BLOCKS, 1.0F, 0.8F + level.getRandom().nextFloat() * 0.4F);
      }
   }

   private void scheduleColumnFall(ServerLevel level, BlockPos pos, BlockState state, int delay) {
      if (state.getValue(TIP_DIRECTION) == Direction.DOWN) {
         level.scheduleTick(this.findHangingColumnTop(level, pos), this, delay);
      } else {
         level.scheduleTick(pos, this, delay);
      }
   }

   private BlockPos findHangingColumnTop(LevelReader level, BlockPos pos) {
      MutableBlockPos cursor = pos.mutable();

      while (true) {
         BlockState above = level.getBlockState(cursor.above());
         if (!above.is(this) || above.getValue(TIP_DIRECTION) != Direction.DOWN) {
            return cursor.immutable();
         }

         cursor.move(Direction.UP);
      }
   }

   private boolean columnExposedToMelt(LevelReader level, BlockPos pos, BlockState state) {
      if (state.getValue(TIP_DIRECTION) == Direction.UP) {
         return shouldMelt(level, pos);
      }

      MutableBlockPos cursor = this.findHangingColumnTop(level, pos).mutable();

      while (true) {
         BlockState current = level.getBlockState(cursor);
         if (!current.is(this) || current.getValue(TIP_DIRECTION) != Direction.DOWN) {
            return false;
         }

         if (shouldMelt(level, cursor)) {
            return true;
         }

         cursor.move(Direction.DOWN);
      }
   }

   private void fallHangingColumn(ServerLevel level, BlockPos topPos) {
      MutableBlockPos fallPos = topPos.mutable();
      BlockState fallState = level.getBlockState(fallPos);
      int topY = topPos.getY();

      while (fallState.is(this) && fallState.getValue(TIP_DIRECTION) == Direction.DOWN) {
         FallingBlockEntity entity = FallingBlockEntity.fall(level, fallPos, fallState);
         DripstoneThickness thickness = (DripstoneThickness)fallState.getValue(THICKNESS);
         boolean tip = thickness == DripstoneThickness.TIP || thickness == DripstoneThickness.TIP_MERGE;
         if (tip) {
            int size = Math.max(1, topY - fallPos.getY() + 1);
            entity.setHurtsEntities(1.0F * size, 16);
            break;
         }

         fallPos.move(Direction.DOWN);
         fallState = level.getBlockState(fallPos);
      }
   }

   private boolean isValidIceOrIcicleSupport(LevelReader level, BlockPos pos, Direction tipDirection) {
      BlockPos supportPos = pos.relative(tipDirection.getOpposite());
      BlockState support = level.getBlockState(supportPos);
      return support.is(this) && support.hasProperty(TIP_DIRECTION) && support.getValue(TIP_DIRECTION) == tipDirection ? true : isValidIceCaveSupport(support);
   }

   private static boolean isValidIceCaveSupport(BlockState support) {
      return support.is(BlockTags.ICE)
         || support.is(Blocks.PACKED_ICE)
         || support.is(Blocks.ICE)
         || support.is(Blocks.BLUE_ICE)
         || support.is(Blocks.CALCITE)
         || support.is(Blocks.STONE)
         || support.is(BlockTags.BASE_STONE_OVERWORLD);
   }

   private DripstoneThickness calculateThickness(LevelReader level, BlockPos pos, Direction tipDirection, boolean mergeOpposingTips) {
      Direction baseDirection = tipDirection.getOpposite();
      BlockState inFrontState = level.getBlockState(pos.relative(tipDirection));
      if (!this.isIcicleWithDirection(inFrontState, baseDirection)) {
         if (!this.isIcicleWithDirection(inFrontState, tipDirection)) {
            return DripstoneThickness.TIP;
         } else {
            DripstoneThickness inFrontThickness = (DripstoneThickness)inFrontState.getValue(THICKNESS);
            if (inFrontThickness != DripstoneThickness.TIP && inFrontThickness != DripstoneThickness.TIP_MERGE) {
               BlockState behindState = level.getBlockState(pos.relative(baseDirection));
               return !this.isIcicleWithDirection(behindState, tipDirection) ? DripstoneThickness.BASE : DripstoneThickness.MIDDLE;
            } else {
               return DripstoneThickness.FRUSTUM;
            }
         }
      } else {
         return !mergeOpposingTips && inFrontState.getValue(THICKNESS) != DripstoneThickness.TIP_MERGE ? DripstoneThickness.TIP : DripstoneThickness.TIP_MERGE;
      }
   }

   private boolean isIcicleWithDirection(BlockState state, Direction tipDirection) {
      return state.is(this) && state.getValue(TIP_DIRECTION) == tipDirection;
   }

   private static void applyFreezingNearby(Level level, FallingBlockEntity falling) {
      if (!level.isClientSide()) {
         AABB box = falling.getBoundingBox().inflate(0.35);

         for (LivingEntity living : level.getEntitiesOfClass(LivingEntity.class, box)) {
            applyFreezing(level, living);
         }
      }
   }

   private static void applyFreezing(Level level, LivingEntity living) {
      if (!level.isClientSide()) {
         ModEffects_forge.applyFreezing(living, 100, null);
      }
   }

   private static boolean shouldMelt(LevelReader level, BlockPos pos) {
      return level.getBrightness(LightLayer.BLOCK, pos) >= 8;
   }
}
