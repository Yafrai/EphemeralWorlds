package net.ephemeralworlds.block.totem;

import net.ephemeralworlds.block.entity.effigy.EffigyProjectionBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class EffigyProjection extends AEffigy {

    public EffigyProjection(String uname) {
        super(uname, Blocks.OAK_LOG);
    }

    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.SOLID;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new EffigyProjectionBlockEntity();
    }
}
