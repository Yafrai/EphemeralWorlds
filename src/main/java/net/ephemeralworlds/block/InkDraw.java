package net.ephemeralworlds.block;

import net.ephemeralworlds.block.base.ModBlock;
import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;

public class InkDraw extends ModBlock implements BlockEntityProvider {

    VoxelShape shape = Block.createCuboidShape(8, 8, 8, 8, 8, 8);

    public InkDraw(String uname, Block base) {
        super(uname, base);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return new InkDrawBlockEntity();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, EntityContext context) {
        return shape;
    }

//    @Environment(EnvType.CLIENT)
//    public static boolean shouldDrawSide(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, Direction direction_1) {
//        return false;
//    }

    @Environment(EnvType.CLIENT)
    public boolean isSideInvisible(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
        return true;
    }

    public boolean isDrawingAgainstBlock(IWorld world, BlockPos drawingPos, Direction side) {
        BlockState state = world.getBlockState(drawingPos.offset(side));
        // todo Solid face
        return !state.isAir();
    }

    // Erase drawing when neighbor is removed
    public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, IWorld iWorld_1, BlockPos blockPos_1, BlockPos blockPos_2) {
        if (!isDrawingAgainstBlock(iWorld_1, blockPos_1, direction_1)) {
            iWorld_1.getBlockTickScheduler().schedule(blockPos_1, this, 1);
        }

        return super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, iWorld_1, blockPos_1, blockPos_2);
    }

    public void onScheduledTick(BlockState blockState_1, World world_1, BlockPos blockPos_1, Random random_1) {
        BlockEntity entity = world_1.getBlockEntity(blockPos_1);
        if (entity instanceof InkDrawBlockEntity) {
            InkDrawBlockEntity draw = (InkDrawBlockEntity)entity;

            for (Direction direction: Direction.values()) {
                if (!isDrawingAgainstBlock(world_1, blockPos_1, direction)) {
                    draw.eraseFaceContents(direction);
                }
            }
        }
    }
}
