package net.ephemeralworlds.item.base;

import net.ephemeralworlds.EphemeralWorlds;
import net.minecraft.client.color.item.ItemColorProvider;

public class ColorTieredItem extends ColorItem implements ITieredResource {

    protected int tier;

    public ColorTieredItem(String uname, boolean canBeBlank, int tier) {
        this(new Settings().group(EphemeralWorlds.GROUP), uname, canBeBlank, tier);
    }

    public ColorTieredItem(Settings settings, String uname, boolean canBeBlank, int tier) {
        super(settings, uname, canBeBlank);
        this.tier = tier;
    }

    @Override
    public int getTier() {
        return tier;
    }
}
