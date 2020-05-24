package net.ephemeralworlds.block.entity.parts;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.math.Direction;

import java.util.Set;

public abstract class AInventoryCircle extends ACircle implements Inventory {

    protected DefaultedList<ItemStack> inventory;

    public AInventoryCircle(InkDrawBlockEntity drawEntity, Direction face, EnumColor color) {
        super(drawEntity, face, color);
        this.drawEntity = drawEntity;
        this.inventory = DefaultedList.ofSize(getInvSize(), ItemStack.EMPTY);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, inventory);
        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        inventory = DefaultedList.ofSize(getInvSize(), ItemStack.EMPTY);
        Inventories.fromTag(tag, inventory);
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
    public abstract int getInvSize();

    @Override
    public boolean isInvEmpty() {
        for (ItemStack stack : inventory) {
            if (!stack.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getInvStack(int slot) {
        return inventory.get(slot);
    }

    @Override
    public ItemStack takeInvStack(int slot, int count) {
        ItemStack stack = Inventories.splitStack(inventory, slot, count);
        markDirty();
        return stack;
    }

    @Override
    public ItemStack removeInvStack(int slot) {
        ItemStack ret = Inventories.removeStack(inventory, slot);
        markDirty();
        return ret;
    }

    @Override
    public void setInvStack(int slot, ItemStack stack) {
        inventory.set(slot, stack);
        markDirty();
    }

    @Override
    public int getInvMaxStackAmount() {
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
    public boolean canPlayerUseInv(PlayerEntity var1) {
        return true;
    }

    @Override
    public void onInvOpen(PlayerEntity playerEntity_1) {

    }

    @Override
    public void onInvClose(PlayerEntity playerEntity_1) {

    }

    @Override
    public boolean isValidInvStack(int int_1, ItemStack itemStack_1) {
        return true; // TODO: 06/12/2019
    }

    @Override
    public int countInInv(Item item_1) {
        return 0; // TODO: 06/12/2019
    }

    @Override
    public boolean containsAnyInInv(Set<Item> set_1) {
        return false;  // TODO: 06/12/2019
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
