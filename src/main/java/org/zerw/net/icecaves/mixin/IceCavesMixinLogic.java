package org.zerw.net.icecaves.mixin;

import net.minecraft.class_1297;
import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1309;
import net.minecraft.class_1680;
import net.minecraft.class_1799;
import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2487;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2619;
import net.minecraft.class_2680;
import net.minecraft.class_3486;
import net.minecraft.class_3730;
import net.minecraft.class_3966;
import net.minecraft.class_4538;
import net.minecraft.class_5425;
import net.minecraft.class_5819;
import net.minecraft.class_2350.class_2353;
import org.zerw.net.icecaves.IceCavesFabric;
import org.zerw.net.icecaves.block.JukeboxChestPlaylist;
import org.zerw.net.icecaves.registry.ModPotions;
import org.zerw.net.icecaves.worldgen.IceCaveSpawnControl;

/**
 * Thin vanilla hooks, kept separate from the Mixin injection declarations.
 * The predicates and effects deliberately match the previous implementation.
 * Avoid changing injection points or worldgen climate mappings during a refactor.
 */
public final class IceCavesMixinLogic {
   private IceCavesMixinLogic() {}

   public static boolean hasIceSound(class_2680 state) {
      class_2248 block = state.method_26204();
      return block == class_2246.field_10295
          || block == class_2246.field_10225
          || block == class_2246.field_10384;
   }

   public static <T extends class_2586> boolean isJukeboxTicker(class_2591<T> type) {
      return type == class_2591.field_11907;
   }

   public static void tickPlaylist(class_1937 world, class_2338 pos, class_2619 jukebox) {
      JukeboxChestPlaylist.tick(world, pos, jukebox, (JukeboxChestPlaylist.Access)jukebox);
   }

   public static boolean spawnPlaylistNotes(class_1936 world, class_2338 pos) {
      return world instanceof class_1937 level && JukeboxChestPlaylist.spawnSideNotes(level, pos);
   }

   public static void writePlaylistState(class_2487 tag, JukeboxChestPlaylist.Access access) {
      tag.method_10569("IceCavesSource", access.iceCaves$sourceSlot());
      tag.method_10569("IceCavesNext", access.iceCaves$nextStart());
      tag.method_10569("IceCavesCooldown", access.iceCaves$cooldown());
      tag.method_10556("IceCavesHolding", access.iceCaves$holding());
   }

   public static void readPlaylistState(class_2487 tag, JukeboxChestPlaylist.Access access) {
      access.iceCaves$sourceSlot(tag.method_10545("IceCavesSource") ? tag.method_10550("IceCavesSource") : -1);
      access.iceCaves$setNextStart(tag.method_10550("IceCavesNext"));
      access.iceCaves$setCooldown(tag.method_10550("IceCavesCooldown"));
      access.iceCaves$setHolding(tag.method_10577("IceCavesHolding"));
   }

   public static boolean preventMobSpawn(class_1308 mob, class_1936 world, class_3730 reason) {
      return IceCaveSpawnControl.shouldBlockMonsterSpawn(mob, world, reason);
   }

   public static Boolean spawnPlacementOverride(class_1299<?> type, class_5425 world,
           class_3730 reason, class_2338 pos, class_5819 random) {
      if (IceCaveSpawnControl.shouldBlockMonsterType(type, world, pos, reason)) {
         return Boolean.FALSE;
      }
      if (IceCaveSpawnControl.shouldAllowCaveStraySpawn(type, world, pos, reason, random)) {
         return Boolean.TRUE;
      }
      return null; // Preserve vanilla's predicate when there is no Ice Caves override.
   }

   public static void tagFreezingStack(class_1799 stack) {
      ModPotions.applyFreezingIcon(stack);
   }

   public static boolean canSupportSnowOnPackedIce(class_4538 world, class_2338 pos) {
      return world.method_8320(pos.method_10074()).method_27852(class_2246.field_10225);
   }

   public static void knockBackSnowballTarget(class_1680 snowball, class_3966 hitResult) {
      class_1937 level = snowball.method_37908();
      if (level.method_8608()) return;

      if (!(hitResult.method_17782() instanceof class_1309 living)) return;

      class_1297 owner = snowball.method_24921();
      if (living == owner) return;
      class_1297 source = owner != null ? owner : snowball;
      living.method_6005(0.3, source.method_23317() - living.method_23317(),
          source.method_23321() - living.method_23321());
   }

   public static void freezeWaterBesideBlueIce(class_2680 state, class_1937 world, class_2338 pos) {
      if (world.method_8608() || IceCavesFabric.BRITTLE_ICE == null
          || !state.method_26227().method_15767(class_3486.field_15517)) {
         return;
      }

      for (class_2350 direction : class_2353.field_11062) {
         if (world.method_8320(pos.method_10093(direction)).method_27852(class_2246.field_10384)) {
            world.method_8652(pos, IceCavesFabric.BRITTLE_ICE.method_9564(), 3);
            return;
         }
      }
   }
}
