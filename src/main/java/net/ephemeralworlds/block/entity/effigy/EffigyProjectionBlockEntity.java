package net.ephemeralworlds.block.entity.effigy;

import net.ephemeralworlds.block.entity.base.AEffigyBlockEntity;
import net.ephemeralworlds.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;


public class EffigyProjectionBlockEntity extends AEffigyBlockEntity {

    public EffigyProjectionBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.EFFIGY_PROJECTION_BLOCK_ENTITY, pos, state);
    }

    public ActionResult interact(PlayerEntity player, BlockHitResult hitResult) {
        return ActionResult.SUCCESS;
    }
}
