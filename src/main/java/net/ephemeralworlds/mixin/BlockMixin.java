package net.ephemeralworlds.mixin;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.block.entity.parts.AInventoryCircle;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class BlockMixin {
	// onRandomTick
	// onScheduledTick
//	@Inject(method = "onRandomTick", at = @At("HEAD"), cancellable = true)
//	public void onRandomTick(BlockState blockState, World world, BlockPos blockPos, Random random, CallbackInfo info) {
//
//		if (world.isClient())
//			return;
//
//		if (world.getDimension().getType() != ModDimensions.ephemerium)
//			return;
//
//		boolean decay;
//		InstanceOptions options = DimensionHelper.getInstanceFromPosition(world, blockPos);
//		if (options != null)
//			decay = options.isDecaying();
//		else
//			decay = true;
//
//		if (decay) {
//			// Self
//			BlockState state = world.getBlockState(blockPos);
//			if (state.getBlock() != ModBlocks.DECAYED_SOIL && !state.isAir()) {
//				world.setBlockState(blockPos, ModBlocks.DECAYED_SOIL.getDefaultState());
//
//			// Underneath
////			for (int h=0; h<=blockPos.getY(); h++) {
////				BlockPos pos = new BlockPos(blockPos.getX(), h, blockPos.getZ());
////				BlockState state = world.getBlockState(pos);
////
////				if (state.getBlock() != ModBlocks.DECAYED_SOIL && !state.isAir()) {
////					world.setBlockState(pos, ModBlocks.DECAYED_SOIL.getDefaultState());
////					break;
////				}
//
//
//
//			}
//		}
//	}

//    @Inject(method = "activate", at = @At("HEAD"), cancellable = true)
//    private void activate(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult, CallbackInfoReturnable<Boolean> info) {
//
//        if (hand == Hand.OFF_HAND) {
//            info.setReturnValue(false);
//            return;
//        }
//
//        if (playerEntity.getStackInHand(hand).isEmpty()) {
//            BlockPos drawingPos = blockPos.offset(blockHitResult.getSide());
//            BlockEntity entity = world.getBlockEntity(drawingPos);
//
//            if (entity instanceof InkDrawBlockEntity) {
//                InkDrawBlockEntity inkEntity = (InkDrawBlockEntity)entity;
//
//                if (inkEntity.getFaceContents(blockHitResult.getSide().getOpposite()) instanceof AInventoryCircle) {
//
//                    ItemStack stack = ((AInventoryCircle) inkEntity.getFaceContents(blockHitResult.getSide().getOpposite())).retrieveItem();
//                    if (!stack.isEmpty() && !playerEntity.isCreative())
//                        playerEntity.giveItemStack(stack);
//
//                    info.setReturnValue(false);
//                }
//            }
//        }
//    }
}
