package org.zerw.net.icecaves.client;

import net.minecraft.class_1799;
import net.minecraft.class_1844;
import net.minecraft.class_9334;
import org.zerw.net.icecaves.registry.ModPotions;

public final class IceCavesClientItems {
   private IceCavesClientItems() {
   }

   public static int potionItemColor(class_1799 stack, int tintIndex) {
      // Always use the potion's real tint so crafted and creative Freezing arrows look identical.
      return tintIndex > 0 ? -1 : ((class_1844)stack.method_57825(class_9334.field_49651, class_1844.field_49274)).method_8064();
   }

   public static int spawnEggItemColor(class_1799 stack, int tintIndex) {
      return -1;
   }
}
