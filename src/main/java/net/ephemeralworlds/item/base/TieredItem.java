package net.ephemeralworlds.item.base;

import net.ephemeralworlds.EphemeralWorlds;

public class TieredItem extends ModItem implements ITieredResource {
    protected int tier;

    public TieredItem(String uname, int tier) {
        this(new Settings().group(EphemeralWorlds.GROUP), uname, tier);
    }

    public TieredItem(Settings settings, String uname, int tier) {
        super(settings, uname);
        this.tier = tier;
    }

    @Override
    public int getTier() {
        return tier;
    }
}
