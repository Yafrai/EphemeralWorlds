package net.ephemeralworlds.dimension;

import net.ephemeralworlds.registry.ModBiomes;
import net.ephemeralworlds.registry.ModDimensions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.CheckerboardBiomeSource;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;

//public class SpiritDimension extends Dimension {
//
//    World world;
//
//    public SpiritDimension(World world, DimensionType type) {
//        super(world, type);
//        this.world = world;
//    }
//
//    @Override
//    public ChunkGenerator<?> createChunkGenerator() {
//        OverworldChunkGeneratorConfig generatorConfig = ChunkGeneratorType.SURFACE.createSettings();
////        return ChunkGeneratorType.SURFACE.create(world, new FixedBiomeSource(new FixedBiomeSourceConfig().setBiome(ModBiomes.forest)), config);
////        return ChunkGeneratorType.SURFACE.create(world, new FixedBiomeSource(new FixedBiomeSourceConfig().setBiome(ModBiomes.impactGrounds)), config);
//
//        Biome[] biomes = new Biome[]{ModBiomes.everflowingSands, ModBiomes.spiritForest, ModBiomes.impactGrounds};
//        CheckerboardBiomeSourceConfig biomeConfig = new CheckerboardBiomeSourceConfig();
//        biomeConfig.method_8777(biomes);
//        biomeConfig.method_8780(1);
//
//        return ChunkGeneratorType.SURFACE.create(world, new CheckerboardBiomeSource(biomeConfig), generatorConfig);
//    }
//
//    @Override
//    public DimensionType getType() {
//        return ModDimensions.spiritWorld;
//    }
//
////    public ChunkGenerator<? extends ChunkGeneratorConfig> createChunkGenerator() {
////
//////        ChunkGeneratorType<CavesChunkGeneratorConfig, CavesChunkGenerator> chunkGeneratorType_3 = ChunkGeneratorType.CAVES;
//////        ChunkGeneratorType<FloatingIslandsChunkGeneratorConfig, FloatingIslandsChunkGenerator> chunkGeneratorType_4 = ChunkGeneratorType.FLOATING_ISLANDS;
////        ChunkGeneratorType<OverworldChunkGeneratorConfig, OverworldChunkGenerator> chunkGeneratorType_5 = ChunkGeneratorType.SURFACE;
//////        BiomeSourceType<FixedBiomeSourceConfig, FixedBiomeSource> biomeSourceType_1 = BiomeSourceType.FIXED;
//////        BiomeSourceType<VanillaLayeredBiomeSourceConfig, VanillaLayeredBiomeSource> biomeSourceType_2 = BiomeSourceType.VANILLA_LAYERED;
//////        BiomeSourceType<CheckerboardBiomeSourceConfig, CheckerboardBiomeSource> biomeSourceType_3 = BiomeSourceType.CHECKERBOARD;
////
////        BiomeSource biomeSource_1 = null;
////        JsonElement jsonElement_1 = (JsonElement)Dynamic.convert(NbtOps.INSTANCE, JsonOps.INSTANCE, this.world.getLevelProperties().getGeneratorOptions());
////        JsonObject jsonObject_1 = jsonElement_1.getAsJsonObject();
////        JsonObject jsonObject_2 = jsonObject_1.getAsJsonObject("biome_source");
////        if (jsonObject_2 != null && jsonObject_2.has("type") && jsonObject_2.has("options")) {
////            BiomeSourceType<?, ?> biomeSourceType_4 = (BiomeSourceType) Registry.BIOME_SOURCE_TYPE.get(new Identifier(jsonObject_2.getAsJsonPrimitive("type").getAsString()));
////            JsonObject jsonObject_3 = jsonObject_2.getAsJsonObject("options");
////            Biome[] biomes_1 = new Biome[]{Biomes.OCEAN};
////            if (jsonObject_3.has("biomes")) {
////                JsonArray jsonArray_1 = jsonObject_3.getAsJsonArray("biomes");
////                biomes_1 = jsonArray_1.size() > 0 ? new Biome[jsonArray_1.size()] : new Biome[]{Biomes.OCEAN};
////
////                for(int int_1 = 0; int_1 < jsonArray_1.size(); ++int_1) {
////                    biomes_1[int_1] = (Biome)Registry.BIOME.getOrEmpty(new Identifier(jsonArray_1.get(int_1).getAsString())).orElse(Biomes.OCEAN);
////                }
////            }
////
//////            if (BiomeSourceType.FIXED == biomeSourceType_4) {
//////                FixedBiomeSourceConfig fixedBiomeSourceConfig_3 = ((FixedBiomeSourceConfig)biomeSourceType_1.getConfig()).setBiome(biomes_1[0]);
//////                biomeSource_1 = biomeSourceType_1.applyConfig(fixedBiomeSourceConfig_3);
//////            }
////
//////            if (BiomeSourceType.CHECKERBOARD == biomeSourceType_4) {
//////                int int_2 = jsonObject_3.has("size") ? jsonObject_3.getAsJsonPrimitive("size").getAsInt() : 2;
//////                CheckerboardBiomeSourceConfig checkerboardBiomeSourceConfig_1 = ((CheckerboardBiomeSourceConfig)biomeSourceType_3.getConfig()).method_8777(biomes_1).method_8780(int_2);
//////                biomeSource_1 = biomeSourceType_3.applyConfig(checkerboardBiomeSourceConfig_1);
//////            }
////
//////            if (BiomeSourceType.VANILLA_LAYERED == biomeSourceType_4) {
//////                VanillaLayeredBiomeSourceConfig vanillaLayeredBiomeSourceConfig_1 = ((VanillaLayeredBiomeSourceConfig)biomeSourceType_2.getConfig()).setGeneratorSettings(new OverworldChunkGeneratorConfig()).setLevelProperties(this.world.getLevelProperties());
//////                biomeSource_1 = biomeSourceType_2.applyConfig(vanillaLayeredBiomeSourceConfig_1);
//////            }
////        }
////
//////        if (biomeSource_1 == null) {
//////            biomeSource_1 = biomeSourceType_1.applyConfig(((FixedBiomeSourceConfig)biomeSourceType_1.getConfig()).setBiome(Biomes.OCEAN));
//////        }
////
////        BlockState blockState_1 = Blocks.STONE.getDefaultState();
////        BlockState blockState_2 = Blocks.WATER.getDefaultState();
////        JsonObject jsonObject_4 = jsonObject_1.getAsJsonObject("chunk_generator");
////        if (jsonObject_4 != null && jsonObject_4.has("options")) {
////            JsonObject jsonObject_5 = jsonObject_4.getAsJsonObject("options");
////            String string_2;
////            if (jsonObject_5.has("default_block")) {
////                string_2 = jsonObject_5.getAsJsonPrimitive("default_block").getAsString();
////                blockState_1 = ((Block)Registry.BLOCK.get(new Identifier(string_2))).getDefaultState();
////            }
////
////            if (jsonObject_5.has("default_fluid")) {
////                string_2 = jsonObject_5.getAsJsonPrimitive("default_fluid").getAsString();
////                blockState_2 = ((Block)Registry.BLOCK.get(new Identifier(string_2))).getDefaultState();
////            }
////        }
////
////        if (jsonObject_4 != null && jsonObject_4.has("type")) {
////            ChunkGeneratorType<?, ?> chunkGeneratorType_6 = (ChunkGeneratorType)Registry.CHUNK_GENERATOR_TYPE.get(new Identifier(jsonObject_4.getAsJsonPrimitive("type").getAsString()));
//////            if (ChunkGeneratorType.CAVES == chunkGeneratorType_6) {
//////                CavesChunkGeneratorConfig cavesChunkGeneratorConfig_1 = (CavesChunkGeneratorConfig)chunkGeneratorType_3.createSettings();
//////                cavesChunkGeneratorConfig_1.setDefaultBlock(blockState_1);
//////                cavesChunkGeneratorConfig_1.setDefaultFluid(blockState_2);
//////                return chunkGeneratorType_3.create(this.world, biomeSource_1, cavesChunkGeneratorConfig_1);
//////            }
////
//////            if (ChunkGeneratorType.FLOATING_ISLANDS == chunkGeneratorType_6) {
//////                FloatingIslandsChunkGeneratorConfig floatingIslandsChunkGeneratorConfig_1 = (FloatingIslandsChunkGeneratorConfig)chunkGeneratorType_4.createSettings();
//////                floatingIslandsChunkGeneratorConfig_1.withCenter(new BlockPos(0, 64, 0));
//////                floatingIslandsChunkGeneratorConfig_1.setDefaultBlock(blockState_1);
//////                floatingIslandsChunkGeneratorConfig_1.setDefaultFluid(blockState_2);
//////                return chunkGeneratorType_4.create(this.world, biomeSource_1, floatingIslandsChunkGeneratorConfig_1);
//////            }
////        }
////
////        OverworldChunkGeneratorConfig overworldChunkGeneratorConfig_1 = (OverworldChunkGeneratorConfig)chunkGeneratorType_5.createSettings();
////        overworldChunkGeneratorConfig_1.setDefaultBlock(blockState_1);
////        overworldChunkGeneratorConfig_1.setDefaultFluid(blockState_2);
////        return chunkGeneratorType_5.create(this.world, biomeSource_1, overworldChunkGeneratorConfig_1);
////    }
//
//    public BlockPos getSpawningBlockInChunk(ChunkPos chunkPos_1, boolean boolean_1) {
////        for(int int_1 = chunkPos_1.getStartX(); int_1 <= chunkPos_1.getEndX(); ++int_1) {
////            for(int int_2 = chunkPos_1.getStartZ(); int_2 <= chunkPos_1.getEndZ(); ++int_2) {
////                BlockPos blockPos_1 = this.getTopSpawningBlockPosition(int_1, int_2, boolean_1);
////                if (blockPos_1 != null) {
////                    return blockPos_1;
////                }
////            }
////        }
//
//        return null;
//    }
//
//    public BlockPos getTopSpawningBlockPosition(int int_1, int int_2, boolean boolean_1) {
//        BlockPos.Mutable blockPos$Mutable_1 = new BlockPos.Mutable(int_1, 0, int_2);
//        Biome biome_1 = this.world.getBiome(blockPos$Mutable_1);
//        BlockState blockState_1 = biome_1.getSurfaceConfig().getTopMaterial();
//        if (boolean_1 && !blockState_1.getBlock().matches(BlockTags.VALID_SPAWN)) {
//            return null;
//        } else {
//            WorldChunk worldChunk_1 = this.world.method_8497(int_1 >> 4, int_2 >> 4);
//            int int_3 = worldChunk_1.sampleHeightmap(Heightmap.Type.MOTION_BLOCKING, int_1 & 15, int_2 & 15);
//            if (int_3 < 0) {
//                return null;
//            } else if (worldChunk_1.sampleHeightmap(Heightmap.Type.WORLD_SURFACE, int_1 & 15, int_2 & 15) > worldChunk_1.sampleHeightmap(Heightmap.Type.OCEAN_FLOOR, int_1 & 15, int_2 & 15)) {
//                return null;
//            } else {
//                for(int int_4 = int_3 + 1; int_4 >= 0; --int_4) {
//                    blockPos$Mutable_1.set(int_1, int_4, int_2);
//                    BlockState blockState_2 = this.world.getBlockState(blockPos$Mutable_1);
//                    if (!blockState_2.getFluidState().isEmpty()) {
//                        break;
//                    }
//
//                    if (blockState_2.equals(blockState_1)) {
//                        return blockPos$Mutable_1.up().toImmutable();
//                    }
//                }
//
//                return null;
//            }
//        }
//    }
//
//    public float getSkyAngle(long long_1, float float_1) {
//        double double_1 = MathHelper.fractionalPart((double)long_1 / 24000.0D - 0.25D);
//        double double_2 = 0.5D - Math.cos(double_1 * 3.141592653589793D) / 2.0D;
//        return (float)(double_1 * 2.0D + double_2) / 3.0F;
//    }
//
//    public boolean hasVisibleSky() {
//        return true;
//    }
//
//    @Environment(EnvType.CLIENT)
//    public Vec3d getFogColor(float float_1, float float_2) {
//        float float_3 = MathHelper.cos(float_1 * 6.2831855F) * 2.0F + 0.5F;
//        float_3 = MathHelper.clamp(float_3, 0.0F, 1.0F);
//        float float_4 = 0.7529412F;
//        float float_5 = 0.84705883F;
//        float float_6 = 1.0F;
//        float_4 *= float_3 * 0.94F + 0.06F;
//        float_5 *= float_3 * 0.94F + 0.06F;
//        float_6 *= float_3 * 0.91F + 0.09F;
//        return new Vec3d((double)float_4, (double)float_5, (double)float_6);
//    }
//
//    public boolean canPlayersSleep() {
//        return false;
//    }
//
//    @Environment(EnvType.CLIENT)
//    public boolean shouldRenderFog(int int_1, int int_2) {
//        return true;
//    }
//}
