package org.zerw.net.icecaves.forgelike.mixin;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.TippedArrowRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.zerw.net.icecaves.registry.ModPotions_forge;

@Mixin(TippedArrowRecipe.class)
public abstract class TippedArrowRecipeMixin {
   @Inject(
      method = "assemble(Lnet/minecraft/world/item/crafting/CraftingInput;Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/world/item/ItemStack;",
      at = @At("RETURN")
   )
   private void iceCaves$tagFreezingIcon(CraftingInput input, Provider registries, CallbackInfoReturnable<ItemStack> cir) {
      ModPotions_forge.applyFreezingIcon((ItemStack)cir.getReturnValue());
   }
}
