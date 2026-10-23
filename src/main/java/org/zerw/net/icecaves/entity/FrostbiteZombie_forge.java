package org.zerw.net.icecaves.entity;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier.Builder;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import org.zerw.net.icecaves.registry.ModEffects_forge;
import org.zerw.net.icecaves.registry.ModSounds_forge;

public class FrostbiteZombie_forge extends Zombie {
   private static final int HIT_FREEZING_TICKS = 60;
   private static final double DAMAGE_EASY_NORMAL = 2.0;
   private static final double DAMAGE_HARD = 3.0;

   public FrostbiteZombie_forge(EntityType<? extends FrostbiteZombie_forge> type, Level level) {
      super(type, level);
   }

   public static Builder createAttributes() {
      return Zombie.createAttributes().add(Attributes.MAX_HEALTH, 24.0).add(Attributes.ATTACK_DAMAGE, 2.0);
   }

   public boolean doHurtTarget(Entity target) {
      AttributeInstance attack = this.getAttribute(Attributes.ATTACK_DAMAGE);
      if (attack != null) {
         attack.setBaseValue(this.level().getDifficulty() == Difficulty.HARD ? 3.0 : 2.0);
      }

      boolean result = super.doHurtTarget(target);
      if (result && target instanceof LivingEntity living) {
         ModEffects_forge.applyFreezing(living, 60, this);
      }

      return result;
   }

   protected SoundEvent getAmbientSound() {
      return ModSounds_forge.FROSTBITE_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource source) {
      return ModSounds_forge.FROSTBITE_HURT;
   }

   protected SoundEvent getDeathSound() {
      return ModSounds_forge.FROSTBITE_DEATH;
   }

   protected SoundEvent getStepSound() {
      return ModSounds_forge.FROSTBITE_STEP;
   }

   protected boolean convertsInWater() {
      return false;
   }
}
