package net.ephemeralworlds.utils.enums;

import net.minecraft.util.StringIdentifiable;

public enum EnumResourceType implements StringIdentifiable {
    STONE(0, "stone"),
    SAND(1, "sand"),
    METAL(2, "metal"),
    PRECIOUS_STONE(3, "precious_stone"),
    WOOD(4, "wood"),
    CROP(5, "crop"),
    BONE(6, "bone"),
    ;

    private final int index;
    private final String name;

    EnumResourceType(int index, String name) {
        this.index = index;
        this.name = name;
    }

    @Override
    public String asString() {
        return name;
    }
}
