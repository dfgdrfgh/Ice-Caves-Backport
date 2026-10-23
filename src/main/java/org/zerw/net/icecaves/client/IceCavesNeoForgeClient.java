package org.zerw.net.icecaves.client;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent.Item;
import org.zerw.net.icecaves.IceCavesNeoForge;
import org.zerw.net.icecaves.neoforge.client.FreezingItemModels;

public final class IceCavesNeoForgeClient {
   private IceCavesNeoForgeClient() {
   }

   public static void register(IEventBus modBus) {
      if (FMLEnvironment.dist == Dist.CLIENT) {
         FreezingItemModels.register(modBus);
         modBus.addListener(IceCavesNeoForgeClient::registerLayerDefinitions);
         modBus.addListener(IceCavesNeoForgeClient::registerRenderers);
         modBus.addListener(IceCavesNeoForgeClient::onClientSetup);
         modBus.addListener(EventPriority.LOWEST, IceCavesNeoForgeClient::registerItemColors);
      }
   }

   private static void registerLayerDefinitions(RegisterLayerDefinitions event) {
      event.registerLayerDefinition(FrostbiteBabyModel_forge.LAYER_LOCATION, FrostbiteBabyModel_forge::createBodyLayer);
   }

   private static void registerRenderers(RegisterRenderers event) {
      event.registerEntityRenderer((EntityType)IceCavesNeoForge.FROSTBITE.get(), FrostbiteZombieRenderer_neoforge::new);
      event.registerEntityRenderer((EntityType)IceCavesNeoForge.ICE_BALL.get(), ThrownItemRenderer::new);
   }

   private static void onClientSetup(FMLClientSetupEvent event) {
      ItemBlockRenderTypes.setRenderLayer((Block)IceCavesNeoForge.ICICLE.get(), RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer((Block)IceCavesNeoForge.ICE_CRYSTAL.get(), RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer((Block)IceCavesNeoForge.ICE_DOOR.get(), RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer((Block)IceCavesNeoForge.BRITTLE_ICE.get(), RenderType.translucent());
   }

   private static void registerItemColors(Item event) {
      event.register(IceCavesClientItems_forge::spawnEggItemColor, new ItemLike[]{(ItemLike)IceCavesNeoForge.FROSTBITE_SPAWN_EGG.get()});
      event.register(IceCavesClientItems_forge::potionItemColor, new ItemLike[]{Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION, Items.TIPPED_ARROW});
   }
}
