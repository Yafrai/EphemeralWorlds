package net.ephemeralworlds.item.base;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.utils.enums.EnumResourceType;


public class TieredColorItem extends ColorItem implements ITieredResource {

    protected int tier;
    protected EnumResourceType type;

    public TieredColorItem(String uname, int tier, EnumResourceType type, boolean canBeBlank) {
        this(new Settings().group(EphemeralWorlds.GROUP), uname, tier, type, canBeBlank);
    }

    public TieredColorItem(Settings settings, String uname, int tier, EnumResourceType type, boolean canBeBlank) {
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
