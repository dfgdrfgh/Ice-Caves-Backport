package org.zerw.net.icecaves.neoforge.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.zerw.net.icecaves.block.JukeboxChestPlaylist$Access_forge;
import org.zerw.net.icecaves.block.JukeboxChestPlaylist_forge;

@Mixin(JukeboxBlockEntity.class)
public class JukeboxPlaylistMixin implements JukeboxChestPlaylist$Access_forge {
   @Unique
   private int iceCaves$sourceSlot = -1;
   @Unique
   private int iceCaves$cooldown;
   @Unique
   private int iceCaves$nextStart;
   @Unique
   private boolean iceCaves$holding;

   @Inject(method = "tick", at = @At("TAIL"))
   private static void iceCaves$playlistTick(Level level, BlockPos pos, BlockState state, JukeboxBlockEntity jukebox, CallbackInfo ci) {
      JukeboxChestPlaylist_forge.tick(level, pos, jukebox, (JukeboxChestPlaylist$Access_forge)jukebox);
   }

   @Inject(method = "saveAdditional", at = @At("TAIL"))
   private void iceCaves$savePlaylist(CompoundTag tag, Provider registries, CallbackInfo ci) {
      tag.putInt("IceCavesSource", this.iceCaves$sourceSlot);
      tag.putInt("IceCavesNext", this.iceCaves$nextStart);
      tag.putInt("IceCavesCooldown", this.iceCaves$cooldown);
      tag.putBoolean("IceCavesHolding", this.iceCaves$holding);
   }

   @Inject(method = "loadAdditional", at = @At("TAIL"))
   private void iceCaves$loadPlaylist(CompoundTag tag, Provider registries, CallbackInfo ci) {
      this.iceCaves$sourceSlot = tag.contains("IceCavesSource") ? tag.getInt("IceCavesSource") : -1;
      this.iceCaves$nextStart = tag.getInt("IceCavesNext");
      this.iceCaves$cooldown = tag.getInt("IceCavesCooldown");
      this.iceCaves$holding = tag.getBoolean("IceCavesHolding");
   }

   @Override
   public int iceCaves$sourceSlot() {
      return this.iceCaves$sourceSlot;
   }

   @Override
   public void iceCaves$sourceSlot(int slot) {
      this.iceCaves$sourceSlot = slot;
   }

   @Override
   public int iceCaves$cooldown() {
      return this.iceCaves$cooldown;
   }

   @Override
   public void iceCaves$setCooldown(int ticks) {
      this.iceCaves$cooldown = ticks;
   }

   @Override
   public int iceCaves$nextStart() {
      return this.iceCaves$nextStart;
   }

   @Override
   public void iceCaves$setNextStart(int slot) {
      this.iceCaves$nextStart = slot;
   }

   @Override
   public boolean iceCaves$holding() {
      return this.iceCaves$holding;
   }

   @Override
   public void iceCaves$setHolding(boolean holding) {
      this.iceCaves$holding = holding;
   }
}
