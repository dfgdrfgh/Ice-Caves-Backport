package org.zerw.net.icecaves.mixin;

import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2488;
import net.minecraft.class_2680;
import net.minecraft.class_4538;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(class_2488.class)
public abstract class SnowLayerBlockMixin {
   @Inject(method = "method_9558", at = @At("HEAD"), cancellable = true)
   private void iceCaves$surviveOnPackedIce(class_2680 state, class_4538 level,
         class_2338 pos, CallbackInfoReturnable<Boolean> cir) {
      if (IceCavesMixinLogic.canSupportSnowOnPackedIce(level, pos)) {
         cir.setReturnValue(true);
      }
   }
}
