package net.ephemeralworlds.block.entity;

import net.ephemeralworlds.block.XeriumPiston;
import net.ephemeralworlds.block.base.ColorBlock;
import net.ephemeralworlds.block.entity.base.ModInkTankBlockEntity;
import net.ephemeralworlds.block.entity.base.ModTickingBlockEntity;
import net.ephemeralworlds.registry.ModBlockEntities;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.helpers.ColorHelper;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ParticleTypes;

public class TeriumGrateHopperBlockEntity extends ModTickingBlockEntity implements BlockEntityClientSerializable {

    protected boolean working;
    protected int steps;
    protected final int STEPS = 30;

    public TeriumGrateHopperBlockEntity() {
        super(ModBlockEntities.TERIUM_HOPPER_ENTITY);
    }

    protected int getTicks() {
        return 20;
    }

    protected void onTick() {
        if (!world.isClient) {

            boolean valid = validateStructure();

            if (!valid && working) {
                // Turn down extraction
                working = false;
                steps = 0;
                saveAndNotify();
            }
            else if (valid && !working) {
                // Start extraction
                working = true;
                steps = 0;
                saveAndNotify();
            }
            else if (valid) {
                // Keep extracting
                steps++;

                if (steps >= STEPS)
                    consumeBlock();

                saveAndNotify();
            }
        }
        else {
            if (working) {
//                world.addParticle(ModParticleEffect.RED, pos.getX() -.5, pos.getY(), pos.getZ() - .5, 0.0D, 0.0D, 0.0D);
                world.addParticle(ParticleTypes.DRIPPING_WATER, pos.getX() + .5, pos.getY(), pos.getZ() + .5, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    private void consumeBlock() {
        steps = 0;
        working = false;

        EnumColor color = ColorHelper.getBlockColor(world, pos.up());

        if (color == null)
            return;

        BlockEntity downBlockEntity = world.getBlockEntity(pos.down());

        if (downBlockEntity instanceof ModInkTankBlockEntity) {
            ((ModInkTankBlockEntity)downBlockEntity).receiveInk(1, color);
        }

        world.setBlockState(pos.up(), Blocks.AIR.getDefaultState());

    }

    private boolean validateStructure() {
        boolean valid = true;

        // Color block on top
        Block colorBlock = world.getBlockState(pos.up()).getBlock();
        if (!(colorBlock instanceof ColorBlock))
            return false;

        // Xerium piston with pressure on color block
        BlockState pistonState = world.getBlockState(pos.up(2));
        Block piston = pistonState.getBlock();
        if (!(piston instanceof XeriumPiston && XeriumPiston.isHeadingDownwards(pistonState) && world.isReceivingRedstonePower(pos.up(2))))
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

    @Override
    public void fromClientTag(CompoundTag tag) {
        working = tag.getBoolean("working");
    }

    @Override
    public CompoundTag toClientTag(CompoundTag tag) {
        tag.putBoolean("working", working);
        return tag;
    }

    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putBoolean("working", working);
        tag.putInt("steps", steps);
        return tag;
    }

    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        working = tag.getBoolean("working");
        steps = tag.getInt("steps");
    }

}
