package net.ephemeralworlds.item.base;

import net.ephemeralworlds.utils.enums.EnumResourceType;

public interface ITieredResource {
    int getTier();
    EnumResourceType getType();
}
