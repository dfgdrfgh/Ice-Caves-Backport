package org.zerw.net.icecaves.neoforge;

import java.util.function.Supplier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.Item.Properties;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

public final class NeoForgeItems {
   private NeoForgeItems() {
   }

   public static SpawnEggItem createFrostbiteSpawnEgg(Supplier<? extends EntityType<? extends Mob>> type) {
      return new DeferredSpawnEggItem(type, 3960447, 13037311, new Properties()) {
         public int getColor(int tintIndex) {
            return -1;
         }
      };
   }
}
