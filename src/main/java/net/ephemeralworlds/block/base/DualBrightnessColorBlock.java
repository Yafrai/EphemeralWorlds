package net.ephemeralworlds.block.base;

import com.google.common.collect.Lists;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.enums.EnumColorBrightness;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ExtendedBlockView;

import java.util.List;

public class DualBrightnessColorBlock extends ColorBlock {

    EnumColorBrightness brightness1;

    public DualBrightnessColorBlock(String uname, Block base, EnumColorBrightness brightness0, EnumColorBrightness brightness1) {
        super(uname, base, brightness0);
        this.brightness1 = brightness1;
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public int getColor(BlockState blockState, ExtendedBlockView blockView, BlockPos blockPos, int tintIndex) {
        if (tintIndex == 0)
            return super.getColor(blockState, blockView, blockPos, tintIndex);

        EnumColor color = getEnumColor(blockState);
        return color.getColorForBrightness(brightness1);
    }
}
