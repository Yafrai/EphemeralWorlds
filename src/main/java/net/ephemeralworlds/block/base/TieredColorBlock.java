package net.ephemeralworlds.block.base;

import net.ephemeralworlds.item.base.ITieredResource;
import net.ephemeralworlds.utils.enums.EnumColorBrightness;
import net.ephemeralworlds.utils.enums.EnumResourceType;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;

public class TieredColorBlock extends ColorBlock implements ITieredResource {

    protected int tier;
    protected EnumResourceType type;

    public TieredColorBlock(String uname, Block base, EnumColorBrightness brightness, int tier, EnumResourceType type) {
        super(uname, FabricBlockSettings.copy(base).ticksRandomly().noCollision().breakInstantly().build(), brightness);
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
