package org.zerw.net.icecaves.registry;

import net.minecraft.class_2498;
import net.minecraft.class_2960;
import net.minecraft.class_3414;
import net.minecraft.class_7923;

public final class ModSounds {
   public static final class_2960 ICE_BALL_HIT_ID = class_2960.method_60655("ice_caves", "entity.ice_ball.hit");
   public static final class_2960 ICE_BREAK_ID = class_2960.method_60655("ice_caves", "block.ice.break");
   public static final class_2960 ICE_STEP_ID = class_2960.method_60655("ice_caves", "block.ice.step");
   public static final class_2960 ICE_PLACE_ID = class_2960.method_60655("ice_caves", "block.ice.place");
   public static final class_2960 ICE_HIT_ID = class_2960.method_60655("ice_caves", "block.ice.hit");
   public static final class_2960 ICE_FALL_ID = class_2960.method_60655("ice_caves", "block.ice.fall");
   public static final class_2960 ICE_DOOR_OPEN_ID = class_2960.method_60655("ice_caves", "block.ice_door.open");
   public static final class_2960 ICE_DOOR_CLOSE_ID = class_2960.method_60655("ice_caves", "block.ice_door.close");
   public static final class_2960 ICE_CRYSTAL_BREAK_ID = class_2960.method_60655("ice_caves", "block.ice_crystal.break");
   public static final class_2960 ICE_CRYSTAL_STEP_ID = class_2960.method_60655("ice_caves", "block.ice_crystal.step");
   public static final class_2960 ICE_CRYSTAL_PLACE_ID = class_2960.method_60655("ice_caves", "block.ice_crystal.place");
   public static final class_2960 ICE_CRYSTAL_HIT_ID = class_2960.method_60655("ice_caves", "block.ice_crystal.hit");
   public static final class_2960 ICE_CRYSTAL_FALL_ID = class_2960.method_60655("ice_caves", "block.ice_crystal.fall");
   public static final class_2960 FROSTBITE_AMBIENT_ID = class_2960.method_60655("ice_caves", "entity.frostbite.ambient");
   public static final class_2960 FROSTBITE_HURT_ID = class_2960.method_60655("ice_caves", "entity.frostbite.hurt");
   public static final class_2960 FROSTBITE_DEATH_ID = class_2960.method_60655("ice_caves", "entity.frostbite.death");
   public static final class_2960 FROSTBITE_STEP_ID = class_2960.method_60655("ice_caves", "entity.frostbite.step");
   public static final class_2960 FROSTBITE_CONVERTED_TO_ZOMBIE_ID = class_2960.method_60655("ice_caves", "entity.frostbite.converted_to_zombie");
   public static final class_3414 ICE_BREAK = class_3414.method_47908(ICE_BREAK_ID);
   public static final class_3414 ICE_STEP = class_3414.method_47908(ICE_STEP_ID);
   public static final class_3414 ICE_PLACE = class_3414.method_47908(ICE_PLACE_ID);
   public static final class_3414 ICE_HIT = class_3414.method_47908(ICE_HIT_ID);
   public static final class_3414 ICE_FALL = class_3414.method_47908(ICE_FALL_ID);
   public static final class_3414 ICE_DOOR_OPEN = class_3414.method_47908(ICE_DOOR_OPEN_ID);
   public static final class_3414 ICE_DOOR_CLOSE = class_3414.method_47908(ICE_DOOR_CLOSE_ID);
   public static final class_3414 ICE_CRYSTAL_BREAK = class_3414.method_47908(ICE_CRYSTAL_BREAK_ID);
   public static final class_3414 ICE_CRYSTAL_STEP = class_3414.method_47908(ICE_CRYSTAL_STEP_ID);
   public static final class_3414 ICE_CRYSTAL_PLACE = class_3414.method_47908(ICE_CRYSTAL_PLACE_ID);
   public static final class_3414 ICE_CRYSTAL_HIT = class_3414.method_47908(ICE_CRYSTAL_HIT_ID);
   public static final class_3414 ICE_CRYSTAL_FALL = class_3414.method_47908(ICE_CRYSTAL_FALL_ID);
   public static final class_3414 FROSTBITE_AMBIENT = class_3414.method_47908(FROSTBITE_AMBIENT_ID);
   public static final class_3414 FROSTBITE_HURT = class_3414.method_47908(FROSTBITE_HURT_ID);
   public static final class_3414 FROSTBITE_DEATH = class_3414.method_47908(FROSTBITE_DEATH_ID);
   public static final class_3414 FROSTBITE_STEP = class_3414.method_47908(FROSTBITE_STEP_ID);
   public static final class_3414 FROSTBITE_CONVERTED_TO_ZOMBIE = class_3414.method_47908(FROSTBITE_CONVERTED_TO_ZOMBIE_ID);
   public static final class_2498 ICE = new class_2498(1.0F, 1.0F, ICE_BREAK, ICE_STEP, ICE_PLACE, ICE_HIT, ICE_FALL);
   public static final class_2498 ICE_CRYSTAL = new class_2498(
      1.0F, 1.0F, ICE_CRYSTAL_BREAK, ICE_CRYSTAL_STEP, ICE_CRYSTAL_PLACE, ICE_CRYSTAL_HIT, ICE_CRYSTAL_FALL
   );

   private ModSounds() {
   }

   public static class_3414 createIceBallHit() {
      return class_3414.method_47908(ICE_BALL_HIT_ID);
   }

   public static class_3414 iceBallHit() {
      return (class_3414)class_7923.field_41172.method_10223(ICE_BALL_HIT_ID);
   }

   public static class_3414 createIceBreak() {
      return ICE_BREAK;
   }

   public static class_3414 createIceStep() {
      return ICE_STEP;
   }

   public static class_3414 createIcePlace() {
      return ICE_PLACE;
   }

   public static class_3414 createIceHit() {
      return ICE_HIT;
   }

   public static class_3414 createIceFall() {
      return ICE_FALL;
   }

   public static class_3414 createIceDoorOpen() {
      return ICE_DOOR_OPEN;
   }

   public static class_3414 createIceDoorClose() {
      return ICE_DOOR_CLOSE;
   }

   public static class_3414 createIceCrystalBreak() {
      return ICE_CRYSTAL_BREAK;
   }

   public static class_3414 createIceCrystalStep() {
      return ICE_CRYSTAL_STEP;
   }

   public static class_3414 createIceCrystalPlace() {
      return ICE_CRYSTAL_PLACE;
   }

   public static class_3414 createIceCrystalHit() {
      return ICE_CRYSTAL_HIT;
   }

   public static class_3414 createIceCrystalFall() {
      return ICE_CRYSTAL_FALL;
   }

   public static class_3414 createFrostbiteAmbient() {
      return FROSTBITE_AMBIENT;
   }

   public static class_3414 createFrostbiteHurt() {
      return FROSTBITE_HURT;
   }

   public static class_3414 createFrostbiteDeath() {
      return FROSTBITE_DEATH;
   }

   public static class_3414 createFrostbiteStep() {
      return FROSTBITE_STEP;
   }

   public static class_3414 createFrostbiteConvertedToZombie() {
      return FROSTBITE_CONVERTED_TO_ZOMBIE;
   }
}
