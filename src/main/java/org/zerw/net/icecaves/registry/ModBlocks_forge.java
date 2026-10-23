package org.zerw.net.icecaves.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockBehaviour.OffsetType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockSetType.PressurePlateSensitivity;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import org.zerw.net.icecaves.block.BrittleIceBlock_forge;
import org.zerw.net.icecaves.block.IcicleBlock_forge;

public final class ModBlocks_forge {
   public static final ResourceLocation ICE_CRYSTAL_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "ice_crystal");
   public static final ResourceLocation ICICLE_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "icicle");
   public static final ResourceLocation BRITTLE_ICE_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "brittle_ice");
   public static final ResourceLocation PACKED_ICE_BRICKS_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "packed_ice_bricks");
   public static final ResourceLocation PACKED_ICE_BRICK_STAIRS_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "packed_ice_brick_stairs");
   public static final ResourceLocation PACKED_ICE_BRICK_SLAB_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "packed_ice_brick_slab");
   public static final ResourceLocation PACKED_ICE_BRICK_WALL_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "packed_ice_brick_wall");
   public static final ResourceLocation CHISELED_PACKED_ICE_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "chiseled_packed_ice");
   public static final ResourceLocation ICE_DOOR_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "ice_door");
   public static final ResourceKey<Block> ICE_CRYSTAL_KEY = ResourceKey.create(Registries.BLOCK, ICE_CRYSTAL_ID);
   public static final ResourceKey<Block> ICICLE_KEY = ResourceKey.create(Registries.BLOCK, ICICLE_ID);
   public static final ResourceKey<Block> BRITTLE_ICE_KEY = ResourceKey.create(Registries.BLOCK, BRITTLE_ICE_ID);

   private ModBlocks_forge() {
   }

   public static AmethystClusterBlock createIceCrystal() {
      return new AmethystClusterBlock(5.0F, 3.0F, Properties.ofLegacyCopy(Blocks.LARGE_AMETHYST_BUD).sound(ModSounds_forge.ICE_CRYSTAL));
   }

   public static IcicleBlock_forge createIcicle() {
      return new IcicleBlock_forge(
         Properties.of()
            .mapColor(MapColor.ICE)
            .forceSolidOn()
            .noOcclusion()
            .sound(ModSounds_forge.ICE_CRYSTAL)
            .randomTicks()
            .strength(0.5F)
            .dynamicShape()
            .offsetType(OffsetType.XZ)
            .pushReaction(PushReaction.DESTROY)
            .isRedstoneConductor((state, level, pos) -> false)
      );
   }

   public static BrittleIceBlock_forge createBrittleIce() {
      return new BrittleIceBlock_forge(Properties.of().mapColor(MapColor.ICE).friction(0.98F).strength(0.5F).sound(ModSounds_forge.ICE).noOcclusion());
   }

   private static Properties iceBrickProperties() {
      return Properties.of().mapColor(MapColor.ICE).friction(0.98F).strength(0.5F).sound(ModSounds_forge.ICE);
   }

   public static Block createPackedIceBricks() {
      return new Block(iceBrickProperties());
   }

   public static StairBlock createPackedIceBrickStairs(Block base) {
      return new StairBlock(base.defaultBlockState(), iceBrickProperties());
   }

   public static SlabBlock createPackedIceBrickSlab() {
      return new SlabBlock(iceBrickProperties());
   }

   public static WallBlock createPackedIceBrickWall() {
      return new WallBlock(iceBrickProperties().forceSolidOn());
   }

   public static Block createChiseledPackedIce() {
      return new Block(iceBrickProperties());
   }

   public static DoorBlock createIceDoor() {
      BlockSetType type = new BlockSetType(
         "ice_door",
         true,
         true,
         false,
         PressurePlateSensitivity.EVERYTHING,
         ModSounds_forge.ICE,
         ModSounds_forge.ICE_DOOR_CLOSE,
         ModSounds_forge.ICE_DOOR_OPEN,
         SoundEvents.COPPER_TRAPDOOR_CLOSE,
         SoundEvents.COPPER_TRAPDOOR_OPEN,
         SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
         SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
         SoundEvents.STONE_BUTTON_CLICK_OFF,
         SoundEvents.STONE_BUTTON_CLICK_ON
      );
      return new DoorBlock(type, Properties.of().mapColor(MapColor.ICE).strength(3.0F).noOcclusion().sound(ModSounds_forge.ICE));
   }

   public static Block iceCrystal() {
      return (Block)BuiltInRegistries.BLOCK.get(ICE_CRYSTAL_ID);
   }

   public static Block icicle() {
      return (Block)BuiltInRegistries.BLOCK.get(ICICLE_ID);
   }
}
