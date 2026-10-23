package org.zerw.net.icecaves.registry;

import java.util.List;
import net.minecraft.class_1761;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1842;
import net.minecraft.class_1935;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_6880;
import net.minecraft.class_7924;

public final class ModCreativeTabs {
   public static final class_2960 TAB_ID = class_2960.method_60655("ice_caves", "ice_caves");
   public static final class_5321<class_1761> TAB_KEY = class_5321.method_29179(class_7924.field_44688, TAB_ID);
   public static final String TITLE_KEY = "itemGroup.ice_caves";

   private ModCreativeTabs() {
   }

   public static class_2561 title() {
      return class_2561.method_43471("itemGroup.ice_caves");
   }

   public static class_1799 icon(class_1935 iceCrystal) {
      return new class_1799(iceCrystal);
   }

   public static List<class_1799> contents(
      class_1935 iceCrystal,
      class_1935 icicle,
      class_1935 brittleIce,
      class_1935 packedIceBricks,
      class_1935 packedIceBrickStairs,
      class_1935 packedIceBrickSlab,
      class_1935 packedIceBrickWall,
      class_1935 chiseledPackedIce,
      class_1935 iceDoor,
      class_1935 iceBall,
      class_1935 frostbiteSpawnEgg,
      class_6880<class_1842> freezing,
      class_6880<class_1842> longFreezing
   ) {
      return List.of(
         new class_1799(iceCrystal),
         new class_1799(icicle),
         new class_1799(brittleIce),
         new class_1799(packedIceBricks),
         new class_1799(packedIceBrickStairs),
         new class_1799(packedIceBrickSlab),
         new class_1799(packedIceBrickWall),
         new class_1799(chiseledPackedIce),
         new class_1799(iceDoor),
         new class_1799(iceBall),
         new class_1799(frostbiteSpawnEgg),
         potion(class_1802.field_8574, freezing),
         potion(class_1802.field_8436, freezing),
         potion(class_1802.field_8150, freezing),
         potion(class_1802.field_8574, longFreezing),
         potion(class_1802.field_8436, longFreezing),
         potion(class_1802.field_8150, longFreezing),
         potion(class_1802.field_8087, freezing)
      );
   }

   private static class_1799 potion(class_1935 item, class_6880<class_1842> potion) {
      return ModPotions.setPotionWithIcon(new class_1799(item), potion);
   }
}
