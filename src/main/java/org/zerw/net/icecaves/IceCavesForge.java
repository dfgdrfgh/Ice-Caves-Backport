package org.zerw.net.icecaves;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent.Operation;
import net.minecraftforge.event.entity.living.MobSpawnEvent.PositionCheck;
import net.minecraftforge.event.entity.living.MobSpawnEvent.SpawnPlacementCheck;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.zerw.net.icecaves.block.BrittleIceBlock_forge;
import org.zerw.net.icecaves.block.IcicleBlock_forge;
import org.zerw.net.icecaves.client.IceCavesForgeClient;
import org.zerw.net.icecaves.entity.FrostbiteZombie_forge;
import org.zerw.net.icecaves.entity.IceBall_forge;
import org.zerw.net.icecaves.forgelike.ForgeLikeItems;
import org.zerw.net.icecaves.item.IceBallItem_forge;
import org.zerw.net.icecaves.registry.ModBlocks_forge;
import org.zerw.net.icecaves.registry.ModCreativeTabs_forge;
import org.zerw.net.icecaves.registry.ModEffects_forge;
import org.zerw.net.icecaves.registry.ModEntities_forge;
import org.zerw.net.icecaves.registry.ModFeatures_forge;
import org.zerw.net.icecaves.registry.ModItems_forge;
import org.zerw.net.icecaves.registry.ModPotions_forge;
import org.zerw.net.icecaves.registry.ModSounds_forge;
import org.zerw.net.icecaves.worldgen.IceCaveFeature_forge;
import org.zerw.net.icecaves.worldgen.IceCaveSpawnControl_forge;

@Mod("ice_caves")
public final class IceCavesForge {
   public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, "ice_caves");
   public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "ice_caves");
   public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, "ice_caves");
   public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "ice_caves");
   public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, "ice_caves");
   public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, "ice_caves");
   public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "ice_caves");
   public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "ice_caves");
   public static final RegistryObject<AmethystClusterBlock> ICE_CRYSTAL = BLOCKS.register("ice_crystal", ModBlocks_forge::createIceCrystal);
   public static final RegistryObject<BlockItem> ICE_CRYSTAL_ITEM = ITEMS.register(
      "ice_crystal", () -> ModItems_forge.createIceCrystalItem((Block)ICE_CRYSTAL.get())
   );
   public static final RegistryObject<IcicleBlock_forge> ICICLE = BLOCKS.register("icicle", ModBlocks_forge::createIcicle);
   public static final RegistryObject<BlockItem> ICICLE_ITEM = ITEMS.register("icicle", () -> ModItems_forge.createIcicleItem((Block)ICICLE.get()));
   public static final RegistryObject<BrittleIceBlock_forge> BRITTLE_ICE = BLOCKS.register("brittle_ice", ModBlocks_forge::createBrittleIce);
   public static final RegistryObject<BlockItem> BRITTLE_ICE_ITEM = ITEMS.register(
      "brittle_ice", () -> ModItems_forge.createBrittleIceItem((Block)BRITTLE_ICE.get())
   );
   public static final RegistryObject<Block> PACKED_ICE_BRICKS = BLOCKS.register("packed_ice_bricks", ModBlocks_forge::createPackedIceBricks);
   public static final RegistryObject<BlockItem> PACKED_ICE_BRICKS_ITEM = ITEMS.register(
      "packed_ice_bricks", () -> ModItems_forge.createPackedIceBricksItem((Block)PACKED_ICE_BRICKS.get())
   );
   public static final RegistryObject<StairBlock> PACKED_ICE_BRICK_STAIRS = BLOCKS.register(
      "packed_ice_brick_stairs", () -> ModBlocks_forge.createPackedIceBrickStairs((Block)PACKED_ICE_BRICKS.get())
   );
   public static final RegistryObject<BlockItem> PACKED_ICE_BRICK_STAIRS_ITEM = ITEMS.register(
      "packed_ice_brick_stairs", () -> ModItems_forge.createPackedIceBrickStairsItem((Block)PACKED_ICE_BRICK_STAIRS.get())
   );
   public static final RegistryObject<SlabBlock> PACKED_ICE_BRICK_SLAB = BLOCKS.register("packed_ice_brick_slab", ModBlocks_forge::createPackedIceBrickSlab);
   public static final RegistryObject<BlockItem> PACKED_ICE_BRICK_SLAB_ITEM = ITEMS.register(
      "packed_ice_brick_slab", () -> ModItems_forge.createPackedIceBrickSlabItem((Block)PACKED_ICE_BRICK_SLAB.get())
   );
   public static final RegistryObject<WallBlock> PACKED_ICE_BRICK_WALL = BLOCKS.register("packed_ice_brick_wall", ModBlocks_forge::createPackedIceBrickWall);
   public static final RegistryObject<BlockItem> PACKED_ICE_BRICK_WALL_ITEM = ITEMS.register(
      "packed_ice_brick_wall", () -> ModItems_forge.createPackedIceBrickWallItem((Block)PACKED_ICE_BRICK_WALL.get())
   );
   public static final RegistryObject<Block> CHISELED_PACKED_ICE = BLOCKS.register("chiseled_packed_ice", ModBlocks_forge::createChiseledPackedIce);
   public static final RegistryObject<BlockItem> CHISELED_PACKED_ICE_ITEM = ITEMS.register(
      "chiseled_packed_ice", () -> ModItems_forge.createChiseledPackedIceItem((Block)CHISELED_PACKED_ICE.get())
   );
   public static final RegistryObject<Block> ICE_DOOR = BLOCKS.register("ice_door", ModBlocks_forge::createIceDoor);
   public static final RegistryObject<BlockItem> ICE_DOOR_ITEM = ITEMS.register("ice_door", () -> ModItems_forge.createIceDoorItem((Block)ICE_DOOR.get()));
   public static final RegistryObject<EntityType<FrostbiteZombie_forge>> FROSTBITE = ENTITY_TYPES.register("frostbite", ModEntities_forge::createFrostbite);
   public static final RegistryObject<SpawnEggItem> FROSTBITE_SPAWN_EGG = ITEMS.register(
      "frostbite_spawn_egg", () -> ForgeLikeItems.createFrostbiteSpawnEgg(FROSTBITE)
   );
   public static final RegistryObject<EntityType<IceBall_forge>> ICE_BALL = ENTITY_TYPES.register("ice_ball", ModEntities_forge::createIceBall);
   public static final RegistryObject<IceBallItem_forge> ICE_BALL_ITEM = ITEMS.register("ice_ball", () -> ModItems_forge.createIceBallItem(ICE_BALL::get));
   public static final RegistryObject<SoundEvent> ICE_BALL_HIT = SOUND_EVENTS.register("entity.ice_ball.hit", ModSounds_forge::createIceBallHit);
   public static final RegistryObject<SoundEvent> ICE_BREAK = SOUND_EVENTS.register("block.ice.break", ModSounds_forge::createIceBreak);
   public static final RegistryObject<SoundEvent> ICE_STEP = SOUND_EVENTS.register("block.ice.step", ModSounds_forge::createIceStep);
   public static final RegistryObject<SoundEvent> ICE_PLACE = SOUND_EVENTS.register("block.ice.place", ModSounds_forge::createIcePlace);
   public static final RegistryObject<SoundEvent> ICE_HIT = SOUND_EVENTS.register("block.ice.hit", ModSounds_forge::createIceHit);
   public static final RegistryObject<SoundEvent> ICE_FALL = SOUND_EVENTS.register("block.ice.fall", ModSounds_forge::createIceFall);
   public static final RegistryObject<SoundEvent> ICE_DOOR_OPEN = SOUND_EVENTS.register("block.ice_door.open", ModSounds_forge::createIceDoorOpen);
   public static final RegistryObject<SoundEvent> ICE_DOOR_CLOSE = SOUND_EVENTS.register("block.ice_door.close", ModSounds_forge::createIceDoorClose);
   public static final RegistryObject<SoundEvent> ICE_CRYSTAL_BREAK = SOUND_EVENTS.register("block.ice_crystal.break", ModSounds_forge::createIceCrystalBreak);
   public static final RegistryObject<SoundEvent> ICE_CRYSTAL_STEP = SOUND_EVENTS.register("block.ice_crystal.step", ModSounds_forge::createIceCrystalStep);
   public static final RegistryObject<SoundEvent> ICE_CRYSTAL_PLACE = SOUND_EVENTS.register("block.ice_crystal.place", ModSounds_forge::createIceCrystalPlace);
   public static final RegistryObject<SoundEvent> ICE_CRYSTAL_HIT = SOUND_EVENTS.register("block.ice_crystal.hit", ModSounds_forge::createIceCrystalHit);
   public static final RegistryObject<SoundEvent> ICE_CRYSTAL_FALL = SOUND_EVENTS.register("block.ice_crystal.fall", ModSounds_forge::createIceCrystalFall);
   public static final RegistryObject<SoundEvent> FROSTBITE_AMBIENT = SOUND_EVENTS.register("entity.frostbite.ambient", ModSounds_forge::createFrostbiteAmbient);
   public static final RegistryObject<SoundEvent> FROSTBITE_HURT = SOUND_EVENTS.register("entity.frostbite.hurt", ModSounds_forge::createFrostbiteHurt);
   public static final RegistryObject<SoundEvent> FROSTBITE_DEATH = SOUND_EVENTS.register("entity.frostbite.death", ModSounds_forge::createFrostbiteDeath);
   public static final RegistryObject<SoundEvent> FROSTBITE_STEP = SOUND_EVENTS.register("entity.frostbite.step", ModSounds_forge::createFrostbiteStep);
   public static final RegistryObject<SoundEvent> FROSTBITE_CONVERTED_TO_ZOMBIE = SOUND_EVENTS.register(
      "entity.frostbite.converted_to_zombie", ModSounds_forge::createFrostbiteConvertedToZombie
   );
   public static final RegistryObject<MobEffect> FREEZING_EFFECT = MOB_EFFECTS.register("freezing", ModEffects_forge::createFreezingEffect);
   public static final RegistryObject<Potion> FREEZING_POTION = POTIONS.register("freezing", () -> ModPotions_forge.createFreezing(freezingEffectHolder()));
   public static final RegistryObject<Potion> LONG_FREEZING_POTION = POTIONS.register(
      "long_freezing", () -> ModPotions_forge.createLongFreezing(freezingEffectHolder())
   );
   public static final RegistryObject<IceCaveFeature_forge> ICE_CAVE_FEATURE = FEATURES.register("ice_cave", ModFeatures_forge::createIceCave);
   public static final RegistryObject<CreativeModeTab> ICE_CAVES_TAB = CREATIVE_MODE_TABS.register(
      "ice_caves",
      () -> CreativeModeTab.builder()
         .title(ModCreativeTabs_forge.title())
         .icon(() -> ModCreativeTabs_forge.icon((ItemLike)ICE_CRYSTAL_ITEM.get()))
         .displayItems(
            (params, output) -> {
               for (ItemStack stack : ModCreativeTabs_forge.contents(
                  (ItemLike)ICE_CRYSTAL_ITEM.get(),
                  (ItemLike)ICICLE_ITEM.get(),
                  (ItemLike)BRITTLE_ICE_ITEM.get(),
                  (ItemLike)PACKED_ICE_BRICKS_ITEM.get(),
                  (ItemLike)PACKED_ICE_BRICK_STAIRS_ITEM.get(),
                  (ItemLike)PACKED_ICE_BRICK_SLAB_ITEM.get(),
                  (ItemLike)PACKED_ICE_BRICK_WALL_ITEM.get(),
                  (ItemLike)CHISELED_PACKED_ICE_ITEM.get(),
                  (ItemLike)ICE_DOOR_ITEM.get(),
                  (ItemLike)ICE_BALL_ITEM.get(),
                  (ItemLike)FROSTBITE_SPAWN_EGG.get(),
                  freezingPotionHolder(),
                  longFreezingPotionHolder()
               )) {
                  output.accept(stack);
               }
            }
         )
         .build()
   );

   public IceCavesForge() {
      IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
      BLOCKS.register(modBus);
      ITEMS.register(modBus);
      ENTITY_TYPES.register(modBus);
      MOB_EFFECTS.register(modBus);
      POTIONS.register(modBus);
      FEATURES.register(modBus);
      CREATIVE_MODE_TABS.register(modBus);
      SOUND_EVENTS.register(modBus);
      modBus.addListener(IceCavesForge::addCreative);
      modBus.addListener(IceCavesForge::registerAttributes);
      modBus.addListener(IceCavesForge::registerSpawnPlacements);
      MinecraftForge.EVENT_BUS.addListener(IceCavesForge::registerBrewingRecipes);
      MinecraftForge.EVENT_BUS.addListener(IceCavesForge::onMobSpawnPlacementCheck);
      MinecraftForge.EVENT_BUS.addListener(IceCavesForge::onMobSpawnPositionCheck);
      IceCavesForgeClient.register(modBus);
      IceCavesCommon_forge.init();
      IceCavesConstants_forge.LOG.info("Ice Caves loaded on Forge");
   }

   private static Holder<MobEffect> freezingEffectHolder() {
      return (Holder<MobEffect>)FREEZING_EFFECT.getHolder().orElseThrow();
   }

   public static Holder<Potion> freezingPotionHolder() {
      return (Holder<Potion>)FREEZING_POTION.getHolder().orElseThrow();
   }

   public static Holder<Potion> longFreezingPotionHolder() {
      return (Holder<Potion>)LONG_FREEZING_POTION.getHolder().orElseThrow();
   }

   private static void registerBrewingRecipes(BrewingRecipeRegisterEvent event) {
      event.getBuilder().addMix(Potions.AWKWARD, (Item)ICE_CRYSTAL_ITEM.get(), freezingPotionHolder());
      event.getBuilder().addMix(freezingPotionHolder(), Items.REDSTONE, longFreezingPotionHolder());
   }

   private static void onMobSpawnPlacementCheck(SpawnPlacementCheck event) {
      if (IceCaveSpawnControl_forge.shouldBlockMonsterType(event.getEntityType(), event.getLevel(), event.getPos(), event.getSpawnType())) {
         event.setResult(Result.DENY);
      } else {
         if (IceCaveSpawnControl_forge.shouldAllowCaveStraySpawn(
            event.getEntityType(), event.getLevel(), event.getPos(), event.getSpawnType(), event.getRandom()
         )) {
            event.setResult(Result.ALLOW);
         }
      }
   }

   private static void onMobSpawnPositionCheck(PositionCheck event) {
      if (IceCaveSpawnControl_forge.shouldBlockMonsterSpawn(event.getEntity(), event.getLevel(), event.getSpawnType())) {
         event.setResult(Result.DENY);
      }
   }

   private static void addCreative(BuildCreativeModeTabContentsEvent event) {
      if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
         event.accept(ICE_CRYSTAL_ITEM);
         event.accept(ICICLE_ITEM);
         event.accept(BRITTLE_ICE_ITEM);
      }

      if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
         event.accept(PACKED_ICE_BRICKS_ITEM);
         event.accept(PACKED_ICE_BRICK_STAIRS_ITEM);
         event.accept(PACKED_ICE_BRICK_SLAB_ITEM);
         event.accept(PACKED_ICE_BRICK_WALL_ITEM);
         event.accept(CHISELED_PACKED_ICE_ITEM);
         event.accept(ICE_DOOR_ITEM);
      }

      if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) {
         event.accept(FROSTBITE_SPAWN_EGG);
      }

      if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
         event.accept(ICE_BALL_ITEM);
      }
   }

   private static void registerAttributes(EntityAttributeCreationEvent event) {
      event.put((EntityType)FROSTBITE.get(), FrostbiteZombie_forge.createAttributes().build());
   }

   private static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
      event.register(
         (EntityType)FROSTBITE.get(),
         SpawnPlacementTypes.ON_GROUND,
         Types.MOTION_BLOCKING_NO_LEAVES,
         IceCaveSpawnControl_forge::checkFrostbiteSpawnRules,
         Operation.REPLACE
      );
   }
}
