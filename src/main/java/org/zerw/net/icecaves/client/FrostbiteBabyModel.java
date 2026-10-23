package org.zerw.net.icecaves.client;

import net.minecraft.class_2960;
import net.minecraft.class_5601;
import net.minecraft.class_5603;
import net.minecraft.class_5605;
import net.minecraft.class_5606;
import net.minecraft.class_5607;
import net.minecraft.class_5609;
import net.minecraft.class_5610;
import net.minecraft.class_623;
import net.minecraft.class_630;
import org.zerw.net.icecaves.entity.FrostbiteZombie;

public class FrostbiteBabyModel<T extends FrostbiteZombie> extends class_623<T> {
   public static final class_5601 LAYER_LOCATION = new class_5601(class_2960.method_60655("ice_caves", "frostbite_baby"), "main");
   private static final float BODY_Y = 17.5F;
   private static final float HEAD_Y = 15.25F;
   private static final float ARM_Y = 15.5F;
   private static final float LEG_Y = 20.0F;

   public FrostbiteBabyModel(class_630 root) {
      super(root);
   }

   public static class_5607 createBodyLayer() {
      class_5609 mesh = new class_5609();
      class_5610 root = mesh.method_32111();
      root.method_32117(
         "body", class_5606.method_32108().method_32101(16, 16).method_32097(-2.0F, -2.5F, -1.0F, 4.0F, 5.0F, 2.0F), class_5603.method_32090(0.0F, 17.5F, 0.0F)
      );
      root.method_32117(
         "head", class_5606.method_32108().method_32101(3, 3).method_32097(-3.0F, -6.25F, -3.0F, 6.0F, 6.0F, 6.0F), class_5603.method_32090(0.0F, 15.25F, 0.0F)
      );
      root.method_32117(
         "hat",
         class_5606.method_32108().method_32101(35, 3).method_32098(-3.0F, -6.15F, -3.0F, 6.0F, 6.0F, 6.0F, new class_5605(0.25F)),
         class_5603.method_32090(0.0F, 15.25F, 0.0F)
      );
      root.method_32117(
         "right_arm",
         class_5606.method_32108().method_32101(36, 16).method_32097(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F),
         class_5603.method_32090(-3.0F, 15.5F, 0.0F)
      );
      root.method_32117(
         "left_arm",
         class_5606.method_32108().method_32101(28, 16).method_32097(-1.0F, -0.5F, -1.0F, 2.0F, 5.0F, 2.0F),
         class_5603.method_32090(3.0F, 15.5F, 0.0F)
      );
      root.method_32117(
         "right_leg",
         class_5606.method_32108().method_32101(8, 16).method_32097(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F),
         class_5603.method_32090(-1.0F, 20.0F, 0.0F)
      );
      root.method_32117(
         "left_leg",
         class_5606.method_32108().method_32101(0, 16).method_32097(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F),
         class_5603.method_32090(1.0F, 20.0F, 0.0F)
      );
      return class_5607.method_32110(mesh, 64, 64);
   }

   public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      super.method_17791(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
      this.field_3391.method_2851(0.0F, 17.5F, 0.0F);
      this.field_3398.method_2851(0.0F, 15.25F, 0.0F);
      this.field_3394.method_2851(0.0F, 15.25F, 0.0F);
      this.field_3401.method_2851(-3.0F, 15.5F, 0.0F);
      this.field_27433.method_2851(3.0F, 15.5F, 0.0F);
      this.field_3392.method_2851(-1.0F, 20.0F, 0.0F);
      this.field_3397.method_2851(1.0F, 20.0F, 0.0F);
      this.field_3448 = false;
   }
}
