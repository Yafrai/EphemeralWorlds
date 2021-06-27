package net.ephemeralworlds.block.entity.parts;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.item.Brush;
import net.ephemeralworlds.recipe.FusionCircleRecipe;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.Optional;

public class FusionCircle extends AInventoryCircle {

    EnumColor paintColor = null;

    public FusionCircle(InkDrawBlockEntity drawEntity, Direction face) {
        super(drawEntity, face, EnumColor.BLUE);
    }

    @Override
    public String getName() {
        return "fusion_circle";
    }

    @Override
    public int getMaxUsages() {
        return 1;
    }

    public boolean activateWithPaint(ItemStack brushStack) {

        if (!(brushStack.getItem() instanceof Brush))
            return false;
        Brush brush = (Brush)brushStack.getItem();

        Optional<FusionCircleRecipe> recipe = getWorld().getRecipeManager().getFirstMatch(FusionCircleRecipe.TYPE, this, getWorld());

        if (recipe.isPresent() && brush.getTagInkAmount(brushStack) >= 1 && recipe.get().colorFits(brush.getTagColor(brushStack))) {
//            inventory.clear();
//            inventory.set(0, recipe.get().getActualOutput(brush.getTagColor(stack)).copy());

            paintColor = brush.getTagColor(brushStack);
            activate(true);
            brush.wipeInk(brushStack);
            saveAndNotify();
            return true;
        }

        return false;
    }

    public boolean endAction() {
        // todo amount
        int amount = 1;
        Optional<FusionCircleRecipe> recipe = getWorld().getRecipeManager().getFirstMatch(FusionCircleRecipe.TYPE, this, getWorld());

        if (recipe.isPresent() && amount >= 1 && recipe.get().colorFits(paintColor)) {
            inventory.clear();
//            inventory.set(0, recipe.get().getActualOutput(paintColor).copy());
            BlockPos pos = drawEntity.getPos();
            ItemEntity itemEntity = new ItemEntity(getWorld(), pos.getX(), pos.getY(), pos.getZ(), recipe.get().getActualOutput(paintColor).copy());
            getWorld().spawnEntity(itemEntity);
//            activate(false);
            paintColor = null;
            return true;
        }

        return false;
    }

    @Override
    public NbtCompound writeTag(NbtCompound tag) {
        tag.putInt("paint_color", EnumColor.indexOf(paintColor));

        return tag;
    }

    @Override
    public void readTag(NbtCompound tag) {
        this.paintColor = EnumColor.byIndex(tag.getInt("paint_color"));
    }

    @Override
    public void tick() {
        super.tick();
        if (paintColor == null)
            return;

//        if (getWorld().getTime() - activatedSince >= 5 * 20)
//            endRecipe();
    }

    long getActivationTime() {
        if (activatedSince > 0)
            return getWorld().getTime() - activatedSince;
        else
            return 0;
    }

    @Override
    public float getItemRotation() {
        long t = getActivationTime();
        float v = 0.01F;
//        if (t > 3 * 20) {
//
//            return 3 * 20 * v * t / 2 - 3 * 20 * v / 2;
//        }
//        else if (t > 0) {
        if (t > 0)
            return t * t * v / 2;
        else
            return 0;
    }

    @Override
    public float getItemRadius() {
        long t = getActivationTime();

        if (t > 5 * 20) {
            return 0.05F;
        }
        else if (t > 3 * 20) {
            return 0.825F * (1F - 0.01F * t) + 0.05F;
        }

        return 0.4F;
    }

    @Override
    public boolean lockedItems() {
        return paintColor != null || fainting;
    }

    @Override
    public int size() {
        return 6;
    }
}
