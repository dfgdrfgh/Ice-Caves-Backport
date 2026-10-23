package org.zerw.net.icecaves.mixin;

import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2498;
import net.minecraft.class_2680;
import net.minecraft.class_4970;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.zerw.net.icecaves.registry.ModSounds;

@Mixin(class_4970.class)
public class IceBlockMixin {
   @Inject(method = "getSoundType", at = @At("HEAD"), cancellable = true)
   private void iceCaves$useCustomIceSounds(class_2680 state, CallbackInfoReturnable<class_2498> cir) {
      if (IceCavesMixinLogic.hasIceSound(state)) {
         cir.setReturnValue(ModSounds.ICE);
      }
   }
}
