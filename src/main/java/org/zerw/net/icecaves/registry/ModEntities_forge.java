package org.zerw.net.icecaves.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType.Builder;
import org.zerw.net.icecaves.entity.FrostbiteZombie_forge;
import org.zerw.net.icecaves.entity.IceBall_forge;

public final class ModEntities_forge {
   public static final ResourceLocation FROSTBITE_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "frostbite");
   public static final ResourceLocation ICE_BALL_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "ice_ball");
   public static final ResourceKey<EntityType<?>> FROSTBITE_KEY = ResourceKey.create(Registries.ENTITY_TYPE, FROSTBITE_ID);
   public static final ResourceKey<EntityType<?>> ICE_BALL_KEY = ResourceKey.create(Registries.ENTITY_TYPE, ICE_BALL_ID);

   private ModEntities_forge() {
   }

   public static EntityType<FrostbiteZombie_forge> createFrostbite() {
      return Builder.of(FrostbiteZombie_forge::new, MobCategory.MONSTER)
         .sized(0.6F, 1.95F)
         .eyeHeight(1.74F)
         .passengerAttachments(new float[]{2.0125F})
         .ridingOffset(-0.7F)
         .clientTrackingRange(8)
         .build(FROSTBITE_ID.toString());
   }

   public static EntityType<IceBall_forge> createIceBall() {
      return Builder.of(IceBall_forge::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build(ICE_BALL_ID.toString());
   }
}
