package org.zerw.net.icecaves.effect;

import net.minecraft.world.Difficulty;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public final class FreezingMobEffect_forge extends MobEffect {
   private static final int COLOR = 7260415;
   private static final int HURT_INTERVAL_TICKS = 20;

   public FreezingMobEffect_forge() {
      super(MobEffectCategory.HARMFUL, 7260415);
   }

   public void onEffectStarted(LivingEntity mob, int amplification) {
      if (mob.canFreeze()) {
         forceFullFreeze(mob);
      }
   }

   public boolean applyEffectTick(LivingEntity mob, int amplification) {
      if (!mob.canFreeze()) {
         return true;
      }

      forceFullFreeze(mob);
      if (mob.level().isClientSide()) {
         return true;
      }

      if (mob.tickCount % 20 == 0) {
         float damage = damageForDifficulty(mob.level().getDifficulty());
         if (damage > 0.0F) {
            mob.hurt(mob.damageSources().freeze(), damage);
         }
      }

      return true;
   }

   public boolean shouldApplyEffectTickThisTick(int duration, int amplification) {
      return true;
   }

   private static void forceFullFreeze(LivingEntity mob) {
      mob.setIsInPowderSnow(true);
      mob.setTicksFrozen(mob.getTicksRequiredToFreeze());
   }

   private static float damageForDifficulty(Difficulty difficulty) {
      return switch (difficulty) {
         case PEACEFUL -> 0.0F;
         case EASY -> 1.0F;
         case NORMAL -> 2.0F;
         case HARD -> 3.0F;
         default -> throw new MatchException(null, null);
      };
   }
}
