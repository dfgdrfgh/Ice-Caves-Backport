package org.zerw.net.icecaves.neoforge.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.JukeboxSongPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.zerw.net.icecaves.block.JukeboxChestPlaylist_forge;

@Mixin(JukeboxSongPlayer.class)
public class JukeboxSongPlayerMixin {
   @Inject(method = "spawnMusicParticles", at = @At("HEAD"), cancellable = true)
   private static void iceCaves$sideNotes(LevelAccessor level, BlockPos pos, CallbackInfo ci) {
      if (level instanceof Level real && JukeboxChestPlaylist_forge.spawnSideNotes(real, pos)) {
         ci.cancel();
      }
   }
}
