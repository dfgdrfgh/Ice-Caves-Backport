package org.zerw.net.icecaves.worldgen;

import com.mojang.serialization.Codec;
import java.util.function.Consumer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos.MutableBlockPos;
import net.minecraft.core.Direction.Plane;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PointedDripstoneBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DripstoneThickness;
import net.minecraft.world.level.levelgen.Heightmap.Types;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.zerw.net.icecaves.block.BrittleIceBlock_forge;
import org.zerw.net.icecaves.registry.ModBlocks_forge;

public final class IceCaveFeature_forge extends Feature<NoneFeatureConfiguration> {
   private static final int RADIUS = 8;
   private static final float CEILING_ICICLE_CHANCE = 0.22F;
   private static final float FLOOR_SNOW_CHANCE = 0.34F;
   private static final float FLOOR_CRYSTAL_CHANCE = 0.06F;
   private static final float ICE_PILLAR_CHANCE = 0.04F;
   private static final float BRITTLE_PATCH_CHANCE = 0.008F;
   private static final int TRAP_MIN_PIT = 6;
   private static final int TRAP_MAX_PIT = 10;
   private static final int TRAP_MAX_SPAN = 8;

   public IceCaveFeature_forge(Codec<NoneFeatureConfiguration> codec) {
      super(codec);
   }

   public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
      WorldGenLevel level = context.level();
      BlockPos origin = context.origin();
      RandomSource random = context.random();
      int surfaceY = level.getHeight(Types.OCEAN_FLOOR_WG, origin.getX(), origin.getZ());
      if (origin.getY() >= surfaceY - 3) {
         return false;
      }

      BlockState originState = level.getBlockState(origin);
      if (!isCaveSpace(originState)) {
         return false;
      }

      Block icicle = (Block)BuiltInRegistries.BLOCK.get(ModBlocks_forge.ICICLE_ID);
      Block crystal = (Block)BuiltInRegistries.BLOCK.get(ModBlocks_forge.ICE_CRYSTAL_ID);
      if (icicle != Blocks.AIR && crystal != Blocks.AIR) {
         boolean placed = false;
         MutableBlockPos cursor = new MutableBlockPos();
         MutableBlockPos neighborPos = new MutableBlockPos();

         for (int dx = -8; dx <= 8; dx++) {
            for (int dy = -8; dy <= 8; dy++) {
               for (int dz = -8; dz <= 8; dz++) {
                  if (dx * dx + dy * dy + dz * dz <= 64) {
                     cursor.set(origin.getX() + dx, origin.getY() + dy, origin.getZ() + dz);
                     if (level.ensureCanWrite(cursor)) {
                        BlockState state = level.getBlockState(cursor);
                        if (isCaveSpace(state)) {
                           boolean touchedWall = false;

                           for (Direction direction : Direction.values()) {
                              neighborPos.setWithOffset(cursor, direction);
                              if (level.ensureCanWrite(neighborPos)) {
                                 BlockState neighbor = level.getBlockState(neighborPos);
                                 if (isReplaceableCaveBlock(neighbor)) {
                                    level.setBlock(neighborPos, pickIceCaveBlock(random), 2);
                                    placed = true;
                                    touchedWall = true;
                                    if (random.nextFloat() < 0.55F) {
                                       MutableBlockPos deeper = neighborPos.mutable().move(direction);
                                       if (level.ensureCanWrite(deeper) && isReplaceableCaveBlock(level.getBlockState(deeper))) {
                                          level.setBlock(deeper, pickIceCaveBlock(random), 2);
                                       }
                                    }
                                 }
                              }
                           }

                           BlockPos below = cursor.below();
                           BlockState belowState = level.getBlockState(below);
                           boolean solidFloor = isIceCaveMaterial(belowState) || belowState.isFaceSturdy(level, below, Direction.UP);
                           BlockPos above = cursor.above();
                           BlockState aboveState = level.getBlockState(above);
                           boolean solidCeiling = isIceCaveMaterial(aboveState) || aboveState.isFaceSturdy(level, above, Direction.DOWN);
                           if (touchedWall || solidFloor || solidCeiling) {
                              if (solidFloor && isCaveSpace(level.getBlockState(cursor)) && !(belowState.getBlock() instanceof BrittleIceBlock_forge)) {
                                 if (random.nextFloat() < 0.04F) {
                                    if (placeIcePillar(level, cursor.immutable(), random)) {
                                       placed = true;
                                    }
                                 } else if (random.nextFloat() < 0.34F) {
                                    level.setBlock(cursor, Blocks.SNOW.defaultBlockState(), 2);
                                    placed = true;
                                 } else if (random.nextFloat() < 0.06F && placeCrystal(level, cursor.immutable(), Direction.UP, crystal)) {
                                    placed = true;
                                 }
                              }

                              if (solidCeiling && isCaveSpace(level.getBlockState(cursor)) && random.nextFloat() < 0.22F) {
                                 if (!aboveState.is(BlockTags.ICE)
                                    && !aboveState.is(Blocks.PACKED_ICE)
                                    && !aboveState.is(Blocks.ICE)
                                    && !aboveState.is(Blocks.BLUE_ICE)
                                    && (
                                       isReplaceableCaveBlock(aboveState)
                                          || isIceCaveMaterial(aboveState)
                                          || aboveState.is(Blocks.CALCITE)
                                          || aboveState.is(Blocks.STONE)
                                    )) {
                                    level.setBlock(above, Blocks.PACKED_ICE.defaultBlockState(), 2);
                                 }

                                 int length = sampleIcicleLength(random);
                                 if (placeIcicleColumn(level, cursor.immutable(), Direction.DOWN, length, icicle)) {
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

         if (random.nextFloat() < 0.008F) {
            Block brittle = (Block)BuiltInRegistries.BLOCK.get(ModBlocks_forge.BRITTLE_ICE_ID);
            if (brittle != Blocks.AIR && placeBrittlePatch(level, origin, surfaceY, random, icicle, brittle)) {
               placed = true;
            }
         }

         return placed;
      } else {
         return false;
      }
   }

   private static boolean placeBrittlePatch(WorldGenLevel level, BlockPos origin, int surfaceY, RandomSource random, Block icicle, Block brittle) {
      int lidY = findCaveFloor(level, origin);
      if (lidY != Integer.MIN_VALUE && lidY < surfaceY - 2) {
         int fall = 4 + random.nextInt(3);
         int spikeRoom = 2 + random.nextInt(3);
         int pit = fall + spikeRoom;
         if (pit >= 6 && pit <= 10 && lidY - pit > level.getMinBuildHeight() + 1) {
            int spanX = 5 + random.nextInt(4);
            int spanZ = 5 + random.nextInt(4);
            boolean[][] mask = buildTrapMask(random, spanX, spanZ);
            int originX = origin.getX() - spanX / 2;
            int originZ = origin.getZ() - spanZ / 2;
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

   private static boolean[][] buildTrapMask(RandomSource random, int spanX, int spanZ) {
      boolean[][] mask = new boolean[spanX][spanZ];
      double cx = (spanX - 1) / 2.0 + (random.nextDouble() - 0.5);
      double cz = (spanZ - 1) / 2.0 + (random.nextDouble() - 0.5);
      double rx = Math.max(1.4, spanX * (0.42 + random.nextDouble() * 0.12));
      double rz = Math.max(1.4, spanZ * (0.42 + random.nextDouble() * 0.12));

      for (int ix = 0; ix < spanX; ix++) {
         for (int iz = 0; iz < spanZ; iz++) {
            double nx = (ix - cx) / rx;
            double nz = (iz - cz) / rz;
            double dist = nx * nx + nz * nz;
            if (!(dist > 1.0) && (!(dist > 0.28) || !(random.nextFloat() < 0.42F))) {
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

   private static void breakSolidRectangle(boolean[][] mask, RandomSource random) {
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

   private static boolean keepCarveableCells(WorldGenLevel level, boolean[][] mask, int originX, int originZ, int lidY, int pit) {
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
      WorldGenLevel level, int originX, int originZ, int lidY, int pit, boolean[][] mask, RandomSource random, Block icicle, Block brittle
   ) {
      int maxSpike = Math.max(3, pit - 3);
      MutableBlockPos cursor = new MutableBlockPos();
      int spanX = mask.length;
      int spanZ = mask[0].length;

      for (int ix = 0; ix < spanX; ix++) {
         for (int iz = 0; iz < spanZ; iz++) {
            if (mask[ix][iz]) {
               int x = originX + ix;
               int z = originZ + iz;
               cursor.set(x, lidY + 1, z);
               if (level.getBlockState(cursor).is(Blocks.SNOW)) {
                  level.setBlock(cursor, Blocks.CAVE_AIR.defaultBlockState(), 2);
               }

               cursor.set(x, lidY, z);
               level.setBlock(cursor, (BlockState)brittle.defaultBlockState().setValue(BrittleIceBlock_forge.STAGE, 1 + random.nextInt(3)), 2);
               int floorY = lidY - pit;

               for (int y = lidY - 1; y > floorY; y--) {
                  cursor.set(x, y, z);
                  level.setBlock(cursor, Blocks.CAVE_AIR.defaultBlockState(), 2);
               }

               cursor.set(x, floorY, z);
               level.setBlock(cursor, Blocks.PACKED_ICE.defaultBlockState(), 2);
               int length = sampleTrapIcicleLength(random, maxSpike);
               placeIcicleColumn(level, new BlockPos(x, floorY + 1, z), Direction.UP, length, icicle);
            }
         }
      }

      blendTrapIntoCave(level, originX, originZ, lidY, pit, mask, random);
   }

   private static void blendTrapIntoCave(WorldGenLevel level, int originX, int originZ, int lidY, int pit, boolean[][] mask, RandomSource random) {
      MutableBlockPos cursor = new MutableBlockPos();
      int floorY = lidY - pit;
      int spanX = mask.length;
      int spanZ = mask[0].length;

      for (int ix = 0; ix < spanX; ix++) {
         for (int iz = 0; iz < spanZ; iz++) {
            if (mask[ix][iz]) {
               int x = originX + ix;
               int z = originZ + iz;

               for (Direction direction : Plane.HORIZONTAL) {
                  int nx = x + direction.getStepX();
                  int nz = z + direction.getStepZ();
                  int localX = nx - originX;
                  int localZ = nz - originZ;
                  if (localX < 0 || localX >= spanX || localZ < 0 || localZ >= spanZ || !mask[localX][localZ]) {
                     cursor.set(nx, lidY + 1, nz);
                     if (canWrite(level, cursor)) {
                        BlockState above = level.getBlockState(cursor);
                        cursor.set(nx, lidY, nz);
                        if (canWrite(level, cursor)) {
                           BlockState floor = level.getBlockState(cursor);
                           if ((isCaveSpace(above) || above.is(Blocks.SNOW))
                              && !isCaveSpace(floor)
                              && canCarveTrap(floor)
                              && !(floor.getBlock() instanceof BrittleIceBlock_forge)) {
                              if (above.is(Blocks.SNOW)) {
                                 cursor.set(nx, lidY + 1, nz);
                                 level.setBlock(cursor, Blocks.CAVE_AIR.defaultBlockState(), 2);
                                 cursor.set(nx, lidY, nz);
                              }

                              level.setBlock(cursor, trapBlendBlock(random), 2);
                           }

                           for (int y = lidY - 1; y >= floorY; y--) {
                              cursor.set(nx, y, nz);
                              if (!canWrite(level, cursor)) {
                                 break;
                              }

                              BlockState wall = level.getBlockState(cursor);
                              if (isReplaceableCaveBlock(wall) || wall.is(Blocks.STONE) || wall.is(Blocks.DEEPSLATE)) {
                                 level.setBlock(cursor, Blocks.PACKED_ICE.defaultBlockState(), 2);
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

   private static BlockState trapBlendBlock(RandomSource random) {
      float roll = random.nextFloat();
      if (roll < 0.62F) {
         return Blocks.PACKED_ICE.defaultBlockState();
      } else {
         return roll < 0.88F ? Blocks.ICE.defaultBlockState() : Blocks.CALCITE.defaultBlockState();
      }
   }

   private static int sampleTrapIcicleLength(RandomSource random, int maxLength) {
      int length = 1 + random.nextInt(maxLength);
      if (random.nextFloat() < 0.4F) {
         length = random.nextBoolean() ? 1 : maxLength;
      }

      return Math.max(1, Math.min(maxLength, length));
   }

   private static int findCaveFloor(WorldGenLevel level, BlockPos origin) {
      int minY = Math.max(level.getMinBuildHeight() + 10 + 2, origin.getY() - 12);
      MutableBlockPos cursor = new MutableBlockPos();

      for (int y = origin.getY(); y >= minY; y--) {
         cursor.set(origin.getX(), y, origin.getZ());
         if (canWrite(level, cursor)) {
            BlockState space = level.getBlockState(cursor);
            cursor.set(origin.getX(), y - 1, origin.getZ());
            if (canWrite(level, cursor)) {
               BlockState floor = level.getBlockState(cursor);
               if ((isCaveSpace(space) || space.is(Blocks.SNOW)) && !isCaveSpace(floor) && !floor.is(Blocks.SNOW) && canCarveTrap(floor)) {
                  return y - 1;
               }
            }
         }
      }

      return Integer.MIN_VALUE;
   }

   private static boolean columnCanBecomeTrap(WorldGenLevel level, int x, int lidY, int z, int pit) {
      MutableBlockPos cursor = new MutableBlockPos();
      cursor.set(x, lidY + 1, z);
      if (!canWrite(level, cursor)) {
         return false;
      }

      BlockState above = level.getBlockState(cursor);
      if (!isCaveSpace(above) && !above.is(Blocks.SNOW)) {
         return false;
      }

      int floorY = lidY - pit;

      for (int y = lidY; y >= floorY; y--) {
         cursor.set(x, y, z);
         if (!canWrite(level, cursor)) {
            return false;
         }

         BlockState state = level.getBlockState(cursor);
         if (y == lidY) {
            if (isCaveSpace(state) || state.is(Blocks.SNOW) || !canCarveTrap(state)) {
               return false;
            }
         } else if (!canCarveTrap(state)) {
            return false;
         }
      }

      return true;
   }

   private static boolean canCarveTrap(BlockState state) {
      return !state.is(Blocks.WATER) && !state.is(Blocks.LAVA) && !state.is(Blocks.BEDROCK) && !state.hasBlockEntity()
         ? state.isAir()
            || state.is(Blocks.CAVE_AIR)
            || state.is(Blocks.VOID_AIR)
            || state.is(Blocks.SNOW)
            || state.is(Blocks.SNOW_BLOCK)
            || state.is(Blocks.POWDER_SNOW)
            || isReplaceableCaveBlock(state)
            || isIceCaveMaterial(state)
            || state.getBlock() instanceof BrittleIceBlock_forge
         : false;
   }

   private static boolean canWrite(WorldGenLevel level, BlockPos pos) {
      return pos.getY() > level.getMinBuildHeight() && pos.getY() < level.getMaxBuildHeight();
   }

   private static int sampleIcicleLength(RandomSource random) {
      float roll = random.nextFloat();
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

   private static boolean placeIcicleColumn(WorldGenLevel level, BlockPos startPos, Direction tipDirection, int height, Block icicle) {
      BlockPos supportPos = startPos.relative(tipDirection.getOpposite());
      BlockState support = level.getBlockState(supportPos);
      if (!support.is(BlockTags.ICE)
         && !support.is(Blocks.PACKED_ICE)
         && !support.is(Blocks.ICE)
         && !support.is(Blocks.BLUE_ICE)
         && !support.is(Blocks.CALCITE)
         && !support.is(Blocks.STONE)
         && !support.is(BlockTags.BASE_STONE_OVERWORLD)) {
         return false;
      }

      MutableBlockPos probe = startPos.mutable();

      for (int i = 0; i < height; i++) {
         if (!level.ensureCanWrite(probe)) {
            return false;
         }

         BlockState space = level.getBlockState(probe);
         if (!isCaveSpace(space) && !space.is(Blocks.SNOW)) {
            return false;
         }

         probe.move(tipDirection);
      }

      MutableBlockPos placePos = startPos.mutable();
      buildBaseToTipColumn(tipDirection, height, state -> {
         level.setBlock(placePos, state, 2);
         placePos.move(tipDirection);
      }, icicle);
      return true;
   }

   private static void buildBaseToTipColumn(Direction direction, int totalLength, Consumer<BlockState> consumer, Block pointedBlock) {
      if (totalLength >= 3) {
         consumer.accept(createIcicleState(direction, DripstoneThickness.BASE, pointedBlock));

         for (int i = 0; i < totalLength - 3; i++) {
            consumer.accept(createIcicleState(direction, DripstoneThickness.MIDDLE, pointedBlock));
         }
      }

      if (totalLength >= 2) {
         consumer.accept(createIcicleState(direction, DripstoneThickness.FRUSTUM, pointedBlock));
      }

      if (totalLength >= 1) {
         consumer.accept(createIcicleState(direction, DripstoneThickness.TIP, pointedBlock));
      }
   }

   private static BlockState createIcicleState(Direction direction, DripstoneThickness thickness, Block pointedBlock) {
      return (BlockState)((BlockState)pointedBlock.defaultBlockState().setValue(PointedDripstoneBlock.TIP_DIRECTION, direction))
         .setValue(PointedDripstoneBlock.THICKNESS, thickness);
   }

   private static boolean placeCrystal(WorldGenLevel level, BlockPos pos, Direction facing, Block crystal) {
      if (!(crystal instanceof AmethystClusterBlock)) {
         return false;
      }

      if (!isCaveSpace(level.getBlockState(pos)) && !level.getBlockState(pos).is(Blocks.SNOW)) {
         return false;
      }

      BlockPos supportPos = pos.relative(facing.getOpposite());
      BlockState support = level.getBlockState(supportPos);
      if (!support.isFaceSturdy(level, supportPos, facing) && !isIceCaveMaterial(support)) {
         return false;
      }

      BlockState state = (BlockState)crystal.defaultBlockState().setValue(AmethystClusterBlock.FACING, facing);
      level.setBlock(pos, state, 2);
      return true;
   }

   private static boolean placeIcePillar(WorldGenLevel level, BlockPos floorAirPos, RandomSource random) {
      int height = 2 + random.nextInt(7);
      MutableBlockPos cursor = floorAirPos.mutable();
      boolean any = false;

      for (int i = 0; i < height && level.ensureCanWrite(cursor); i++) {
         BlockState space = level.getBlockState(cursor);
         if (!isCaveSpace(space) && !space.is(Blocks.SNOW)) {
            break;
         }

         Block block = random.nextFloat() < 0.7F ? Blocks.PACKED_ICE : Blocks.ICE;
         level.setBlock(cursor, block.defaultBlockState(), 2);
         if (random.nextFloat() < 0.35F) {
            Direction side = Plane.HORIZONTAL.getRandomDirection(random);
            BlockPos sidePos = cursor.relative(side);
            if (level.ensureCanWrite(sidePos) && (isCaveSpace(level.getBlockState(sidePos)) || level.getBlockState(sidePos).is(Blocks.SNOW))) {
               level.setBlock(sidePos, Blocks.PACKED_ICE.defaultBlockState(), 2);
            }
         }

         any = true;
         cursor.move(Direction.UP);
      }

      return any;
   }

   private static BlockState pickIceCaveBlock(RandomSource random) {
      float roll = random.nextFloat();
      if (roll < 0.48F) {
         return Blocks.PACKED_ICE.defaultBlockState();
      } else if (roll < 0.62F) {
         return Blocks.ICE.defaultBlockState();
      } else {
         return roll < 0.88F ? Blocks.CALCITE.defaultBlockState() : Blocks.STONE.defaultBlockState();
      }
   }

   private static boolean isReplaceableCaveBlock(BlockState state) {
      return state.is(BlockTags.BASE_STONE_OVERWORLD)
         || state.is(Blocks.DEEPSLATE)
         || state.is(Blocks.COBBLED_DEEPSLATE)
         || state.is(Blocks.TUFF)
         || state.is(Blocks.GRAVEL)
         || state.is(Blocks.DIRT)
         || state.is(Blocks.COARSE_DIRT)
         || state.is(Blocks.ROOTED_DIRT)
         || state.is(Blocks.MOSS_BLOCK)
         || state.is(Blocks.DRIPSTONE_BLOCK)
         || state.is(Blocks.SMOOTH_BASALT);
   }

   private static boolean isIceCaveMaterial(BlockState state) {
      return state.is(Blocks.PACKED_ICE)
         || state.is(Blocks.ICE)
         || state.is(Blocks.BLUE_ICE)
         || state.is(Blocks.CALCITE)
         || state.is(Blocks.STONE)
         || state.is(BlockTags.ICE);
   }

   private static boolean isCaveSpace(BlockState state) {
      return state.isAir() || state.is(Blocks.CAVE_AIR) || state.is(Blocks.VOID_AIR);
   }
}
