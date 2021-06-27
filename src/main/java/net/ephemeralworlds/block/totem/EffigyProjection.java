package net.ephemeralworlds.block.totem;

import net.ephemeralworlds.block.entity.effigy.EffigyProjectionBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class EffigyProjection extends AEffigy {

    public EffigyProjection(String uname) {
        super(uname, Blocks.OAK_LOG);
    }

//    public BlockRenderLayer getRenderLayer() {
//        return BlockRenderLayer.SOLID;
//    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EffigyProjectionBlockEntity(pos, state);
    }
}
