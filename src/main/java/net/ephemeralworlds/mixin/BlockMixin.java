package net.ephemeralworlds.mixin;

import net.ephemeralworlds.registry.ModBlocks;
import net.ephemeralworlds.registry.ModDimensions;
import net.ephemeralworlds.utils.helpers.DimensionHelper;
import net.ephemeralworlds.utils.instanceHandler.InstanceOptions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Block.class)
public class BlockMixin {
	// onRandomTick
	// onScheduledTick
	@Inject(method = "onRandomTick", at = @At("HEAD"), cancellable = true)
	public void onRandomTick(BlockState blockState, World world, BlockPos blockPos, Random random, CallbackInfo info) {

		if (world.isClient())
			return;

		if (world.getDimension().getType() != ModDimensions.ephemerium)
			return;

		boolean decay;
		InstanceOptions options = DimensionHelper.getInstanceFromPosition(world, blockPos);
		if (options != null)
			decay = options.isDecaying();
		else
			decay = true;

		if (decay) {
			// Self
			BlockState state = world.getBlockState(blockPos);
			if (state.getBlock() != ModBlocks.DECAYED_SOIL && !state.isAir()) {
				world.setBlockState(blockPos, ModBlocks.DECAYED_SOIL.getDefaultState());

			// Underneath
//			for (int h=0; h<=blockPos.getY(); h++) {
//				BlockPos pos = new BlockPos(blockPos.getX(), h, blockPos.getZ());
//				BlockState state = world.getBlockState(pos);
//
//				if (state.getBlock() != ModBlocks.DECAYED_SOIL && !state.isAir()) {
//					world.setBlockState(pos, ModBlocks.DECAYED_SOIL.getDefaultState());
//					break;
//				}



			}
		}
	}
}
