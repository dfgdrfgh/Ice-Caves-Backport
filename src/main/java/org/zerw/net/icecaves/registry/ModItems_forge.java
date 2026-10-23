package org.zerw.net.icecaves.registry;

import java.util.function.Supplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Block;
import org.zerw.net.icecaves.entity.IceBall_forge;
import org.zerw.net.icecaves.item.FrostbiteSpawnEggItem_forge;
import org.zerw.net.icecaves.item.IceBallItem_forge;

public final class ModItems_forge {
   public static final ResourceLocation ICE_CRYSTAL_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "ice_crystal");
   public static final ResourceLocation ICICLE_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "icicle");
   public static final ResourceLocation BRITTLE_ICE_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "brittle_ice");
   public static final ResourceLocation PACKED_ICE_BRICKS_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "packed_ice_bricks");
   public static final ResourceLocation PACKED_ICE_BRICK_STAIRS_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "packed_ice_brick_stairs");
   public static final ResourceLocation PACKED_ICE_BRICK_SLAB_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "packed_ice_brick_slab");
   public static final ResourceLocation PACKED_ICE_BRICK_WALL_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "packed_ice_brick_wall");
   public static final ResourceLocation CHISELED_PACKED_ICE_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "chiseled_packed_ice");
   public static final ResourceLocation ICE_DOOR_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "ice_door");
   public static final ResourceLocation FROSTBITE_SPAWN_EGG_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "frostbite_spawn_egg");
   public static final ResourceLocation ICE_BALL_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "ice_ball");
   public static final ResourceKey<Item> ICE_CRYSTAL_KEY = ResourceKey.create(Registries.ITEM, ICE_CRYSTAL_ID);
   public static final ResourceKey<Item> ICICLE_KEY = ResourceKey.create(Registries.ITEM, ICICLE_ID);
   public static final ResourceKey<Item> FROSTBITE_SPAWN_EGG_KEY = ResourceKey.create(Registries.ITEM, FROSTBITE_SPAWN_EGG_ID);
   public static final ResourceKey<Item> ICE_BALL_KEY = ResourceKey.create(Registries.ITEM, ICE_BALL_ID);

   private ModItems_forge() {
   }

   public static BlockItem createIceCrystalItem(Block block) {
      return new BlockItem(block, new Properties());
   }

   public static BlockItem createIcicleItem(Block block) {
      return new BlockItem(block, new Properties());
   }

   public static BlockItem createBrittleIceItem(Block block) {
      return new BlockItem(block, new Properties());
   }

   public static BlockItem createPackedIceBricksItem(Block block) {
      return new BlockItem(block, new Properties());
   }

   public static BlockItem createPackedIceBrickStairsItem(Block block) {
      return new BlockItem(block, new Properties());
   }

   public static BlockItem createPackedIceBrickSlabItem(Block block) {
      return new BlockItem(block, new Properties());
   }

   public static BlockItem createPackedIceBrickWallItem(Block block) {
      return new BlockItem(block, new Properties());
   }

   public static BlockItem createChiseledPackedIceItem(Block block) {
      return new BlockItem(block, new Properties());
   }

   public static BlockItem createIceDoorItem(Block block) {
      return new BlockItem(block, new Properties());
   }

   public static SpawnEggItem createFrostbiteSpawnEgg(EntityType<? extends Mob> type) {
      return new FrostbiteSpawnEggItem_forge(type, new Properties());
   }

   public static IceBallItem_forge createIceBallItem(Supplier<EntityType<? extends IceBall_forge>> entityType) {
      return new IceBallItem_forge(entityType, new Properties().stacksTo(16));
   }
}
