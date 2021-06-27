package net.ephemeralworlds.biome.feature;

//import com.mojang.datafixers.Dynamic;
//import net.ephemeralworlds.registry.ModBlocks;
//import net.minecraft.block.BlockState;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.gen.chunk.ChunkGenerator;
//import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
//import net.minecraft.world.gen.feature.DefaultFeatureConfig;
//import net.minecraft.world.gen.feature.Feature;
//
//import java.util.Random;
//import java.util.function.Function;
//
//public class CraterFeature extends Feature<DefaultFeatureConfig> {
//
//    public CraterFeature(Function<Dynamic<?>, ? extends DefaultFeatureConfig> function_1) {
//        super(function_1);
//    }
//
//    @Override
//    public boolean generate(IWorld iWorld, ChunkGenerator<? extends ChunkGeneratorConfig> chunkGenerator, Random random, BlockPos blockPos, DefaultFeatureConfig defaultFeatureConfig) {
//
//        int radius = random.nextInt(4) + 4;
//        BlockState state = ModBlocks.COLOR_STONE.getDefaultState();
//
//        for (int dx=-radius; dx<radius; dx++) {
//            for (int dz=-radius; dz<radius; dz++) {
//
//                if (radius*radius <= dx*dx + dz*dz && dx*dx + dz*dz < radius*radius+1)
//                    iWorld.setBlockState(new BlockPos(blockPos.getX()+dx, blockPos.getY(), blockPos.getZ()+dz), state, 3);
//            }
//        }
//
//        return false;
//    }
//}
