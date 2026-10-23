package org.zerw.net.icecaves.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public final class JukeboxChestPlaylist_forge {
   public static final int COOLDOWN_TICKS = 60;

   private JukeboxChestPlaylist_forge() {
   }

   public static <T extends BlockEntity> BlockEntityTicker<T> vanillaTicker() {
      return JukeboxBlockEntity::tick;
   }

   public static void tick(Level level, BlockPos pos, JukeboxBlockEntity jukebox, JukeboxChestPlaylist$Access_forge access) {
      if (!level.isClientSide()) {
         Container chest = chestAbove(level, pos);
         if (chest == null) {
            reset(access);
         } else if (!jukebox.getTheItem().isEmpty()) {
            if (!jukebox.getSongPlayer().isPlaying()) {
               int from = access.iceCaves$holding() ? access.iceCaves$sourceSlot() : -1;
               ItemStack returned = jukebox.getTheItem().copy();
               jukebox.setTheItem(ItemStack.EMPTY);
               jukebox.setChanged();
               int placed = insert(level, pos, chest, returned, from);
               access.iceCaves$setHolding(false);
               access.iceCaves$sourceSlot(-1);
               access.iceCaves$setNextStart(placed >= 0 ? placed + 1 : 0);
               access.iceCaves$setCooldown(60);
               level.updateNeighbourForOutputSignal(pos, level.getBlockState(pos).getBlock());
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

   public static boolean skip(Level level, BlockPos pos) {
      Container chest = chestAbove(level, pos);
      if (chest != null && level.getBlockEntity(pos) instanceof JukeboxBlockEntity jukebox) {
         JukeboxChestPlaylist$Access_forge var9 = (JukeboxChestPlaylist$Access_forge)jukebox;
         boolean hasDisc = !jukebox.getTheItem().isEmpty();
         if (!hasDisc && var9.iceCaves$cooldown() <= 0) {
            return false;
         }

         if (level.isClientSide()) {
            return true;
         }

         if (hasDisc) {
            int preferred = var9.iceCaves$holding() ? var9.iceCaves$sourceSlot() : -1;
            ItemStack returned = jukebox.getTheItem().copy();
            jukebox.setTheItem(ItemStack.EMPTY);
            int placed = insert(level, pos, chest, returned, preferred);
            var9.iceCaves$setNextStart(placed >= 0 ? placed + 1 : 0);
            var9.iceCaves$setHolding(false);
            var9.iceCaves$sourceSlot(-1);
         }

         var9.iceCaves$setCooldown(0);
         pullNext(level, pos, jukebox, chest, var9);
         jukebox.setChanged();
         return true;
      } else {
         return false;
      }
   }

   private static void pullNext(Level level, BlockPos pos, JukeboxBlockEntity jukebox, Container chest, JukeboxChestPlaylist$Access_forge access) {
      int slot = findDisc(chest, access.iceCaves$nextStart());
      if (slot >= 0) {
         ItemStack one = chest.getItem(slot).split(1);
         chest.setChanged();
         access.iceCaves$sourceSlot(slot);
         access.iceCaves$setHolding(true);
         access.iceCaves$setNextStart(slot + 1);
         jukebox.setTheItem(one);
         jukebox.setChanged();
         level.updateNeighbourForOutputSignal(pos, level.getBlockState(pos).getBlock());
      }
   }

   private static int findDisc(Container chest, int start) {
      int size = chest.getContainerSize();
      if (size <= 0) {
         return -1;
      }

      int origin = Math.floorMod(start, size);

      for (int i = 0; i < size; i++) {
         int slot = (origin + i) % size;
         if (isDisc(chest.getItem(slot))) {
            return slot;
         }
      }

      return -1;
   }

   private static int insert(Level level, BlockPos pos, Container chest, ItemStack stack, int preferred) {
      if (stack.isEmpty()) {
         return -1;
      }

      int size = chest.getContainerSize();
      if (preferred >= 0 && preferred < size && chest.getItem(preferred).isEmpty()) {
         chest.setItem(preferred, stack);
         chest.setChanged();
         return preferred;
      }

      for (int slot = 0; slot < size; slot++) {
         if (chest.getItem(slot).isEmpty()) {
            chest.setItem(slot, stack);
            chest.setChanged();
            return slot;
         }
      }

      Containers.dropItemStack(level, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, stack);
      return -1;
   }

   private static boolean isDisc(ItemStack stack) {
      return stack.has(DataComponents.JUKEBOX_PLAYABLE);
   }

   public static boolean spawnSideNotes(Level level, BlockPos pos) {
      if (!level.isClientSide() && chestAbove(level, pos) != null && level instanceof ServerLevel server) {
         RandomSource random = level.getRandom();
         int side = random.nextInt(4);
         double along = (random.nextDouble() - 0.5) * 0.85;
         double radius = 0.62 + random.nextDouble() * 0.22;
         double x = pos.getX() + 0.5;
         double y = pos.getY() + 0.92 + random.nextDouble() * 0.4;
         double z = pos.getZ() + 0.5;
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

         double size = 0.5 + random.nextDouble() * 1.1;
         server.sendParticles(ParticleTypes.NOTE, x, y, z, 0, random.nextFloat(), size, 0.0, 1.0);
         return true;
      } else {
         return false;
      }
   }

   private static Container chestAbove(Level level, BlockPos pos) {
      BlockPos above = pos.above();
      BlockState state = level.getBlockState(above);
      return state.getBlock() instanceof ChestBlock chest ? ChestBlock.getContainer(chest, state, level, above, true) : null;
   }

   private static void reset(JukeboxChestPlaylist$Access_forge access) {
      access.iceCaves$sourceSlot(-1);
      access.iceCaves$setCooldown(0);
      access.iceCaves$setNextStart(0);
      access.iceCaves$setHolding(false);
   }
}
