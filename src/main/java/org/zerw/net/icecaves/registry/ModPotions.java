package org.zerw.net.icecaves.registry;

import javax.annotation.Nullable;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1799;
import net.minecraft.class_1842;
import net.minecraft.class_1844;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_6880;
import net.minecraft.class_7924;
import net.minecraft.class_9280;
import net.minecraft.class_9334;

public final class ModPotions {
   public static final class_2960 FREEZING_ID = class_2960.method_60655("ice_caves", "freezing");
   public static final class_2960 LONG_FREEZING_ID = class_2960.method_60655("ice_caves", "long_freezing");
   public static final class_5321<class_1842> FREEZING_KEY = class_5321.method_29179(class_7924.field_41215, FREEZING_ID);
   public static final class_5321<class_1842> LONG_FREEZING_KEY = class_5321.method_29179(class_7924.field_41215, LONG_FREEZING_ID);
   public static final int FREEZING_DURATION_TICKS = 900;
   public static final int LONG_FREEZING_DURATION_TICKS = 1800;
   public static final int FREEZING_CUSTOM_MODEL_DATA = 1;
   public static final int FREEZING_FALLBACK_TINT = 7260415;

   private ModPotions() {
   }

   public static class_1842 createFreezing(class_6880<class_1291> freezingEffect) {
      return new class_1842("freezing", new class_1293[]{new class_1293(freezingEffect, 900)});
   }

   public static class_1842 createLongFreezing(class_6880<class_1291> freezingEffect) {
      return new class_1842("freezing", new class_1293[]{new class_1293(freezingEffect, 1800)});
   }

   public static boolean isFreezing(@Nullable class_6880<class_1842> potion) {
      return potion != null && (potion.method_40225(FREEZING_KEY) || potion.method_40225(LONG_FREEZING_KEY));
   }

   public static boolean isFreezing(class_1799 stack) {
      return ((class_1844)stack.method_57825(class_9334.field_49651, class_1844.field_49274)).comp_2378().filter(ModPotions::isFreezing).isPresent();
   }

   public static boolean hasFreezingModel(class_1799 stack) {
      class_9280 data = (class_9280)stack.method_57824(class_9334.field_49637);
      return data != null && data.comp_2382() == 1;
   }

   public static class_1799 applyFreezingIcon(class_1799 stack) {
      if (!stack.method_7960() && isFreezing(stack)) {
         stack.method_57379(class_9334.field_49637, new class_9280(1));
      }

      return stack;
   }

   public static class_1799 setPotionWithIcon(class_1799 stack, class_6880<class_1842> potion) {
      stack.method_57379(class_9334.field_49651, new class_1844(potion));
      return applyFreezingIcon(stack);
   }
}
