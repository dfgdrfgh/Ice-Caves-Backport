package org.zerw.net.icecaves.registry;

import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2323;
import net.minecraft.class_2482;
import net.minecraft.class_2510;
import net.minecraft.class_2544;
import net.minecraft.class_2960;
import net.minecraft.class_3417;
import net.minecraft.class_3619;
import net.minecraft.class_3620;
import net.minecraft.class_5321;
import net.minecraft.class_5542;
import net.minecraft.class_7923;
import net.minecraft.class_7924;
import net.minecraft.class_8177;
import net.minecraft.class_4970.class_2250;
import net.minecraft.class_4970.class_2251;
import net.minecraft.class_8177.class_2441;
import org.zerw.net.icecaves.block.BrittleIceBlock;
import org.zerw.net.icecaves.block.IcicleBlock;

public final class ModBlocks {
   public static final class_2960 ICE_CRYSTAL_ID = class_2960.method_60655("ice_caves", "ice_crystal");
   public static final class_2960 ICICLE_ID = class_2960.method_60655("ice_caves", "icicle");
   public static final class_2960 BRITTLE_ICE_ID = class_2960.method_60655("ice_caves", "brittle_ice");
   public static final class_2960 PACKED_ICE_BRICKS_ID = class_2960.method_60655("ice_caves", "packed_ice_bricks");
   public static final class_2960 PACKED_ICE_BRICK_STAIRS_ID = class_2960.method_60655("ice_caves", "packed_ice_brick_stairs");
   public static final class_2960 PACKED_ICE_BRICK_SLAB_ID = class_2960.method_60655("ice_caves", "packed_ice_brick_slab");
   public static final class_2960 PACKED_ICE_BRICK_WALL_ID = class_2960.method_60655("ice_caves", "packed_ice_brick_wall");
   public static final class_2960 CHISELED_PACKED_ICE_ID = class_2960.method_60655("ice_caves", "chiseled_packed_ice");
   public static final class_2960 ICE_DOOR_ID = class_2960.method_60655("ice_caves", "ice_door");
   public static final class_5321<class_2248> ICE_CRYSTAL_KEY = class_5321.method_29179(class_7924.field_41254, ICE_CRYSTAL_ID);
   public static final class_5321<class_2248> ICICLE_KEY = class_5321.method_29179(class_7924.field_41254, ICICLE_ID);
   public static final class_5321<class_2248> BRITTLE_ICE_KEY = class_5321.method_29179(class_7924.field_41254, BRITTLE_ICE_ID);

   private ModBlocks() {
   }

   public static class_5542 createIceCrystal() {
      return new class_5542(5.0F, 3.0F, class_2251.method_55226(class_2246.field_27162).method_9626(ModSounds.ICE_CRYSTAL));
   }

   public static IcicleBlock createIcicle() {
      return new IcicleBlock(
         class_2251.method_9637()
            .method_31710(class_3620.field_16016)
            .method_51369()
            .method_22488()
            .method_9626(ModSounds.ICE_CRYSTAL)
            .method_9640()
            .method_9632(0.5F)
            .method_9624()
            .method_49229(class_2250.field_10657)
            .method_50012(class_3619.field_15971)
            .method_26236((state, level, pos) -> false)
      );
   }

   public static BrittleIceBlock createBrittleIce() {
      return new BrittleIceBlock(
         class_2251.method_9637().method_31710(class_3620.field_16016).method_9628(0.98F).method_9632(0.5F).method_9626(ModSounds.ICE).method_22488()
      );
   }

   private static class_2251 iceBrickProperties() {
      return class_2251.method_9637().method_31710(class_3620.field_16016).method_9628(0.98F).method_9632(0.5F).method_9626(ModSounds.ICE);
   }

   public static class_2248 createPackedIceBricks() {
      return new class_2248(iceBrickProperties());
   }

   public static class_2510 createPackedIceBrickStairs(class_2248 base) {
      return new class_2510(base.method_9564(), iceBrickProperties());
   }

   public static class_2482 createPackedIceBrickSlab() {
      return new class_2482(iceBrickProperties());
   }

   public static class_2544 createPackedIceBrickWall() {
      return new class_2544(iceBrickProperties().method_51369());
   }

   public static class_2248 createChiseledPackedIce() {
      return new class_2248(iceBrickProperties());
   }

   public static class_2323 createIceDoor() {
      class_8177 type = new class_8177(
         "ice_door",
         true,
         true,
         false,
         class_2441.field_11361,
         ModSounds.ICE,
         ModSounds.ICE_DOOR_CLOSE,
         ModSounds.ICE_DOOR_OPEN,
         class_3417.field_46943,
         class_3417.field_46944,
         class_3417.field_15100,
         class_3417.field_14988,
         class_3417.field_14954,
         class_3417.field_14791
      );
      return new class_2323(type, class_2251.method_9637().method_31710(class_3620.field_16016).method_9632(3.0F).method_22488().method_9626(ModSounds.ICE));
   }

   public static class_2248 iceCrystal() {
      return (class_2248)class_7923.field_41175.method_10223(ICE_CRYSTAL_ID);
   }

   public static class_2248 icicle() {
      return (class_2248)class_7923.field_41175.method_10223(ICICLE_ID);
   }
}
