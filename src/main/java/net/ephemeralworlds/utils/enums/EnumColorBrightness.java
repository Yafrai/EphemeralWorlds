package net.ephemeralworlds.utils.enums;

import net.minecraft.util.StringIdentifiable;

public enum EnumColorBrightness implements StringIdentifiable {
    DEFAULT(0, "default"),
    MINERAL(1, "mineral"),
    ORGANIC(2, "organic")
    ;

    private final int index;
    private final String name;

    EnumColorBrightness(int index, String name) {
        this.index = index;
        this.name = name;
    }

    @Override
    public String asString() {
        return name;
    }
}
