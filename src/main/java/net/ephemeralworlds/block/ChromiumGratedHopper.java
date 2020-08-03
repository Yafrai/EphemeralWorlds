package net.ephemeralworlds.block;

import net.ephemeralworlds.block.base.ModBlock;
import net.ephemeralworlds.block.entity.ChromiumGrateHopperBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class ChromiumGratedHopper extends ModBlock implements BlockEntityProvider {

    public ChromiumGratedHopper(String uname, Block base) {
        super(uname, base);
    }

    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new ChromiumGrateHopperBlockEntity();
    }
}
