package net.ephemeralworlds.block.entity.base;

import net.ephemeralworlds.utils.enums.EnumColor;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;

public abstract class ModInkTankBlockEntity extends ModBlockEntity implements BlockEntityClientSerializable {

    private int storedInk;
    private EnumColor storedInkColor;

    public ModInkTankBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    protected abstract int getTankSize();

    public NbtCompound writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        tag.putInt("ink", storedInk);
        tag.putInt("color", storedInkColor==null?-1:storedInkColor.getIndex());
        return tag;
    }

    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        storedInk = tag.getInt("ink");
        storedInkColor = EnumColor.byIndex(tag.getInt("color"));
    }

    @Override
    public void fromClientTag(NbtCompound tag) {
        storedInk = tag.getInt("ink");
        storedInkColor = EnumColor.byIndex(tag.getInt("color"));
    }

    @Override
    public NbtCompound toClientTag(NbtCompound tag) {
        tag.putInt("ink", storedInk);
        tag.putInt("color", storedInkColor!=null?storedInkColor.getIndex():-1);
        return tag;
    }

    public boolean isFull() {
        return storedInk == getTankSize();
    }

    public boolean isEmpty() {
        return storedInk == 0;
    }

    public EnumColor getColor() {
        return storedInkColor;
    }

    public float getInkRatio() {
        return ((float)storedInk) / getTankSize();
    }

    public boolean canReceiveColor(EnumColor color) {
        return isEmpty() || (storedInkColor == color && !isFull());
    }

    public InkTransferStatus receiveInk(int amount, EnumColor color) {
        // Check valid color
        if (color != storedInkColor && storedInk > 0)
            return getFailedTransferStatus();

        // Check valid amount
        if (storedInk + amount > getTankSize()) {
            int transferred = getTankSize() - storedInk;
            if (transferred > 0) {
                addInk(transferred, color);
                return getRestrictedTransferStatus(transferred, color);
            }
            else {
                drawInk(-transferred);
                return getFailedTransferStatus();
            }
        }

        // Success
        addInk(amount, color);
        return geSuccessfulTransferStatus(amount, color);
    }

    public InkTransferStatus provideInk(int amount) {
        return provideInk(amount, null);
    }

    public InkTransferStatus provideInk(int amount, EnumColor color) {
        // Check valid color and any amount
        if ((color != storedInkColor && color != null) || storedInkColor == null)
            return getFailedTransferStatus();

        if (color == null)
            color = this.storedInkColor;

        //  Check amount
        if (storedInk < amount) {
            int transferred = storedInk;
            drawInk(transferred);
            return getRestrictedTransferStatus(transferred, color);
        }

        // Success
        drawInk(amount);
        return geSuccessfulTransferStatus(amount, color);
    }

    public Pair<Integer, EnumColor> pickInk(int amount) {

        InkTransferStatus status = provideInk(amount);
        return new Pair<>(status.transferredInkAmount, status.transferredInkColor);
    }

    // Effective action without checks
    protected void addInk(int amount, EnumColor color) {
        storedInk += amount;
        if (storedInk > getTankSize())
            storedInk = getTankSize();

        storedInkColor = color;
        saveAndNotify();
    }

    // Effective action without checks
    protected void drawInk(int amount) {
        storedInk -= amount;
        if (storedInk <= 0) {
            storedInk = 0;
            storedInkColor = null;
        }
        saveAndNotify();
    }

    // Empty ink
    protected void emptyInk() {
        storedInk = 0;
        storedInkColor = null;
        saveAndNotify();
    }

    private InkTransferStatus getFailedTransferStatus() {
        return new InkTransferStatus(0, null, false, false);
    }

    private InkTransferStatus getRestrictedTransferStatus(int amount, EnumColor color) {
        return new InkTransferStatus(amount, color, true, false);
    }

    private InkTransferStatus geSuccessfulTransferStatus(int amount, EnumColor color) {
        return new InkTransferStatus(amount, color, true, true);
    }

    public class InkTransferStatus {
        int transferredInkAmount;
        EnumColor transferredInkColor;
        boolean hasTransferredInk; // at least 1 unit
        boolean hasTransferredAllInk; // everything

        public InkTransferStatus(int amount, EnumColor color, boolean success, boolean totalSuccess) {
            transferredInkAmount = amount;
            transferredInkColor = color;
            hasTransferredInk = success;
            hasTransferredAllInk = totalSuccess;
        }

        public boolean isSuccess() {
            return hasTransferredInk;
        }
    }

}
