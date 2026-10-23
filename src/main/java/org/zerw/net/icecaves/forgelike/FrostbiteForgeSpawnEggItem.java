package org.zerw.net.icecaves.forgelike;

import java.util.function.Supplier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Item.Properties;
import net.minecraftforge.common.ForgeSpawnEggItem;

public class FrostbiteForgeSpawnEggItem extends ForgeSpawnEggItem {
   public FrostbiteForgeSpawnEggItem(Supplier<? extends EntityType<? extends Mob>> type, Properties properties) {
      super(type, 3960447, 13037311, properties);
   }

   public int getColor(int tintIndex) {
      return -1;
   }
}
