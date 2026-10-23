package org.zerw.net.icecaves.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import org.zerw.net.icecaves.entity.FrostbiteZombie_forge;
import org.zerw.net.icecaves.registry.ModBlocks_forge;
import org.zerw.net.icecaves.registry.ModEntities_forge;

public final class IceCaveSpawnControl_forge {
   public static final TagKey<Biome> HAS_ICE_CAVES = TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath("ice_caves", "has_ice_caves"));

   private IceCaveSpawnControl_forge() {
   }

   public static boolean shouldBlockMonsterSpawn(Mob mob, LevelAccessor level, MobSpawnType reason) {
      if (!isFilteredSpawn(reason)) {
         return false;
      } else {
         BlockPos pos = mob.blockPosition();
         if (mob instanceof FrostbiteZombie_forge && !hasSolidSpawnFloor(level, pos, mob.getType())) {
            return true;
         } else if (!level.getBiome(pos).is(HAS_ICE_CAVES)) {
            return false;
         } else {
            return !isIceCaveInterior(level, pos) ? mob instanceof FrostbiteZombie_forge : isMonster(mob) && !isAllowedCaveHostile(mob);
         }
      }
   }

   public static boolean checkFrostbiteSpawnRules(
      EntityType<FrostbiteZombie_forge> type, ServerLevelAccessor level, MobSpawnType reason, BlockPos pos, RandomSource random
   ) {
      return Monster.checkMonsterSpawnRules(type, level, reason, pos, random) && hasSolidSpawnFloor(level, pos, type);
   }

   public static boolean hasSolidSpawnFloor(LevelAccessor level, BlockPos pos, EntityType<?> type) {
      BlockPos below = pos.below();
      BlockState floor = level.getBlockState(below);
      return !floor.isAir() && floor.isValidSpawn(level, below, type);
   }

   public static boolean shouldBlockMonsterSpawn(Entity entity, LevelAccessor level, MobSpawnType reason) {
      return entity instanceof Mob mob && shouldBlockMonsterSpawn(mob, level, reason);
   }

   public static boolean shouldBlockMonsterType(EntityType<?> type, LevelAccessor level, BlockPos pos, MobSpawnType reason) {
      if (!isFilteredSpawn(reason) || type.getCategory() != MobCategory.MONSTER) {
         return false;
      } else if (isFrostbiteType(type) && !hasSolidSpawnFloor(level, pos, type)) {
         return true;
      } else if (!level.getBiome(pos).is(HAS_ICE_CAVES)) {
         return false;
      } else {
         return isIceCaveInterior(level, pos) ? !isAllowedCaveHostileType(type) : isFrostbiteType(type);
      }
   }

   public static boolean shouldAllowCaveStraySpawn(EntityType<?> type, ServerLevelAccessor level, BlockPos pos, MobSpawnType reason, RandomSource random) {
      if (type == EntityType.STRAY && isFilteredSpawn(reason)) {
         return level.getBiome(pos).is(HAS_ICE_CAVES) && isIceCaveInterior(level, pos)
            ? Monster.checkMonsterSpawnRules(EntityType.STRAY, level, reason, pos, random)
            : false;
      } else {
         return false;
      }
   }

   private static boolean isMonster(Mob mob) {
      return mob.getType().getCategory() == MobCategory.MONSTER;
   }

   private static boolean isAllowedCaveHostile(Mob mob) {
      return mob instanceof FrostbiteZombie_forge || mob instanceof Stray;
   }

   private static boolean isAllowedCaveHostileType(EntityType<?> type) {
      return isFrostbiteType(type) || type == EntityType.STRAY;
   }

   private static boolean isFrostbiteType(EntityType<?> type) {
      ResourceLocation key = BuiltInRegistries.ENTITY_TYPE.getKey(type);
      return ModEntities_forge.FROSTBITE_ID.equals(key);
   }

   private static boolean isIceCaveInterior(LevelAccessor level, BlockPos pos) {
      int surfaceY = Math.max(level.getHeight(Types.WORLD_SURFACE, pos.getX(), pos.getZ()), level.getHeight(Types.MOTION_BLOCKING, pos.getX(), pos.getZ()));
      if (pos.getY() >= surfaceY - 1) {
         return false;
      } else {
         return pos.getY() < surfaceY - 3 ? true : countNearbyIceCaveBlocks(level, pos) >= 3;
      }
   }

   private static int countNearbyIceCaveBlocks(LevelAccessor level, BlockPos pos) {
      int hits = 0;
      MutableBlockPos cursor = new MutableBlockPos();

      for (Direction direction : Direction.values()) {
         for (int step = 1; step <= 3; step++) {
            cursor.setWithOffset(pos, direction.getStepX() * step, direction.getStepY() * step, direction.getStepZ() * step);
            if (!level.hasChunkAt(cursor)) {
               break;
            }

            if (isIceCavePalette(level.getBlockState(cursor))) {
               hits++;
               break;
            }
         }
      }

      for (int dy = 1; dy <= 2; dy++) {
         if (isIceCavePalette(level.getBlockState(pos.below(dy)))) {
            hits++;
         }
      }

      return hits;
   }

   private static boolean isIceCavePalette(BlockState state) {
      Block iceCrystal = ModBlocks_forge.iceCrystal();
      Block icicle = ModBlocks_forge.icicle();
      return state.is(Blocks.PACKED_ICE)
         || state.is(Blocks.ICE)
         || state.is(Blocks.BLUE_ICE)
         || state.is(Blocks.CALCITE)
         || state.is(BlockTags.ICE)
         || state.is(Blocks.SNOW_BLOCK)
         || state.is(Blocks.POWDER_SNOW)
         || iceCrystal != Blocks.AIR && state.is(iceCrystal)
         || icicle != Blocks.AIR && state.is(icicle);
   }

   private static boolean isFilteredSpawn(MobSpawnType reason) {
      return reason == MobSpawnType.NATURAL
         || reason == MobSpawnType.CHUNK_GENERATION
         || reason == MobSpawnType.REINFORCEMENT
         || reason == MobSpawnType.PATROL
         || reason == MobSpawnType.STRUCTURE;
   }
}
