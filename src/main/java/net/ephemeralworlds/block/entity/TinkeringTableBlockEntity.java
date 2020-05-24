package net.ephemeralworlds.block.entity;

import net.ephemeralworlds.block.entity.base.ModInkTankInventoryBlockEntity;
import net.ephemeralworlds.block.entity.base.ModInventoryBlockEntity;
import net.ephemeralworlds.recipe.FusionCircleRecipe;
import net.ephemeralworlds.recipe.TinkeringTableRecipe;
import net.ephemeralworlds.registry.ModBlockEntities;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.helpers.PlayerHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.Optional;


public class TinkeringTableBlockEntity extends ModInkTankInventoryBlockEntity {

    public TinkeringTableBlockEntity() {
        super(ModBlockEntities.TINKERING_TABLE_ENTITY, 9);
    }

    public boolean interact(PlayerEntity player, BlockHitResult hitResult) {
        if (player.isSneaking()) {
            return false;
        }

        int slotIndex = getSlotIndex(hitResult);

        if (slotIndex == -1) {
            return false;
        }

        ItemStack playerStack = player.getMainHandStack();
        ItemStack slotStack = getInvStack(slotIndex);

        if (playerStack.isEmpty()) {
            if (slotStack.isEmpty())
                return false;

            // Pick from slot
            if (!player.world.isClient) {
                PlayerHelper.givePlayerOrDrop(player, slotStack);
                setInvStack(slotIndex, ItemStack.EMPTY);
                saveAndNotify();
            }
            return true;
        }
        else {
            return addItem(player, playerStack, slotIndex);
        }

    }

    public boolean addItem(PlayerEntity player, ItemStack stack, int slotIndex) {

        ItemStack invStack = inventory.get(slotIndex);
        if (invStack.isEmpty()) {
            if (!player.world.isClient) {
                inventory.set(slotIndex, stack.split(1));
                saveAndNotify();
            }
            return true;
        }
        return false;
    }

    public int getSlotIndex(BlockHitResult hitResult) {
        if (hitResult.getSide() != Direction.UP)
            return -1;

        Vec3d pos = hitResult.getPos();
        BlockPos blockPos = hitResult.getBlockPos();
        double x = pos.x - blockPos.getX();
        double z = pos.z - blockPos.getZ();

        int xsec = x > 0.66F ? 2 : x > 0.33F ? 1 : 0;
        int zsec = z > 0.66F ? 2 : z > 0.33F ? 1 : 0;

        return xsec + 3 * zsec;
    }

    @Override
    protected int getTankSize() {
        return isRecipeValid() ? 10 : 0;
    }


    public ItemStack getRecipeResult() {

        Optional<TinkeringTableRecipe> recipe = getWorld().getRecipeManager().getFirstMatch(TinkeringTableRecipe.TYPE, this, getWorld());

        if (recipe.isPresent()) {
            return recipe.get().getActualOutput(getColor());
        }

        return null;
    }

    public boolean isRecipeValid() {
        return getRecipeResult() != null;
    }

    protected void addInk(int amount, EnumColor color) {
        super.addInk(amount, color);

        if (isFull()) {
            craftRecipe();
        }

        saveAndNotify();
    }

    private void craftRecipe() {
        if (!world.isClient()) {

            ItemStack result = getRecipeResult().copy();

            ItemEntity itemEntity = new ItemEntity(getWorld(), pos.getX(), pos.getY() + 1, pos.getZ(), result);
            getWorld().spawnEntity(itemEntity);

            inventory.clear();
            emptyInk();
        }
    }
}
