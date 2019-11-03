package net.ephemeralworlds.biome.base;

import net.ephemeralworlds.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class ModSurfaceConfig {

    public static TernarySurfaceConfig DEFAULT = getModSurfaceConfig(ModBlocks.COLOR_DIRT.getDefaultState(), ModBlocks.COLOR_STONE.getDefaultState(), ModBlocks.COLOR_STONE.getDefaultState());
    public static TernarySurfaceConfig CRYSTAL = getModSurfaceConfig(ModBlocks.TERIUM_BLOCK.getDefaultState(), ModBlocks.XERIUM_BLOCK.getDefaultState(), ModBlocks.GEM_ORE.getDefaultState());

    public static TernarySurfaceConfig getModSurfaceConfig(BlockState top, BlockState under, BlockState underwater) {
        return new TernarySurfaceConfig(top, under, underwater);
    }
}
