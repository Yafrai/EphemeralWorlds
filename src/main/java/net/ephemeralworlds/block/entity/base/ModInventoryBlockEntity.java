package net.ephemeralworlds.block.entity.base;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class ModInventoryBlockEntity extends ModBlockEntity implements Inventory {

    protected int size;
    protected DefaultedList<ItemStack> inventory;

    public ModInventoryBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void readNbt(NbtCompound tag) {
        super.readNbt(tag);
        inventory = DefaultedList.ofSize(size, ItemStack.EMPTY);
        Inventories.readNbt(tag, inventory);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound tag) {
        super.writeNbt(tag);
        Inventories.writeNbt(tag, inventory);
        return tag;
    }

    @Override
    public int size() {
        return size;
    }

    public int getInvSizeNotEmpty() {
        int count = 0;
        for (int i=0; i<size; i++) {
            if (!getStack(i).isEmpty())
                count++;
        }

        return count;
    }

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
    public boolean canPlayerUse(PlayerEntity var1) {
        return true;
    }

    @Override
    public void clear() {
        inventory.clear();
        markDirty();
    }
}
