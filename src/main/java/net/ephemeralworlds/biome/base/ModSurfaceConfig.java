package net.ephemeralworlds.biome.base;

import net.ephemeralworlds.block.base.ColorBlock;
import net.ephemeralworlds.registry.ModBlocks;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class ModSurfaceConfig {

    public static TernarySurfaceConfig SANDS = getModSurfaceConfig(
            ColorBlock.getStateWithColor(ModBlocks.COLOR_GRASS, EnumColor.BLUE),
            ColorBlock.getStateWithColor(ModBlocks.COLOR_STONE, EnumColor.BLUE),
            ColorBlock.getStateWithColor(ModBlocks.COLOR_STONE, EnumColor.BLUE)
            );
    public static TernarySurfaceConfig FOREST = getModSurfaceConfig(
            ColorBlock.getStateWithColor(ModBlocks.COLOR_GRASS, EnumColor.GREEN),
            ColorBlock.getStateWithColor(ModBlocks.COLOR_STONE, EnumColor.GREEN),
            ColorBlock.getStateWithColor(ModBlocks.COLOR_STONE, EnumColor.GREEN)
    );
    public static TernarySurfaceConfig IMPACT = getModSurfaceConfig(
            ColorBlock.getStateWithColor(ModBlocks.COLOR_DIRT, EnumColor.RED),
            ColorBlock.getStateWithColor(ModBlocks.COLOR_STONE, EnumColor.RED),
            ColorBlock.getStateWithColor(ModBlocks.COLOR_STONE, EnumColor.RED)
    );

    public static TernarySurfaceConfig getModSurfaceConfig(BlockState top, BlockState under, BlockState underwater) {
        return new TernarySurfaceConfig(top, under, underwater);
    }
}
