package net.ephemeralworlds.block;

import net.ephemeralworlds.block.base.ModBlock;
import net.ephemeralworlds.block.entity.RunicForgeBlockEntity;
import net.ephemeralworlds.block.entity.TinkeringTableBlockEntity;
import net.ephemeralworlds.registry.ModBlockEntities;
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
import org.jetbrains.annotations.Nullable;

public class RunicForge extends ModBlock implements BlockEntityProvider {

    public RunicForge(String uname, Block base) {
        super(uname, base);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new RunicForgeBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (!(blockEntity instanceof RunicForgeBlockEntity)) {
            return ActionResult.FAIL;
        }

        return ((RunicForgeBlockEntity)blockEntity).interact(player);
    }
}
