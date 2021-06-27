package net.ephemeralworlds.block.base;

import net.ephemeralworlds.item.base.ITieredResource;
import net.ephemeralworlds.utils.enums.EnumColorBrightness;
import net.ephemeralworlds.utils.enums.EnumResourceType;
import net.minecraft.block.Block;

public class TieredColorPillarBlock extends ColorPillarBlock implements ITieredResource {

    protected int tier;
    protected EnumResourceType type;

    public TieredColorPillarBlock(String uname, Block base, EnumColorBrightness brightness, int tier, EnumResourceType type) {
        super(uname, base, brightness);
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
