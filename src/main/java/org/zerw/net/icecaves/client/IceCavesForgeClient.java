package org.zerw.net.icecaves.client;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.minecraftforge.client.event.RegisterColorHandlersEvent.Item;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.zerw.net.icecaves.IceCavesForge;
import org.zerw.net.icecaves.forgelike.client.FreezingItemModels;

public final class IceCavesForgeClient {
   private IceCavesForgeClient() {
   }

   public static void register(IEventBus modBus) {
      if (FMLEnvironment.dist == Dist.CLIENT) {
         FreezingItemModels.register(modBus);
         modBus.addListener(IceCavesForgeClient::registerLayerDefinitions);
         modBus.addListener(IceCavesForgeClient::registerRenderers);
         modBus.addListener(IceCavesForgeClient::onClientSetup);
         modBus.addListener(EventPriority.LOWEST, IceCavesForgeClient::registerItemColors);
      }
   }

   private static void registerLayerDefinitions(RegisterLayerDefinitions event) {
      event.registerLayerDefinition(FrostbiteBabyModel_forge.LAYER_LOCATION, FrostbiteBabyModel_forge::createBodyLayer);
   }

   private static void registerRenderers(RegisterRenderers event) {
      event.registerEntityRenderer((EntityType)IceCavesForge.FROSTBITE.get(), FrostbiteZombieRenderer_forge::new);
      event.registerEntityRenderer((EntityType)IceCavesForge.ICE_BALL.get(), ThrownItemRenderer::new);
   }

   private static void onClientSetup(FMLClientSetupEvent event) {
      ItemBlockRenderTypes.setRenderLayer((Block)IceCavesForge.ICICLE.get(), RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer((Block)IceCavesForge.ICE_CRYSTAL.get(), RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer((Block)IceCavesForge.ICE_DOOR.get(), RenderType.cutout());
      ItemBlockRenderTypes.setRenderLayer((Block)IceCavesForge.BRITTLE_ICE.get(), RenderType.translucent());
   }

   private static void registerItemColors(Item event) {
      event.register(IceCavesClientItems_forge::spawnEggItemColor, new ItemLike[]{(ItemLike)IceCavesForge.FROSTBITE_SPAWN_EGG.get()});
      event.register(IceCavesClientItems_forge::potionItemColor, new ItemLike[]{Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION, Items.TIPPED_ARROW});
   }
}
