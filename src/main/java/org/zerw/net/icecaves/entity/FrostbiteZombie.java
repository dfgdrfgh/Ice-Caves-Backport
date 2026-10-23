package org.zerw.net.icecaves.entity;

import net.minecraft.class_1267;
import net.minecraft.class_1266;
import net.minecraft.class_1268;
import net.minecraft.class_1304;
import net.minecraft.class_1381;
import net.minecraft.class_1603;
import net.minecraft.class_1799;
import net.minecraft.class_1935;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3417;
import net.minecraft.class_5819;
import org.zerw.net.icecaves.IceCavesFabric;
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

public class FrostbiteZombie extends class_1642 implements class_1603 {
   private static final int HIT_FREEZING_TICKS = 60;
   private static final double DAMAGE_EASY_NORMAL = 2.0;
   private static final double DAMAGE_HARD = 3.0;

   public FrostbiteZombie(class_1299<? extends FrostbiteZombie> type, class_1937 level) {
      super(type, level);
   }

   public static class_5133 createAttributes() {
      return class_1642.method_26940().method_26868(class_5134.field_23716, 24.0).method_26868(class_5134.field_23721, 2.0);
   }

   // Adapted from the CC0 Bananaman backport, without Architectury or TerraBlender.
   protected void method_5964(class_5819 random, class_1266 difficulty) {
      super.method_5964(random, difficulty);
      if (random.method_43057() > 0.75F) {
         this.method_5673(class_1304.field_6173, new class_1799((class_1935)IceCavesFabric.ICE_BALL_ITEM));
      }
   }

   protected void method_5959() {
      super.method_5959();
      this.field_6201.method_6277(1, new class_1381(this, 1.0, 20, 40, 16.0F) {
         private boolean ranged;

         public boolean method_6264() {
            class_1309 target = FrostbiteZombie.this.method_5968();
            if (target == null || !FrostbiteZombie.this.method_6047().method_31574(IceCavesFabric.ICE_BALL_ITEM)) {
               return false;
            }
            boolean canAttack = FrostbiteZombie.this.method_5858(target) >= (this.ranged ? 9.0 : 25.0) && super.method_6264();
            if (canAttack) this.ranged = true;
            return canAttack;
         }

         public boolean method_6266() {
            class_1309 target = FrostbiteZombie.this.method_5968();
            boolean keep = target != null && FrostbiteZombie.this.method_6047().method_31574(IceCavesFabric.ICE_BALL_ITEM)
               && FrostbiteZombie.this.method_5858(target) >= 9.0 && super.method_6266();
            if (!keep) this.ranged = false;
            return keep;
         }
      });
   }

   public void method_7105(class_1309 target, float distanceFactor) {
      if (this.method_37908() instanceof class_3218 server) {
         IceBall projectile = new IceBall(IceCavesFabric.ICE_BALL, this, this.method_37908());
         double dy = target.method_23323(0.3333333333333333) - projectile.method_23318();
         double dx = target.method_23317() - this.method_23317();
         double dz = target.method_23321() - this.method_23321();
         projectile.method_7485(dx, dy + Math.sqrt(dx * dx + dz * dz) * 0.2, dz,
            1.6F, 14.0F - this.method_37908().method_8407().method_5461() * 4.0F);
         server.method_8649(projectile);
         this.method_5783(class_3417.field_14753, 1.0F, 1.0F);
         this.method_6104(class_1268.field_5808);
      }
   }

   // Frostbites are not freezeable and move normally across powder snow.
   public boolean method_32316() {
      return false;
   }

   public void method_5844(class_2680 state, class_243 multiplier) {
      if (!state.method_27852(class_2246.field_27879)) {
         super.method_5844(state, multiplier);
      }
   }

   protected void method_7218() {
      this.method_5783(ModSounds.FROSTBITE_CONVERTED_TO_ZOMBIE, 1.0F, 1.0F);
      this.method_7200(class_1299.field_6051);
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

}
