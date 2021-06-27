package net.ephemeralworlds.mixin;

import net.ephemeralworlds.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class MushroomMixin {

//	@Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
////	                          (BlockState blockState, World world, BlockPos blockPos, int type, int data)
//	private void onBlockAction(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> info) {
//		if (blockState.getBlock().equals(Blocks.BROWN_MUSHROOM) || blockState.getBlock().equals(Blocks.RED_MUSHROOM)) {
//			if (world.isClient) {
//				world.playSound(blockPos.getX(), blockPos.getY(), blockPos.getZ(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.PLAYERS, 1F, 1F, true);
//			}
//			else {
//				world.setBlockState(blockPos, ModBlocks.MAGIC_MUSHROOM_BLOCK.getDefaultState());
//				playerEntity.getStackInHand(hand).decrement(1);
//			}
//
//			info.setReturnValue(ActionResult.CONSUME);
//		}
//
//	}
}
