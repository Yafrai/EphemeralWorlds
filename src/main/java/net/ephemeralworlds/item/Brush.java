package net.ephemeralworlds.item;

import net.ephemeralworlds.item.base.ColorTieredIngredientsItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Brush extends ColorTieredIngredientsItem {

    public Brush(String uname) {
        super(uname, true);
    }

    public TypedActionResult<ItemStack> use(World world_1, PlayerEntity playerEntity_1, Hand hand_1) {
        if (playerEntity_1.isSneaking()) {
            if (wipeInk(playerEntity_1.getStackInHand(hand_1))) {
                return new TypedActionResult<>(ActionResult.SUCCESS, playerEntity_1.getStackInHand(hand_1));
            }

        }

        return new TypedActionResult<>(ActionResult.FAIL, playerEntity_1.getStackInHand(hand_1));
    }

    public int getTagInkAmount(ItemStack stack) {
        CompoundTag tag = getItemTag(stack);

        if (!tag.containsKey("amount")) {
            tag.putInt("amount", 0);
        }
        return tag.getInt("amount");
    }

    public void setTagAmount(ItemStack stack, int amount) {
        CompoundTag tag = getItemTag(stack);

        tag.putInt("amount", amount);
    }

    public boolean wipeInk(ItemStack brush) {
        if (getTagInkAmount(brush) > 0) {
            setTagAmount(brush, 0);
            setTagColor(brush, null);
            return true;
        }
        return false;
    }

}
