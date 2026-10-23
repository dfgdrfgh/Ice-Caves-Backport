package org.zerw.net.icecaves.neoforge.mixin;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.particle.NoteParticle.Provider;
import net.minecraft.core.particles.SimpleParticleType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Provider.class)
public class NoteParticleMixin {
   @Inject(method = "createParticle", at = @At("RETURN"))
   private void iceCaves$randomNoteSize(
      SimpleParticleType type,
      ClientLevel level,
      double x,
      double y,
      double z,
      double xSpeed,
      double ySpeed,
      double zSpeed,
      CallbackInfoReturnable<Particle> cir
   ) {
      if (ySpeed > 0.0 && cir.getReturnValue() instanceof SingleQuadParticle particle) {
         particle.scale((float)ySpeed);
      }
   }
}
