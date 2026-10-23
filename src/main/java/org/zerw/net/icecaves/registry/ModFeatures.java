package org.zerw.net.icecaves.registry;

import net.minecraft.class_2960;
import net.minecraft.class_2975;
import net.minecraft.class_3031;
import net.minecraft.class_3111;
import net.minecraft.class_5321;
import net.minecraft.class_6796;
import net.minecraft.class_7924;
import org.zerw.net.icecaves.worldgen.IceCaveFeature;

public final class ModFeatures {
   public static final class_2960 ICE_CAVE_ID = class_2960.method_60655("ice_caves", "ice_cave");
   public static final class_5321<class_3031<?>> ICE_CAVE_FEATURE_KEY = class_5321.method_29179(class_7924.field_41267, ICE_CAVE_ID);
   public static final class_5321<class_2975<?, ?>> ICE_CAVE_CONFIGURED_KEY = class_5321.method_29179(class_7924.field_41239, ICE_CAVE_ID);
   public static final class_5321<class_6796> ICE_CAVE_PLACED_KEY = class_5321.method_29179(class_7924.field_41245, ICE_CAVE_ID);

   private ModFeatures() {
   }

   public static IceCaveFeature createIceCave() {
      return new IceCaveFeature(class_3111.field_24893);
   }
}
