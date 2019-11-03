package net.ephemeralworlds.utils.enums;

import net.minecraft.util.StringIdentifiable;

public enum EnumRecipeColor implements StringIdentifiable {

    BLUE(0, "blue"),
    CYAN(1, "cyan"),
    GREEN(2, "green"),
    YELLOW(3, "yellow"),
    RED(4, "red"),
    MAGENTA(5, "magenta"),

    ANY(6, "any"),
//    ANY_BLUE(13, "any_blue"),
//    ANY_GREEN(14, "any_green"),
//    ANY_RED(15, "any_red")
    ;

    private final String name;
    private final int index;

    public String getName() {return this.name;}
    public String asString() {return this.name;}

    public static EnumRecipeColor byIndex(int index) {
        if (index == -1)
            return null;
        return values()[index];
    }

    EnumRecipeColor(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public static EnumRecipeColor byName(String name) {
        for (EnumRecipeColor color: values())
            if (color.name.equals(name))
                return color;
        return null;
    }

    public boolean fits(EnumColor color) {
        switch (this) {
            case ANY:
                return true;

            default:
                // Specific color
                return this.index == color.getIndex();
        }

    }
}
