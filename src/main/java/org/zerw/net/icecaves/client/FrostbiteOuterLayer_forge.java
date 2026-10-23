package org.zerw.net.icecaves.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.zerw.net.icecaves.entity.FrostbiteZombie_forge;

public class FrostbiteOuterLayer_forge<T extends FrostbiteZombie_forge> extends RenderLayer<T, ZombieModel<T>> {
   private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath("ice_caves", "textures/entity/frostbite_outer_layer.png");
   private final ZombieModel<T> model;

   public FrostbiteOuterLayer_forge(RenderLayerParent<T, ZombieModel<T>> parent, EntityModelSet models) {
      super(parent);
      this.model = new ZombieModel(models.bakeLayer(ModelLayers.DROWNED_OUTER_LAYER));
   }

   public void render(
      PoseStack poseStack,
      MultiBufferSource buffer,
      int packedLight,
      T entity,
      float limbSwing,
      float limbSwingAmount,
      float partialTicks,
      float ageInTicks,
      float netHeadYaw,
      float headPitch
   ) {
      if (!entity.isBaby()) {
         coloredCutoutModelCopyLayerRender(
            this.getParentModel(),
            this.model,
            TEXTURE,
            poseStack,
            buffer,
            packedLight,
            entity,
            limbSwing,
            limbSwingAmount,
            ageInTicks,
            netHeadYaw,
            headPitch,
            partialTicks,
            -1
         );
      }
   }
}
