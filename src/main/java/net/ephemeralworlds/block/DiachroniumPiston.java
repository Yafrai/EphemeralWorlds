package net.ephemeralworlds.block;

import net.ephemeralworlds.block.base.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

import java.util.Objects;

public class DiachroniumPiston extends ModBlock implements BlockEntityProvider {

    static final BooleanProperty extended = BooleanProperty.of("extended");
    static final EnumProperty<Direction> facing = EnumProperty.of("facing", Direction.class);

    public DiachroniumPiston(String uname, Block base) {
        super(uname, base);

        setDefaultState(getDefaultState().with(extended, false).with(facing, Direction.UP));
    }

    public static boolean isHeadingDownwards(BlockState state) {
        if (!(state.getBlock() instanceof DiachroniumPiston))
            return false;

        return state.get(facing) == Direction.DOWN;
    }

    @Override
    protected void appendProperties(StateFactory.Builder<Block, BlockState> map) {
        super.appendProperties(map);
        map.add(extended);
        map.add(facing);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction face = ctx.getPlayerLookDirection().getOpposite();
        return Objects.requireNonNull(super.getPlacementState(ctx)).with(facing, face);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(facing, rotation.rotate(state.get(facing)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(facing)));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView var1) {
        return null;
    }
}
