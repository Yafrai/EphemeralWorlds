package net.ephemeralworlds.block.entity.base;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;

public class ModInventoryBlockEntity extends ModBlockEntity implements Inventory {

    protected int size;
    protected DefaultedList<ItemStack> inventory;

    public ModInventoryBlockEntity(BlockEntityType<?> type, int size) {
        super(type);
        this.size = size;
        this.inventory = DefaultedList.ofSize(size, ItemStack.EMPTY);
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        inventory = DefaultedList.ofSize(size, ItemStack.EMPTY);
        Inventories.fromTag(tag, inventory);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, inventory);
        return tag;
    }

    @Override
    public int getInvSize() {
        return size;
    }

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
    public boolean canPlayerUseInv(PlayerEntity var1) {
        return true;
    }

    @Override
    public void clear() {
        inventory.clear();
        markDirty();
    }
}
