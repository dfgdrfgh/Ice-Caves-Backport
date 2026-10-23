package org.zerw.net.icecaves.mixin;

import org.zerw.net.icecaves.hooks.IceCavesMixinLogic;

import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2404;
import net.minecraft.class_2680;
import net.minecraft.class_3486;
import net.minecraft.class_2350.class_2353;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.zerw.net.icecaves.IceCavesFabric;

@Mixin(class_2404.class)
public abstract class WaterOnBlueIceMixin {
   @Inject(method = "method_9615", at = @At("HEAD"))
   private void iceCaves$onPlaced(class_2680 state, class_1937 world, class_2338 pos,
         class_2680 previous, boolean movedByPiston, CallbackInfo ci) {
      IceCavesMixinLogic.freezeWaterBesideBlueIce(state, world, pos);
   }

   @Inject(method = "method_9612", at = @At("HEAD"))
   private void iceCaves$onNeighbor(class_2680 state, class_1937 world, class_2338 pos,
         class_2248 neighbor, class_2338 neighborPos, boolean movedByPiston, CallbackInfo ci) {
      IceCavesMixinLogic.freezeWaterBesideBlueIce(state, world, pos);
   }

}
