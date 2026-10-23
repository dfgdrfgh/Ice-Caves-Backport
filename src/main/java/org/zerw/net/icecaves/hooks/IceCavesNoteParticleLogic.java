package org.zerw.net.icecaves.hooks;

import net.minecraft.class_3940;
import net.minecraft.class_703;

/** Client-only note particle size adjustment. */
public final class IceCavesNoteParticleLogic {
   private IceCavesNoteParticleLogic() {}

   public static void updateSize(class_703 particle, double velocity) {
      if (velocity > 0.0 && particle instanceof class_3940 note) {
         note.method_3087((float)velocity);
      }
   }
}
