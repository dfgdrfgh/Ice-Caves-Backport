package org.zerw.net.icecaves.mixin;

import net.minecraft.class_1299;
import net.minecraft.class_1317;
import net.minecraft.class_2338;
import net.minecraft.class_3730;
import net.minecraft.class_5425;
import net.minecraft.class_5819;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.zerw.net.icecaves.worldgen.IceCaveSpawnControl;

@Mixin(class_1317.class)
public abstract class SpawnPlacementsMixin {
   @Inject(method = "method_20638", at = @At("HEAD"), cancellable = true)
   private static void iceCaves$adjustIceCaveSpawns(
      class_1299<?> entityType, class_5425 level, class_3730 spawnReason, class_2338 pos, class_5819 random, CallbackInfoReturnable<Boolean> cir
   ) {
      if (IceCaveSpawnControl.shouldBlockMonsterType(entityType, level, pos, spawnReason)) {
         cir.setReturnValue(false);
      } else {
         if (IceCaveSpawnControl.shouldAllowCaveStraySpawn(entityType, level, pos, spawnReason, random)) {
            cir.setReturnValue(true);
         }
      }
   }
}
