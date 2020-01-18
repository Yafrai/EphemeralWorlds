package net.ephemeralworlds.utils.tools;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

public class BlockAndPos {
    public BlockState state;
    public BlockPos pos;
    public boolean required;

    public BlockAndPos(BlockState state, BlockPos pos, boolean required) {
        this.state = state;
        this.pos = pos;
        this.required = required;
    }
}
