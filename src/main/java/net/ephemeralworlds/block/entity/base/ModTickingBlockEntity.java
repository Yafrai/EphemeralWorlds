package net.ephemeralworlds.block.entity.base;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;

public abstract class ModTickingBlockEntity extends ModBlockEntity implements Tickable {

    private long nextTick;

    public ModTickingBlockEntity(BlockEntityType<?> type) {
        super(type);

    }

    @Override
    public final void tick() {

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

    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putLong("tick", nextTick);
        return tag;
    }

    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        nextTick = tag.getLong("tick");
    }
}
