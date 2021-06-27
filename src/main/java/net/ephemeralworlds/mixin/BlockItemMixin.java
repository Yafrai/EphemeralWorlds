package net.ephemeralworlds.mixin;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.block.entity.parts.AInventoryCircle;
import net.ephemeralworlds.block.entity.parts.ISerializablePart;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class BlockItemMixin {

    @Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
    public void useOnBlock(ItemUsageContext itemUsageContext, CallbackInfoReturnable<ActionResult> info) {

//        BlockPos blockPos = itemUsageContext_1.getBlockPos();
//        PlayerEntity playerEntity = itemUsageContext_1.getPlayer();
//        Hand hand = itemUsageContext_1.getHand();
//        Direction side = itemUsageContext_1.getSide();
//
//        if (hand == Hand.OFF_HAND) {
////            info.setReturnValue(false);
//            return;
//        }
//
//        if (playerEntity.getStackInHand(hand).isEmpty()) {
//            BlockPos drawingPos = blockPos.offset(side);
//            BlockEntity entity = playerEntity.world.getBlockEntity(drawingPos);
//
//            if (entity instanceof InkDrawBlockEntity) {
//                InkDrawBlockEntity inkEntity = (InkDrawBlockEntity)entity;
//
//                if (inkEntity.getFaceContents(side.getOpposite()) instanceof AInventoryCircle) {
//
//                    ItemStack stack = ((AInventoryCircle) inkEntity.getFaceContents(side.getOpposite())).retrieveItem();
//                    if (!stack.isEmpty() && !playerEntity.isCreative())
//                        playerEntity.giveItemStack(stack);
//
//                    info.setReturnValue(ActionResult.SUCCESS);
//                }
//            }
//        }

        BlockPos drawPos = itemUsageContext.getBlockPos().offset(itemUsageContext.getSide());
        World world = itemUsageContext.getWorld();

        BlockEntity blockEntity = world.getBlockEntity(drawPos);

        if (blockEntity instanceof InkDrawBlockEntity) {
            InkDrawBlockEntity inkDraw = (InkDrawBlockEntity)blockEntity;
            ISerializablePart drawing = inkDraw.getFaceContents(itemUsageContext.getSide().getOpposite());

            if (drawing instanceof AInventoryCircle) {
                ItemStack playerStack = itemUsageContext.getPlayer().getStackInHand(itemUsageContext.getHand());

                boolean success = ((AInventoryCircle)drawing).addItem(playerStack);

                if (success) {
                    playerStack.decrement(1);
                    itemUsageContext.getPlayer().setStackInHand(itemUsageContext.getHand(), playerStack);
                    info.setReturnValue(ActionResult.SUCCESS);
                }
            }

            info.setReturnValue(ActionResult.FAIL);
        }
    }
}
