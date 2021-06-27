package net.ephemeralworlds.block.base;

import net.ephemeralworlds.utils.enums.EnumColorBrightness;
import net.minecraft.block.Block;

public class DualBrightnessColorBlock extends ColorBlock {

    EnumColorBrightness brightness1;

    public DualBrightnessColorBlock(String uname, Block base, EnumColorBrightness brightness0, EnumColorBrightness brightness1) {
        super(uname, base, brightness0);
        this.brightness1 = brightness1;
    }

//    @Override
//    public BlockRenderLayer getRenderLayer() {
//        return BlockRenderLayer.CUTOUT_MIPPED;
//    }

    protected EnumColorBrightness getBrightness(int tintIndex) {
        if (tintIndex == 1)
            return brightness1;
        else
            return super.getBrightness(tintIndex);
    }
}
