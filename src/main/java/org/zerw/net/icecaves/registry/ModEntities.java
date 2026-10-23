package org.zerw.net.icecaves.registry;

import net.minecraft.class_1299;
import net.minecraft.class_1311;
import net.minecraft.class_2960;
import net.minecraft.class_5321;
import net.minecraft.class_7924;
import net.minecraft.class_1299.class_1300;
import org.zerw.net.icecaves.entity.FrostbiteZombie;
import org.zerw.net.icecaves.entity.IceBall;

public final class ModEntities {
   public static final class_2960 FROSTBITE_ID = class_2960.method_60655("ice_caves", "frostbite");
   public static final class_2960 ICE_BALL_ID = class_2960.method_60655("ice_caves", "ice_ball");
   public static final class_5321<class_1299<?>> FROSTBITE_KEY = class_5321.method_29179(class_7924.field_41266, FROSTBITE_ID);
   public static final class_5321<class_1299<?>> ICE_BALL_KEY = class_5321.method_29179(class_7924.field_41266, ICE_BALL_ID);

   private ModEntities() {
   }

   public static class_1299<FrostbiteZombie> createFrostbite() {
      return class_1300.method_5903(FrostbiteZombie::new, class_1311.field_6302)
         .method_17687(0.6F, 1.95F)
         .method_55687(1.74F)
         .method_55689(new float[]{2.0125F})
         .method_55691(-0.7F)
         .method_27299(8)
         .method_5905(FROSTBITE_ID.toString());
   }

   public static class_1299<IceBall> createIceBall() {
      return class_1300.method_5903(IceBall::new, class_1311.field_17715)
         .method_17687(0.25F, 0.25F)
         .method_27299(4)
         .method_27300(10)
         .method_5905(ICE_BALL_ID.toString());
   }
}
