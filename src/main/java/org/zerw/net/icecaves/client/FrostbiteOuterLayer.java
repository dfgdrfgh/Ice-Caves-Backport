package org.zerw.net.icecaves.client;

import net.minecraft.class_2960;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_5599;
import net.minecraft.class_5602;
import net.minecraft.class_623;
import org.zerw.net.icecaves.entity.FrostbiteZombie;

public class FrostbiteOuterLayer<T extends FrostbiteZombie> extends class_3887<T, class_623<T>> {
   private static final class_2960 TEXTURE = class_2960.method_60655("ice_caves", "textures/entity/frostbite_outer_layer.png");
   private final class_623<T> model;

   public FrostbiteOuterLayer(class_3883<T, class_623<T>> parent, class_5599 models) {
      super(parent);
      this.model = new class_623(models.method_32072(class_5602.field_27557));
   }

   public void method_4199(
      class_4587 poseStack,
      class_4597 buffer,
      int packedLight,
      T entity,
      float limbSwing,
      float limbSwingAmount,
      float partialTicks,
      float ageInTicks,
      float netHeadYaw,
      float headPitch
   ) {
      if (!entity.method_6109()) {
         method_23196(
            this.method_17165(),
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
