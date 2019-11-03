package net.ephemeralworlds.item.base;

import net.minecraft.item.Item;

public class TieredIngredientsItem extends ModItem {

    public TieredIngredientsItem(String uname) {
        super(new Item.Settings().maxCount(1), uname);
    }
}
