package net.ephemeralworlds.utils.enums;

import net.ephemeralworlds.registry.ModBlocks;
import net.ephemeralworlds.registry.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringIdentifiable;

public enum CircleType implements StringIdentifiable {

    FUSION(0, "crafting", EnumColor.BLUE, Blocks.CRAFTING_TABLE),
    FIRE(1, "fire", EnumColor.RED, Blocks.FURNACE),
    INK(2, "ink", ModBlocks.RUNE_STONE),
    SEAL(3, "seal", ModItems.TOP),
    REWIND(4, "rewind", ModItems.HOURGLASS),
    ACTIVATION(5, "activation", ModItems.MIRROR),
;
    private final String name;
    private final int index;
    private final Block block;
    private final Item item;
    private final EnumColor color;

    public String getName() {return this.name;}
    public String asString() {return this.name;}
    public int getIndex() {return this.index;}
    public Block getBlock() {return this.block;}

    CircleType(int index, String name, Block block) {
        this(index, name, null, block);
    }
    CircleType(int index, String name, Item item) {
        this(index, name, null, item);
    }
    CircleType(int index, String name, EnumColor color, Block block)
    {
        this.index = index;
        this.name = name;
        this.block = block;
        this.item = null;
        this.color = color;
    }

    CircleType(int index, String name, EnumColor color, Item item)
    {
        this.index = index;
        this.name = name;
        this.block = null;
        this.item = item;
        this.color = color;
    }

    public static CircleType byIndex(int index) {
        if (index == -1)
            return null;
        return index <= values().length ? values()[index] : null;
    }

    public static int indexOf(CircleType element) {
        if (element == null)
            return -1;

        return element.getIndex();
    }

    public boolean colorFits(EnumColor color) {
        if (this.color == null)
            return true;

        return (this.color == color);

    }

    public static CircleType fromItem(ItemStack stack, EnumColor inkColor) {

        Item item = stack.getItem();
        if (item instanceof BlockItem) {
            for (CircleType circle : values())
                if (circle.block != null && circle.block.equals(((BlockItem) item).getBlock())) {
                    if (circle.colorFits(inkColor))
                        return circle;
                    else
                        return null;
                }
        } else {
            for (CircleType circle : values())
                if (circle.item != null && circle.item.equals(item)) {
                    if (circle.colorFits(inkColor))
                        return circle;
                    else
                        return null;
                }
        }
        return null;
    }
}
