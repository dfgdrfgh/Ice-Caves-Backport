package org.zerw.net.icecaves.item;

import java.util.function.Supplier;
import net.minecraft.class_1268;
import net.minecraft.class_1271;
import net.minecraft.class_1299;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3468;
import net.minecraft.class_1792.class_1793;
import org.zerw.net.icecaves.entity.IceBall;

public class IceBallItem extends class_1792 {
   private final Supplier<class_1299<? extends IceBall>> entityType;

   public IceBallItem(Supplier<class_1299<? extends IceBall>> entityType, class_1793 properties) {
      super(properties);
      this.entityType = entityType;
   }

   public class_1271<class_1799> method_7836(class_1937 level, class_1657 player, class_1268 hand) {
      class_1799 itemStack = player.method_5998(hand);
      level.method_43128(
         null,
         player.method_23317(),
         player.method_23318(),
         player.method_23321(),
         class_3417.field_14873,
         class_3419.field_15254,
         0.5F,
         0.4F / (level.method_8409().method_43057() * 0.4F + 0.8F)
      );
      if (!level.method_8608()) {
         IceBall projectile = new IceBall(this.entityType.get(), player, level);
         projectile.method_16940(itemStack);
         projectile.method_24919(player, player.method_36455(), player.method_36454(), 0.0F, 1.5F, 1.0F);
         level.method_8649(projectile);
      }

      player.method_7259(class_3468.field_15372.method_14956(this));
      if (!player.method_31549().field_7477) {
         itemStack.method_7934(1);
      }

      return class_1271.method_29237(itemStack, level.method_8608());
   }
}
