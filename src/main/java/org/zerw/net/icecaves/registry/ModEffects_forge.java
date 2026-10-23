package org.zerw.net.icecaves.registry;

import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.zerw.net.icecaves.effect.FreezingMobEffect_forge;

public final class ModEffects_forge {
   public static final ResourceLocation FREEZING_EFFECT_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "freezing");
   public static final ResourceKey<MobEffect> FREEZING_EFFECT_KEY = ResourceKey.create(Registries.MOB_EFFECT, FREEZING_EFFECT_ID);

   private ModEffects_forge() {
   }

   public static MobEffect createFreezingEffect() {
      return new FreezingMobEffect_forge();
   }

   public static Optional<Reference<MobEffect>> freezing() {
      return BuiltInRegistries.MOB_EFFECT.getHolder(FREEZING_EFFECT_KEY);
   }

   public static void applyFreezing(LivingEntity target, int durationTicks, @Nullable Entity source) {
      freezing().ifPresent(effect -> target.addEffect(new MobEffectInstance(effect, durationTicks), source));
   }
}
