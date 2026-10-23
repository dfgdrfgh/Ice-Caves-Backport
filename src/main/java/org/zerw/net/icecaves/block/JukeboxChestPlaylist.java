package org.zerw.net.icecaves.block;

import net.minecraft.class_1263;
import net.minecraft.class_1264;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2281;
import net.minecraft.class_2338;
import net.minecraft.class_2398;
import net.minecraft.class_2586;
import net.minecraft.class_2619;
import net.minecraft.class_2680;
import net.minecraft.class_3218;
import net.minecraft.class_5558;
import net.minecraft.class_5819;
import net.minecraft.class_9334;

public final class JukeboxChestPlaylist {
   public static final int COOLDOWN_TICKS = 60;

   private JukeboxChestPlaylist() {
   }

   public static <T extends class_2586> class_5558<T> vanillaTicker() {
      return class_2619::method_44370;
   }

   public static void tick(class_1937 level, class_2338 pos, class_2619 jukebox, JukeboxChestPlaylist.Access access) {
      if (!level.method_8608()) {
         class_1263 chest = chestAbove(level, pos);
         if (chest == null) {
            reset(access);
         } else if (!jukebox.method_54079().method_7960()) {
            if (!jukebox.method_60784().method_60754()) {
               int from = access.iceCaves$holding() ? access.iceCaves$sourceSlot() : -1;
               class_1799 returned = jukebox.method_54079().method_7972();
               jukebox.method_54077(class_1799.field_8037);
               jukebox.method_5431();
               int placed = insert(level, pos, chest, returned, from);
               access.iceCaves$setHolding(false);
               access.iceCaves$sourceSlot(-1);
               access.iceCaves$setNextStart(placed >= 0 ? placed + 1 : 0);
               access.iceCaves$setCooldown(60);
               level.method_8455(pos, level.method_8320(pos).method_26204());
            }
         } else {
            if (access.iceCaves$cooldown() > 0) {
               access.iceCaves$setCooldown(access.iceCaves$cooldown() - 1);
               if (access.iceCaves$cooldown() > 0) {
                  return;
               }
            }

            pullNext(level, pos, jukebox, chest, access);
         }
      }
   }

   public static boolean skip(class_1937 level, class_2338 pos) {
      class_1263 chest = chestAbove(level, pos);
      if (chest != null && level.method_8321(pos) instanceof class_2619 jukebox) {
         JukeboxChestPlaylist.Access var9 = (JukeboxChestPlaylist.Access)jukebox;
         boolean hasDisc = !jukebox.method_54079().method_7960();
         if (!hasDisc && var9.iceCaves$cooldown() <= 0) {
            return false;
         }

         if (level.method_8608()) {
            return true;
         }

         if (hasDisc) {
            int preferred = var9.iceCaves$holding() ? var9.iceCaves$sourceSlot() : -1;
            class_1799 returned = jukebox.method_54079().method_7972();
            jukebox.method_54077(class_1799.field_8037);
            int placed = insert(level, pos, chest, returned, preferred);
            var9.iceCaves$setNextStart(placed >= 0 ? placed + 1 : 0);
            var9.iceCaves$setHolding(false);
            var9.iceCaves$sourceSlot(-1);
         }

         var9.iceCaves$setCooldown(0);
         pullNext(level, pos, jukebox, chest, var9);
         jukebox.method_5431();
         return true;
      } else {
         return false;
      }
   }

   private static void pullNext(class_1937 level, class_2338 pos, class_2619 jukebox, class_1263 chest, JukeboxChestPlaylist.Access access) {
      int slot = findDisc(chest, access.iceCaves$nextStart());
      if (slot >= 0) {
         class_1799 one = chest.method_5438(slot).method_7971(1);
         chest.method_5431();
         access.iceCaves$sourceSlot(slot);
         access.iceCaves$setHolding(true);
         access.iceCaves$setNextStart(slot + 1);
         jukebox.method_54077(one);
         jukebox.method_5431();
         level.method_8455(pos, level.method_8320(pos).method_26204());
      }
   }

   private static int findDisc(class_1263 chest, int start) {
      int size = chest.method_5439();
      if (size <= 0) {
         return -1;
      }

      int origin = Math.floorMod(start, size);

      for (int i = 0; i < size; i++) {
         int slot = (origin + i) % size;
         if (isDisc(chest.method_5438(slot))) {
            return slot;
         }
      }

      return -1;
   }

   private static int insert(class_1937 level, class_2338 pos, class_1263 chest, class_1799 stack, int preferred) {
      if (stack.method_7960()) {
         return -1;
      }

      int size = chest.method_5439();
      if (preferred >= 0 && preferred < size && chest.method_5438(preferred).method_7960()) {
         chest.method_5447(preferred, stack);
         chest.method_5431();
         return preferred;
      }

      for (int slot = 0; slot < size; slot++) {
         if (chest.method_5438(slot).method_7960()) {
            chest.method_5447(slot, stack);
            chest.method_5431();
            return slot;
         }
      }

      class_1264.method_5449(level, pos.method_10263() + 0.5, pos.method_10264() + 1.0, pos.method_10260() + 0.5, stack);
      return -1;
   }

   private static boolean isDisc(class_1799 stack) {
      return stack.method_57826(class_9334.field_52175);
   }

   public static boolean spawnSideNotes(class_1937 level, class_2338 pos) {
      if (!level.method_8608() && chestAbove(level, pos) != null && level instanceof class_3218 server) {
         class_5819 random = level.method_8409();
         int side = random.method_43048(4);
         double along = (random.method_43058() - 0.5) * 0.85;
         double radius = 0.62 + random.method_43058() * 0.22;
         double x = pos.method_10263() + 0.5;
         double y = pos.method_10264() + 0.92 + random.method_43058() * 0.4;
         double z = pos.method_10260() + 0.5;
         if (side == 0) {
            x += radius;
            z += along;
         } else if (side == 1) {
            x -= radius;
            z += along;
         } else if (side == 2) {
            z += radius;
            x += along;
         } else {
            z -= radius;
            x += along;
         }

         double size = 0.5 + random.method_43058() * 1.1;
         server.method_14199(class_2398.field_11224, x, y, z, 0, random.method_43057(), size, 0.0, 1.0);
         return true;
      } else {
         return false;
      }
   }

   private static class_1263 chestAbove(class_1937 level, class_2338 pos) {
      class_2338 above = pos.method_10084();
      class_2680 state = level.method_8320(above);
      return state.method_26204() instanceof class_2281 chest ? class_2281.method_17458(chest, state, level, above, true) : null;
   }

   private static void reset(JukeboxChestPlaylist.Access access) {
      access.iceCaves$sourceSlot(-1);
      access.iceCaves$setCooldown(0);
      access.iceCaves$setNextStart(0);
      access.iceCaves$setHolding(false);
   }

   public interface Access {
      int iceCaves$sourceSlot();

      void iceCaves$sourceSlot(int var1);

      int iceCaves$cooldown();

      void iceCaves$setCooldown(int var1);

      int iceCaves$nextStart();

      void iceCaves$setNextStart(int var1);

      boolean iceCaves$holding();

      void iceCaves$setHolding(boolean var1);
   }
}
