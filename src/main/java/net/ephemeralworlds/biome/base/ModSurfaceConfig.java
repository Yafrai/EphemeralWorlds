package net.ephemeralworlds.biome.base;

import net.ephemeralworlds.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class ModSurfaceConfig {

    public static TernarySurfaceConfig DEFAULT = getModSurfaceConfig(ModBlocks.COLOR_GRASS.getDefaultState(), ModBlocks.COLOR_STONE.getDefaultState(), ModBlocks.COLOR_STONE.getDefaultState());
    public static TernarySurfaceConfig CRYSTAL = getModSurfaceConfig(ModBlocks.CHROMIUM_BLOCK.getDefaultState(), ModBlocks.DIACHRONIUM_ORE.getDefaultState(), ModBlocks.GEM_ORE1.getDefaultState());

    public static TernarySurfaceConfig getModSurfaceConfig(BlockState top, BlockState under, BlockState underwater) {
        return new TernarySurfaceConfig(top, under, underwater);
    }
}
