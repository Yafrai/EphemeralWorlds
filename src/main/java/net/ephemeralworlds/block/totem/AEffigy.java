package net.ephemeralworlds.block.totem;

import net.ephemeralworlds.block.entity.base.AEffigyBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class AEffigy extends ATotemPart implements BlockEntityProvider {

    public AEffigy(String uname, Block base) {
        super(uname);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (!(blockEntity instanceof AEffigyBlockEntity)) {
            return ActionResult.FAIL;
        }

        return ((AEffigyBlockEntity)blockEntity).interact(player, hitResult);
    }
}
