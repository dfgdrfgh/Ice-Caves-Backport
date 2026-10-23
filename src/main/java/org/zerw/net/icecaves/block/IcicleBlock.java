package org.zerw.net.icecaves.block;

import javax.annotation.Nullable;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1540;
import net.minecraft.class_1750;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_1944;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_3419;
import net.minecraft.class_3481;
import net.minecraft.class_3612;
import net.minecraft.class_4538;
import net.minecraft.class_5689;
import net.minecraft.class_5691;
import net.minecraft.class_5819;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_4970.class_2251;
import org.zerw.net.icecaves.registry.ModEffects;
import org.zerw.net.icecaves.registry.ModSounds;

public final class IcicleBlock extends class_5689 {
   private static final int MELT_LIGHT_LEVEL = 8;
   private static final int MELT_CHECK_DELAY = 20;
   private static final float RANDOM_FALL_CHANCE = 0.05F;
   private static final int HIT_FREEZING_TICKS = 100;
   private static final float FALL_DAMAGE_PER_DISTANCE = 1.0F;
   private static final int FALL_DAMAGE_MAX = 16;

   public IcicleBlock(class_2251 properties) {
      super(properties);
   }

   protected void method_9615(class_2680 state, class_1937 level, class_2338 pos, class_2680 oldState, boolean movedByPiston) {
      if (!level.method_8608() && this.columnExposedToMelt(level, pos, state)) {
         this.scheduleColumnFall((class_3218)level, pos, state, 20);
      }
   }

   protected void method_9514(class_2680 state, class_3218 level, class_2338 pos, class_5819 random) {
      if (this.columnExposedToMelt(level, pos, state)) {
         this.scheduleColumnFall(level, pos, state, 2);
      } else {
         if (state.method_11654(field_28050) == class_2350.field_11033
            && state.method_11654(field_28051) == class_5691.field_28065
            && random.method_43057() < 0.05F) {
            this.scheduleColumnFall(level, pos, state, 2);
         }
      }
   }

   protected void method_9588(class_2680 state, class_3218 level, class_2338 pos, class_5819 random) {
      boolean melt = this.columnExposedToMelt(level, pos, state);
      boolean unsupported = !this.method_9558(state, level, pos);
      if (melt || unsupported) {
         if (state.method_11654(field_28050) == class_2350.field_11036) {
            level.method_22352(pos, true);
         } else {
            this.fallHangingColumn(level, this.findHangingColumnTop(level, pos));
         }
      }
   }

   protected void method_9612(class_2680 state, class_1937 level, class_2338 pos, class_2248 neighborBlock, class_2338 fromPos, boolean movedByPiston) {
      if (!level.method_8608() && this.columnExposedToMelt(level, pos, state)) {
         this.scheduleColumnFall((class_3218)level, pos, state, 2);
      }
   }

   protected boolean method_9558(class_2680 state, class_4538 level, class_2338 pos) {
      class_2350 tipDirection = (class_2350)state.method_11654(field_28050);
      class_2338 supportPos = pos.method_10093(tipDirection.method_10153());
      class_2680 support = level.method_8320(supportPos);
      return support.method_27852(this) && support.method_28498(field_28050) && support.method_11654(field_28050) == tipDirection
         ? true
         : isValidIceCaveSupport(support);
   }

   protected class_2680 method_9559(class_2680 state, class_2350 direction, class_2680 neighborState, class_1936 level, class_2338 pos, class_2338 neighborPos) {
      if (direction != class_2350.field_11036 && direction != class_2350.field_11033) {
         return state;
      } else {
         class_2350 tipDirection = (class_2350)state.method_11654(field_28050);
         if (direction == tipDirection.method_10153() && !this.method_9558(state, level, pos)) {
            level.method_39279(pos, this, tipDirection == class_2350.field_11033 ? 2 : 1);
            return state;
         } else {
            boolean mergeOpposingTips = state.method_11654(field_28051) == class_5691.field_28064;
            class_5691 thickness = this.calculateThickness(level, pos, tipDirection, mergeOpposingTips);
            return (class_2680)state.method_11657(field_28051, thickness);
         }
      }
   }

   @Nullable
   public class_2680 method_9605(class_1750 context) {
      class_1936 level = context.method_8045();
      class_2338 pos = context.method_8037();
      class_2350 clicked = context.method_8038();
      class_2350 preferred;
      if (clicked.method_10166().method_10178()) {
         preferred = clicked;
      } else {
         preferred = context.method_32760().method_10153();
      }

      class_2350 tipDirection = null;
      if (this.isValidIceOrIcicleSupport(level, pos, preferred)) {
         tipDirection = preferred;
      } else if (this.isValidIceOrIcicleSupport(level, pos, preferred.method_10153())) {
         tipDirection = preferred.method_10153();
      }

      if (tipDirection == null) {
         return null;
      }

      boolean mergeOpposingTips = !context.method_8046();
      class_5691 thickness = this.calculateThickness(level, pos, tipDirection, mergeOpposingTips);
      return (class_2680)((class_2680)((class_2680)this.method_9564().method_11657(field_28050, tipDirection)).method_11657(field_28051, thickness))
         .method_11657(field_28052, level.method_8316(pos).method_15772() == class_3612.field_15910);
   }

   public void method_9554(class_1937 level, class_2680 state, class_2338 pos, class_1297 entity, float fallDistance) {
      if (state.method_11654(field_28050) == class_2350.field_11036 && state.method_11654(field_28051) == class_5691.field_28065) {
         entity.method_5747(fallDistance + 2.5F, 2.0F, level.method_48963().method_48837());
         if (entity instanceof class_1309 living) {
            applyFreezing(level, living);
         }
      } else {
         super.method_9554(level, state, pos, entity, fallDistance);
      }
   }

   public void method_10127(class_1937 level, class_2338 pos, class_2680 state, class_2680 replacedBlock, class_1540 entity) {
      applyFreezingNearby(level, entity);
      playLandingImpactSound(level, pos, entity);
      super.method_10127(level, pos, state, replacedBlock, entity);
   }

   public void method_10129(class_1937 level, class_2338 pos, class_1540 entity) {
      applyFreezingNearby(level, entity);
      playLandingImpactSound(level, pos, entity);
   }

   private static void playLandingImpactSound(class_1937 level, class_2338 pos, class_1540 entity) {
      if (!entity.method_5701()) {
         level.method_8396(null, pos, ModSounds.ICE_BREAK, class_3419.field_15245, 1.0F, 0.8F + level.method_8409().method_43057() * 0.4F);
      }
   }

   private void scheduleColumnFall(class_3218 level, class_2338 pos, class_2680 state, int delay) {
      if (state.method_11654(field_28050) == class_2350.field_11033) {
         level.method_39279(this.findHangingColumnTop(level, pos), this, delay);
      } else {
         level.method_39279(pos, this, delay);
      }
   }

   private class_2338 findHangingColumnTop(class_4538 level, class_2338 pos) {
      class_2339 cursor = pos.method_25503();

      while (true) {
         class_2680 above = level.method_8320(cursor.method_10084());
         if (!above.method_27852(this) || above.method_11654(field_28050) != class_2350.field_11033) {
            return cursor.method_10062();
         }

         cursor.method_10098(class_2350.field_11036);
      }
   }

   private boolean columnExposedToMelt(class_4538 level, class_2338 pos, class_2680 state) {
      if (state.method_11654(field_28050) == class_2350.field_11036) {
         return shouldMelt(level, pos);
      }

      class_2339 cursor = this.findHangingColumnTop(level, pos).method_25503();

      while (true) {
         class_2680 current = level.method_8320(cursor);
         if (!current.method_27852(this) || current.method_11654(field_28050) != class_2350.field_11033) {
            return false;
         }

         if (shouldMelt(level, cursor)) {
            return true;
         }

         cursor.method_10098(class_2350.field_11033);
      }
   }

   private void fallHangingColumn(class_3218 level, class_2338 topPos) {
      class_2339 fallPos = topPos.method_25503();
      class_2680 fallState = level.method_8320(fallPos);
      int topY = topPos.method_10264();

      while (fallState.method_27852(this) && fallState.method_11654(field_28050) == class_2350.field_11033) {
         class_1540 entity = class_1540.method_40005(level, fallPos, fallState);
         class_5691 thickness = (class_5691)fallState.method_11654(field_28051);
         boolean tip = thickness == class_5691.field_28065 || thickness == class_5691.field_28064;
         if (tip) {
            int size = Math.max(1, topY - fallPos.method_10264() + 1);
            entity.method_6965(1.0F * size, 16);
            break;
         }

         fallPos.method_10098(class_2350.field_11033);
         fallState = level.method_8320(fallPos);
      }
   }

   private boolean isValidIceOrIcicleSupport(class_4538 level, class_2338 pos, class_2350 tipDirection) {
      class_2338 supportPos = pos.method_10093(tipDirection.method_10153());
      class_2680 support = level.method_8320(supportPos);
      return support.method_27852(this) && support.method_28498(field_28050) && support.method_11654(field_28050) == tipDirection
         ? true
         : isValidIceCaveSupport(support);
   }

   private static boolean isValidIceCaveSupport(class_2680 support) {
      return support.method_26164(class_3481.field_15467)
         || support.method_27852(class_2246.field_10225)
         || support.method_27852(class_2246.field_10295)
         || support.method_27852(class_2246.field_10384)
         || support.method_27852(class_2246.field_27114)
         || support.method_27852(class_2246.field_10340)
         || support.method_26164(class_3481.field_25806);
   }

   private class_5691 calculateThickness(class_4538 level, class_2338 pos, class_2350 tipDirection, boolean mergeOpposingTips) {
      class_2350 baseDirection = tipDirection.method_10153();
      class_2680 inFrontState = level.method_8320(pos.method_10093(tipDirection));
      if (!this.isIcicleWithDirection(inFrontState, baseDirection)) {
         if (!this.isIcicleWithDirection(inFrontState, tipDirection)) {
            return class_5691.field_28065;
         } else {
            class_5691 inFrontThickness = (class_5691)inFrontState.method_11654(field_28051);
            if (inFrontThickness != class_5691.field_28065 && inFrontThickness != class_5691.field_28064) {
               class_2680 behindState = level.method_8320(pos.method_10093(baseDirection));
               return !this.isIcicleWithDirection(behindState, tipDirection) ? class_5691.field_28068 : class_5691.field_28067;
            } else {
               return class_5691.field_28066;
            }
         }
      } else {
         return !mergeOpposingTips && inFrontState.method_11654(field_28051) != class_5691.field_28064 ? class_5691.field_28065 : class_5691.field_28064;
      }
   }

   private boolean isIcicleWithDirection(class_2680 state, class_2350 tipDirection) {
      return state.method_27852(this) && state.method_11654(field_28050) == tipDirection;
   }

   private static void applyFreezingNearby(class_1937 level, class_1540 falling) {
      if (!level.method_8608()) {
         class_238 box = falling.method_5829().method_1014(0.35);

         for (class_1309 living : level.method_18467(class_1309.class, box)) {
            applyFreezing(level, living);
         }
      }
   }

   private static void applyFreezing(class_1937 level, class_1309 living) {
      if (!level.method_8608()) {
         ModEffects.applyFreezing(living, 100, null);
      }
   }

   private static boolean shouldMelt(class_4538 level, class_2338 pos) {
      return level.method_8314(class_1944.field_9282, pos) >= 8;
   }
}
