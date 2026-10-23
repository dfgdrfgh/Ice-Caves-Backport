package org.zerw.net.icecaves.client;

import net.minecraft.class_1799;
import net.minecraft.class_1844;
import net.minecraft.class_9334;
import org.zerw.net.icecaves.registry.ModPotions;

public final class IceCavesClientItems {
   private IceCavesClientItems() {
   }

   public static int potionItemColor(class_1799 stack, int tintIndex) {
      if (ModPotions.isFreezing(stack) || ModPotions.hasFreezingModel(stack)) {
         return -1;
      } else {
         return tintIndex > 0 ? -1 : ((class_1844)stack.method_57825(class_9334.field_49651, class_1844.field_49274)).method_8064();
      }
   }

   public static int spawnEggItemColor(class_1799 stack, int tintIndex) {
      return -1;
   }
}
