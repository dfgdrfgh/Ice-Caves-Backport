package org.zerw.net.icecaves.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.AABB;
import org.zerw.net.icecaves.registry.ModSounds_forge;

public class BrittleIceBlock_forge extends Block {
   public static final MapCodec<BrittleIceBlock_forge> CODEC = simpleCodec(BrittleIceBlock_forge::new);
   public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 3);
   private static final int CRACK_DELAY = 15;

   public BrittleIceBlock_forge(Properties properties) {
      super(properties);
      this.registerDefaultState((BlockState)((BlockState)this.stateDefinition.any()).setValue(STAGE, 0));
   }

   public MapCodec<BrittleIceBlock_forge> codec() {
      return CODEC;
   }

   protected boolean skipRendering(BlockState state, BlockState neighbor, Direction direction) {
      return neighbor.is(this);
   }

   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(new Property[]{STAGE});
   }

   public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
      if (!level.isClientSide() && isWeight(entity)) {
         if (!level.getBlockTicks().hasScheduledTick(pos, this)) {
            level.scheduleTick(pos, this, 15);
         }
      }
   }

   protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
      if (level.getBlockState(pos).getBlock() == this && hasWeight(level, pos)) {
         this.crack(level, pos, state);
      }
   }

   private void crack(ServerLevel level, BlockPos pos, BlockState state) {
      int stage = (Integer)state.getValue(STAGE);
      float pitch = 0.85F + stage * 0.06F;
      level.playSound((Player)null, pos, ModSounds_forge.ICE_BREAK, SoundSource.BLOCKS, 1.0F, pitch);
      if (stage >= 3) {
         level.destroyBlock(pos, false, null, 512);
      } else {
         level.setBlock(pos, (BlockState)state.setValue(STAGE, stage + 1), 3);
         level.scheduleTick(pos, this, 15);
      }
   }

   private static boolean isWeight(Entity entity) {
      return entity instanceof LivingEntity living && living.isAlive() && !living.isSpectator() && living.onGround();
   }

   private static boolean hasWeight(ServerLevel level, BlockPos pos) {
      AABB box = new AABB(pos.getX() - 0.05, pos.getY(), pos.getZ() - 0.05, pos.getX() + 1.05, pos.getY() + 2.0, pos.getZ() + 1.05);

      for (LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, box)) {
         if (isWeight(entity) && entity.getOnPos().equals(pos)) {
            return true;
         }
      }

      return false;
   }
}
