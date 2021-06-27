package net.ephemeralworlds.block.entity.parts;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.Direction;

import java.util.Set;

public abstract class AInventoryCircle extends ACircle implements Inventory {

    protected DefaultedList<ItemStack> inventory;

    public AInventoryCircle(InkDrawBlockEntity drawEntity, Direction face, EnumColor color) {
        super(drawEntity, face, color);
        this.drawEntity = drawEntity;
        this.inventory = DefaultedList.ofSize(size(), ItemStack.EMPTY);
    }

    @Override
    public NbtCompound writeTag(NbtCompound tag) {
        super.writeTag(tag);
        Inventories.writeNbt(tag, inventory);
        return tag;
    }

    @Override
    public void readTag(NbtCompound tag) {
        super.readTag(tag);
        inventory = DefaultedList.ofSize(size(), ItemStack.EMPTY);
        Inventories.readNbt(tag, inventory);
    }

    public boolean addItem(ItemStack stack) {
        if (lockedItems())
            return false;

        for (int i=0; i<inventory.size(); i++) {
            ItemStack invStack = inventory.get(i);
            if (invStack.isEmpty()) {
                ItemStack part = stack.copy();
                part.setCount(1);
                inventory.set(i, part);
                saveAndNotify();
                return true;
            }

        }
        return false;
    }

    public ItemStack retrieveItem() {
        if (lockedItems())
            return ItemStack.EMPTY;

        for (int i=inventory.size()-1; i>=0; i--) {
            ItemStack invStack = inventory.get(i);
            if (!invStack.isEmpty()) {
                inventory.set(i, ItemStack.EMPTY);
                saveAndNotify();
                return invStack;
            }

        }
        return ItemStack.EMPTY;
    }

    public abstract float getItemRotation();

    public abstract float getItemRadius();

    public abstract boolean lockedItems();

    @Override
    public abstract int size();

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : inventory) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return inventory.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int count) {
        ItemStack stack = Inventories.splitStack(inventory, slot, count);
        markDirty();
        return stack;
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack ret = Inventories.removeStack(inventory, slot);
        markDirty();
        return ret;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        markDirty();
    }

    @Override
    public int getMaxCountPerStack() {
        return 0;
    }

    @Override
    public void markDirty() {
        drawEntity.markDirty();
    }

    public void saveAndNotify() {
        drawEntity.saveAndNotify();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity var1) {
        return true;
    }

    @Override
    public void onOpen(PlayerEntity playerEntity_1) {

    }

    @Override
    public void onClose(PlayerEntity playerEntity_1) {

    }

    @Override
    public void clear() {
        inventory.clear();
        markDirty();
    }

    public void drop() {
        for (ItemStack stack: inventory) {
            if (!stack.isEmpty()) {
//                drawEntity.getWorld(). // todo
            }
        }
    }

    public int getItemCount() {
        int c = 0;
        for (ItemStack stack: inventory) {
            if (!stack.isEmpty()) {
                c++;
            }
        }
        return c;
    }
}
