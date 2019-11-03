package net.ephemeralworlds.utils.helpers;

import net.ephemeralworlds.block.base.ColorBlock;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ColorHelper {

    public static EnumColor getBlockColor(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if (!(block instanceof ColorBlock))
            return null;

        return ColorBlock.getEnumColor(state);
    }

    public static float getColorRedComponent(int color) {
        return color & 255;
    }

    public static float getColorGreenComponent(int color) {
        return (color / 255) & 255;
    }

    public static float getColorBlueComponent(int color) {
        return color / (255 * 255) & 255;
    }

}
