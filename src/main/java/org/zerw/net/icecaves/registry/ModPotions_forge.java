package org.zerw.net.icecaves.registry;

import javax.annotation.Nullable;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.component.CustomModelData;

public final class ModPotions_forge {
   public static final ResourceLocation FREEZING_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "freezing");
   public static final ResourceLocation LONG_FREEZING_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "long_freezing");
   public static final ResourceKey<Potion> FREEZING_KEY = ResourceKey.create(Registries.POTION, FREEZING_ID);
   public static final ResourceKey<Potion> LONG_FREEZING_KEY = ResourceKey.create(Registries.POTION, LONG_FREEZING_ID);
   public static final int FREEZING_DURATION_TICKS = 900;
   public static final int LONG_FREEZING_DURATION_TICKS = 1800;
   public static final int FREEZING_CUSTOM_MODEL_DATA = 1;
   public static final int FREEZING_FALLBACK_TINT = 7260415;

   private ModPotions_forge() {
   }

   public static Potion createFreezing(Holder<MobEffect> freezingEffect) {
      return new Potion("freezing", new MobEffectInstance[]{new MobEffectInstance(freezingEffect, 900)});
   }

   public static Potion createLongFreezing(Holder<MobEffect> freezingEffect) {
      return new Potion("freezing", new MobEffectInstance[]{new MobEffectInstance(freezingEffect, 1800)});
   }

   public static boolean isFreezing(@Nullable Holder<Potion> potion) {
      return potion != null && (potion.is(FREEZING_KEY) || potion.is(LONG_FREEZING_KEY));
   }

   public static boolean isFreezing(ItemStack stack) {
      return ((PotionContents)stack.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY))
         .potion()
         .filter(ModPotions_forge::isFreezing)
         .isPresent();
   }

   public static boolean hasFreezingModel(ItemStack stack) {
      CustomModelData data = (CustomModelData)stack.get(DataComponents.CUSTOM_MODEL_DATA);
      return data != null && data.value() == 1;
   }

   public static ItemStack applyFreezingIcon(ItemStack stack) {
      if (!stack.isEmpty() && isFreezing(stack)) {
         stack.set(DataComponents.CUSTOM_MODEL_DATA, new CustomModelData(1));
      }

      return stack;
   }

   public static ItemStack setPotionWithIcon(ItemStack stack, Holder<Potion> potion) {
      stack.set(DataComponents.POTION_CONTENTS, new PotionContents(potion));
      return applyFreezingIcon(stack);
   }
}
