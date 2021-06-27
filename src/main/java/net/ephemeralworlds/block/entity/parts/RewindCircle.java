package net.ephemeralworlds.block.entity.parts;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Direction;

public class RewindCircle extends ACircle {

    public RewindCircle(InkDrawBlockEntity drawEntity, Direction face) {
        super(drawEntity, face, EnumColor.GREEN);
    }

    @Override
    public String getName() {
        return "rewind_circle";
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
    public NbtCompound writeTag(NbtCompound tag) {
//        tag.putInt("paint_color", EnumColor.indexOf(paintColor));
//        tag.putLong("activated", activatedSince);

        return tag;
    }

    @Override
    public void readTag(NbtCompound tag) {
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
