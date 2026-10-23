package org.zerw.net.icecaves.worldgen;

import com.mojang.serialization.Codec;
import java.util.function.Consumer;
import net.minecraft.class_2246;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2680;
import net.minecraft.class_3031;
import net.minecraft.class_3111;
import net.minecraft.class_3481;
import net.minecraft.class_5281;
import net.minecraft.class_5542;
import net.minecraft.class_5689;
import net.minecraft.class_5691;
import net.minecraft.class_5819;
import net.minecraft.class_5821;
import net.minecraft.class_7923;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_2350.class_2353;
import net.minecraft.class_2902.class_2903;
import org.zerw.net.icecaves.block.BrittleIceBlock;
import org.zerw.net.icecaves.registry.ModBlocks;

public final class IceCaveFeature extends class_3031<class_3111> {
   private static final int RADIUS = 8;
   private static final float CEILING_ICICLE_CHANCE = 0.22F;
   private static final float FLOOR_SNOW_CHANCE = 0.34F;
   private static final float FLOOR_CRYSTAL_CHANCE = 0.06F;
   private static final float ICE_PILLAR_CHANCE = 0.04F;
   private static final float BRITTLE_PATCH_CHANCE = 0.008F;
   private static final int TRAP_MIN_PIT = 6;
   private static final int TRAP_MAX_PIT = 10;
   private static final int TRAP_MAX_SPAN = 8;

   public IceCaveFeature(Codec<class_3111> codec) {
      super(codec);
   }

   public boolean method_13151(class_5821<class_3111> context) {
      class_5281 level = context.method_33652();
      class_2338 origin = context.method_33655();
      class_5819 random = context.method_33654();
      int surfaceY = level.method_8624(class_2903.field_13195, origin.method_10263(), origin.method_10260());
      if (origin.method_10264() >= surfaceY - 3) {
         return false;
      }

      class_2680 originState = level.method_8320(origin);
      if (!isCaveSpace(originState)) {
         return false;
      }

      class_2248 icicle = (class_2248)class_7923.field_41175.method_10223(ModBlocks.ICICLE_ID);
      class_2248 crystal = (class_2248)class_7923.field_41175.method_10223(ModBlocks.ICE_CRYSTAL_ID);
      if (icicle != class_2246.field_10124 && crystal != class_2246.field_10124) {
         boolean placed = false;
         class_2339 cursor = new class_2339();
         class_2339 neighborPos = new class_2339();

         for (int dx = -8; dx <= 8; dx++) {
            for (int dy = -8; dy <= 8; dy++) {
               for (int dz = -8; dz <= 8; dz++) {
                  if (dx * dx + dy * dy + dz * dz <= 64) {
                     cursor.method_10103(origin.method_10263() + dx, origin.method_10264() + dy, origin.method_10260() + dz);
                     if (level.method_37368(cursor)) {
                        class_2680 state = level.method_8320(cursor);
                        if (isCaveSpace(state)) {
                           boolean touchedWall = false;

                           for (class_2350 direction : class_2350.values()) {
                              neighborPos.method_25505(cursor, direction);
                              if (level.method_37368(neighborPos)) {
                                 class_2680 neighbor = level.method_8320(neighborPos);
                                 if (isReplaceableCaveBlock(neighbor)) {
                                    level.method_8652(neighborPos, pickIceCaveBlock(random), 2);
                                    placed = true;
                                    touchedWall = true;
                                    if (random.method_43057() < 0.55F) {
                                       class_2339 deeper = neighborPos.method_25503().method_10098(direction);
                                       if (level.method_37368(deeper) && isReplaceableCaveBlock(level.method_8320(deeper))) {
                                          level.method_8652(deeper, pickIceCaveBlock(random), 2);
                                       }
                                    }
                                 }
                              }
                           }

                           class_2338 below = cursor.method_10074();
                           class_2680 belowState = level.method_8320(below);
                           boolean solidFloor = isIceCaveMaterial(belowState) || belowState.method_26206(level, below, class_2350.field_11036);
                           class_2338 above = cursor.method_10084();
                           class_2680 aboveState = level.method_8320(above);
                           boolean solidCeiling = isIceCaveMaterial(aboveState) || aboveState.method_26206(level, above, class_2350.field_11033);
                           if (touchedWall || solidFloor || solidCeiling) {
                              if (solidFloor && isCaveSpace(level.method_8320(cursor)) && !(belowState.method_26204() instanceof BrittleIceBlock)) {
                                 if (random.method_43057() < 0.04F) {
                                    if (placeIcePillar(level, cursor.method_10062(), random)) {
                                       placed = true;
                                    }
                                 } else if (random.method_43057() < 0.34F) {
                                    level.method_8652(cursor, class_2246.field_10477.method_9564(), 2);
                                    placed = true;
                                 } else if (random.method_43057() < 0.06F && placeCrystal(level, cursor.method_10062(), class_2350.field_11036, crystal)) {
                                    placed = true;
                                 }
                              }

                              if (solidCeiling && isCaveSpace(level.method_8320(cursor)) && random.method_43057() < 0.22F) {
                                 if (!aboveState.method_26164(class_3481.field_15467)
                                    && !aboveState.method_27852(class_2246.field_10225)
                                    && !aboveState.method_27852(class_2246.field_10295)
                                    && !aboveState.method_27852(class_2246.field_10384)
                                    && (
                                       isReplaceableCaveBlock(aboveState)
                                          || isIceCaveMaterial(aboveState)
                                          || aboveState.method_27852(class_2246.field_27114)
                                          || aboveState.method_27852(class_2246.field_10340)
                                    )) {
                                    level.method_8652(above, class_2246.field_10225.method_9564(), 2);
                                 }

                                 int length = sampleIcicleLength(random);
                                 if (placeIcicleColumn(level, cursor.method_10062(), class_2350.field_11033, length, icicle)) {
                                    placed = true;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (random.method_43057() < 0.008F) {
            class_2248 brittle = (class_2248)class_7923.field_41175.method_10223(ModBlocks.BRITTLE_ICE_ID);
            if (brittle != class_2246.field_10124 && placeBrittlePatch(level, origin, surfaceY, random, icicle, brittle)) {
               placed = true;
            }
         }

         return placed;
      } else {
         return false;
      }
   }

   private static boolean placeBrittlePatch(class_5281 level, class_2338 origin, int surfaceY, class_5819 random, class_2248 icicle, class_2248 brittle) {
      int lidY = findCaveFloor(level, origin);
      if (lidY != Integer.MIN_VALUE && lidY < surfaceY - 2) {
         int fall = 4 + random.method_43048(3);
         int spikeRoom = 2 + random.method_43048(3);
         int pit = fall + spikeRoom;
         if (pit >= 6 && pit <= 10 && lidY - pit > level.method_31607() + 1) {
            int spanX = 5 + random.method_43048(4);
            int spanZ = 5 + random.method_43048(4);
            boolean[][] mask = buildTrapMask(random, spanX, spanZ);
            int originX = origin.method_10263() - spanX / 2;
            int originZ = origin.method_10260() - spanZ / 2;
            if (!keepCarveableCells(level, mask, originX, originZ, lidY, pit)) {
               return false;
            }

            placeTrapColumns(level, originX, originZ, lidY, pit, mask, random, icicle, brittle);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private static boolean[][] buildTrapMask(class_5819 random, int spanX, int spanZ) {
      boolean[][] mask = new boolean[spanX][spanZ];
      double cx = (spanX - 1) / 2.0 + (random.method_43058() - 0.5);
      double cz = (spanZ - 1) / 2.0 + (random.method_43058() - 0.5);
      double rx = Math.max(1.4, spanX * (0.42 + random.method_43058() * 0.12));
      double rz = Math.max(1.4, spanZ * (0.42 + random.method_43058() * 0.12));

      for (int ix = 0; ix < spanX; ix++) {
         for (int iz = 0; iz < spanZ; iz++) {
            double nx = (ix - cx) / rx;
            double nz = (iz - cz) / rz;
            double dist = nx * nx + nz * nz;
            if (!(dist > 1.0) && (!(dist > 0.28) || !(random.method_43057() < 0.42F))) {
               mask[ix][iz] = true;
            }
         }
      }

      fillMaskToMinimum(mask, cx, cz, 9);
      breakSolidRectangle(mask, random);
      return mask;
   }

   private static void fillMaskToMinimum(boolean[][] mask, double cx, double cz, int minimum) {
      int spanX = mask.length;
      int spanZ = mask[0].length;

      while (countMask(mask) < minimum) {
         int bestX = -1;
         int bestZ = -1;
         double best = Double.MAX_VALUE;

         for (int ix = 0; ix < spanX; ix++) {
            for (int iz = 0; iz < spanZ; iz++) {
               if (!mask[ix][iz]) {
                  double dist = (ix - cx) * (ix - cx) + (iz - cz) * (iz - cz);
                  if (dist < best) {
                     best = dist;
                     bestX = ix;
                     bestZ = iz;
                  }
               }
            }
         }

         if (bestX < 0) {
            return;
         }

         mask[bestX][bestZ] = true;
      }
   }

   private static void breakSolidRectangle(boolean[][] mask, class_5819 random) {
      int spanX = mask.length;
      int spanZ = mask[0].length;
      int minX = spanX;
      int maxX = -1;
      int minZ = spanZ;
      int maxZ = -1;

      for (int ix = 0; ix < spanX; ix++) {
         for (int iz = 0; iz < spanZ; iz++) {
            if (mask[ix][iz]) {
               minX = Math.min(minX, ix);
               maxX = Math.max(maxX, ix);
               minZ = Math.min(minZ, iz);
               maxZ = Math.max(maxZ, iz);
            }
         }
      }

      if (maxX >= 0) {
         boolean solid = true;

         for (int ix = minX; ix <= maxX && solid; ix++) {
            for (int iz = minZ; iz <= maxZ; iz++) {
               if (!mask[ix][iz]) {
                  solid = false;
                  break;
               }
            }
         }

         if (solid) {
            if (maxX + 1 < spanX) {
               mask[maxX + 1][minZ] = true;
            } else if (minX > 0) {
               mask[minX - 1][maxZ] = true;
            }

            mask[minX][minZ] = false;
            if (countMask(mask) > 9) {
               mask[maxX][maxZ] = false;
            }
         }
      }
   }

   private static boolean[][] copyMask(boolean[][] mask) {
      boolean[][] copy = new boolean[mask.length][mask[0].length];

      for (int ix = 0; ix < mask.length; ix++) {
         System.arraycopy(mask[ix], 0, copy[ix], 0, mask[ix].length);
      }

      return copy;
   }

   private static int countMask(boolean[][] mask) {
      int count = 0;

      for (boolean[] row : mask) {
         for (boolean cell : row) {
            if (cell) {
               count++;
            }
         }
      }

      return count;
   }

   private static boolean keepCarveableCells(class_5281 level, boolean[][] mask, int originX, int originZ, int lidY, int pit) {
      int spanX = mask.length;
      int spanZ = mask[0].length;

      for (int ix = 0; ix < spanX; ix++) {
         for (int iz = 0; iz < spanZ; iz++) {
            if (mask[ix][iz] && !columnCanBecomeTrap(level, originX + ix, lidY, originZ + iz, pit)) {
               mask[ix][iz] = false;
            }
         }
      }

      if (countMask(mask) < 9) {
         return false;
      }

      int minX = spanX;
      int maxX = -1;
      int minZ = spanZ;
      int maxZ = -1;

      for (int ix = 0; ix < spanX; ix++) {
         for (int iz = 0; iz < spanZ; iz++) {
            if (mask[ix][iz]) {
               minX = Math.min(minX, ix);
               maxX = Math.max(maxX, ix);
               minZ = Math.min(minZ, iz);
               maxZ = Math.max(maxZ, iz);
            }
         }
      }

      return maxX - minX >= 2 && maxZ - minZ >= 2;
   }

   private static void placeTrapColumns(
      class_5281 level, int originX, int originZ, int lidY, int pit, boolean[][] mask, class_5819 random, class_2248 icicle, class_2248 brittle
   ) {
      int maxSpike = Math.max(3, pit - 3);
      class_2339 cursor = new class_2339();
      int spanX = mask.length;
      int spanZ = mask[0].length;

      for (int ix = 0; ix < spanX; ix++) {
         for (int iz = 0; iz < spanZ; iz++) {
            if (mask[ix][iz]) {
               int x = originX + ix;
               int z = originZ + iz;
               cursor.method_10103(x, lidY + 1, z);
               if (level.method_8320(cursor).method_27852(class_2246.field_10477)) {
                  level.method_8652(cursor, class_2246.field_10543.method_9564(), 2);
               }

               cursor.method_10103(x, lidY, z);
               level.method_8652(cursor, (class_2680)brittle.method_9564().method_11657(BrittleIceBlock.STAGE, 1 + random.method_43048(3)), 2);
               int floorY = lidY - pit;

               for (int y = lidY - 1; y > floorY; y--) {
                  cursor.method_10103(x, y, z);
                  level.method_8652(cursor, class_2246.field_10543.method_9564(), 2);
               }

               cursor.method_10103(x, floorY, z);
               level.method_8652(cursor, class_2246.field_10225.method_9564(), 2);
               int length = sampleTrapIcicleLength(random, maxSpike);
               placeIcicleColumn(level, new class_2338(x, floorY + 1, z), class_2350.field_11036, length, icicle);
            }
         }
      }

      blendTrapIntoCave(level, originX, originZ, lidY, pit, mask, random);
   }

   private static void blendTrapIntoCave(class_5281 level, int originX, int originZ, int lidY, int pit, boolean[][] mask, class_5819 random) {
      class_2339 cursor = new class_2339();
      int floorY = lidY - pit;
      int spanX = mask.length;
      int spanZ = mask[0].length;

      for (int ix = 0; ix < spanX; ix++) {
         for (int iz = 0; iz < spanZ; iz++) {
            if (mask[ix][iz]) {
               int x = originX + ix;
               int z = originZ + iz;

               for (class_2350 direction : class_2353.field_11062) {
                  int nx = x + direction.method_10148();
                  int nz = z + direction.method_10165();
                  int localX = nx - originX;
                  int localZ = nz - originZ;
                  if (localX < 0 || localX >= spanX || localZ < 0 || localZ >= spanZ || !mask[localX][localZ]) {
                     cursor.method_10103(nx, lidY + 1, nz);
                     if (canWrite(level, cursor)) {
                        class_2680 above = level.method_8320(cursor);
                        cursor.method_10103(nx, lidY, nz);
                        if (canWrite(level, cursor)) {
                           class_2680 floor = level.method_8320(cursor);
                           if ((isCaveSpace(above) || above.method_27852(class_2246.field_10477))
                              && !isCaveSpace(floor)
                              && canCarveTrap(floor)
                              && !(floor.method_26204() instanceof BrittleIceBlock)) {
                              if (above.method_27852(class_2246.field_10477)) {
                                 cursor.method_10103(nx, lidY + 1, nz);
                                 level.method_8652(cursor, class_2246.field_10543.method_9564(), 2);
                                 cursor.method_10103(nx, lidY, nz);
                              }

                              level.method_8652(cursor, trapBlendBlock(random), 2);
                           }

                           for (int y = lidY - 1; y >= floorY; y--) {
                              cursor.method_10103(nx, y, nz);
                              if (!canWrite(level, cursor)) {
                                 break;
                              }

                              class_2680 wall = level.method_8320(cursor);
                              if (isReplaceableCaveBlock(wall) || wall.method_27852(class_2246.field_10340) || wall.method_27852(class_2246.field_28888)) {
                                 level.method_8652(cursor, class_2246.field_10225.method_9564(), 2);
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   private static class_2680 trapBlendBlock(class_5819 random) {
      float roll = random.method_43057();
      if (roll < 0.62F) {
         return class_2246.field_10225.method_9564();
      } else {
         return roll < 0.88F ? class_2246.field_10295.method_9564() : class_2246.field_27114.method_9564();
      }
   }

   private static int sampleTrapIcicleLength(class_5819 random, int maxLength) {
      int length = 1 + random.method_43048(maxLength);
      if (random.method_43057() < 0.4F) {
         length = random.method_43056() ? 1 : maxLength;
      }

      return Math.max(1, Math.min(maxLength, length));
   }

   private static int findCaveFloor(class_5281 level, class_2338 origin) {
      int minY = Math.max(level.method_31607() + 10 + 2, origin.method_10264() - 12);
      class_2339 cursor = new class_2339();

      for (int y = origin.method_10264(); y >= minY; y--) {
         cursor.method_10103(origin.method_10263(), y, origin.method_10260());
         if (canWrite(level, cursor)) {
            class_2680 space = level.method_8320(cursor);
            cursor.method_10103(origin.method_10263(), y - 1, origin.method_10260());
            if (canWrite(level, cursor)) {
               class_2680 floor = level.method_8320(cursor);
               if ((isCaveSpace(space) || space.method_27852(class_2246.field_10477))
                  && !isCaveSpace(floor)
                  && !floor.method_27852(class_2246.field_10477)
                  && canCarveTrap(floor)) {
                  return y - 1;
               }
            }
         }
      }

      return Integer.MIN_VALUE;
   }

   private static boolean columnCanBecomeTrap(class_5281 level, int x, int lidY, int z, int pit) {
      class_2339 cursor = new class_2339();
      cursor.method_10103(x, lidY + 1, z);
      if (!canWrite(level, cursor)) {
         return false;
      }

      class_2680 above = level.method_8320(cursor);
      if (!isCaveSpace(above) && !above.method_27852(class_2246.field_10477)) {
         return false;
      }

      int floorY = lidY - pit;

      for (int y = lidY; y >= floorY; y--) {
         cursor.method_10103(x, y, z);
         if (!canWrite(level, cursor)) {
            return false;
         }

         class_2680 state = level.method_8320(cursor);
         if (y == lidY) {
            if (isCaveSpace(state) || state.method_27852(class_2246.field_10477) || !canCarveTrap(state)) {
               return false;
            }
         } else if (!canCarveTrap(state)) {
            return false;
         }
      }

      return true;
   }

   private static boolean canCarveTrap(class_2680 state) {
      return !state.method_27852(class_2246.field_10382)
            && !state.method_27852(class_2246.field_10164)
            && !state.method_27852(class_2246.field_9987)
            && !state.method_31709()
         ? state.method_26215()
            || state.method_27852(class_2246.field_10543)
            || state.method_27852(class_2246.field_10243)
            || state.method_27852(class_2246.field_10477)
            || state.method_27852(class_2246.field_10491)
            || state.method_27852(class_2246.field_27879)
            || isReplaceableCaveBlock(state)
            || isIceCaveMaterial(state)
            || state.method_26204() instanceof BrittleIceBlock
         : false;
   }

   private static boolean canWrite(class_5281 level, class_2338 pos) {
      return pos.method_10264() > level.method_31607() && pos.method_10264() < level.method_31600();
   }

   private static int sampleIcicleLength(class_5819 random) {
      float roll = random.method_43057();
      if (roll < 0.34F) {
         return 1;
      } else if (roll < 0.58F) {
         return 2;
      } else if (roll < 0.78F) {
         return 3;
      } else if (roll < 0.9F) {
         return 4;
      } else {
         return roll < 0.97F ? 5 : 6;
      }
   }

   private static boolean placeIcicleColumn(class_5281 level, class_2338 startPos, class_2350 tipDirection, int height, class_2248 icicle) {
      class_2338 supportPos = startPos.method_10093(tipDirection.method_10153());
      class_2680 support = level.method_8320(supportPos);
      if (!support.method_26164(class_3481.field_15467)
         && !support.method_27852(class_2246.field_10225)
         && !support.method_27852(class_2246.field_10295)
         && !support.method_27852(class_2246.field_10384)
         && !support.method_27852(class_2246.field_27114)
         && !support.method_27852(class_2246.field_10340)
         && !support.method_26164(class_3481.field_25806)) {
         return false;
      }

      class_2339 probe = startPos.method_25503();

      for (int i = 0; i < height; i++) {
         if (!level.method_37368(probe)) {
            return false;
         }

         class_2680 space = level.method_8320(probe);
         if (!isCaveSpace(space) && !space.method_27852(class_2246.field_10477)) {
            return false;
         }

         probe.method_10098(tipDirection);
      }

      class_2339 placePos = startPos.method_25503();
      buildBaseToTipColumn(tipDirection, height, state -> {
         level.method_8652(placePos, state, 2);
         placePos.method_10098(tipDirection);
      }, icicle);
      return true;
   }

   private static void buildBaseToTipColumn(class_2350 direction, int totalLength, Consumer<class_2680> consumer, class_2248 pointedBlock) {
      if (totalLength >= 3) {
         consumer.accept(createIcicleState(direction, class_5691.field_28068, pointedBlock));

         for (int i = 0; i < totalLength - 3; i++) {
            consumer.accept(createIcicleState(direction, class_5691.field_28067, pointedBlock));
         }
      }

      if (totalLength >= 2) {
         consumer.accept(createIcicleState(direction, class_5691.field_28066, pointedBlock));
      }

      if (totalLength >= 1) {
         consumer.accept(createIcicleState(direction, class_5691.field_28065, pointedBlock));
      }
   }

   private static class_2680 createIcicleState(class_2350 direction, class_5691 thickness, class_2248 pointedBlock) {
      return (class_2680)((class_2680)pointedBlock.method_9564().method_11657(class_5689.field_28050, direction))
         .method_11657(class_5689.field_28051, thickness);
   }

   private static boolean placeCrystal(class_5281 level, class_2338 pos, class_2350 facing, class_2248 crystal) {
      if (!(crystal instanceof class_5542)) {
         return false;
      }

      if (!isCaveSpace(level.method_8320(pos)) && !level.method_8320(pos).method_27852(class_2246.field_10477)) {
         return false;
      }

      class_2338 supportPos = pos.method_10093(facing.method_10153());
      class_2680 support = level.method_8320(supportPos);
      if (!support.method_26206(level, supportPos, facing) && !isIceCaveMaterial(support)) {
         return false;
      }

      class_2680 state = (class_2680)crystal.method_9564().method_11657(class_5542.field_27087, facing);
      level.method_8652(pos, state, 2);
      return true;
   }

   private static boolean placeIcePillar(class_5281 level, class_2338 floorAirPos, class_5819 random) {
      int height = 2 + random.method_43048(7);
      class_2339 cursor = floorAirPos.method_25503();
      boolean any = false;

      for (int i = 0; i < height && level.method_37368(cursor); i++) {
         class_2680 space = level.method_8320(cursor);
         if (!isCaveSpace(space) && !space.method_27852(class_2246.field_10477)) {
            break;
         }

         class_2248 block = random.method_43057() < 0.7F ? class_2246.field_10225 : class_2246.field_10295;
         level.method_8652(cursor, block.method_9564(), 2);
         if (random.method_43057() < 0.35F) {
            class_2350 side = class_2353.field_11062.method_10183(random);
            class_2338 sidePos = cursor.method_10093(side);
            if (level.method_37368(sidePos) && (isCaveSpace(level.method_8320(sidePos)) || level.method_8320(sidePos).method_27852(class_2246.field_10477))) {
               level.method_8652(sidePos, class_2246.field_10225.method_9564(), 2);
            }
         }

         any = true;
         cursor.method_10098(class_2350.field_11036);
      }

      return any;
   }

   private static class_2680 pickIceCaveBlock(class_5819 random) {
      float roll = random.method_43057();
      if (roll < 0.48F) {
         return class_2246.field_10225.method_9564();
      } else if (roll < 0.62F) {
         return class_2246.field_10295.method_9564();
      } else {
         return roll < 0.88F ? class_2246.field_27114.method_9564() : class_2246.field_10340.method_9564();
      }
   }

   private static boolean isReplaceableCaveBlock(class_2680 state) {
      return state.method_26164(class_3481.field_25806)
         || state.method_27852(class_2246.field_28888)
         || state.method_27852(class_2246.field_29031)
         || state.method_27852(class_2246.field_27165)
         || state.method_27852(class_2246.field_10255)
         || state.method_27852(class_2246.field_10566)
         || state.method_27852(class_2246.field_10253)
         || state.method_27852(class_2246.field_28685)
         || state.method_27852(class_2246.field_28681)
         || state.method_27852(class_2246.field_28049)
         || state.method_27852(class_2246.field_29032);
   }

   private static boolean isIceCaveMaterial(class_2680 state) {
      return state.method_27852(class_2246.field_10225)
         || state.method_27852(class_2246.field_10295)
         || state.method_27852(class_2246.field_10384)
         || state.method_27852(class_2246.field_27114)
         || state.method_27852(class_2246.field_10340)
         || state.method_26164(class_3481.field_15467);
   }

   private static boolean isCaveSpace(class_2680 state) {
      return state.method_26215() || state.method_27852(class_2246.field_10543) || state.method_27852(class_2246.field_10243);
   }
}
