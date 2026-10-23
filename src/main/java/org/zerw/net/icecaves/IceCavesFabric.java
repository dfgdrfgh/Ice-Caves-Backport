package org.zerw.net.icecaves;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents.ModifyEntries;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder.BuildCallback;
import net.minecraft.class_1291;
import net.minecraft.class_1299;
import net.minecraft.class_1311;
import net.minecraft.class_1747;
import net.minecraft.class_1761;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1826;
import net.minecraft.class_1842;
import net.minecraft.class_1847;
import net.minecraft.class_2248;
import net.minecraft.class_2378;
import net.minecraft.class_2482;
import net.minecraft.class_2510;
import net.minecraft.class_2544;
import net.minecraft.class_2960;
import net.minecraft.class_5542;
import net.minecraft.class_6880;
import net.minecraft.class_7706;
import net.minecraft.class_7923;
import net.minecraft.class_9169;
import net.minecraft.class_2893.class_2895;
import net.minecraft.class_2902.class_2903;
import org.zerw.net.icecaves.block.BrittleIceBlock;
import org.zerw.net.icecaves.block.IcicleBlock;
import org.zerw.net.icecaves.entity.FrostbiteZombie;
import org.zerw.net.icecaves.entity.IceBall;
import org.zerw.net.icecaves.item.IceBallItem;
import org.zerw.net.icecaves.mixin.SpawnPlacementsInvoker;
import org.zerw.net.icecaves.registry.ModBlocks;
import org.zerw.net.icecaves.registry.ModCreativeTabs;
import org.zerw.net.icecaves.registry.ModEffects;
import org.zerw.net.icecaves.registry.ModEntities;
import org.zerw.net.icecaves.registry.ModFeatures;
import org.zerw.net.icecaves.registry.ModItems;
import org.zerw.net.icecaves.registry.ModPotions;
import org.zerw.net.icecaves.registry.ModSounds;
import org.zerw.net.icecaves.worldgen.IceCaveFeature;
import org.zerw.net.icecaves.worldgen.IceCaveSpawnControl;

public final class IceCavesFabric implements ModInitializer {
   private static final class_1299<?>[] ICE_CAVE_BLOCKED_HOSTILES = new class_1299[]{
      class_1299.field_6051,
      class_1299.field_6054,
      class_1299.field_6071,
      class_1299.field_6123,
      class_1299.field_6079,
      class_1299.field_6084,
      class_1299.field_6046,
      class_1299.field_6137,
      class_1299.field_6076,
      class_1299.field_49148,
      class_1299.field_6091,
      class_1299.field_6145,
      class_1299.field_6069,
      class_1299.field_6078,
      class_1299.field_6125
   };
   public static class_5542 ICE_CRYSTAL;
   public static class_1747 ICE_CRYSTAL_ITEM;
   public static IcicleBlock ICICLE;
   public static class_1747 ICICLE_ITEM;
   public static BrittleIceBlock BRITTLE_ICE;
   public static class_1747 BRITTLE_ICE_ITEM;
   public static class_2248 PACKED_ICE_BRICKS;
   public static class_1747 PACKED_ICE_BRICKS_ITEM;
   public static class_2510 PACKED_ICE_BRICK_STAIRS;
   public static class_1747 PACKED_ICE_BRICK_STAIRS_ITEM;
   public static class_2482 PACKED_ICE_BRICK_SLAB;
   public static class_1747 PACKED_ICE_BRICK_SLAB_ITEM;
   public static class_2544 PACKED_ICE_BRICK_WALL;
   public static class_1747 PACKED_ICE_BRICK_WALL_ITEM;
   public static class_2248 CHISELED_PACKED_ICE;
   public static class_1747 CHISELED_PACKED_ICE_ITEM;
   public static class_2248 ICE_DOOR;
   public static class_1747 ICE_DOOR_ITEM;
   public static class_1299<FrostbiteZombie> FROSTBITE;
   public static class_1826 FROSTBITE_SPAWN_EGG;
   public static class_1299<IceBall> ICE_BALL;
   public static IceBallItem ICE_BALL_ITEM;
   public static class_6880<class_1291> FREEZING_EFFECT;
   public static class_6880<class_1842> FREEZING_POTION;
   public static class_6880<class_1842> LONG_FREEZING_POTION;
   public static IceCaveFeature ICE_CAVE_FEATURE;

   public void onInitialize() {
      ICE_CRYSTAL = (class_5542)class_2378.method_10230(class_7923.field_41175, ModBlocks.ICE_CRYSTAL_ID, ModBlocks.createIceCrystal());
      ICE_CRYSTAL_ITEM = (class_1747)class_2378.method_10230(class_7923.field_41178, ModItems.ICE_CRYSTAL_ID, ModItems.createIceCrystalItem(ICE_CRYSTAL));
      ICICLE = (IcicleBlock)class_2378.method_10230(class_7923.field_41175, ModBlocks.ICICLE_ID, ModBlocks.createIcicle());
      ICICLE_ITEM = (class_1747)class_2378.method_10230(class_7923.field_41178, ModItems.ICICLE_ID, ModItems.createIcicleItem(ICICLE));
      BRITTLE_ICE = (BrittleIceBlock)class_2378.method_10230(class_7923.field_41175, ModBlocks.BRITTLE_ICE_ID, ModBlocks.createBrittleIce());
      BRITTLE_ICE_ITEM = (class_1747)class_2378.method_10230(class_7923.field_41178, ModItems.BRITTLE_ICE_ID, ModItems.createBrittleIceItem(BRITTLE_ICE));
      PACKED_ICE_BRICKS = (class_2248)class_2378.method_10230(class_7923.field_41175, ModBlocks.PACKED_ICE_BRICKS_ID, ModBlocks.createPackedIceBricks());
      PACKED_ICE_BRICKS_ITEM = (class_1747)class_2378.method_10230(
         class_7923.field_41178, ModItems.PACKED_ICE_BRICKS_ID, ModItems.createPackedIceBricksItem(PACKED_ICE_BRICKS)
      );
      PACKED_ICE_BRICK_STAIRS = (class_2510)class_2378.method_10230(
         class_7923.field_41175, ModBlocks.PACKED_ICE_BRICK_STAIRS_ID, ModBlocks.createPackedIceBrickStairs(PACKED_ICE_BRICKS)
      );
      PACKED_ICE_BRICK_STAIRS_ITEM = (class_1747)class_2378.method_10230(
         class_7923.field_41178, ModItems.PACKED_ICE_BRICK_STAIRS_ID, ModItems.createPackedIceBrickStairsItem(PACKED_ICE_BRICK_STAIRS)
      );
      PACKED_ICE_BRICK_SLAB = (class_2482)class_2378.method_10230(
         class_7923.field_41175, ModBlocks.PACKED_ICE_BRICK_SLAB_ID, ModBlocks.createPackedIceBrickSlab()
      );
      PACKED_ICE_BRICK_SLAB_ITEM = (class_1747)class_2378.method_10230(
         class_7923.field_41178, ModItems.PACKED_ICE_BRICK_SLAB_ID, ModItems.createPackedIceBrickSlabItem(PACKED_ICE_BRICK_SLAB)
      );
      PACKED_ICE_BRICK_WALL = (class_2544)class_2378.method_10230(
         class_7923.field_41175, ModBlocks.PACKED_ICE_BRICK_WALL_ID, ModBlocks.createPackedIceBrickWall()
      );
      PACKED_ICE_BRICK_WALL_ITEM = (class_1747)class_2378.method_10230(
         class_7923.field_41178, ModItems.PACKED_ICE_BRICK_WALL_ID, ModItems.createPackedIceBrickWallItem(PACKED_ICE_BRICK_WALL)
      );
      CHISELED_PACKED_ICE = (class_2248)class_2378.method_10230(class_7923.field_41175, ModBlocks.CHISELED_PACKED_ICE_ID, ModBlocks.createChiseledPackedIce());
      CHISELED_PACKED_ICE_ITEM = (class_1747)class_2378.method_10230(
         class_7923.field_41178, ModItems.CHISELED_PACKED_ICE_ID, ModItems.createChiseledPackedIceItem(CHISELED_PACKED_ICE)
      );
      ICE_DOOR = (class_2248)class_2378.method_10230(class_7923.field_41175, ModBlocks.ICE_DOOR_ID, ModBlocks.createIceDoor());
      ICE_DOOR_ITEM = (class_1747)class_2378.method_10230(class_7923.field_41178, ModItems.ICE_DOOR_ID, ModItems.createIceDoorItem(ICE_DOOR));
      ICE_CAVE_FEATURE = (IceCaveFeature)class_2378.method_10230(class_7923.field_41144, ModFeatures.ICE_CAVE_ID, ModFeatures.createIceCave());
      FROSTBITE = (class_1299<FrostbiteZombie>)class_2378.method_10230(class_7923.field_41177, ModEntities.FROSTBITE_ID, ModEntities.createFrostbite());
      FabricDefaultAttributeRegistry.register(FROSTBITE, FrostbiteZombie.createAttributes());
      SpawnPlacementsInvoker.registerFrostbitePlacement(FROSTBITE, class_9169.field_48745, class_2903.field_13203, IceCaveSpawnControl::checkFrostbiteSpawnRules);
      FROSTBITE_SPAWN_EGG = (class_1826)class_2378.method_10230(
         class_7923.field_41178, ModItems.FROSTBITE_SPAWN_EGG_ID, ModItems.createFrostbiteSpawnEgg(FROSTBITE)
      );
      ICE_BALL = (class_1299<IceBall>)class_2378.method_10230(class_7923.field_41177, ModEntities.ICE_BALL_ID, ModEntities.createIceBall());
      ICE_BALL_ITEM = (IceBallItem)class_2378.method_10230(class_7923.field_41178, ModItems.ICE_BALL_ID, ModItems.createIceBallItem(() -> ICE_BALL));
      class_2378.method_10230(class_7923.field_41172, ModSounds.ICE_BALL_HIT_ID, ModSounds.createIceBallHit());
      class_2378.method_10230(class_7923.field_41172, ModSounds.ICE_BREAK_ID, ModSounds.ICE_BREAK);
      class_2378.method_10230(class_7923.field_41172, ModSounds.ICE_STEP_ID, ModSounds.ICE_STEP);
      class_2378.method_10230(class_7923.field_41172, ModSounds.ICE_PLACE_ID, ModSounds.ICE_PLACE);
      class_2378.method_10230(class_7923.field_41172, ModSounds.ICE_HIT_ID, ModSounds.ICE_HIT);
      class_2378.method_10230(class_7923.field_41172, ModSounds.ICE_FALL_ID, ModSounds.ICE_FALL);
      class_2378.method_10230(class_7923.field_41172, ModSounds.ICE_DOOR_OPEN_ID, ModSounds.ICE_DOOR_OPEN);
      class_2378.method_10230(class_7923.field_41172, ModSounds.ICE_DOOR_CLOSE_ID, ModSounds.ICE_DOOR_CLOSE);
      class_2378.method_10230(class_7923.field_41172, ModSounds.ICE_CRYSTAL_BREAK_ID, ModSounds.ICE_CRYSTAL_BREAK);
      class_2378.method_10230(class_7923.field_41172, ModSounds.ICE_CRYSTAL_STEP_ID, ModSounds.ICE_CRYSTAL_STEP);
      class_2378.method_10230(class_7923.field_41172, ModSounds.ICE_CRYSTAL_PLACE_ID, ModSounds.ICE_CRYSTAL_PLACE);
      class_2378.method_10230(class_7923.field_41172, ModSounds.ICE_CRYSTAL_HIT_ID, ModSounds.ICE_CRYSTAL_HIT);
      class_2378.method_10230(class_7923.field_41172, ModSounds.ICE_CRYSTAL_FALL_ID, ModSounds.ICE_CRYSTAL_FALL);
      class_2378.method_10230(class_7923.field_41172, ModSounds.FROSTBITE_AMBIENT_ID, ModSounds.FROSTBITE_AMBIENT);
      class_2378.method_10230(class_7923.field_41172, ModSounds.FROSTBITE_HURT_ID, ModSounds.FROSTBITE_HURT);
      class_2378.method_10230(class_7923.field_41172, ModSounds.FROSTBITE_DEATH_ID, ModSounds.FROSTBITE_DEATH);
      class_2378.method_10230(class_7923.field_41172, ModSounds.FROSTBITE_STEP_ID, ModSounds.FROSTBITE_STEP);
      class_2378.method_10230(class_7923.field_41172, ModSounds.FROSTBITE_CONVERTED_TO_ZOMBIE_ID, ModSounds.FROSTBITE_CONVERTED_TO_ZOMBIE);
      FREEZING_EFFECT = class_2378.method_47985(class_7923.field_41174, ModEffects.FREEZING_EFFECT_ID, ModEffects.createFreezingEffect());
      FREEZING_POTION = class_2378.method_47985(class_7923.field_41179, ModPotions.FREEZING_ID, ModPotions.createFreezing(FREEZING_EFFECT));
      LONG_FREEZING_POTION = class_2378.method_47985(class_7923.field_41179, ModPotions.LONG_FREEZING_ID, ModPotions.createLongFreezing(FREEZING_EFFECT));
      FabricBrewingRecipeRegistryBuilder.BUILD.register((BuildCallback)builder -> {
         builder.method_59705(class_1847.field_8999, ICE_BALL_ITEM, FREEZING_POTION);
         builder.method_59705(FREEZING_POTION, class_1802.field_8725, LONG_FREEZING_POTION);
      });
      // TrueVanillaBackport exposes these items through its own creative inventory.
      // Do not register a second standalone Ice Caves tab when integrated.
      if (!net.fabricmc.loader.api.FabricLoader.getInstance().isModLoaded("vanillabackport")) {
         class_1761 tab = FabricItemGroup.builder()
            .method_47321(ModCreativeTabs.title())
            .method_47320(() -> ModCreativeTabs.icon(ICE_CRYSTAL_ITEM))
            .method_47317(
               (params, output) -> {
                  for (class_1799 stack : ModCreativeTabs.contents(
                     ICE_CRYSTAL_ITEM,
                     ICICLE_ITEM,
                     BRITTLE_ICE_ITEM,
                     PACKED_ICE_BRICKS_ITEM,
                     PACKED_ICE_BRICK_STAIRS_ITEM,
                     PACKED_ICE_BRICK_SLAB_ITEM,
                     PACKED_ICE_BRICK_WALL_ITEM,
                     CHISELED_PACKED_ICE_ITEM,
                     ICE_DOOR_ITEM,
                     ICE_BALL_ITEM,
                     FROSTBITE_SPAWN_EGG,
                     FREEZING_POTION,
                     LONG_FREEZING_POTION
                  )) {
                     output.method_45420(stack);
                  }
               }
            )
            .method_47324();
         class_2378.method_39197(class_7923.field_44687, ModCreativeTabs.TAB_KEY, tab);
         ItemGroupEvents.modifyEntriesEvent(class_7706.field_40743).register((ModifyEntries)entries -> {
            entries.method_45421(ICE_CRYSTAL_ITEM);
            entries.method_45421(ICICLE_ITEM);
            entries.method_45421(BRITTLE_ICE_ITEM);
         });
         ItemGroupEvents.modifyEntriesEvent(class_7706.field_40195).register((ModifyEntries)entries -> {
            entries.method_45421(PACKED_ICE_BRICKS_ITEM);
            entries.method_45421(PACKED_ICE_BRICK_STAIRS_ITEM);
            entries.method_45421(PACKED_ICE_BRICK_SLAB_ITEM);
            entries.method_45421(PACKED_ICE_BRICK_WALL_ITEM);
            entries.method_45421(CHISELED_PACKED_ICE_ITEM);
            entries.method_45421(ICE_DOOR_ITEM);
         });
         ItemGroupEvents.modifyEntriesEvent(class_7706.field_40205).register((ModifyEntries)entries -> entries.method_45421(FROSTBITE_SPAWN_EGG));
         ItemGroupEvents.modifyEntriesEvent(class_7706.field_41062).register((ModifyEntries)entries -> entries.method_45421(ICE_BALL_ITEM));
      }
      BiomeModifications.addFeature(BiomeSelectors.tag(IceCaveSpawnControl.HAS_ICE_CAVES), class_2895.field_13177, ModFeatures.ICE_CAVE_PLACED_KEY);
      BiomeModifications.addSpawn(BiomeSelectors.tag(IceCaveSpawnControl.HAS_ICE_CAVES), class_1311.field_6302, FROSTBITE, 100, 1, 3);
      BiomeModifications.addSpawn(BiomeSelectors.tag(IceCaveSpawnControl.HAS_ICE_CAVES), class_1311.field_6302, class_1299.field_6098, 15, 1, 2);
      BiomeModifications.create(class_2960.method_60655("ice_caves", "clear_vanilla_hostiles"))
         .add(ModificationPhase.REMOVALS, BiomeSelectors.tag(IceCaveSpawnControl.HAS_ICE_CAVES), (selection, context) -> {
            for (class_1299<?> type : ICE_CAVE_BLOCKED_HOSTILES) {
               context.getSpawnSettings().removeSpawnsOfEntityType(type);
            }
         });
      IceCavesCommon.init();
      IceCavesConstants.LOG.info("Ice Caves loaded on Fabric");
   }
}
