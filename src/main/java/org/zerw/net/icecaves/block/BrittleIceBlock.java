package org.zerw.net.icecaves.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2680;
import net.minecraft.class_2758;
import net.minecraft.class_2769;
import net.minecraft.class_3218;
import net.minecraft.class_3419;
import net.minecraft.class_5819;
import net.minecraft.class_2689.class_2690;
import net.minecraft.class_4970.class_2251;
import org.zerw.net.icecaves.registry.ModSounds;

public class BrittleIceBlock extends class_2248 {
   public static final MapCodec<BrittleIceBlock> CODEC = method_54094(BrittleIceBlock::new);
   public static final class_2758 STAGE = class_2758.method_11867("stage", 0, 3);
   private static final int CRACK_DELAY = 15;

   public BrittleIceBlock(class_2251 properties) {
      super(properties);
      this.method_9590((class_2680)((class_2680)this.field_10647.method_11664()).method_11657(STAGE, 0));
   }

   public MapCodec<BrittleIceBlock> method_53969() {
      return CODEC;
   }

   protected boolean method_9522(class_2680 state, class_2680 neighbor, class_2350 direction) {
      return neighbor.method_27852(this);
   }

   protected void method_9515(class_2690<class_2248, class_2680> builder) {
      builder.method_11667(new class_2769[]{STAGE});
   }

   public void method_9591(class_1937 level, class_2338 pos, class_2680 state, class_1297 entity) {
      if (!level.method_8608() && isWeight(entity)) {
         if (!level.method_8397().method_8674(pos, this)) {
            level.method_39279(pos, this, 15);
         }
      }
   }

   protected void method_9588(class_2680 state, class_3218 level, class_2338 pos, class_5819 random) {
      if (level.method_8320(pos).method_26204() == this && hasWeight(level, pos)) {
         this.crack(level, pos, state);
      }
   }

   private void crack(class_3218 level, class_2338 pos, class_2680 state) {
      int stage = (Integer)state.method_11654(STAGE);
      float pitch = 0.85F + stage * 0.06F;
      level.method_8396((class_1657)null, pos, ModSounds.ICE_BREAK, class_3419.field_15245, 1.0F, pitch);
      if (stage >= 3) {
         level.method_30093(pos, false, null, 512);
      } else {
         level.method_8652(pos, (class_2680)state.method_11657(STAGE, stage + 1), 3);
         level.method_39279(pos, this, 15);
      }
   }

   private static boolean isWeight(class_1297 entity) {
      return !(entity instanceof org.zerw.net.icecaves.entity.FrostbiteZombie) && entity instanceof class_1309 living && living.method_5805() && !living.method_7325() && living.method_24828();
   }

   private static boolean hasWeight(class_3218 level, class_2338 pos) {
      class_238 box = new class_238(
         pos.method_10263() - 0.05,
         pos.method_10264(),
         pos.method_10260() - 0.05,
         pos.method_10263() + 1.05,
         pos.method_10264() + 2.0,
         pos.method_10260() + 1.05
      );

      for (class_1309 entity : level.method_18467(class_1309.class, box)) {
         if (isWeight(entity) && entity.method_23312().equals(pos)) {
            return true;
         }
      }

      return false;
   }
}
