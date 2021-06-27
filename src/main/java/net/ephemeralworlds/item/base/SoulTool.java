package net.ephemeralworlds.item.base;

import net.ephemeralworlds.utils.enums.EnumResourceType;

import java.util.Arrays;

public class SoulTool extends ModItem {

    EnumResourceType[] effectiveTypes;

    public SoulTool(String uname, EnumResourceType... types) {
        super(uname);
        this.effectiveTypes = types;
    }

    public boolean isEffectiveAgainst(EnumResourceType type) {
        return Arrays.asList(effectiveTypes).contains(type);
    }
}
