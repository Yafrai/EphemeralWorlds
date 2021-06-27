package net.ephemeralworlds.block.base;

import net.ephemeralworlds.item.base.ITieredResource;
import net.ephemeralworlds.utils.enums.EnumResourceType;
import net.minecraft.block.Block;

public class TieredBlock extends ModBlock implements ITieredResource {

    protected int tier;
    protected EnumResourceType type;

    public TieredBlock(String uname, Block base, int tier, EnumResourceType type) {
        super(uname, base);
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
