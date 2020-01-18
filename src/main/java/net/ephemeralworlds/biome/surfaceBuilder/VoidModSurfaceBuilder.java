package net.ephemeralworlds.biome.surfaceBuilder;

import com.mojang.datafixers.Dynamic;
import net.ephemeralworlds.block.base.ColorBlock;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.helpers.DimensionHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import java.util.Random;
import java.util.function.Function;

public class VoidModSurfaceBuilder extends SurfaceBuilder<TernarySurfaceConfig> {

    public VoidModSurfaceBuilder(Function<Dynamic<?>, ? extends TernarySurfaceConfig> function_1) {
        super(function_1);
    }

    public void generate(Random random_1, Chunk chunk_1, Biome biome_1, int int_1, int int_2, int int_3, double double_1, BlockState blockState_1, BlockState blockState_2, int int_4, long long_1, TernarySurfaceConfig ternarySurfaceConfig_1) {
        this.generate(random_1, chunk_1, biome_1, int_1, int_2, int_3, double_1, blockState_1, blockState_2, ternarySurfaceConfig_1.getTopMaterial(), ternarySurfaceConfig_1.getUnderMaterial(), ternarySurfaceConfig_1.getUnderwaterMaterial(), int_4);
    }

    protected void generate(Random random, Chunk chunk, Biome biome, int x, int z, int ymax, double amplitude, BlockState blockState_default, BlockState blockState_default_top, BlockState top, BlockState under, BlockState underwater, int seaLevel) {

    }
}