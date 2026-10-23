package org.zerw.net.icecaves.mixin;

import org.zerw.net.icecaves.hooks.IceCavesNoteParticleLogic;

import net.minecraft.class_2400;
import net.minecraft.class_3940;
import net.minecraft.class_638;
import net.minecraft.class_703;
import net.minecraft.class_698.class_699;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_699.class)
public class NoteParticleMixin {
   @Inject(method = "createParticle", at = @At("RETURN"))
   private void iceCaves$randomNoteSize(
      class_2400 type, class_638 level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, CallbackInfoReturnable<class_703> cir
   ) {
      IceCavesNoteParticleLogic.updateSize(cir.getReturnValue(), ySpeed);
   }
}
