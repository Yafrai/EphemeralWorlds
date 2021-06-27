package net.ephemeralworlds.block.base;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;

public class CutoutBlock extends ModBlock {

    public CutoutBlock(String uname, Block base) {
        this(uname, FabricBlockSettings.copy(base).build());
    }

    public CutoutBlock(String uname, Settings settings) {
        super(uname, settings);
    }

//    public BlockRenderLayer getRenderLayer() {
//        return BlockRenderLayer.CUTOUT;
//    }
}
