package net.ephemeralworlds.mixin;

import net.ephemeralworlds.block.base.ColorBlock;
import net.ephemeralworlds.block.entity.base.ModInkTankBlockEntity;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.helpers.ColorHelper;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HopperBlockEntity.class)
public abstract class HopperMixin extends BlockEntity {

	public HopperMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Inject(method = "serverTick", at = @At("HEAD"), cancellable = true)
	private static void serverTick(World world, BlockPos pos, BlockState state, HopperBlockEntity blockEntity, CallbackInfo info) {

		if (world != null && !world.isClient) {

			boolean valid = validateStructure(world, pos, state, blockEntity);
			HopperBlockEntityAccessor accessor = ((HopperBlockEntityAccessor)blockEntity);
			accessor.getCooldown();
			if (!valid && accessor.getNeedsCooldown()) {
				// Turn down extraction
				accessor.setTransferCooldown(0);
				blockEntity.markDirty();
			}
			else if (valid && !accessor.getNeedsCooldown()) {
				// Start extraction
				accessor.setTransferCooldown(60);
				blockEntity.markDirty();
			}
			else if (valid) {
				// Keep extracting
				if (accessor.getCooldown() <= 1)
					consumeBlock(world, pos, state, blockEntity);

				blockEntity.markDirty();
			}

			if (valid) {
				accessor.setTransferCooldown(accessor.getCooldown()-1);
				info.cancel();
			}
		}
		else {
			if (validateStructure(world, pos, state, blockEntity)) {
//                world.addParticle(ModParticleEffect.RED, pos.getX() -.5, pos.getY(), pos.getZ() - .5, 0.0D, 0.0D, 0.0D);
				if (world.getTime() % 30 == 0)
					world.addParticle(ParticleTypes.DRIPPING_WATER, pos.getX() + .5, pos.getY(), pos.getZ() + .5, 0.0D, 0.0D, 0.0D);
			}
		}

	}

	private static boolean validateStructure(World world, BlockPos pos, BlockState state, HopperBlockEntity blockEntity) {

		// Hopper heading down
		BlockState hopperState = world.getBlockState(pos);
		if (!(hopperState.getBlock() instanceof HopperBlock && hopperState.get(HopperBlock.FACING).equals(Direction.DOWN)))
			return false;

		// Color block on top
		Block colorBlock = world.getBlockState(pos.up()).getBlock();
		if (!(colorBlock instanceof ColorBlock))
			return false;

		// Piston with pressure on color block
		BlockState pistonState = world.getBlockState(pos.up(2));
		Block piston = pistonState.getBlock();
//		if (!(piston instanceof PistonBlock && PistonBlock.isHeadingDownwards(pistonState) && world.isReceivingRedstonePower(pos.up(2)))) // todo
		if (!(piston instanceof PistonBlock && world.isReceivingRedstonePower(pos.up(2)) && pistonState.get(FacingBlock.FACING).equals(Direction.DOWN)))
			return false;

		// InkTank below
		BlockEntity inkTankBlockEntity = world.getBlockEntity(pos.down());
		if (!(inkTankBlockEntity instanceof ModInkTankBlockEntity))
			return false;

		// InkTank color and capacity
		EnumColor color = ColorHelper.getBlockColor(world, pos.up());
		if (!((ModInkTankBlockEntity)inkTankBlockEntity).canReceiveColor(color))
			return false;

		return true;
	}

	private static void consumeBlock(World world, BlockPos pos, BlockState state, HopperBlockEntity blockEntity) {

		EnumColor color = ColorHelper.getBlockColor(world, pos.up());

		if (color == null)
			return;

		BlockEntity downBlockEntity = world.getBlockEntity(pos.down());

		if (downBlockEntity instanceof ModInkTankBlockEntity) {
			((ModInkTankBlockEntity)downBlockEntity).receiveInk(1, color);
		}

		// todo put back
//        world.setBlockState(pos.up(), Blocks.AIR.getDefaultState());

	}
}
