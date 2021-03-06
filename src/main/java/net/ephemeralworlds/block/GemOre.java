package net.ephemeralworlds.block;

import net.ephemeralworlds.block.base.ColorBlock;
import net.ephemeralworlds.block.base.LayeredColorBlock;
import net.ephemeralworlds.block.base.ModBlock;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.enums.EnumColorBrightness;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ExtendedBlockView;

import java.awt.*;

public class GemOre extends LayeredColorBlock {

    public GemOre(String uname) {
        super(uname, Blocks.COAL_ORE, EnumColorBrightness.MINERAL);
    }

    public int getColor(BlockState blockState, ExtendedBlockView blockView, BlockPos blockPos, int tintIndex) {
        if (tintIndex == 0) {
            EnumColor color = getEnumColor(blockState);
            return color.getColorForBrightness(EnumColorBrightness.DEFAULT);
        }
        else {
            EnumColor color = getEnumColor(blockState);
            return color.getColorForBrightness(EnumColorBrightness.MINERAL);
        }
    }
}
