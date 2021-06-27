package net.ephemeralworlds.utils.enums;

import net.minecraft.util.StringIdentifiable;

public enum EnumGlyph implements StringIdentifiable {

    INK(0, "ink"),
    ENERGY(1, "energy"),
    SOUL( 2, "soul"),
    MARK(3, "mark"),
    CHARM(4, "charm"),
//    ILLUSION(2, "illusion"),

//    ELEMENT(4, "element"),
//    TOTEM(5, "totem"),
    ;

    private final String name;
    private final int index;

    public String getName() {return this.name;}
    public String asString() {return this.name;}
    public int getIndex() {return this.index;}

    public static EnumGlyph byIndex(int index) {
        if (index == -1)
            return null;
        return values()[index];
    }

    EnumGlyph(int index, String name)
    {
        this.index = index;
        this.name = name;
    }

    public static EnumGlyph byName(String name) {
        for (EnumGlyph color: values())
            if (color.name.equals(name))
                return color;
        return null;
    }

    public static int indexOf(EnumGlyph element) {
        if (element == null)
            return -1;

        return element.getIndex();
    }
}
