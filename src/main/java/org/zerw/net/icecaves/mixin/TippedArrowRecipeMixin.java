package org.zerw.net.icecaves.mixin;

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
      method = "assemble(Lnet/minecraft/world/item/crafting/CraftingInput;Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/world/item/ItemStack;",
      at = @At("RETURN")
   )
   private void iceCaves$tagFreezingIcon(class_9694 input, class_7874 registries, CallbackInfoReturnable<class_1799> cir) {
      ModPotions.applyFreezingIcon((class_1799)cir.getReturnValue());
   }
}
