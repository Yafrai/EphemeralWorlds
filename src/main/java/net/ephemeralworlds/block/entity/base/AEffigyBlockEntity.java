package net.ephemeralworlds.block.entity.base;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

public abstract class AEffigyBlockEntity extends ModBlockEntity {

    public AEffigyBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public void saveAndNotify() {
        markDirty();
        if (world != null) {
            world.updateListeners(pos, world.getBlockState(pos), world.getBlockState(pos), 0);
        }
    }

    public abstract ActionResult interact(PlayerEntity player, BlockHitResult hitResult);
}
