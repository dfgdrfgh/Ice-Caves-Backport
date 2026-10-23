package org.zerw.net.icecaves.entity;

import net.minecraft.class_1267;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1324;
import net.minecraft.class_1642;
import net.minecraft.class_1937;
import net.minecraft.class_3414;
import net.minecraft.class_5134;
import net.minecraft.class_5132.class_5133;
import org.zerw.net.icecaves.registry.ModEffects;
import org.zerw.net.icecaves.registry.ModSounds;

public class FrostbiteZombie extends class_1642 {
   private static final int HIT_FREEZING_TICKS = 60;
   private static final double DAMAGE_EASY_NORMAL = 2.0;
   private static final double DAMAGE_HARD = 3.0;

   public FrostbiteZombie(class_1299<? extends FrostbiteZombie> type, class_1937 level) {
      super(type, level);
   }

   public static class_5133 createAttributes() {
      return class_1642.method_26940().method_26868(class_5134.field_23716, 24.0).method_26868(class_5134.field_23721, 2.0);
   }

   public boolean method_6121(class_1297 target) {
      class_1324 attack = this.method_5996(class_5134.field_23721);
      if (attack != null) {
         attack.method_6192(this.method_37908().method_8407() == class_1267.field_5807 ? 3.0 : 2.0);
      }

      boolean result = super.method_6121(target);
      if (result && target instanceof class_1309 living) {
         ModEffects.applyFreezing(living, 60, this);
      }

      return result;
   }

   protected class_3414 method_5994() {
      return ModSounds.FROSTBITE_AMBIENT;
   }

   protected class_3414 method_6011(class_1282 source) {
      return ModSounds.FROSTBITE_HURT;
   }

   protected class_3414 method_6002() {
      return ModSounds.FROSTBITE_DEATH;
   }

   protected class_3414 method_7207() {
      return ModSounds.FROSTBITE_STEP;
   }

   protected boolean method_7209() {
      return false;
   }
}
