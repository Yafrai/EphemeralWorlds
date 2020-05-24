package net.ephemeralworlds.block.base;

import net.ephemeralworlds.block.entity.base.ModInkTankBlockEntity;
import net.ephemeralworlds.item.Brush;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Pair;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public abstract class ModInkTankBlock extends ModBlock implements BlockEntityProvider {

    public ModInkTankBlock(String uname, Block base) {
        super(uname, base);
    }

    @Override
    public boolean activate(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hitResult) {
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (!(blockEntity instanceof ModInkTankBlockEntity)) {
            return false;
        }

        ItemStack playerStack = player.getStackInHand(hand);
        if (playerStack.getItem() instanceof Brush) {
            Brush brush = (Brush)playerStack.getItem();

            if (brush.getTagInkAmount(playerStack) > 0) {
                return false;
            }

            Pair<Integer, EnumColor> pair = ((ModInkTankBlockEntity)blockEntity).pickInk(1);
            if (pair.getLeft() == 1) {
                brush.setTagColor(playerStack, pair.getRight());
                brush.setTagAmount(playerStack, pair.getLeft());
                return true;
            }

        }

        return false;
    }

    @Override
    public abstract BlockEntity createBlockEntity(BlockView var1);
}
