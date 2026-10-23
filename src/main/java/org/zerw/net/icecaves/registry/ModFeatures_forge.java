package org.zerw.net.icecaves.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.zerw.net.icecaves.worldgen.IceCaveFeature_forge;

public final class ModFeatures_forge {
   public static final ResourceLocation ICE_CAVE_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "ice_cave");
   public static final ResourceKey<Feature<?>> ICE_CAVE_FEATURE_KEY = ResourceKey.create(Registries.FEATURE, ICE_CAVE_ID);
   public static final ResourceKey<ConfiguredFeature<?, ?>> ICE_CAVE_CONFIGURED_KEY = ResourceKey.create(Registries.CONFIGURED_FEATURE, ICE_CAVE_ID);
   public static final ResourceKey<PlacedFeature> ICE_CAVE_PLACED_KEY = ResourceKey.create(Registries.PLACED_FEATURE, ICE_CAVE_ID);

   private ModFeatures_forge() {
   }

   public static IceCaveFeature_forge createIceCave() {
      return new IceCaveFeature_forge(NoneFeatureConfiguration.CODEC);
   }
}
