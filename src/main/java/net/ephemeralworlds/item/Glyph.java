package net.ephemeralworlds.item;

import net.ephemeralworlds.item.base.ColorItem;
import net.ephemeralworlds.utils.enums.EnumGlyph;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class Glyph extends ColorItem {

    public Glyph(String uname) {
        super(uname, false);
    }

    public EnumGlyph getGlyph(ItemStack stack) {
        CompoundTag tag = getItemTag(stack);

        if (!tag.containsKey("glyph")) {
            tag.putInt("glyph", 0);
        }
        return EnumGlyph.byIndex(tag.getInt("glyph"));
    }

    public void setGlyph(ItemStack stack, EnumGlyph glyph) {
        CompoundTag tag = getItemTag(stack);

        tag.putInt("glyph", glyph.getIndex());
    }

//    public ActionResult useOnBlock(ItemUsageContext itemUsageContext_1) {
//
//        if (itemUsageContext_1.isPlayerSneaking())
//            return ActionResult.PASS;
//
//        ItemStack stack = itemUsageContext_1.getStack();
//        Glyph glyph = (Glyph)stack.getItem();
//
//        EnumColor glyphColor = glyph.getTagColor(stack);
//        EnumGlyph glyphVariant = glyph.getGlyph(stack);
//
//        if (glyphColor == null || glyphVariant == null)
//            return ActionResult.PASS;
//
//        BlockPos pos = itemUsageContext_1.getBlockPos().offset(itemUsageContext_1.getSide());
//
//        BlockEntity entity = itemUsageContext_1.getWorld().getBlockEntity(pos);
//
//        if (entity instanceof InkDrawBlockEntity) {
//            InkDrawBlockEntity drawEntity = (InkDrawBlockEntity)entity;
//
//            ISerializablePart part = drawEntity.getFaceContents(itemUsageContext_1.getSide().getOpposite());
//
//            if (part instanceof Circle) {
//                Circle circle = (Circle)part;
//                if (!circle.hasGlyph() && circle.color == glyphColor) {
//
//                    if (!itemUsageContext_1.getWorld().isClient()) {
//                        circle.setGlyph(glyphVariant);
//                        drawEntity.saveAndNotify();
//                        stack.decrement(1);
//                    }
//                    return ActionResult.SUCCESS;
//                }
//            }
//
//
//        }
//
//        return ActionResult.PASS;
//    }
}
