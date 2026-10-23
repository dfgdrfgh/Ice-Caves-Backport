package org.zerw.net.icecaves.mixin;

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
      JukeboxChestPlaylist.tick(level, pos, jukebox, (JukeboxChestPlaylist.Access)jukebox);
   }

   @Inject(method = "saveAdditional", at = @At("TAIL"))
   private void iceCaves$savePlaylist(class_2487 tag, class_7874 registries, CallbackInfo ci) {
      tag.method_10569("IceCavesSource", this.iceCaves$sourceSlot);
      tag.method_10569("IceCavesNext", this.iceCaves$nextStart);
      tag.method_10569("IceCavesCooldown", this.iceCaves$cooldown);
      tag.method_10556("IceCavesHolding", this.iceCaves$holding);
   }

   @Inject(method = "loadAdditional", at = @At("TAIL"))
   private void iceCaves$loadPlaylist(class_2487 tag, class_7874 registries, CallbackInfo ci) {
      this.iceCaves$sourceSlot = tag.method_10545("IceCavesSource") ? tag.method_10550("IceCavesSource") : -1;
      this.iceCaves$nextStart = tag.method_10550("IceCavesNext");
      this.iceCaves$cooldown = tag.method_10550("IceCavesCooldown");
      this.iceCaves$holding = tag.method_10577("IceCavesHolding");
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
