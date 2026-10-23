package org.zerw.net.icecaves.mixin;

import net.minecraft.class_1269;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2387;
import net.minecraft.class_2586;
import net.minecraft.class_2591;
import net.minecraft.class_2680;
import net.minecraft.class_3965;
import net.minecraft.class_5558;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.zerw.net.icecaves.block.JukeboxChestPlaylist;

@Mixin(class_2387.class)
public class JukeboxBlockPlaylistMixin {
   @Inject(method = "getTicker", at = @At("HEAD"), cancellable = true)
   private <T extends class_2586> void iceCaves$alwaysTick(class_1937 level, class_2680 state, class_2591<T> type, CallbackInfoReturnable<class_5558<T>> cir) {
      if (type == class_2591.field_11907) {
         cir.setReturnValue(JukeboxChestPlaylist.vanillaTicker());
      }
   }

   @Inject(method = "useWithoutItem", at = @At("HEAD"), cancellable = true)
   private void iceCaves$skipToNext(
      class_2680 state, class_1937 level, class_2338 pos, class_1657 player, class_3965 hit, CallbackInfoReturnable<class_1269> cir
   ) {
      if (JukeboxChestPlaylist.skip(level, pos)) {
         cir.setReturnValue(class_1269.field_5812);
      }
   }
}
