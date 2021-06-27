package net.ephemeralworlds.block.entity.base;

import net.ephemeralworlds.utils.helpers.PlayerHelper;
import net.fabricmc.fabric.api.block.entity.BlockEntityClientSerializable;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public abstract class ModInkTankInventoryBlockEntity extends ModInkTankBlockEntity implements BlockEntityClientSerializable, Inventory {

    protected int size;
    protected DefaultedList<ItemStack> inventory;

    public ModInkTankInventoryBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int size) {
        super(type, pos, state);
        this.size = size;
        this.inventory = DefaultedList.ofSize(size, ItemStack.EMPTY);
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
    public void fromClientTag(NbtCompound tag) {
        super.fromClientTag(tag);
        inventory = DefaultedList.ofSize(size, ItemStack.EMPTY);
        Inventories.readNbt(tag, inventory);
    }

    @Override
    public NbtCompound toClientTag(NbtCompound tag) {
        super.toClientTag(tag);
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

    public boolean putStack(PlayerEntity player, int slot, Hand hand) {
        ItemStack playerStack = player.getStackInHand(hand);
        ItemStack slotStack = getStack(slot);

        if (playerStack.isEmpty())
            return false;

        if (slotStack.isEmpty()) {
            setStack(slot, playerStack);
            player.setStackInHand(hand, ItemStack.EMPTY);
            saveAndNotify();
            return true;
        }

        return false;
    }

    public boolean retrieveStack(PlayerEntity player, int slot) {
        ItemStack stack = removeStack(slot);

        if (stack.isEmpty())
            return false;

        PlayerHelper.givePlayerOrDrop(player, stack);
        saveAndNotify();
        return true;
    }
}
