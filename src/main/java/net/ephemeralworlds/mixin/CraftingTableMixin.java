package net.ephemeralworlds.mixin;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.block.entity.parts.AInventoryCircle;
import net.ephemeralworlds.item.Brush;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
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

@Mixin(CraftingTableBlock.class)
public class CraftingTableMixin {

	@Inject(method = "activate", at = @At("HEAD"), cancellable = true)
	private void activate(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult, CallbackInfoReturnable<Boolean> info) {

		if (hand == Hand.OFF_HAND) {
			info.setReturnValue(false);
			return;
		}

		if (playerEntity.getStackInHand(hand).getItem() instanceof Brush) {
			info.setReturnValue(false);
		}
		else {
			BlockPos drawingPos = blockPos.offset(blockHitResult.getSide());
			BlockEntity entity = world.getBlockEntity(drawingPos);

			if (entity instanceof InkDrawBlockEntity) {
				InkDrawBlockEntity inkEntity = (InkDrawBlockEntity)entity;

				if (inkEntity.getFaceContents(blockHitResult.getSide().getOpposite()) instanceof AInventoryCircle) {

					if (playerEntity.getStackInHand(hand).isEmpty()) {
						ItemStack stack = ((AInventoryCircle) inkEntity.getFaceContents(blockHitResult.getSide().getOpposite())).retrieveItem();
						if (!stack.isEmpty())
							playerEntity.giveItemStack(stack);
						info.setReturnValue(false);
					}
					else
						info.setReturnValue(false);
				}

			}
		}
	}
}
