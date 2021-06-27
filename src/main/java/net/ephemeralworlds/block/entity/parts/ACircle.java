package net.ephemeralworlds.block.entity.parts;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.block.entity.base.ModInkTankBlockEntity;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public abstract class ACircle implements ISerializablePart {

    protected final long FAINTING_DELAY = 80;
    protected InkDrawBlockEntity drawEntity;
    protected Direction face;
    protected EnumColor color;
    protected long activatedSince;
    protected boolean fainting;
    protected int usages;

    public ACircle(InkDrawBlockEntity drawEntity, Direction face, EnumColor color) {
        this.drawEntity = drawEntity;
        this.color = color;
        this.activatedSince = -1;
        this.fainting = false;
        this.face = face;
        this.usages = getMaxUsages();
    }

    public EnumColor getColor() {
        return color;
    }
    public World getWorld() {
        return drawEntity.getWorld();
    }

    public boolean isActive() {
        return activatedSince >= 0 && !fainting;
    }

    public void activate(boolean active) {
        if (active != isActive()) {
            if (active) {
                this.activatedSince = getWorld().getTime();
            }
            else {
                this.activatedSince = -1;
                this.usages--;
                if (usages <= 0) {
                    this.fainting = true;
                    this.activatedSince = getWorld().getTime();
                }
            }
            saveAndNotify();
        }
    }

    public abstract boolean activateWithPaint(ItemStack brushStack);
    public long getProcessingTime() {
        return 6 * 20;
    }

    @Override
    public NbtCompound writeTag(NbtCompound tag) {
        tag.putInt("color", EnumColor.indexOf(color));
        tag.putLong("activated", activatedSince);
        tag.putBoolean("fainting", fainting);
        tag.putInt("face", face.ordinal());
        tag.putInt("usages", usages);
        return tag;
    }

    @Override
    public void readTag(NbtCompound tag) {
        color = EnumColor.byIndex(tag.getInt("color"));
        activatedSince = tag.getInt("activated");
        fainting = tag.getBoolean("fainting");
        face = Direction.byId(tag.getInt("face"));
        usages = tag.getInt("usages");
    }

    @Override
    public abstract String getName();

    public abstract int getMaxUsages();

    public abstract boolean endAction();

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

    public boolean isFainting() {
        return fainting;
    }

    public float getFaintingRate() {
        return fainting ? (float)(getActivatedTime()) / FAINTING_DELAY : 0F;
    }

    @Override
    public void tick() {
        if (fainting && getActivatedTime() >= FAINTING_DELAY) {
            vanish();
        }

        if (isActive() && getWorld().getTime() > activatedSince + getProcessingTime()) {
            endAction();
            activate(false);
        }
    }

    public void vanish() {
        drawEntity.eraseFaceContents(this);
    }

    public ModInkTankBlockEntity getInkTankEntity(BlockPos pos) {
        if (pos == null)
            return null;

        BlockEntity entity = getWorld().getBlockEntity(pos);
        if (entity instanceof ModInkTankBlockEntity) {
            return (ModInkTankBlockEntity)entity;
        }

        return null;
    }

    public float getProcessingRatio() {
        if (!isActive()) {
            return 0F;
        }

        return ((float)(getActivatedTime()) / getProcessingTime());
    }

    public long getActivatedTime() {
        if (!isActive() && !fainting)
            return 0L;
        long worldTime = getWorld().getTime();
        return worldTime - activatedSince;
    }
}
