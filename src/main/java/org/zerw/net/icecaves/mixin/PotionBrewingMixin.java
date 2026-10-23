package org.zerw.net.icecaves.mixin;

import org.zerw.net.icecaves.hooks.IceCavesMixinLogic;

import net.minecraft.class_1799;
import net.minecraft.class_1845;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.zerw.net.icecaves.registry.ModPotions;

@Mixin(class_1845.class)
public abstract class PotionBrewingMixin {
   @Inject(method = "mix", at = @At("RETURN"))
   private void iceCaves$tagFreezingIcon(class_1799 reagent, class_1799 potionStack, CallbackInfoReturnable<class_1799> cir) {
      IceCavesMixinLogic.tagFreezingStack(cir.getReturnValue());
   }
}
