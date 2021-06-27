package net.ephemeralworlds.block.entity.parts;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Direction;

public class GlyphCircle extends AInventoryCircle {

    int size;

    public GlyphCircle(InkDrawBlockEntity drawEntity, Direction face, EnumColor color, int size) {
        super(drawEntity, face, color);
        this.size = size;
    }

    @Override
    public String getName() {
        return "glyph_circle";
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
        tag.putInt("size", size);

        return tag;
    }

    @Override
    public void readTag(NbtCompound tag) {
        this.size = tag.getInt("size");
    }

    @Override
    public float getItemRotation() {
        return 0;
    }

    @Override
    public float getItemRadius() {
        return 0;
    }

    @Override
    public boolean lockedItems() {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void drop() {

    }

    @Override
    public void tick() {

    }
}
