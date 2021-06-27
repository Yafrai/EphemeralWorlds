package net.ephemeralworlds.block;

import net.ephemeralworlds.block.base.ModOrientableBlock;
import net.ephemeralworlds.block.entity.TinkeringTableBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class TinkeringTable extends ModOrientableBlock implements BlockEntityProvider {

    public TinkeringTable(String uname, Block base) {
        super(uname, base);
    }

//    public BlockRenderLayer getRenderLayer() {
//        return BlockRenderLayer.SOLID;
//    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new TinkeringTableBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (!(blockEntity instanceof TinkeringTableBlockEntity)) {
            return ActionResult.FAIL;
        }

        return ((TinkeringTableBlockEntity)blockEntity).interact(player, hitResult);
    }
}
