package org.zerw.net.icecaves.neoforge.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.zerw.net.icecaves.block.JukeboxChestPlaylist_forge;

@Mixin(JukeboxBlock.class)
public class JukeboxBlockPlaylistMixin {
   @Inject(method = "getTicker", at = @At("HEAD"), cancellable = true)
   private <T extends BlockEntity> void iceCaves$alwaysTick(
      Level level, BlockState state, BlockEntityType<T> type, CallbackInfoReturnable<BlockEntityTicker<T>> cir
   ) {
      if (type == BlockEntityType.JUKEBOX) {
         cir.setReturnValue(JukeboxChestPlaylist_forge.vanillaTicker());
      }
   }

   @Inject(method = "useWithoutItem", at = @At("HEAD"), cancellable = true)
   private void iceCaves$skipToNext(
      BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir
   ) {
      if (JukeboxChestPlaylist_forge.skip(level, pos)) {
         cir.setReturnValue(InteractionResult.SUCCESS);
      }
   }
}
