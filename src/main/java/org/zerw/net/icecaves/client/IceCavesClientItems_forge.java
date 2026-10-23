package org.zerw.net.icecaves.client;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionContents;
import org.zerw.net.icecaves.registry.ModPotions_forge;

public final class IceCavesClientItems_forge {
   private IceCavesClientItems_forge() {
   }

   public static int potionItemColor(ItemStack stack, int tintIndex) {
      if (ModPotions_forge.isFreezing(stack) || ModPotions_forge.hasFreezingModel(stack)) {
         return -1;
      } else {
         return tintIndex > 0 ? -1 : ((PotionContents)stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)).getColor();
      }
   }

   public static int spawnEggItemColor(ItemStack stack, int tintIndex) {
      return -1;
   }
}
