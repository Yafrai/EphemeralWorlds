package net.ephemeralworlds.block.fluid;

import net.ephemeralworlds.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

//public class ModFluid extends BaseFluid {
//    @Override
//    public Fluid getFlowing() {
//        return new Flowing();
//    }
//
//    @Override
//    public Fluid getStill() {
//        return new Still();
//    }
//
//    @Override
//    protected boolean isInfinite() {
//        return false;
//    }
//
//    @Override
//    protected void beforeBreakingBlock(IWorld iWorld, BlockPos blockPos, BlockState blockState) {
//
//    }
//
//    @Override
//    protected int method_15733(WorldView worldView) {
//        return 0;
//    }
//
//    @Override
//    protected int getLevelDecreasePerBlock(WorldView view) {
//        return 1;
//    }
//
////    @Override
////    protected BlockRenderLayer getRenderLayer() {
////        return BlockRenderLayer.CUTOUT;
////    }
//
//    @Override
//    public Item getBucketItem() {
//        return null;
//    }
//
//    @Override
//    protected boolean method_15777(FluidState fluidState, BlockView blockView, BlockPos blockPos, Fluid fluid, Direction direction) {
//        return false;
//    }
//
//    @Override
//    public int getTickRate(WorldView worldView) {
//        return 0;
//    }
//
//    @Override
//    protected float getBlastResistance() {
//        return 0;
//    }
//
//    @Override
//    protected BlockState toBlockState(FluidState fluidState) {
//        return ModBlocks.FLOWING_SAND.getDefaultState().with(FluidBlock.LEVEL, method_15741(fluidState));
//    }
//
//    @Override
//    public boolean isStill(FluidState fluidState) {
//        return fluidState.isStill();
//    }
//
//    @Override
//    public int getLevel(FluidState fluidState) {
//        return 0;
//    }
//
//    public static class Flowing extends ModFluid {
//        public Flowing() {
//        }
//
//        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
//            super.appendProperties(builder);
//            builder.add(LEVEL);
//        }
//
//        public int getLevel(FluidState fluidState) {
//            return (Integer)fluidState.get(LEVEL);
//        }
//
//        public boolean isStill(FluidState fluidState) {
//            return false;
//        }
//    }
//
//    public static class Still extends ModFluid {
//        public Still() {
//        }
//
//        public int getLevel(FluidState fluidState) {
//            return 8;
//        }
//
//        public boolean isStill(FluidState fluidState) {
//            return true;
//        }
//    }
//}
