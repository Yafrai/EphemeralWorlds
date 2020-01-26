package net.ephemeralworlds.block;

import net.ephemeralworlds.block.base.LayeredColorBlock;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.enums.EnumColorBrightness;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ExtendedBlockView;

import java.awt.*;

public class MetalOre extends LayeredColorBlock {

    public MetalOre(String uname) {
        super(uname, Blocks.IRON_ORE, EnumColorBrightness.MINERAL);
    }

    public int getColor(BlockState blockState, ExtendedBlockView blockView, BlockPos blockPos, int tintIndex) {
        if (tintIndex == 0) {
            return new Color(255, 255, 255).getRGB();
        }
        else {
            EnumColor color = getEnumColor(blockState);
            return color.getColorForBrightness(EnumColorBrightness.MINERAL);
        }
    }
}
