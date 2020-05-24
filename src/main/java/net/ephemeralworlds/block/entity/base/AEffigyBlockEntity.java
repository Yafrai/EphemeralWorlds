package net.ephemeralworlds.block.entity.base;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.hit.BlockHitResult;

public abstract class AEffigyBlockEntity extends ModBlockEntity {

    public AEffigyBlockEntity(BlockEntityType<?> type) {
        super(type);
    }

    public void saveAndNotify() {
        markDirty();
        if (world != null) {
            world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), 0);
        }
    }

    @Override
    public void fromClientTag(CompoundTag compoundTag) {
        fromTag(compoundTag);
    }

    @Override
    public CompoundTag toClientTag(CompoundTag compoundTag) {
        return toTag(compoundTag);
    }

    public abstract boolean interact(PlayerEntity player, BlockHitResult hitResult);
}
