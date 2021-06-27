package net.ephemeralworlds.block.entity.base;

import net.ephemeralworlds.block.ResourceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class ResourceBlockEntity extends ModBlockEntity implements BlockEntityTicker<ModBlockEntity> {

    protected long repop;

    public ResourceBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public NbtCompound writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        tag.putLong("repop", repop);
        return tag;
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        repop = tag.getLong("repop");
    }

    @Override
    public void fromClientTag(NbtCompound tag) {
        repop = tag.getLong("repop");
    }

    @Override
    public NbtCompound toClientTag(NbtCompound tag) {
        tag.putLong("repop", repop);
        return tag;
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state, ModBlockEntity blockEntity) {
        if (repop > 0 && world.getTime() >= repop) {
            repop = 0;

            if (state.getBlock() instanceof ResourceBlock) {
                ((ResourceBlock)state.getBlock()).replenish(world, pos);
            }

            saveAndNotify();
        }
    }

    public void setReplenishTime(long ticks) {
        repop = world.getTime() + ticks;
        saveAndNotify();
    }
}
