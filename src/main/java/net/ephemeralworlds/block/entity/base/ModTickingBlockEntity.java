package net.ephemeralworlds.block.entity.base;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ModTickingBlockEntity extends ModBlockEntity implements BlockEntityTicker<ModTickingBlockEntity> {

    private long nextTick;

    public ModTickingBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public final void tick(World world, BlockPos pos, BlockState state, ModTickingBlockEntity blockEntity) {

        if (world.getTime() >= nextTick) {
            if (nextTick > 0) {
                onTick();
            }
            updateNextTick();
        }
    }

    private void updateNextTick()
    {
        nextTick = world.getTime() + getTicks();
        markDirty();
    }

    protected abstract int getTicks();
    protected abstract void onTick();

    @Override
    public NbtCompound writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        tag.putLong("tick", nextTick);
        return tag;
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        nextTick = tag.getLong("tick");
    }
}
