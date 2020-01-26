package net.ephemeralworlds.item.base;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.awt.*;

public class ColorItem extends ModItem implements ItemColorProvider {

    boolean canBeBlank;

    public ColorItem(String uname, boolean canBeBlank) {
        this(new Settings().group(EphemeralWorlds.GROUP), uname, canBeBlank);
    }

    public ColorItem(Settings settings, String uname, boolean canBeBlank) {
        super(settings, uname);
        this.canBeBlank = canBeBlank;
    }

    @Override
    public int getColor(ItemStack stack, int tintIndex) {
        if (tintIndex == 0) {
            EnumColor color = getTagColor(stack);
            return color!=null?color.getColorValue():new Color(255, 255, 255).getRGB();
        }
        else
            return new Color(255, 255, 255).getRGB();
    }

    public EnumColor getTagColor(ItemStack stack) {
        CompoundTag tag = getItemTag(stack);

        if (!tag.containsKey("color")) {
            int col = canBeBlank?-1:0;
            tag.putInt("color", col);
        }
        return EnumColor.byIndex(tag.getInt("color"));
    }

    public void setTagColor(ItemStack stack, EnumColor color) {
        CompoundTag tag = getItemTag(stack);

        tag.putInt("color", color!=null?color.getIndex():-1);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        ItemStack itemStack = playerEntity.getStackInHand(hand);
        if (playerEntity.isCreative() && playerEntity.isSneaking()) {
            playerEntity.setCurrentHand(hand);
            cycleThroughItemColor(itemStack);
            return new TypedActionResult(ActionResult.SUCCESS, itemStack);
        } else {
            return super.use(world, playerEntity, hand);
        }
    }

    public ItemStack cycleThroughItemColor(ItemStack stack) {
        EnumColor color = getTagColor(stack);
        int i = color.getIndex();
        i++;
        if (i >= EnumColor.values().length)
            i = 0;

        setTagColor(stack, EnumColor.byIndex(i));
        return stack;
    }
}
