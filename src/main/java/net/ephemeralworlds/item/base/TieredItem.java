package net.ephemeralworlds.item.base;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.utils.enums.EnumResourceType;

public class TieredItem extends ModItem implements ITieredResource {

    protected int tier;
    protected EnumResourceType type;

    public TieredItem(String uname, int tier, EnumResourceType type) {
        this(new Settings().group(EphemeralWorlds.GROUP), uname, tier, type);
    }

    public TieredItem(Settings settings, String uname, int tier, EnumResourceType type) {
        super(settings, uname);
        this.tier = tier;
        this.type = type;
    }

    @Override
    public int getTier() {
        return tier;
    }

    @Override
    public EnumResourceType getType() {
        return type;
    }
}
