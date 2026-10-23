package org.zerw.net.icecaves.mixin;

import net.minecraft.class_1299;
import net.minecraft.class_1308;
import net.minecraft.class_1317;
import net.minecraft.class_9168;
import net.minecraft.class_1317.class_4306;
import net.minecraft.class_2902.class_2903;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(class_1317.class)
public interface SpawnPlacementsInvoker {
   @Invoker("register")
   static <T extends class_1308> void invokeRegister(class_1299<T> entityType, class_9168 placementType, class_2903 heightmapType, class_4306<T> predicate) {
      throw new AssertionError();
   }
}
