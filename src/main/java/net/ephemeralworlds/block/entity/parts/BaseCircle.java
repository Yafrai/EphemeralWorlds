package net.ephemeralworlds.block.entity.parts;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.item.Brush;
import net.ephemeralworlds.utils.enums.CircleType;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Direction;

public class BaseCircle extends AInventoryCircle {

    public BaseCircle(InkDrawBlockEntity drawEntity, Direction face, EnumColor color) {
        super(drawEntity, face, color);
    }

    @Override
    public boolean activateWithPaint(ItemStack brushStack) {

        Brush brush = (Brush)brushStack.getItem();
        EnumColor brushColor = brush.getTagColor(brushStack);

        if (!color.equals(brushColor) || brush.getTagInkAmount(brushStack) < 1)
            return false;

        ItemStack itemStack = inventory.get(0);

        CircleType type = CircleType.fromItem(itemStack, color);

        if (type != null) {
            ACircle circle = null;
            switch (type) {
                case FUSION:
                    circle = new FusionCircle(drawEntity, face);
                    break;
                case FIRE:
                    circle = new FireCircle(drawEntity, face);
                    break;
                case INK:
                    circle = new InkCircle(drawEntity, face, color);
                    break;
                case SEAL:
                    circle = new SealCircle(drawEntity, face, color);
                    break;
                case REWIND:
                    circle = new RewindCircle(drawEntity, face);
                    break;
                case ACTIVATION:
                    circle = new ActivationCircle(drawEntity, face, color);
                    break;
            }

            drawEntity.setCircle(face.getOpposite(), circle);
            brush.useInk(brushStack, 1);
            return true;
        }

        return false;
    }

    @Override
    public String getName() {
        return "base_circle";
    }

    @Override
    public int getMaxUsages() {
        return 1;
    }

    @Override
    public boolean endAction() {
        return false;
    }

    @Override
    public NbtCompound writeTag(NbtCompound tag) {
        return tag;
    }

    @Override
    public void readTag(NbtCompound tag) {
    }

    @Override
    public void tick() {

    }

    @Override
    public float getItemRotation() {
        return 0;
    }

    @Override
    public float getItemRadius() {
        return 0.4F;
    }

    @Override
    public boolean lockedItems() {
        return false;
    }

    @Override
    public int size() {
        return 1;
    }
}
