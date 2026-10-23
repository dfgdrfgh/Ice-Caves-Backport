package org.zerw.net.icecaves.neoforge.mixin;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionBrewing;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.zerw.net.icecaves.registry.ModPotions_forge;

@Mixin(PotionBrewing.class)
public abstract class PotionBrewingMixin {
   @Inject(method = "mix", at = @At("RETURN"))
   private void iceCaves$tagFreezingIcon(ItemStack reagent, ItemStack potionStack, CallbackInfoReturnable<ItemStack> cir) {
      ModPotions_forge.applyFreezingIcon((ItemStack)cir.getReturnValue());
   }
}
