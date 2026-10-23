package org.zerw.net.icecaves.forgelike;

import java.util.function.Supplier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.Item.Properties;

public final class ForgeLikeItems {
   private ForgeLikeItems() {
   }

   public static SpawnEggItem createFrostbiteSpawnEgg(Supplier<? extends EntityType<? extends Mob>> type) {
      return new FrostbiteForgeSpawnEggItem(type, new Properties());
   }
}
