package net.ephemeralworlds.block;

import net.ephemeralworlds.block.base.ModBlock;
import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.block.entity.base.ModBlockEntity;
import net.ephemeralworlds.registry.ModBlockEntities;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class InkDraw extends ModBlock implements BlockEntityProvider {

    public InkDraw(String uname, Block base) {
        super(uname, base);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new InkDrawBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.empty();
    }

    @Environment(EnvType.CLIENT)
    public boolean isSideInvisible(BlockState blockState_1, BlockState blockState_2, Direction direction_1) {
        return true;
    }

    public boolean isDrawingAgainstBlock(World world, BlockPos drawingPos, Direction side) {
        BlockState state = world.getBlockState(drawingPos.offset(side));
        // todo Solid face
        return !state.isAir();
    }

    // Erase drawing when neighbor is removed
    public BlockState getStateForNeighborUpdate(BlockState blockState_1, Direction direction_1, BlockState blockState_2, World world, BlockPos blockPos_1, BlockPos blockPos_2) {
        if (!isDrawingAgainstBlock(world, blockPos_1, direction_1)) {
            world.getBlockTickScheduler().schedule(blockPos_1, this, 1);
        }

        return super.getStateForNeighborUpdate(blockState_1, direction_1, blockState_2, world, blockPos_1, blockPos_2);
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
