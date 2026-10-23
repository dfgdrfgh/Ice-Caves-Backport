package org.zerw.net.icecaves.worldgen;

import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1311;
import net.minecraft.class_1588;
import net.minecraft.class_1627;
import net.minecraft.class_1936;
import net.minecraft.class_1959;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_2960;
import net.minecraft.class_3481;
import net.minecraft.class_3730;
import net.minecraft.class_5425;
import net.minecraft.class_5819;
import net.minecraft.class_6862;
import net.minecraft.class_7923;
import net.minecraft.class_7924;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_2902.class_2903;
import org.zerw.net.icecaves.entity.FrostbiteZombie;
import org.zerw.net.icecaves.registry.ModBlocks;
import org.zerw.net.icecaves.registry.ModEntities;

public final class IceCaveSpawnControl {
   public static final class_6862<class_1959> HAS_ICE_CAVES = class_6862.method_40092(
      class_7924.field_41236, class_2960.method_60655("ice_caves", "has_ice_caves")
   );

   private IceCaveSpawnControl() {
   }

   public static boolean shouldBlockMonsterSpawn(class_1308 mob, class_1936 level, class_3730 reason) {
      if (!isFilteredSpawn(reason)) {
         return false;
      } else {
         class_2338 pos = mob.method_24515();
         if (mob instanceof FrostbiteZombie && !hasSolidSpawnFloor(level, pos, mob.method_5864())) {
            return true;
         } else if (!level.method_23753(pos).method_40220(HAS_ICE_CAVES)) {
            return false;
         } else {
            return !isIceCaveInterior(level, pos) ? mob instanceof FrostbiteZombie : isMonster(mob) && !isAllowedCaveHostile(mob);
         }
      }
   }

   public static boolean checkFrostbiteSpawnRules(class_1299<FrostbiteZombie> type, class_5425 level, class_3730 reason, class_2338 pos, class_5819 random) {
      return class_1588.method_20680(type, level, reason, pos, random) && hasSolidSpawnFloor(level, pos, type);
   }

   public static boolean hasSolidSpawnFloor(class_1936 level, class_2338 pos, class_1299<?> type) {
      class_2338 below = pos.method_10074();
      class_2680 floor = level.method_8320(below);
      return !floor.method_26215() && floor.method_26170(level, below, type);
   }

   public static boolean shouldBlockMonsterSpawn(class_1297 entity, class_1936 level, class_3730 reason) {
      return entity instanceof class_1308 mob && shouldBlockMonsterSpawn(mob, level, reason);
   }

   public static boolean shouldBlockMonsterType(class_1299<?> type, class_1936 level, class_2338 pos, class_3730 reason) {
      if (!isFilteredSpawn(reason) || type.method_5891() != class_1311.field_6302) {
         return false;
      } else if (isFrostbiteType(type) && !hasSolidSpawnFloor(level, pos, type)) {
         return true;
      } else if (!level.method_23753(pos).method_40220(HAS_ICE_CAVES)) {
         return false;
      } else {
         return isIceCaveInterior(level, pos) ? !isAllowedCaveHostileType(type) : isFrostbiteType(type);
      }
   }

   public static boolean shouldAllowCaveStraySpawn(class_1299<?> type, class_5425 level, class_2338 pos, class_3730 reason, class_5819 random) {
      if (type == class_1299.field_6098 && isFilteredSpawn(reason)) {
         return level.method_23753(pos).method_40220(HAS_ICE_CAVES) && isIceCaveInterior(level, pos)
            ? class_1588.method_20680(class_1299.field_6098, level, reason, pos, random)
            : false;
      } else {
         return false;
      }
   }

   private static boolean isMonster(class_1308 mob) {
      return mob.method_5864().method_5891() == class_1311.field_6302;
   }

   private static boolean isAllowedCaveHostile(class_1308 mob) {
      return mob instanceof FrostbiteZombie || mob instanceof class_1627;
   }

   private static boolean isAllowedCaveHostileType(class_1299<?> type) {
      return isFrostbiteType(type) || type == class_1299.field_6098;
   }

   private static boolean isFrostbiteType(class_1299<?> type) {
      class_2960 key = class_7923.field_41177.method_10221(type);
      return ModEntities.FROSTBITE_ID.equals(key);
   }

   private static boolean isIceCaveInterior(class_1936 level, class_2338 pos) {
      int surfaceY = Math.max(
         level.method_8624(class_2903.field_13202, pos.method_10263(), pos.method_10260()),
         level.method_8624(class_2903.field_13197, pos.method_10263(), pos.method_10260())
      );
      if (pos.method_10264() >= surfaceY - 1) {
         return false;
      } else {
         return pos.method_10264() < surfaceY - 3 ? true : countNearbyIceCaveBlocks(level, pos) >= 3;
      }
   }

   private static int countNearbyIceCaveBlocks(class_1936 level, class_2338 pos) {
      int hits = 0;
      class_2339 cursor = new class_2339();

      for (class_2350 direction : class_2350.values()) {
         for (int step = 1; step <= 3; step++) {
            cursor.method_25504(pos, direction.method_10148() * step, direction.method_10164() * step, direction.method_10165() * step);
            if (!level.method_22340(cursor)) {
               break;
            }

            if (isIceCavePalette(level.method_8320(cursor))) {
               hits++;
               break;
            }
         }
      }

      for (int dy = 1; dy <= 2; dy++) {
         if (isIceCavePalette(level.method_8320(pos.method_10087(dy)))) {
            hits++;
         }
      }

      return hits;
   }

   private static boolean isIceCavePalette(class_2680 state) {
      class_2248 iceCrystal = ModBlocks.iceCrystal();
      class_2248 icicle = ModBlocks.icicle();
      return state.method_27852(class_2246.field_10225)
         || state.method_27852(class_2246.field_10295)
         || state.method_27852(class_2246.field_10384)
         || state.method_27852(class_2246.field_27114)
         || state.method_26164(class_3481.field_15467)
         || state.method_27852(class_2246.field_10491)
         || state.method_27852(class_2246.field_27879)
         || iceCrystal != class_2246.field_10124 && state.method_27852(iceCrystal)
         || icicle != class_2246.field_10124 && state.method_27852(icicle);
   }

   private static boolean isFilteredSpawn(class_3730 reason) {
      return reason == class_3730.field_16459
         || reason == class_3730.field_16472
         || reason == class_3730.field_16463
         || reason == class_3730.field_16527
         || reason == class_3730.field_16474;
   }
}
