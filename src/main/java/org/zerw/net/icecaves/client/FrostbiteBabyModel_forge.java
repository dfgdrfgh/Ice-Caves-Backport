package org.zerw.net.icecaves.client;

import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import org.zerw.net.icecaves.entity.FrostbiteZombie_forge;

public class FrostbiteBabyModel_forge<T extends FrostbiteZombie_forge> extends ZombieModel<T> {
   public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("ice_caves", "frostbite_baby"), "main");
   private static final float BODY_Y = 17.5F;
   private static final float HEAD_Y = 15.25F;
   private static final float ARM_Y = 15.5F;
   private static final float LEG_Y = 20.0F;

   public FrostbiteBabyModel_forge(ModelPart root) {
      super(root);
   }

   public static LayerDefinition createBodyLayer() {
      MeshDefinition mesh = new MeshDefinition();
      PartDefinition root = mesh.getRoot();
      root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-2.0F, -2.5F, -1.0F, 4.0F, 5.0F, 2.0F), PartPose.offset(0.0F, 17.5F, 0.0F));
      root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(3, 3).addBox(-3.0F, -6.25F, -3.0F, 6.0F, 6.0F, 6.0F), PartPose.offset(0.0F, 15.25F, 0.0F));
      root.addOrReplaceChild(
         "hat",
         CubeListBuilder.create().texOffs(35, 3).addBox(-3.0F, -6.15F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.25F)),
         PartPose.offset(0.0F, 15.25F, 0.0F)
      );
      root.addOrReplaceChild(
         "right_arm", CubeListBuilder.create().texOffs(36, 16).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F), PartPose.offset(-3.0F, 15.5F, 0.0F)
      );
      root.addOrReplaceChild(
         "left_arm", CubeListBuilder.create().texOffs(28, 16).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F), PartPose.offset(3.0F, 15.5F, 0.0F)
      );
      root.addOrReplaceChild(
         "right_leg", CubeListBuilder.create().texOffs(8, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F), PartPose.offset(-1.0F, 20.0F, 0.0F)
      );
      root.addOrReplaceChild(
         "left_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F), PartPose.offset(1.0F, 20.0F, 0.0F)
      );
      return LayerDefinition.create(mesh, 64, 64);
   }

   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
      this.body.setPos(0.0F, 17.5F, 0.0F);
      this.head.setPos(0.0F, 15.25F, 0.0F);
      this.hat.setPos(0.0F, 15.25F, 0.0F);
      this.rightArm.setPos(-3.0F, 15.5F, 0.0F);
      this.leftArm.setPos(3.0F, 15.5F, 0.0F);
      this.rightLeg.setPos(-1.0F, 20.0F, 0.0F);
      this.leftLeg.setPos(1.0F, 20.0F, 0.0F);
      this.young = false;
   }
}
