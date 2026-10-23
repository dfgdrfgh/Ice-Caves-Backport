package org.zerw.net.icecaves.mixin;

import net.minecraft.class_1308;
import net.minecraft.class_1936;
import net.minecraft.class_3730;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.zerw.net.icecaves.worldgen.IceCaveSpawnControl;

@Mixin(class_1308.class)
public abstract class MobSpawnMixin {
   @Inject(method = "checkSpawnRules", at = @At("HEAD"), cancellable = true)
   private void iceCaves$blockNonFrostbiteInIceCaves(class_1936 level, class_3730 spawnReason, CallbackInfoReturnable<Boolean> cir) {
      class_1308 self = (class_1308)(Object)this;
      if (IceCaveSpawnControl.shouldBlockMonsterSpawn(self, level, spawnReason)) {
         cir.setReturnValue(false);
      }
   }
}
