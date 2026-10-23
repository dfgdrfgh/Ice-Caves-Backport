package org.zerw.net.icecaves.effect;

import net.minecraft.class_1291;
import net.minecraft.class_1309;
import net.minecraft.class_4081;

public final class FreezingMobEffect extends class_1291 {
   private static final int COLOR = 7260415;
   private static final int HURT_INTERVAL_TICKS = 20;

   public FreezingMobEffect() {
      super(class_4081.field_18272, 7260415);
   }

   public void method_52520(class_1309 mob, int amplification) {
      if (mob.method_32316()) {
         increaseFreeze(mob, amplification);
      }
   }

   public boolean method_5572(class_1309 mob, int amplification) {
      if (mob.method_32316()) {
         increaseFreeze(mob, amplification);
      }
      return true;
   }

   public boolean method_5552(int duration, int amplification) {
      return true;
   }

   private static void increaseFreeze(class_1309 mob, int amplification) {
      // Reproduce progressive freezing without applying an extra source of freeze damage.
      int current = mob.method_32312();
      int threshold = mob.method_32315();
      mob.method_32317(Math.min(threshold + 3 + Math.max(0, amplification), current + 2));
   }
}
