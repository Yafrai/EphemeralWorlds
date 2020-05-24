package net.ephemeralworlds.block.entity.parts;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.math.Direction;

public class FireCircle extends ACircle {

    public FireCircle(InkDrawBlockEntity drawEntity, Direction face) {
        super(drawEntity, face, EnumColor.RED);
    }

    @Override
    public String getName() {
        return "fire_circle";
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
    public boolean activateWithPaint(ItemStack brushStack) {
        return false;
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);

//        tag.putInt("paint_color", EnumColor.indexOf(paintColor));
//        tag.putLong("activated", activatedSince);

        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
//        this.paintColor = EnumColor.byIndex(tag.getInt("paint_color"));
//        this.activatedSince = tag.getInt("activated");
    }

    @Override
    public void drop() {

    }

    @Override
    public void tick() {
        if (isActive()) {

        }
        else {

        }
    }
}
