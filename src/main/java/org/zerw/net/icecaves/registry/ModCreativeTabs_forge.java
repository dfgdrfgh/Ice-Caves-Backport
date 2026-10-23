package org.zerw.net.icecaves.registry;

import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.ItemLike;

public final class ModCreativeTabs_forge {
   public static final ResourceLocation TAB_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "ice_caves");
   public static final ResourceKey<CreativeModeTab> TAB_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, TAB_ID);
   public static final String TITLE_KEY = "itemGroup.ice_caves";

   private ModCreativeTabs_forge() {
   }

   public static Component title() {
      return Component.translatable("itemGroup.ice_caves");
   }

   public static ItemStack icon(ItemLike iceCrystal) {
      return new ItemStack(iceCrystal);
   }

   public static List<ItemStack> contents(
      ItemLike iceCrystal,
      ItemLike icicle,
      ItemLike brittleIce,
      ItemLike packedIceBricks,
      ItemLike packedIceBrickStairs,
      ItemLike packedIceBrickSlab,
      ItemLike packedIceBrickWall,
      ItemLike chiseledPackedIce,
      ItemLike iceDoor,
      ItemLike iceBall,
      ItemLike frostbiteSpawnEgg,
      Holder<Potion> freezing,
      Holder<Potion> longFreezing
   ) {
      return List.of(
         new ItemStack(iceCrystal),
         new ItemStack(icicle),
         new ItemStack(brittleIce),
         new ItemStack(packedIceBricks),
         new ItemStack(packedIceBrickStairs),
         new ItemStack(packedIceBrickSlab),
         new ItemStack(packedIceBrickWall),
         new ItemStack(chiseledPackedIce),
         new ItemStack(iceDoor),
         new ItemStack(iceBall),
         new ItemStack(frostbiteSpawnEgg),
         potion(Items.POTION, freezing),
         potion(Items.SPLASH_POTION, freezing),
         potion(Items.LINGERING_POTION, freezing),
         potion(Items.POTION, longFreezing),
         potion(Items.SPLASH_POTION, longFreezing),
         potion(Items.LINGERING_POTION, longFreezing),
         potion(Items.TIPPED_ARROW, freezing)
      );
   }

   private static ItemStack potion(ItemLike item, Holder<Potion> potion) {
      return ModPotions_forge.setPotionWithIcon(new ItemStack(item), potion);
   }
}
