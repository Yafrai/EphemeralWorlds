package net.ephemeralworlds.utils.enums;

import net.minecraft.util.StringIdentifiable;

import java.awt.*;

public enum EnumColor implements StringIdentifiable {

    BLUE(0, "blue", 1, 32765),
    CYAN(1, "cyan", 3, 312314),
    GREEN(2, "green", 2, 65215),
    YELLOW(3, "yellow", 1, 8780035),
    RED(4, "red", 1, 16592769),
    MAGENTA(5, "magenta", 3, 16725454),

//    BLUE(0, "blue", 1, 32765),
//    BLUE_GREEN(1, "blue_green", 3, 312314),
//    GREEN(2, "green", 2, 65215),
//    YELLOW_GREEN(3, "yellow_green", 3, 194665),
//    YELLOW(4, "yellow", 1, 8780035),
//    YELLOW_ORANGE(5, "yellow_orange", 3, 16708352),
//    ORANGE(6, "orange", 2, 16557568),
//    RED_ORANGE(7, "red_orange", 3, 16655873),
//    RED(8, "red", 1, 16592769),
//    RED_PURPLE(9, "red_purple", 3, 16725454),
//    PURPLE(10, "purple", 2, 11484927),
//    BLUE_PURPLE(11, "blue_purple", 3, 5716477);
;
    private final String name;
    private final int order;
    private final int index;
    private final int colorValue;

    public String getName() {return this.name;}
    public String asString() {return this.name;}
    public int getIndex() {return this.index;}
    public boolean isPure() {return this.order == 1;}
    public boolean isPrimary() {return this.isPure();}
    public boolean isSecondary() {return this.order == 2;}
    public boolean isTertiary() {return this.order == 3;}
    public int getColorValue() {return this.colorValue;}
    public Color getColor() {return new Color(this.colorValue);}
    public float[] getHSVColor() {
        Color color = getColor();
        return Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
    }
    public int HSVToColor(float[] hsv) {
        return Color.getHSBColor(Math.min(hsv[0], 1F), Math.min(hsv[1], 1F), Math.min(hsv[2], 1F)).getRGB();
    }

    public int getMineralColor() {
        float[] hsv = getHSVColor();
        hsv[1] *= 0.45F;
        hsv[2] *= 1.1F;
        return HSVToColor(hsv);
    }

    public int getVegetalColor() {
        float[] hsv = getHSVColor();
        hsv[1] *= 0.8F;
        return HSVToColor(hsv);
    }

    public static EnumColor byIndex(int index) {
        if (index == -1)
            return null;
        return values()[index];
    }

    EnumColor(int index, String name, int order, int colorValue)
    {
        this.index = index;
        this.name = name;
        this.order = order;
        this.colorValue = colorValue;
    }

    public static EnumColor byName(String name) {
        for (EnumColor color: values())
            if (color.name.equals(name))
                return color;
        return null;
    }

    public boolean fits(EnumRecipeColor recipe) {
        return recipe.fits(this);

    }
}
