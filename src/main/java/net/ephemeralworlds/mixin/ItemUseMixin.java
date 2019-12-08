package net.ephemeralworlds.mixin;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.block.entity.parts.AInventoryCircle;
import net.ephemeralworlds.block.entity.parts.ISerializablePart;
import net.ephemeralworlds.item.Brush;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemUseMixin {

	@Inject(method = "useOnBlock", at = @At("HEAD"), cancellable = true)
	public void useOnBlock(ItemUsageContext itemUsageContext, CallbackInfoReturnable<ActionResult> info) {

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
		}
	}
}
