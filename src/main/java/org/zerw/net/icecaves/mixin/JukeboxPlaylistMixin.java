package org.zerw.net.icecaves.mixin;

import org.zerw.net.icecaves.hooks.IceCavesMixinLogic;

import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2487;
import net.minecraft.class_2619;
import net.minecraft.class_2680;
import net.minecraft.class_7225.class_7874;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.zerw.net.icecaves.block.JukeboxChestPlaylist;

@Mixin(class_2619.class)
public class JukeboxPlaylistMixin implements JukeboxChestPlaylist.Access {
   @Unique
   private int iceCaves$sourceSlot = -1;
   @Unique
   private int iceCaves$cooldown;
   @Unique
   private int iceCaves$nextStart;
   @Unique
   private boolean iceCaves$holding;

   @Inject(method = "tick", at = @At("TAIL"))
   private static void iceCaves$playlistTick(class_1937 level, class_2338 pos, class_2680 state, class_2619 jukebox, CallbackInfo ci) {
      IceCavesMixinLogic.tickPlaylist(level, pos, jukebox);
   }

   @Inject(method = "saveAdditional", at = @At("TAIL"))
   private void iceCaves$savePlaylist(class_2487 tag, class_7874 registries, CallbackInfo ci) {
      IceCavesMixinLogic.writePlaylistState(tag, this);
   }

   @Inject(method = "loadAdditional", at = @At("TAIL"))
   private void iceCaves$loadPlaylist(class_2487 tag, class_7874 registries, CallbackInfo ci) {
      IceCavesMixinLogic.readPlaylistState(tag, this);
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
