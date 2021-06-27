package net.ephemeralworlds.dimension.generation;

public class NoiseGenerator {
//
//    OctavePerlinNoiseSampler generator;
//
//    public NoiseGenerator(Random random) {
//        generator = new OctavePerlinNoiseSampler(random, 16);
//    }
//
//    private static final float[] BIOME_WEIGHT_TABLE = (float[]) SystemUtil.consume(new float[25], (floats_1) -> {
//        for(int int_1 = -2; int_1 <= 2; ++int_1) {
//            for(int int_2 = -2; int_2 <= 2; ++int_2) {
//                float float_1 = 10.0F / MathHelper.sqrt((float)(int_1 * int_1 + int_2 * int_2) + 0.2F);
//                floats_1[int_1 + 2 + (int_2 + 2) * 5] = float_1;
//            }
//        }
//
//    });
//
//    public double sampleNoise(int x, int z) {
////        double double_1 = generator.sample((double)(int_1 * 200), 10.0D, (double)(int_2 * 200), 1.0D, 0.0D, true) / 8000.0D;
//        double double_1 = generator.sample((double)(x * 200), 10.0D, (double)(z * 200), 1.0D, 0.0D, true) / 8000.0D;
//
//        if (double_1 < 0.0D) {
//            double_1 = -double_1 * 0.3D;
//        }
//
//        double_1 = double_1 * 3.0D - 2.0D;
//        if (double_1 < 0.0D) {
//            double_1 /= 28.0D;
//        } else {
//            if (double_1 > 1.0D) {
//                double_1 = 1.0D;
//            }
//
//            double_1 /= 40.0D;
//        }
//
//        return double_1;
//    }
//
//    protected double[] computeNoiseRange(int x, int z) {
//        double[] min_max_doubles = new double[2];
//        float weighted_scale = 0.0F;
//        float weighted_depth = 0.0F;
//        float weight = 0.0F;
//
//        float depth = this.getDepth();
//        float scale = this.getScale();
//
//        // Weighted average
//        for(int dx = -2; dx <= 2; ++dx) {
//            for(int dz = -2; dz <= 2; ++dz) {
//
//                float factor = BIOME_WEIGHT_TABLE[dx + 2 + (dz + 2) * 5] / (depth + 2.0F);
//                if (this.getDepth() > depth) {
//                    factor /= 2.0F;
//                }
//
//                weighted_scale += scale * factor;
//                weighted_depth += depth * factor;
//                weight += factor;
//            }
//        }
//
//        weighted_scale /= weight;
//        weighted_depth /= weight;
//        weighted_scale = weighted_scale * 0.9F + 0.1F;
//        weighted_depth = (weighted_depth * 4.0F - 1.0F) / 8.0F;
//        min_max_doubles[0] = (double)weighted_depth + this.sampleNoise(x, z);
//        min_max_doubles[1] = (double)weighted_scale;
//        return min_max_doubles;
//    }
//
//    public float getDepth() {
//        return 0.125F;
////        0.45F // mountains
////        1.5F // plateau
//    }
//
//    public float getScale() {
//        return 0.05F;
////        0.3F // mountains
////        0.025F // plateau
//    }
//
//
//    // From SurfaceChunckGenerator
////    public void buildSurface(Chunk chunk_1) {
////        ChunkPos chunkPos_1 = chunk_1.getPos();
////        int int_1 = chunkPos_1.x;
////        int int_2 = chunkPos_1.z;
//////        ChunkRandom chunkRandom_1 = new ChunkRandom();
////        chunkRandom_1.setSeed(int_1, int_2);
////        ChunkPos chunkPos_2 = chunk_1.getPos();
////        int int_3 = chunkPos_2.getStartX();
////        int int_4 = chunkPos_2.getStartZ();
////        double double_1 = 0.0625D;
////        Biome[] biomes_1 = chunk_1.getBiomeArray();
////
////        for(int int_5 = 0; int_5 < 16; ++int_5) {
////            for(int int_6 = 0; int_6 < 16; ++int_6) {
////                int int_7 = int_3 + int_5;
////                int int_8 = int_4 + int_6;
////                int int_9 = chunk_1.sampleHeightmap(Heightmap.Type.WORLD_SURFACE_WG, int_5, int_6) + 1;
////                double double_2 = this.surfaceDepthNoise.sample((double)int_7 * 0.0625D, (double)int_8 * 0.0625D, 0.0625D, (double)int_5 * 0.0625D);
////                biomes_1[int_6 * 16 + int_5].buildSurface(chunkRandom_1, chunk_1, int_7, int_8, int_9, double_2, this.getConfig().getDefaultBlock(), this.getConfig().getDefaultFluid(), this.getSeaLevel(), this.world.getSeed());
////            }
////        }
////
////        this.buildBedrock(chunk_1, chunkRandom_1);
////    }
//
////    public void populateNoise(IWorld iWorld_1, Chunk chunk_1) {
////        int int_1 = this.getSeaLevel();
////        ObjectList<PoolStructurePiece> objectList_1 = new ObjectArrayList(10);
////        ObjectList<JigsawJunction> objectList_2 = new ObjectArrayList(32);
////        ChunkPos chunkPos_1 = chunk_1.getPos();
////        int int_2 = chunkPos_1.x;
////        int int_3 = chunkPos_1.z;
////        int int_4 = int_2 << 4;
////        int int_5 = int_3 << 4;
////        Iterator var11 = Feature.JIGSAW_STRUCTURES.iterator();
////
////        label178:
////        while(var11.hasNext()) {
////            StructureFeature<?> structureFeature_1 = (StructureFeature)var11.next();
////            String string_1 = structureFeature_1.getName();
////            LongIterator longIterator_1 = chunk_1.getStructureReferences(string_1).iterator();
////
////            label176:
////            while(true) {
////                StructureStart structureStart_1;
////                do {
////                    do {
////                        if (!longIterator_1.hasNext()) {
////                            continue label178;
////                        }
////
////                        long long_1 = longIterator_1.nextLong();
////                        ChunkPos chunkPos_2 = new ChunkPos(long_1);
////                        Chunk chunk_2 = iWorld_1.getChunk(chunkPos_2.x, chunkPos_2.z);
////                        structureStart_1 = chunk_2.getStructureStart(string_1);
////                    } while(structureStart_1 == null);
////                } while(!structureStart_1.hasChildren());
////
////                Iterator var20 = structureStart_1.getChildren().iterator();
////
////                while(true) {
////                    StructurePiece structurePiece_1;
////                    do {
////                        do {
////                            if (!var20.hasNext()) {
////                                continue label176;
////                            }
////
////                            structurePiece_1 = (StructurePiece)var20.next();
////                        } while(!structurePiece_1.method_16654(chunkPos_1, 12));
////                    } while(!(structurePiece_1 instanceof PoolStructurePiece));
////
////                    PoolStructurePiece poolStructurePiece_1 = (PoolStructurePiece)structurePiece_1;
////                    StructurePool.Projection structurePool$Projection_1 = poolStructurePiece_1.getPoolElement().getProjection();
////                    if (structurePool$Projection_1 == StructurePool.Projection.RIGID) {
////                        objectList_1.add(poolStructurePiece_1);
////                    }
////
////                    Iterator var24 = poolStructurePiece_1.getJunctions().iterator();
////
////                    while(var24.hasNext()) {
////                        JigsawJunction jigsawJunction_1 = (JigsawJunction)var24.next();
////                        int int_6 = jigsawJunction_1.getSourceX();
////                        int int_7 = jigsawJunction_1.getSourceZ();
////                        if (int_6 > int_4 - 12 && int_7 > int_5 - 12 && int_6 < int_4 + 15 + 12 && int_7 < int_5 + 15 + 12) {
////                            objectList_2.add(jigsawJunction_1);
////                        }
////                    }
////                }
////            }
////        }
////
////        double[][][] doubles_1 = new double[2][this.noiseSizeZ + 1][this.noiseSizeY + 1];
////
////        for(int int_8 = 0; int_8 < this.noiseSizeZ + 1; ++int_8) {
////            doubles_1[0][int_8] = new double[this.noiseSizeY + 1];
////            this.sampleNoiseColumn(doubles_1[0][int_8], int_2 * this.noiseSizeX, int_3 * this.noiseSizeZ + int_8);
////            doubles_1[1][int_8] = new double[this.noiseSizeY + 1];
////        }
////
////        ProtoChunk protoChunk_1 = (ProtoChunk)chunk_1;
////        Heightmap heightmap_1 = protoChunk_1.getHeightmap(Heightmap.Type.OCEAN_FLOOR_WG);
////        Heightmap heightmap_2 = protoChunk_1.getHeightmap(Heightmap.Type.WORLD_SURFACE_WG);
////        BlockPos.Mutable blockPos$Mutable_1 = new BlockPos.Mutable();
////        ObjectListIterator<PoolStructurePiece> objectListIterator_1 = objectList_1.iterator();
////        ObjectListIterator<JigsawJunction> objectListIterator_2 = objectList_2.iterator();
////
////        for(int int_9 = 0; int_9 < this.noiseSizeX; ++int_9) {
////            int int_11;
////            for(int_11 = 0; int_11 < this.noiseSizeZ + 1; ++int_11) {
////                this.sampleNoiseColumn(doubles_1[1][int_11], int_2 * this.noiseSizeX + int_9 + 1, int_3 * this.noiseSizeZ + int_11);
////            }
////
////            for(int_11 = 0; int_11 < this.noiseSizeZ; ++int_11) {
////                ChunkSection chunkSection_1 = protoChunk_1.getSection(15);
////                chunkSection_1.lock();
////
////                for(int int_12 = this.noiseSizeY - 1; int_12 >= 0; --int_12) {
////                    double double_1 = doubles_1[0][int_11][int_12];
////                    double double_2 = doubles_1[0][int_11 + 1][int_12];
////                    double double_3 = doubles_1[1][int_11][int_12];
////                    double double_4 = doubles_1[1][int_11 + 1][int_12];
////                    double double_5 = doubles_1[0][int_11][int_12 + 1];
////                    double double_6 = doubles_1[0][int_11 + 1][int_12 + 1];
////                    double double_7 = doubles_1[1][int_11][int_12 + 1];
////                    double double_8 = doubles_1[1][int_11 + 1][int_12 + 1];
////
////                    for(int int_13 = this.verticalNoiseResolution - 1; int_13 >= 0; --int_13) {
////                        int int_14 = int_12 * this.verticalNoiseResolution + int_13;
////                        int int_15 = int_14 & 15;
////                        int int_16 = int_14 >> 4;
////                        if (chunkSection_1.getYOffset() >> 4 != int_16) {
////                            chunkSection_1.unlock();
////                            chunkSection_1 = protoChunk_1.getSection(int_16);
////                            chunkSection_1.lock();
////                        }
////
////                        double double_9 = (double)int_13 / (double)this.verticalNoiseResolution;
////                        double double_10 = MathHelper.lerp(double_9, double_1, double_5);
////                        double double_11 = MathHelper.lerp(double_9, double_3, double_7);
////                        double double_12 = MathHelper.lerp(double_9, double_2, double_6);
////                        double double_13 = MathHelper.lerp(double_9, double_4, double_8);
////
////                        for(int int_17 = 0; int_17 < this.horizontalNoiseResolution; ++int_17) {
////                            int int_18 = int_4 + int_9 * this.horizontalNoiseResolution + int_17;
////                            int int_19 = int_18 & 15;
////                            double double_14 = (double)int_17 / (double)this.horizontalNoiseResolution;
////                            double double_15 = MathHelper.lerp(double_14, double_10, double_11);
////                            double double_16 = MathHelper.lerp(double_14, double_12, double_13);
////
////                            for(int int_20 = 0; int_20 < this.horizontalNoiseResolution; ++int_20) {
////                                int int_21 = int_5 + int_11 * this.horizontalNoiseResolution + int_20;
////                                int int_22 = int_21 & 15;
////                                double double_17 = (double)int_20 / (double)this.horizontalNoiseResolution;
////                                double double_18 = MathHelper.lerp(double_17, double_15, double_16);
////                                double double_19 = MathHelper.clamp(double_18 / 200.0D, -1.0D, 1.0D);
////
////                                int int_27;
////                                int int_28;
////                                int int_25;
////                                for(double_19 = double_19 / 2.0D - double_19 * double_19 * double_19 / 24.0D; objectListIterator_1.hasNext(); double_19 += method_16572(int_27, int_28, int_25) * 0.8D) {
////                                    PoolStructurePiece poolStructurePiece_2 = (PoolStructurePiece)objectListIterator_1.next();
////                                    MutableIntBoundingBox mutableIntBoundingBox_1 = poolStructurePiece_2.getBoundingBox();
////                                    int_27 = Math.max(0, Math.max(mutableIntBoundingBox_1.minX - int_18, int_18 - mutableIntBoundingBox_1.maxX));
////                                    int_28 = int_14 - (mutableIntBoundingBox_1.minY + poolStructurePiece_2.getGroundLevelDelta());
////                                    int_25 = Math.max(0, Math.max(mutableIntBoundingBox_1.minZ - int_21, int_21 - mutableIntBoundingBox_1.maxZ));
////                                }
////
////                                objectListIterator_1.back(objectList_1.size());
////
////                                while(objectListIterator_2.hasNext()) {
////                                    JigsawJunction jigsawJunction_2 = (JigsawJunction)objectListIterator_2.next();
////                                    int int_26 = int_18 - jigsawJunction_2.getSourceX();
////                                    int_27 = int_14 - jigsawJunction_2.getSourceGroundY();
////                                    int_28 = int_21 - jigsawJunction_2.getSourceZ();
////                                    double_19 += method_16572(int_26, int_27, int_28) * 0.4D;
////                                }
////
////                                objectListIterator_2.back(objectList_2.size());
////                                BlockState blockState_3;
////                                if (double_19 > 0.0D) {
////                                    blockState_3 = this.defaultBlock;
////                                } else if (int_14 < int_1) {
////                                    blockState_3 = this.defaultFluid;
////                                } else {
////                                    blockState_3 = AIR;
////                                }
////
////                                if (blockState_3 != AIR) {
////                                    if (blockState_3.getLuminance() != 0) {
////                                        blockPos$Mutable_1.set(int_18, int_14, int_21);
////                                        protoChunk_1.addLightSource(blockPos$Mutable_1);
////                                    }
////
////                                    chunkSection_1.setBlockState(int_19, int_15, int_22, blockState_3, false);
////                                    heightmap_1.trackUpdate(int_19, int_14, int_22, blockState_3);
////                                    heightmap_2.trackUpdate(int_19, int_14, int_22, blockState_3);
////                                }
////                            }
////                        }
////                    }
////                }
////
////                chunkSection_1.unlock();
////            }
////
////            double[][] doubles_2 = doubles_1[0];
////            doubles_1[0] = doubles_1[1];
////            doubles_1[1] = doubles_2;
////        }
////
////    }

}
