package org.zerw.net.icecaves.registry;

import java.util.function.Supplier;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1747;
import net.minecraft.class_1792;
import net.minecraft.class_1826;
import net.minecraft.class_2248;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_7924;
import net.minecraft.class_1792.class_1793;
import org.zerw.net.icecaves.entity.IceBall;
import org.zerw.net.icecaves.item.FrostbiteSpawnEggItem;
import org.zerw.net.icecaves.item.IceBallItem;

public final class ModItems {
   public static final class_2960 ICE_CRYSTAL_ID = class_2960.method_60655("ice_caves", "ice_crystal");
   public static final class_2960 ICICLE_ID = class_2960.method_60655("ice_caves", "icicle");
   public static final class_2960 BRITTLE_ICE_ID = class_2960.method_60655("ice_caves", "brittle_ice");
   public static final class_2960 PACKED_ICE_BRICKS_ID = class_2960.method_60655("ice_caves", "packed_ice_bricks");
   public static final class_2960 PACKED_ICE_BRICK_STAIRS_ID = class_2960.method_60655("ice_caves", "packed_ice_brick_stairs");
   public static final class_2960 PACKED_ICE_BRICK_SLAB_ID = class_2960.method_60655("ice_caves", "packed_ice_brick_slab");
   public static final class_2960 PACKED_ICE_BRICK_WALL_ID = class_2960.method_60655("ice_caves", "packed_ice_brick_wall");
   public static final class_2960 CHISELED_PACKED_ICE_ID = class_2960.method_60655("ice_caves", "chiseled_packed_ice");
   public static final class_2960 ICE_DOOR_ID = class_2960.method_60655("ice_caves", "ice_door");
   public static final class_2960 FROSTBITE_SPAWN_EGG_ID = class_2960.method_60655("ice_caves", "frostbite_spawn_egg");
   public static final class_2960 ICE_BALL_ID = class_2960.method_60655("ice_caves", "ice_ball");
   public static final class_5321<class_1792> ICE_CRYSTAL_KEY = class_5321.method_29179(class_7924.field_41197, ICE_CRYSTAL_ID);
   public static final class_5321<class_1792> ICICLE_KEY = class_5321.method_29179(class_7924.field_41197, ICICLE_ID);
   public static final class_5321<class_1792> FROSTBITE_SPAWN_EGG_KEY = class_5321.method_29179(class_7924.field_41197, FROSTBITE_SPAWN_EGG_ID);
   public static final class_5321<class_1792> ICE_BALL_KEY = class_5321.method_29179(class_7924.field_41197, ICE_BALL_ID);

   private ModItems() {
   }

   public static class_1747 createIceCrystalItem(class_2248 block) {
      return new class_1747(block, new class_1793());
   }

   public static class_1747 createIcicleItem(class_2248 block) {
      return new class_1747(block, new class_1793());
   }

   public static class_1747 createBrittleIceItem(class_2248 block) {
      return new class_1747(block, new class_1793());
   }

   public static class_1747 createPackedIceBricksItem(class_2248 block) {
      return new class_1747(block, new class_1793());
   }

   public static class_1747 createPackedIceBrickStairsItem(class_2248 block) {
      return new class_1747(block, new class_1793());
   }

   public static class_1747 createPackedIceBrickSlabItem(class_2248 block) {
      return new class_1747(block, new class_1793());
   }

   public static class_1747 createPackedIceBrickWallItem(class_2248 block) {
      return new class_1747(block, new class_1793());
   }

   public static class_1747 createChiseledPackedIceItem(class_2248 block) {
      return new class_1747(block, new class_1793());
   }

   public static class_1747 createIceDoorItem(class_2248 block) {
      return new class_1747(block, new class_1793());
   }

   public static class_1826 createFrostbiteSpawnEgg(class_1299<? extends class_1308> type) {
      return new FrostbiteSpawnEggItem(type, new class_1793());
   }

   public static IceBallItem createIceBallItem(Supplier<class_1299<? extends IceBall>> entityType) {
      return new IceBallItem(entityType, new class_1793().method_7889(16));
   }
}
