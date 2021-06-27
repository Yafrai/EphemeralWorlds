package net.ephemeralworlds.dimension;

import net.ephemeralworlds.registry.ModBiomes;
import net.ephemeralworlds.registry.ModDimensions;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.FixedBiomeSource;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.ChunkGenerator;

//public class IllusionDimension extends DimensionOptions {
//
//    World world;
//
//    public IllusionDimension(World world, DimensionType type) {
//        super(world, type);
//        this.world = world;
//    }
//
//    @Override
//    public ChunkGenerator<?> createChunkGenerator() {
//        OverworldChunkGeneratorConfig config = ChunkGeneratorType.SURFACE.createSettings();
//        return ChunkGeneratorType.SURFACE.create(world, new FixedBiomeSource(new FixedBiomeSourceConfig().setBiome(ModBiomes.spiritForest)), config);
//    }
//
//    @Override
//    public BlockPos getSpawningBlockInChunk(ChunkPos chunkPos, boolean b) {
//        return null;
//    }
//
//    @Override
//    public BlockPos getTopSpawningBlockPosition(int i, int i1, boolean b) {
//        return null;
//    }
//
//    @Override
//    public float getSkyAngle(long l, float v) {
//        return 0;
//    }
//
//    @Override
//    public boolean hasVisibleSky() {
//        return true;
//    }
//
//    @Override
//    public Vec3d getFogColor(float v, float v1) {
//        return new Vec3d(0, 0, 1);
//    }
//
//    @Override
//    public boolean canPlayersSleep() {
//        return false;
//    }
//
//    @Override
//    public boolean shouldRenderFog(int i, int i1) {
//        return false;
//    }
//
//    @Override
//    public DimensionType getType() {
//        return ModDimensions.ephemerium;
//    }
//
//}
