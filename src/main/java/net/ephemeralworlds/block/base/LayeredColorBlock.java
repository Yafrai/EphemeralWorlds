package net.ephemeralworlds.block.base;

import net.ephemeralworlds.utils.enums.EnumColorBrightness;
import net.minecraft.block.Block;

public class LayeredColorBlock extends ColorBlock {

    public LayeredColorBlock(String uname, Block base, EnumColorBrightness brightness) {
        super(uname, base, brightness);
    }

//    @Override
//    public BlockRenderLayer getRenderLayer() {
//        return BlockRenderLayer.CUTOUT_MIPPED;
//    }
    
}
