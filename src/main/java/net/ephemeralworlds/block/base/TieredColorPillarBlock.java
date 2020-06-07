package net.ephemeralworlds.block.base;

import net.ephemeralworlds.item.base.ITieredResource;
import net.ephemeralworlds.utils.enums.EnumColorBrightness;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;

public class TieredColorPillarBlock extends ColorPillarBlock implements ITieredResource {

    protected int tier;

    public TieredColorPillarBlock(String uname, Block base, EnumColorBrightness brightness, int tier) {
        super(uname, base, brightness);
        this.tier = tier;
    }

    @Override
    public int getTier() {
        return tier;
    }
}
