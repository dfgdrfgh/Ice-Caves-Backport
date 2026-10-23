package org.zerw.net.icecaves.registry;

import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.class_1291;
import net.minecraft.class_1293;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_7923;
import net.minecraft.class_7924;
import net.minecraft.class_6880.class_6883;
import org.zerw.net.icecaves.effect.FreezingMobEffect;

public final class ModEffects {
   public static final class_2960 FREEZING_EFFECT_ID = class_2960.method_60655("ice_caves", "freezing");
   public static final class_5321<class_1291> FREEZING_EFFECT_KEY = class_5321.method_29179(class_7924.field_41208, FREEZING_EFFECT_ID);

   private ModEffects() {
   }

   public static class_1291 createFreezingEffect() {
      return new FreezingMobEffect();
   }

   public static Optional<class_6883<class_1291>> freezing() {
      return class_7923.field_41174.method_40264(FREEZING_EFFECT_KEY);
   }

   public static void applyFreezing(class_1309 target, int durationTicks, @Nullable class_1297 source) {
      freezing().ifPresent(effect -> target.method_37222(new class_1293(effect, durationTicks), source));
   }
}
