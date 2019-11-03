package net.ephemeralworlds.block.base;

import net.ephemeralworlds.block.base.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class CutoutMippedBlock extends ModBlock {

    public CutoutMippedBlock(String uname, Block block) {
        super(uname, block);
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

}
