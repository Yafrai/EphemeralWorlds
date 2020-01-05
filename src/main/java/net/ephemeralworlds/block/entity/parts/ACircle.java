package net.ephemeralworlds.block.entity.parts;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Set;

public abstract class ACircle implements ISerializablePart {

    protected InkDrawBlockEntity drawEntity;
    protected EnumColor color;
    protected long activatedSince;

    public ACircle(InkDrawBlockEntity drawEntity, EnumColor color) {
        this.drawEntity = drawEntity;
        this.color = color;
        this.activatedSince = -1;
    }

    public EnumColor getColor() {
        return color;
    }
    public World getWorld() {
        return drawEntity.getWorld();
    }

    public boolean isActive() {
        return activatedSince >= 0;
    }

    public void activate(boolean active) {
        if (active != isActive()) {
            if (active) {
                this.activatedSince = getWorld().getTime();
            }
            else {
                this.activatedSince = -1;
            }
            saveAndNotify();
        }
    }

    public abstract boolean activateWithPaint(ItemStack brushStack);

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        tag.putInt("color", EnumColor.indexOf(color));
        tag.putLong("activated", activatedSince);
        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        color = EnumColor.byIndex(tag.getInt("color"));
        activatedSince = tag.getInt("activated");
    }

    @Override
    public abstract String getName();

    public void markDirty() {
        drawEntity.markDirty();
    }

    public void saveAndNotify() {
        drawEntity.saveAndNotify();
    }

    public BlockEntity getSupportBlockEntity() {
        Direction face = drawEntity.getFace(this);

        if (face == null)
            return null;

        return getWorld().getBlockEntity(this.drawEntity.getPos().offset(face));
    }
}
