package net.ephemeralworlds.block.entity.parts;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.item.Brush;
import net.ephemeralworlds.recipe.EaselRecipe;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.enums.EnumGlyph;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;

import javax.swing.*;
import java.util.Optional;
import java.util.Set;

public class FusionCircle extends AInventoryCircle {

    EnumColor paintColor = null;

    public FusionCircle(InkDrawBlockEntity drawEntity) {
        super(drawEntity, EnumColor.BLUE);
    }

    @Override
    public String getName() {
        return "fusion_circle";
    }

    public boolean activateWithPaint(ItemStack brushStack) {

        if (!(brushStack.getItem() instanceof Brush))
            return false;
        Brush brush = (Brush)brushStack.getItem();

        Optional<EaselRecipe> recipe = getWorld().getRecipeManager().getFirstMatch(EaselRecipe.TYPE, this, getWorld());

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

    public boolean endRecipe() {
        // todo amount
        int amount = 1;
        Optional<EaselRecipe> recipe = getWorld().getRecipeManager().getFirstMatch(EaselRecipe.TYPE, this, getWorld());

        if (recipe.isPresent() && amount >= 1 && recipe.get().colorFits(paintColor)) {
            inventory.clear();
            inventory.set(0, recipe.get().getActualOutput(paintColor).copy());

            activate(false);
            paintColor = null;
            return true;
        }

        return false;
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);

        tag.putInt("paint_color", EnumColor.indexOf(paintColor));

        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        this.paintColor = EnumColor.byIndex(tag.getInt("paint_color"));
    }

    @Override
    public void tick() {
        if (paintColor == null)
            return;

        if (getWorld().getTime() - activatedSince >= 5 * 20)
            endRecipe();
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
            return 0F;
        }
        else if (t > 3 * 20) {
            return -0.01F * t + 1F;
        }

        return 0.4F;
    }

    @Override
    public boolean lockedItems() {
        return paintColor != null;
    }

    @Override
    public int getInvSize() {
        return 6;
    }
}
