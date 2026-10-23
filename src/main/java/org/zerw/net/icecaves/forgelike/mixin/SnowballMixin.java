package org.zerw.net.icecaves.forgelike.mixin;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Snowball.class)
public class SnowballMixin {
   @Inject(method = "onHitEntity", at = @At("TAIL"))
   private void iceCaves$knockbackOnHit(EntityHitResult hitResult, CallbackInfo ci) {
      Snowball self = (Snowball)this;
      Level level = self.level();
      if (!level.isClientSide()) {
         if (hitResult.getEntity() instanceof LivingEntity living) {
            if (living != self.getOwner()) {
               Entity source = (Entity)(self.getOwner() != null ? self.getOwner() : self);
               living.knockback(0.3, source.getX() - living.getX(), source.getZ() - living.getZ());
            }
         }
      }
   }
}
