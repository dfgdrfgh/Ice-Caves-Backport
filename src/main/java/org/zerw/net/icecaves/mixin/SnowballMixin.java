package org.zerw.net.icecaves.mixin;

import org.zerw.net.icecaves.hooks.IceCavesMixinLogic;

import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1680;
import net.minecraft.class_1937;
import net.minecraft.class_3966;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(class_1680.class)
public class SnowballMixin {
   @Inject(method = "onHitEntity", at = @At("TAIL"))
   private void iceCaves$knockbackOnHit(class_3966 hitResult, CallbackInfo ci) {
      IceCavesMixinLogic.knockBackSnowballTarget((class_1680)(Object)this, hitResult);
   }
}
