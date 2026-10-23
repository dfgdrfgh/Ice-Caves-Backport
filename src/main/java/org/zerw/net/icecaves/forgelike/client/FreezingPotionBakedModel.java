package org.zerw.net.icecaves.forgelike.client;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.zerw.net.icecaves.registry.ModPotions_forge;

public final class FreezingPotionBakedModel implements BakedModel {
   private final BakedModel base;
   private final BakedModel freezing;
   private final ItemOverrides overrides;

   public FreezingPotionBakedModel(BakedModel base, BakedModel freezing) {
      this.base = base;
      this.freezing = freezing;
      this.overrides = new ItemOverrides() {
         public BakedModel resolve(BakedModel model, ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed) {
            if (!ModPotions_forge.isFreezing(stack) && !ModPotions_forge.hasFreezingModel(stack)) {
               BakedModel resolved = base.getOverrides().resolve(base, stack, level, entity, seed);
               return resolved != null ? resolved : base;
            } else {
               BakedModel painted = freezing.getOverrides().resolve(freezing, stack, level, entity, seed);
               return painted != null ? painted : freezing;
            }
         }
      };
   }

   public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction side, RandomSource random) {
      return this.base.getQuads(state, side, random);
   }

   public boolean useAmbientOcclusion() {
      return this.base.useAmbientOcclusion();
   }

   public boolean isGui3d() {
      return this.base.isGui3d();
   }

   public boolean usesBlockLight() {
      return this.base.usesBlockLight();
   }

   public boolean isCustomRenderer() {
      return this.base.isCustomRenderer();
   }

   public TextureAtlasSprite getParticleIcon() {
      return this.base.getParticleIcon();
   }

   public ItemTransforms getTransforms() {
      return this.base.getTransforms();
   }

   public ItemOverrides getOverrides() {
      return this.overrides;
   }
}
