package net.ephemeralworlds.block.entity.parts;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.item.Brush;
import net.ephemeralworlds.recipe.EaselRecipe;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

import java.util.Optional;

public class FireCircle extends ACircle {

    public FireCircle(InkDrawBlockEntity drawEntity) {
        super(drawEntity, EnumColor.RED);
    }

    @Override
    public String getName() {
        return "fire_circle";
    }


    @Override
    public boolean activateWithPaint(ItemStack brushStack) {
        return false;
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);

//        tag.putInt("paint_color", EnumColor.indexOf(paintColor));
//        tag.putLong("activated", activatedSince);

        return tag;
    }

    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
//        this.paintColor = EnumColor.byIndex(tag.getInt("paint_color"));
//        this.activatedSince = tag.getInt("activated");
    }

    @Override
    public void drop() {

    }

    @Override
    public void tick() {
        if (isActive()) {

        }
        else {

        }
    }
}
