package org.zerw.net.icecaves.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;

public final class ModSounds_forge {
   public static final ResourceLocation ICE_BALL_HIT_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "entity.ice_ball.hit");
   public static final ResourceLocation ICE_BREAK_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "block.ice.break");
   public static final ResourceLocation ICE_STEP_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "block.ice.step");
   public static final ResourceLocation ICE_PLACE_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "block.ice.place");
   public static final ResourceLocation ICE_HIT_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "block.ice.hit");
   public static final ResourceLocation ICE_FALL_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "block.ice.fall");
   public static final ResourceLocation ICE_DOOR_OPEN_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "block.ice_door.open");
   public static final ResourceLocation ICE_DOOR_CLOSE_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "block.ice_door.close");
   public static final ResourceLocation ICE_CRYSTAL_BREAK_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "block.ice_crystal.break");
   public static final ResourceLocation ICE_CRYSTAL_STEP_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "block.ice_crystal.step");
   public static final ResourceLocation ICE_CRYSTAL_PLACE_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "block.ice_crystal.place");
   public static final ResourceLocation ICE_CRYSTAL_HIT_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "block.ice_crystal.hit");
   public static final ResourceLocation ICE_CRYSTAL_FALL_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "block.ice_crystal.fall");
   public static final ResourceLocation FROSTBITE_AMBIENT_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "entity.frostbite.ambient");
   public static final ResourceLocation FROSTBITE_HURT_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "entity.frostbite.hurt");
   public static final ResourceLocation FROSTBITE_DEATH_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "entity.frostbite.death");
   public static final ResourceLocation FROSTBITE_STEP_ID = ResourceLocation.fromNamespaceAndPath("ice_caves", "entity.frostbite.step");
   public static final ResourceLocation FROSTBITE_CONVERTED_TO_ZOMBIE_ID = ResourceLocation.fromNamespaceAndPath(
      "ice_caves", "entity.frostbite.converted_to_zombie"
   );
   public static final SoundEvent ICE_BREAK = SoundEvent.createVariableRangeEvent(ICE_BREAK_ID);
   public static final SoundEvent ICE_STEP = SoundEvent.createVariableRangeEvent(ICE_STEP_ID);
   public static final SoundEvent ICE_PLACE = SoundEvent.createVariableRangeEvent(ICE_PLACE_ID);
   public static final SoundEvent ICE_HIT = SoundEvent.createVariableRangeEvent(ICE_HIT_ID);
   public static final SoundEvent ICE_FALL = SoundEvent.createVariableRangeEvent(ICE_FALL_ID);
   public static final SoundEvent ICE_DOOR_OPEN = SoundEvent.createVariableRangeEvent(ICE_DOOR_OPEN_ID);
   public static final SoundEvent ICE_DOOR_CLOSE = SoundEvent.createVariableRangeEvent(ICE_DOOR_CLOSE_ID);
   public static final SoundEvent ICE_CRYSTAL_BREAK = SoundEvent.createVariableRangeEvent(ICE_CRYSTAL_BREAK_ID);
   public static final SoundEvent ICE_CRYSTAL_STEP = SoundEvent.createVariableRangeEvent(ICE_CRYSTAL_STEP_ID);
   public static final SoundEvent ICE_CRYSTAL_PLACE = SoundEvent.createVariableRangeEvent(ICE_CRYSTAL_PLACE_ID);
   public static final SoundEvent ICE_CRYSTAL_HIT = SoundEvent.createVariableRangeEvent(ICE_CRYSTAL_HIT_ID);
   public static final SoundEvent ICE_CRYSTAL_FALL = SoundEvent.createVariableRangeEvent(ICE_CRYSTAL_FALL_ID);
   public static final SoundEvent FROSTBITE_AMBIENT = SoundEvent.createVariableRangeEvent(FROSTBITE_AMBIENT_ID);
   public static final SoundEvent FROSTBITE_HURT = SoundEvent.createVariableRangeEvent(FROSTBITE_HURT_ID);
   public static final SoundEvent FROSTBITE_DEATH = SoundEvent.createVariableRangeEvent(FROSTBITE_DEATH_ID);
   public static final SoundEvent FROSTBITE_STEP = SoundEvent.createVariableRangeEvent(FROSTBITE_STEP_ID);
   public static final SoundEvent FROSTBITE_CONVERTED_TO_ZOMBIE = SoundEvent.createVariableRangeEvent(FROSTBITE_CONVERTED_TO_ZOMBIE_ID);
   public static final SoundType ICE = new SoundType(1.0F, 1.0F, ICE_BREAK, ICE_STEP, ICE_PLACE, ICE_HIT, ICE_FALL);
   public static final SoundType ICE_CRYSTAL = new SoundType(
      1.0F, 1.0F, ICE_CRYSTAL_BREAK, ICE_CRYSTAL_STEP, ICE_CRYSTAL_PLACE, ICE_CRYSTAL_HIT, ICE_CRYSTAL_FALL
   );

   private ModSounds_forge() {
   }

   public static SoundEvent createIceBallHit() {
      return SoundEvent.createVariableRangeEvent(ICE_BALL_HIT_ID);
   }

   public static SoundEvent iceBallHit() {
      return (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ICE_BALL_HIT_ID);
   }

   public static SoundEvent createIceBreak() {
      return ICE_BREAK;
   }

   public static SoundEvent createIceStep() {
      return ICE_STEP;
   }

   public static SoundEvent createIcePlace() {
      return ICE_PLACE;
   }

   public static SoundEvent createIceHit() {
      return ICE_HIT;
   }

   public static SoundEvent createIceFall() {
      return ICE_FALL;
   }

   public static SoundEvent createIceDoorOpen() {
      return ICE_DOOR_OPEN;
   }

   public static SoundEvent createIceDoorClose() {
      return ICE_DOOR_CLOSE;
   }

   public static SoundEvent createIceCrystalBreak() {
      return ICE_CRYSTAL_BREAK;
   }

   public static SoundEvent createIceCrystalStep() {
      return ICE_CRYSTAL_STEP;
   }

   public static SoundEvent createIceCrystalPlace() {
      return ICE_CRYSTAL_PLACE;
   }

   public static SoundEvent createIceCrystalHit() {
      return ICE_CRYSTAL_HIT;
   }

   public static SoundEvent createIceCrystalFall() {
      return ICE_CRYSTAL_FALL;
   }

   public static SoundEvent createFrostbiteAmbient() {
      return FROSTBITE_AMBIENT;
   }

   public static SoundEvent createFrostbiteHurt() {
      return FROSTBITE_HURT;
   }

   public static SoundEvent createFrostbiteDeath() {
      return FROSTBITE_DEATH;
   }

   public static SoundEvent createFrostbiteStep() {
      return FROSTBITE_STEP;
   }

   public static SoundEvent createFrostbiteConvertedToZombie() {
      return FROSTBITE_CONVERTED_TO_ZOMBIE;
   }
}
