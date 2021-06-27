package net.ephemeralworlds.block;

import net.ephemeralworlds.block.base.ModInkTankBlock;
import net.ephemeralworlds.block.entity.InkJarBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EntityShapeContext;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class InkJar extends ModInkTankBlock {

    private static final VoxelShape shape;
    static {
        VoxelShape bottom = Block.createCuboidShape(3, 0, 3, 13, 1, 13);

        VoxelShape face1 = Block.createCuboidShape(3, 1, 4, 4, 11, 13);
        VoxelShape face2 = Block.createCuboidShape(12, 1, 4, 13, 11, 13);
        VoxelShape face3 = Block.createCuboidShape(4, 1, 3, 13, 11, 4);
        VoxelShape face4 = Block.createCuboidShape(4, 1, 12, 13, 11, 13);

        shape = VoxelShapes.union(
                VoxelShapes.union(
                VoxelShapes.union(
                VoxelShapes.union(
                        bottom, face1), face2), face3), face4);
    }

    public InkJar(String uname, Block base) {
        super(uname, base);
        settings.nonOpaque().dynamicBounds();
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new InkJarBlockEntity(pos, state);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return shape;
    }
}
