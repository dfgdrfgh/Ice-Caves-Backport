package org.zerw.net.icecaves.neoforge.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.zerw.net.icecaves.registry.ModSounds_forge;

@Mixin(BlockBehaviour.class)
public class IceBlockMixin {
   @Inject(method = "getSoundType", at = @At("HEAD"), cancellable = true)
   private void iceCaves$useCustomIceSounds(BlockState state, CallbackInfoReturnable<SoundType> cir) {
      Block block = state.getBlock();
      if (block == Blocks.ICE || block == Blocks.PACKED_ICE || block == Blocks.BLUE_ICE) {
         cir.setReturnValue(ModSounds_forge.ICE);
      }
   }
}
