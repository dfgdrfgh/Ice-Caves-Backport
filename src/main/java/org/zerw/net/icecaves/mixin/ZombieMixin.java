package org.zerw.net.icecaves.mixin;

import net.minecraft.class_1299;
import net.minecraft.class_1551;
import net.minecraft.class_1642;
import net.minecraft.class_3218;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.zerw.net.icecaves.IceCavesFabric;
import org.zerw.net.icecaves.entity.FrostbiteZombie;

@Mixin(class_1642.class)
public abstract class ZombieMixin {
   @Unique private int iceCaves$powderSnowTicks;
   @Unique private int iceCaves$conversionTicks = -1;

   @Inject(method = "method_5773", at = @At("TAIL"))
   private void iceCaves$powderSnowConversion(CallbackInfo ci) {
      class_1642 zombie = (class_1642)(Object)this;
      if (zombie instanceof FrostbiteZombie || !(zombie.method_37908() instanceof class_3218)
            || !zombie.method_5805() || zombie.method_5987()) {
         return;
      }
      if (!(zombie instanceof class_1551) && zombie.method_5864() != class_1299.field_6051
            && zombie.method_5864() != class_1299.field_6071) {
         return;
      }
      if (!zombie.field_27857) {
         iceCaves$powderSnowTicks = 0;
         iceCaves$conversionTicks = -1;
         return;
      }
      if (iceCaves$conversionTicks < 0) {
         if (++iceCaves$powderSnowTicks >= 140) {
            iceCaves$conversionTicks = 300;
         }
         return;
      }
      if (--iceCaves$conversionTicks >= 0) {
         return;
      }
      // Drowned revert to Zombies; Zombies and Husks transform into Frostbites.
      class_1299<? extends class_1642> target = zombie instanceof class_1551
         ? class_1299.field_6051 : IceCavesFabric.FROSTBITE;
      if (zombie.method_29243(target, true) == null) {
         iceCaves$conversionTicks = 0;
      }
   }
}
