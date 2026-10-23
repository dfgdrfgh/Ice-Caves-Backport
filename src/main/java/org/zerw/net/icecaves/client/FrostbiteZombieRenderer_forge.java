package org.zerw.net.icecaves.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.AbstractZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import org.zerw.net.icecaves.entity.FrostbiteZombie_forge;

public class FrostbiteZombieRenderer_forge extends AbstractZombieRenderer<FrostbiteZombie_forge, ZombieModel<FrostbiteZombie_forge>> {
   private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("ice_caves", "textures/entity/frostbite.png");
   private static final ResourceLocation BABY_TEXTURE = ResourceLocation.fromNamespaceAndPath("ice_caves", "textures/entity/frostbite_baby.png");
   private final ZombieModel<FrostbiteZombie_forge> adultModel = (ZombieModel<FrostbiteZombie_forge>)this.model;
   private final FrostbiteBabyModel_forge<FrostbiteZombie_forge> babyModel;

   public FrostbiteZombieRenderer_forge(Context context) {
      super(
         context,
         new ZombieModel(context.bakeLayer(ModelLayers.ZOMBIE)),
         new ZombieModel(context.bakeLayer(ModelLayers.ZOMBIE_INNER_ARMOR)),
         new ZombieModel(context.bakeLayer(ModelLayers.ZOMBIE_OUTER_ARMOR))
      );
      this.babyModel = new FrostbiteBabyModel_forge<>(context.bakeLayer(FrostbiteBabyModel_forge.LAYER_LOCATION));
      this.addLayer(new FrostbiteOuterLayer_forge(this, context.getModelSet()));
   }

   public void render(FrostbiteZombie_forge entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
      this.model = entity.isBaby() ? this.babyModel : this.adultModel;
      super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
   }

   protected void scale(FrostbiteZombie_forge entity, PoseStack poseStack, float partialTick) {
      this.shadowRadius = entity.isBaby() ? 0.25F : 0.5F;
   }

   public ResourceLocation getTextureLocation(FrostbiteZombie_forge entity) {
      return entity.isBaby() ? BABY_TEXTURE : TEXTURE;
   }
}
