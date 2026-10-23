package org.zerw.net.icecaves.mixin;

import org.zerw.net.icecaves.hooks.IceCavesMixinLogic;

import net.minecraft.class_1936;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_9794;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.zerw.net.icecaves.block.JukeboxChestPlaylist;

@Mixin(class_9794.class)
public class JukeboxSongPlayerMixin {
   @Inject(method = "spawnMusicParticles", at = @At("HEAD"), cancellable = true)
   private static void iceCaves$sideNotes(class_1936 level, class_2338 pos, CallbackInfo ci) {
      if (IceCavesMixinLogic.spawnPlaylistNotes(level, pos)) {
         ci.cancel();
      }
   }
}
