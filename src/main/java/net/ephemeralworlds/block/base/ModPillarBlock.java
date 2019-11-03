package net.ephemeralworlds.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;

public class ModPillarBlock extends ModBlock {

    public static final EnumProperty<Direction.Axis> AXIS;

    public ModPillarBlock(String uname, Block base) {
        super(uname, base);
        this.setDefaultState((BlockState)this.getDefaultState().with(AXIS, Direction.Axis.Y));
    }

    public BlockState rotate(BlockState blockState_1, BlockRotation blockRotation_1) {
        switch(blockRotation_1) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch((Direction.Axis)blockState_1.get(AXIS)) {
                    case X:
                        return (BlockState)blockState_1.with(AXIS, Direction.Axis.Z);
                    case Z:
                        return (BlockState)blockState_1.with(AXIS, Direction.Axis.X);
                    default:
                        return blockState_1;
                }
            default:
                return blockState_1;
        }
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
        super.appendProperties(stateFactory$Builder_1);
        stateFactory$Builder_1.add(new Property[]{AXIS});
    }

    public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
        return (BlockState)this.getDefaultState().with(AXIS, itemPlacementContext_1.getSide().getAxis());
    }

    static {
        AXIS = Properties.AXIS;
    }
}
