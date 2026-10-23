package org.zerw.net.icecaves.item;

import java.util.function.Supplier;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.Level;
import org.zerw.net.icecaves.entity.IceBall_forge;

public class IceBallItem_forge extends Item {
   private final Supplier<EntityType<? extends IceBall_forge>> entityType;

   public IceBallItem_forge(Supplier<EntityType<? extends IceBall_forge>> entityType, Properties properties) {
      super(properties);
      this.entityType = entityType;
   }

   public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
      ItemStack itemStack = player.getItemInHand(hand);
      level.playSound(
         null,
         player.getX(),
         player.getY(),
         player.getZ(),
         SoundEvents.SNOWBALL_THROW,
         SoundSource.NEUTRAL,
         0.5F,
         0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
      );
      if (!level.isClientSide()) {
         IceBall_forge projectile = new IceBall_forge(this.entityType.get(), player, level);
         projectile.setItem(itemStack);
         projectile.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
         level.addFreshEntity(projectile);
      }

      player.awardStat(Stats.ITEM_USED.get(this));
      if (!player.getAbilities().instabuild) {
         itemStack.shrink(1);
      }

      return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
   }
}
