package net.ephemeralworlds.block.totem;

import net.ephemeralworlds.block.base.ModOrientableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;

public abstract class ATotemInventoryPart extends ModOrientableBlock {
    // todo
    public ATotemInventoryPart(String uname, Block base) {
        super(uname, base);
    }

    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }
}
