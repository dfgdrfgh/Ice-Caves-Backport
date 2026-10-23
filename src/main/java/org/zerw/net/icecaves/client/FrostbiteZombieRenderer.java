package org.zerw.net.icecaves.client;

import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_5602;
import net.minecraft.class_623;
import net.minecraft.class_968;
import net.minecraft.class_5617.class_5618;
import org.zerw.net.icecaves.entity.FrostbiteZombie;

public class FrostbiteZombieRenderer extends class_968<FrostbiteZombie, class_623<FrostbiteZombie>> {
   private static final class_2960 TEXTURE = class_2960.method_60655("ice_caves", "textures/entity/frostbite.png");
   private static final class_2960 BABY_TEXTURE = class_2960.method_60655("ice_caves", "textures/entity/frostbite_baby.png");
   private final class_623<FrostbiteZombie> adultModel = (class_623<FrostbiteZombie>)this.field_4737;
   private final FrostbiteBabyModel<FrostbiteZombie> babyModel;

   public FrostbiteZombieRenderer(class_5618 context) {
      super(
         context,
         new class_623(context.method_32167(class_5602.field_27638)),
         new class_623(context.method_32167(class_5602.field_27642)),
         new class_623(context.method_32167(class_5602.field_27643))
      );
      this.babyModel = new FrostbiteBabyModel<>(context.method_32167(FrostbiteBabyModel.LAYER_LOCATION));
      this.method_4046(new FrostbiteOuterLayer(this, context.method_32170()));
   }

   public void render(FrostbiteZombie entity, float entityYaw, float partialTicks, class_4587 poseStack, class_4597 buffer, int packedLight) {
      this.field_4737 = entity.method_6109() ? this.babyModel : this.adultModel;
      super.method_4054(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
   }

   protected void scale(FrostbiteZombie entity, class_4587 poseStack, float partialTick) {
      this.field_4673 = entity.method_6109() ? 0.25F : 0.5F;
   }

   public class_2960 getTextureLocation(FrostbiteZombie entity) {
      return entity.method_6109() ? BABY_TEXTURE : TEXTURE;
   }
}
