package org.zerw.net.icecaves.item;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.Item.Properties;

public class FrostbiteSpawnEggItem_forge extends SpawnEggItem {
   public FrostbiteSpawnEggItem_forge(EntityType<? extends Mob> type, Properties properties) {
      super(type, 3960447, 13037311, properties);
   }

   public int getColor(int tintIndex) {
      return -1;
   }
}
