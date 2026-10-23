package org.zerw.net.icecaves.mixin;

import org.zerw.net.icecaves.hooks.IceCavesMixinLogic;

import net.minecraft.class_1799;
import net.minecraft.class_1876;
import net.minecraft.class_9694;
import net.minecraft.class_7225.class_7874;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.zerw.net.icecaves.registry.ModPotions;

@Mixin(class_1876.class)
public abstract class TippedArrowRecipeMixin {
   @Inject(
      method = "method_17740",
      at = @At("RETURN")
   )
   private void iceCaves$tagFreezingIcon(class_9694 input, class_7874 registries, CallbackInfoReturnable<class_1799> cir) {
      IceCavesMixinLogic.tagFreezingStack(cir.getReturnValue());
   }
}
