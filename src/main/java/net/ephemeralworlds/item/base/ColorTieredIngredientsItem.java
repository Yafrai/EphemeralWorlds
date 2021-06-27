package net.ephemeralworlds.item.base;

public class ColorTieredIngredientsItem extends ColorItem {

    public ColorTieredIngredientsItem(String uname, boolean canBeBlank) {
        super(new Settings().maxCount(1), uname, canBeBlank);
    }
}
