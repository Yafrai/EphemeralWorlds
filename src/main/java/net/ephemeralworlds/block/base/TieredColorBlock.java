package net.ephemeralworlds.block.base;

import net.ephemeralworlds.item.base.ITieredResource;
import net.ephemeralworlds.utils.enums.EnumColorBrightness;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;

public class TieredColorBlock extends ColorBlock implements ITieredResource {

    protected int tier;

    public TieredColorBlock(String uname, Block base, EnumColorBrightness brightness, int tier) {
        super(uname, FabricBlockSettings.copy(base).ticksRandomly().noCollision().breakInstantly().build(), brightness);
        this.tier = tier;
    }

    @Override
    public int getTier() {
        return tier;
    }
}
