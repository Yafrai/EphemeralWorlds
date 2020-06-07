package net.ephemeralworlds.block.base;

import net.ephemeralworlds.item.base.ITieredResource;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;

public class TieredPillarBlock extends ModPillarBlock implements ITieredResource {

    protected int tier;

    public TieredPillarBlock(String uname, Block base,  int tier) {
        super(uname, base);
        this.tier = tier;
    }

    @Override
    public int getTier() {
        return tier;
    }
}
