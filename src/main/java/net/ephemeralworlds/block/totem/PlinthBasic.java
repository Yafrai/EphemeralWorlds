package net.ephemeralworlds.block.totem;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class PlinthBasic extends ATotemPart {

    public PlinthBasic(String uname) {
        super(uname);
    }

    public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, World world, BlockPos blockPos_1, BlockPos blockPos_2) {
        return direction_1 == Direction.DOWN && !blockState_1.canPlaceAt(world, blockPos_1) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, world, blockPos_1, blockPos_2);
    }

    public boolean canPlaceAt(BlockState blockState_1, WorldView viewableWorld_1, BlockPos blockPos_1) {
        BlockState downBlockState = viewableWorld_1.getBlockState(blockPos_1.down());
        return (!(downBlockState.getBlock() instanceof AEffigy));
    }
}
