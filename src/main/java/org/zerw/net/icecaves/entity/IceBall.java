package org.zerw.net.icecaves.entity;

import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_239;
import net.minecraft.class_2392;
import net.minecraft.class_2394;
import net.minecraft.class_2398;
import net.minecraft.class_3414;
import net.minecraft.class_3419;
import net.minecraft.class_3857;
import net.minecraft.class_3966;
import net.minecraft.class_7923;
import org.zerw.net.icecaves.registry.ModEffects;
import org.zerw.net.icecaves.registry.ModItems;
import org.zerw.net.icecaves.registry.ModSounds;

public class IceBall extends class_3857 {
   private static final int HIT_FREEZING_TICKS = 20;

   public IceBall(class_1299<? extends IceBall> type, class_1937 level) {
      super(type, level);
   }

   public IceBall(class_1299<? extends IceBall> type, class_1309 thrower, class_1937 level) {
      super(type, thrower, level);
   }

   public IceBall(class_1299<? extends IceBall> type, double x, double y, double z, class_1937 level) {
      super(type, x, y, z, level);
   }

   protected class_1792 method_16942() {
      return (class_1792)class_7923.field_41178.method_10223(ModItems.ICE_BALL_ID);
   }

   private class_2394 getParticle() {
      class_1799 item = this.method_7495();
      return (class_2394)(item.method_7960() ? class_2398.field_11230 : new class_2392(class_2398.field_11218, item));
   }

   public void method_5711(byte id) {
      if (id == 3) {
         class_2394 particle = this.getParticle();

         for (int i = 0; i < 8; i++) {
            this.method_37908().method_8406(particle, this.method_23317(), this.method_23318(), this.method_23321(), 0.0, 0.0, 0.0);
         }
      }
   }

   protected void method_7454(class_3966 hitResult) {
      super.method_7454(hitResult);
      if (!this.method_37908().method_8608()) {
         if (hitResult.method_17782() instanceof class_1309 living) {
            // Match the snapshot's speed-dependent impact while preserving the Freezing effect.
            float damage = Math.max(0.0F, (float)Math.ceil(this.method_18798().method_1033() * 1.395));
            if (damage > 0.0F) {
               living.method_5643(this.method_48923().method_48811(this, this.method_24921()), damage);
            }
            ModEffects.applyFreezing(living, 20, this.method_24921());
            class_3414 sound = ModSounds.iceBallHit();
            if (sound != null) {
               this.method_37908()
                  .method_43128(
                     null,
                     this.method_23317(),
                     this.method_23318(),
                     this.method_23321(),
                     sound,
                     class_3419.field_15254,
                     1.0F,
                     1.0F / (this.field_5974.method_43057() * 0.4F + 0.8F)
                  );
            }
         }
      }
   }

   protected void method_7488(class_239 hitResult) {
      super.method_7488(hitResult);
      if (!this.method_37908().method_8608()) {
         this.method_37908().method_8421(this, (byte)3);
         this.method_31472();
      }
   }
}
