package net.ephemeralworlds.item.base;

import net.ephemeralworlds.EphemeralWorlds;


public class GlyphItem extends ColorItem {


    public GlyphItem(String uname, boolean canBeBlank) {
        this(new Settings().group(EphemeralWorlds.GROUP), uname, canBeBlank);
    }

    public GlyphItem(Settings settings, String uname, boolean canBeBlank) {
        super(settings, uname, canBeBlank);
    }
}
