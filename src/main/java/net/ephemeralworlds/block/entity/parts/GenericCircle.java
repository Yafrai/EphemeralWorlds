package net.ephemeralworlds.block.entity.parts;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;

public class GenericCircle extends AInventoryCircle {

    public GenericCircle(InkDrawBlockEntity drawEntity, Direction face, EnumColor color) {
        super(drawEntity, face, color);
    }

    @Override
    public boolean activateWithPaint(ItemStack brushStack) {
        return false;
    }

    @Override
    public String getName() {
        return "generic_circle";
    }

    @Override
    public int getMaxUsages() {
        return 1;
    }

    @Override
    public boolean endAction() {
        return false;
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
    }

    @Override
    public void tick() {

    }

    @Override
    public float getItemRotation() {
        return 0;
    }

    @Override
    public float getItemRadius() {
        return 0.4F;
    }

    @Override
    public boolean lockedItems() {
        return false;
    }

    @Override
    public int getInvSize() {
        return 6;
    }
}
