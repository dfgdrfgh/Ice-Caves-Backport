package org.zerw.net.icecaves.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.class_1802;
import net.minecraft.class_1921;
import net.minecraft.class_1935;
import net.minecraft.class_953;
import org.zerw.net.icecaves.IceCavesFabric;

public final class IceCavesFabricClient implements ClientModInitializer {
   public void onInitializeClient() {
      BlockRenderLayerMap.INSTANCE.putBlock(IceCavesFabric.ICICLE, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(IceCavesFabric.ICE_CRYSTAL, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(IceCavesFabric.ICE_DOOR, class_1921.method_23581());
      BlockRenderLayerMap.INSTANCE.putBlock(IceCavesFabric.BRITTLE_ICE, class_1921.method_23583());
      ColorProviderRegistry.ITEM.register(IceCavesClientItems::spawnEggItemColor, new class_1935[]{IceCavesFabric.FROSTBITE_SPAWN_EGG});
      ColorProviderRegistry.ITEM
         .register(
            IceCavesClientItems::potionItemColor, new class_1935[]{class_1802.field_8574, class_1802.field_8436, class_1802.field_8150, class_1802.field_8087}
         );
      EntityModelLayerRegistry.registerModelLayer(FrostbiteBabyModel.LAYER_LOCATION, FrostbiteBabyModel::createBodyLayer);
      EntityRendererRegistry.register(IceCavesFabric.FROSTBITE, FrostbiteZombieRenderer::new);
      EntityRendererRegistry.register(IceCavesFabric.ICE_BALL, class_953::new);
   }
}
