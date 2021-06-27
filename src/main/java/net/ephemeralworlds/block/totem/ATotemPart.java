package net.ephemeralworlds.block.totem;

import net.ephemeralworlds.block.base.ModOrientableBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Random;

public abstract class ATotemPart extends ModOrientableBlock {

    public ATotemPart(String uname) {
        super(uname, Blocks.OAK_PLANKS);
    }

//    public BlockRenderLayer getRenderLayer() {
//        return BlockRenderLayer.SOLID;
//    }

    public void onScheduledTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
        if (!blockState_1.canPlaceAt(world_1, blockPos_1)) {
            world_1.breakBlock(blockPos_1, true);
        }
    }

    public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, World world, BlockPos blockPos_1, BlockPos blockPos_2) {
        if (!blockState_1.canPlaceAt(world, blockPos_1)) {
            world.getBlockTickScheduler().schedule(blockPos_1, this, 1);
        }

        return super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, world, blockPos_1, blockPos_2);
    }

    public boolean canPlaceAt(BlockState blockState_1, WorldView viewableWorld_1, BlockPos blockPos_1) {
        Block block_1 = viewableWorld_1.getBlockState(blockPos_1.down()).getBlock();
        return (block_1 instanceof ATotemPart && !(block_1 instanceof AEffigy));
    }
}
