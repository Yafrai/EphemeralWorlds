package net.ephemeralworlds.block.entity;

import net.ephemeralworlds.block.entity.base.ModInkTankInventoryBlockEntity;
import net.ephemeralworlds.recipe.TinkeringTableRecipe;
import net.ephemeralworlds.registry.ModBlockEntities;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.helpers.PlayerHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;

import java.util.Optional;


public class TinkeringTableBlockEntity extends ModInkTankInventoryBlockEntity {

    public TinkeringTableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TINKERING_TABLE_ENTITY, pos, state, 9);
    }

    public ActionResult interact(PlayerEntity player, BlockHitResult hitResult) {
        if (player.isSneaking()) {
            return ActionResult.FAIL;
        }

        int slotIndex = getSlotIndex(hitResult);

        if (slotIndex == -1) {
            return ActionResult.FAIL;
        }

        ItemStack playerStack = player.getMainHandStack();
        ItemStack slotStack = getStack(slotIndex);

        if (playerStack.isEmpty()) {
            if (slotStack.isEmpty())
                return ActionResult.FAIL;

            // Pick from slot
            if (!player.world.isClient) {
                PlayerHelper.givePlayerOrDrop(player, slotStack);
                setStack(slotIndex, ItemStack.EMPTY);
                saveAndNotify();
            }
            return ActionResult.SUCCESS;
        }
        else {
            return addItem(player, playerStack, slotIndex);
        }

    }

    public ActionResult addItem(PlayerEntity player, ItemStack stack, int slotIndex) {

        ItemStack invStack = inventory.get(slotIndex);
        if (invStack.isEmpty()) {
            if (!player.world.isClient) {
                inventory.set(slotIndex, stack.split(1));
                saveAndNotify();
            }
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
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
