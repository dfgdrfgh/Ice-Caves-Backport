package org.zerw.net.icecaves.effect;

import net.minecraft.class_1267;
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
         forceFullFreeze(mob);
      }
   }

   public boolean method_5572(class_1309 mob, int amplification) {
      if (!mob.method_32316()) {
         return true;
      }

      forceFullFreeze(mob);
      if (mob.method_37908().method_8608()) {
         return true;
      }

      if (mob.field_6012 % 20 == 0) {
         float damage = damageForDifficulty(mob.method_37908().method_8407());
         if (damage > 0.0F) {
            mob.method_5643(mob.method_48923().method_48836(), damage);
         }
      }

      return true;
   }

   public boolean method_5552(int duration, int amplification) {
      return true;
   }

   private static void forceFullFreeze(class_1309 mob) {
      mob.method_32319(true);
      mob.method_32317(mob.method_32315());
   }

   private static float damageForDifficulty(class_1267 difficulty) {
      return switch (difficulty) {
         case field_5801 -> 0.0F;
         case field_5805 -> 1.0F;
         case field_5802 -> 2.0F;
         case field_5807 -> 3.0F;
         default -> throw new MatchException(null, null);
      };
   }
}
