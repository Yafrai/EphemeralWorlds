package net.ephemeralworlds.utils.enums;

import net.minecraft.util.StringIdentifiable;

import java.awt.*;

public enum EnumColor implements StringIdentifiable {

    BLUE(0, "blue", 1, 0, 97, 255, 1.5F), // ex 32765
    CYAN(1, "cyan", 3, 0, 241, 255, 0.5F), // ex 312314
    GREEN(2, "green", 2, 77, 255, 50, 2), // ex 65215
    YELLOW(3, "yellow", 1, 255, 250, 36, 0.7F), // ex 8780035
    ORANGE(4, "orange", 1, 255, 157, 38, 0.7F), // ex 8780035
    RED(5, "red", 1, 255, 49, 43,0.8F), // ex 16592769
    MAGENTA(6, "magenta", 3, 244, 78,240, 2), // ex 16725454
    PURPLE(7, "purple", 3, 162, 77,242, 2), // ex 16725454

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
    private final float hueFactor;

    public String getName() {return this.name;}
    public String asString() {return this.name;}
    public int getIndex() {return this.index;}
    public boolean isPure() {return this.order == 1;}
    public boolean isPrimary() {return this.isPure();}
    public boolean isSecondary() {return this.order == 2;}
    public boolean isTertiary() {return this.order == 3;}
    public int getColorValue() {return this.colorValue;}
    public Color getColor() {return new Color(this.colorValue);}
    public float getHueFactor() {return this.hueFactor;
    }
    public float[] getHSVColor() {
        Color color = getColor();
        return Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
    }
    public int HSVToColor(float[] hsv) {
        return Color.getHSBColor(Math.min(hsv[0], 1F), Math.min(hsv[1], 1F), Math.min(hsv[2], 1F)).getRGB();
    }

    public int getMineralColor() {
        float[] hsv = getHSVColor();
        hsv[1] *= 0.15F;
        hsv[2] *= 1F;
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
        return index <= values().length ? values()[index] : EnumColor.byIndex(0);
    }

    EnumColor(int index, String name, int order, int redValue, int greenValue, int blueValue, float hueFactor)
    {
        this.index = index;
        this.name = name;
        this.order = order;
        this.colorValue = new Color(redValue, greenValue, blueValue).getRGB();
        this.hueFactor = hueFactor;
    }

    public static EnumColor byName(String name) {
        for (EnumColor color: values())
            if (color.name.equals(name))
                return color;
        return null;
    }

    public static int indexOf(EnumColor element) {
        if (element == null)
            return -1;

        return element.getIndex();
    }

    public boolean fits(EnumRecipeColor recipe) {
        return recipe.fits(this);
    }

    public int getColorForBrightness(EnumColorBrightness brightness) {
        switch (brightness) {
            case MINERAL:
                return this.getMineralColor();
            case ORGANIC:
                return this.getVegetalColor();
            default:
                return this.getColorValue();
        }
    }
}
