package net.ephemeralworlds.utils.enums;

import net.ephemeralworlds.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.StringIdentifiable;

import java.awt.*;

public enum CircleType implements StringIdentifiable {

    CRAFTING(0, "crafting", Blocks.CRAFTING_TABLE, EnumColor.BLUE),
    FIRE(1, "fire", Blocks.FURNACE, EnumColor.RED),
    INK(2, "ink", ModBlocks.RUNE_STONE),
;
    private final String name;
    private final int index;
    private final Block block;
    private final EnumColor color;

    public String getName() {return this.name;}
    public String asString() {return this.name;}
    public int getIndex() {return this.index;}
    public Block getBlock() {return this.block;}

    CircleType(int index, String name, Block block) {
        this(index, name, block, null);
    }
    CircleType(int index, String name, Block block, EnumColor color)
    {
        this.index = index;
        this.name = name;
        this.block = block;
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

    public static CircleType fromBlock(Block block, EnumColor inkColor) {
        for (CircleType circle: values())
            if (circle.block.equals(block)) {
                if (circle.colorFits(inkColor))
                    return circle;
                else
                    return null;
            }
        return null;
    }
}
