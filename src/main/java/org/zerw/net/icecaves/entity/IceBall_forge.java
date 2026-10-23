package org.zerw.net.icecaves.entity;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.zerw.net.icecaves.registry.ModEffects_forge;
import org.zerw.net.icecaves.registry.ModItems_forge;
import org.zerw.net.icecaves.registry.ModSounds_forge;

public class IceBall_forge extends ThrowableItemProjectile {
   private static final int HIT_FREEZING_TICKS = 20;

   public IceBall_forge(EntityType<? extends IceBall_forge> type, Level level) {
      super(type, level);
   }

   public IceBall_forge(EntityType<? extends IceBall_forge> type, LivingEntity thrower, Level level) {
      super(type, thrower, level);
   }

   public IceBall_forge(EntityType<? extends IceBall_forge> type, double x, double y, double z, Level level) {
      super(type, x, y, z, level);
   }

   protected Item getDefaultItem() {
      return (Item)BuiltInRegistries.ITEM.get(ModItems_forge.ICE_BALL_ID);
   }

   private ParticleOptions getParticle() {
      ItemStack item = this.getItem();
      return (ParticleOptions)(item.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, item));
   }

   public void handleEntityEvent(byte id) {
      if (id == 3) {
         ParticleOptions particle = this.getParticle();

         for (int i = 0; i < 8; i++) {
            this.level().addParticle(particle, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
         }
      }
   }

   protected void onHitEntity(EntityHitResult hitResult) {
      super.onHitEntity(hitResult);
      if (!this.level().isClientSide()) {
         if (hitResult.getEntity() instanceof LivingEntity living) {
            ModEffects_forge.applyFreezing(living, 20, this.getOwner());
            SoundEvent sound = ModSounds_forge.iceBallHit();
            if (sound != null) {
               this.level()
                  .playSound(null, this.getX(), this.getY(), this.getZ(), sound, SoundSource.NEUTRAL, 1.0F, 1.0F / (this.random.nextFloat() * 0.4F + 0.8F));
            }
         }
      }
   }

   protected void onHit(HitResult hitResult) {
      super.onHit(hitResult);
      if (!this.level().isClientSide()) {
         this.level().broadcastEntityEvent(this, (byte)3);
         this.discard();
      }
   }
}
