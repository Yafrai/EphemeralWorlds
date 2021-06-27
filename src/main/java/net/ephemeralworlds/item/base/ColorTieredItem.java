package net.ephemeralworlds.item.base;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.utils.enums.EnumResourceType;

public class ColorTieredItem extends ColorItem implements ITieredResource {

    protected int tier;
    protected  EnumResourceType type;

    public ColorTieredItem(String uname, boolean canBeBlank, int tier, EnumResourceType type) {
        this(new Settings().group(EphemeralWorlds.GROUP), uname, canBeBlank, tier, type);
    }

    public ColorTieredItem(Settings settings, String uname, boolean canBeBlank, int tier, EnumResourceType type) {
        super(settings, uname, canBeBlank);
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
