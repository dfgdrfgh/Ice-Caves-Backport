package org.zerw.net.icecaves.mixin;

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
      class_1680 self = (class_1680)this;
      class_1937 level = self.method_37908();
      if (!level.method_8608()) {
         if (hitResult.method_17782() instanceof class_1309 living) {
            if (living != self.method_24921()) {
               class_1297 source = (class_1297)(self.method_24921() != null ? self.method_24921() : self);
               living.method_6005(0.3, source.method_23317() - living.method_23317(), source.method_23321() - living.method_23321());
            }
         }
      }
   }
}
