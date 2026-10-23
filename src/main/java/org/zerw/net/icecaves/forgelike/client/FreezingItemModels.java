package org.zerw.net.icecaves.forgelike.client;

import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ModelEvent.ModifyBakingResult;
import net.minecraftforge.client.event.ModelEvent.RegisterAdditional;
import net.minecraftforge.eventbus.api.IEventBus;
import org.zerw.net.icecaves.IceCavesConstants_forge;

public final class FreezingItemModels {
   private static final ModelResourceLocation POTION_OF_FREEZING = standalone("potion_of_freezing");
   private static final ModelResourceLocation SPLASH_POTION_OF_FREEZING = standalone("splash_potion_of_freezing");
   private static final ModelResourceLocation LINGERING_POTION_OF_FREEZING = standalone("lingering_potion_of_freezing");
   private static final ModelResourceLocation ARROW_OF_FREEZING = standalone("arrow_of_freezing");

   private static ModelResourceLocation standalone(String name) {
      return new ModelResourceLocation(ResourceLocation.fromNamespaceAndPath("ice_caves", "item/" + name), "standalone");
   }

   private FreezingItemModels() {
   }

   public static void register(IEventBus modBus) {
      modBus.addListener(FreezingItemModels::onRegisterAdditional);
      modBus.addListener(FreezingItemModels::onModifyBakingResult);
   }

   private static void onRegisterAdditional(RegisterAdditional event) {
      event.register(POTION_OF_FREEZING);
      event.register(SPLASH_POTION_OF_FREEZING);
      event.register(LINGERING_POTION_OF_FREEZING);
      event.register(ARROW_OF_FREEZING);
   }

   private static void onModifyBakingResult(ModifyBakingResult event) {
      wrap(event, "potion", POTION_OF_FREEZING);
      wrap(event, "splash_potion", SPLASH_POTION_OF_FREEZING);
      wrap(event, "lingering_potion", LINGERING_POTION_OF_FREEZING);
      wrap(event, "tipped_arrow", ARROW_OF_FREEZING);
   }

   private static void wrap(ModifyBakingResult event, String vanillaItem, ModelResourceLocation freezingModel) {
      ModelResourceLocation vanillaKey = ModelResourceLocation.inventory(ResourceLocation.withDefaultNamespace(vanillaItem));
      BakedModel base = (BakedModel)event.getModels().get(vanillaKey);
      BakedModel freezing = (BakedModel)event.getModels().get(freezingModel);
      if (base != null && freezing != null) {
         event.getModels().put(vanillaKey, new FreezingPotionBakedModel(base, freezing));
      } else {
         IceCavesConstants_forge.LOG
            .warn(
               "Could not wrap {} with {} (base={}, freezing={}) — freezing icons may render white",
               new Object[]{vanillaKey, freezingModel, base != null, freezing != null}
            );
      }
   }
}
