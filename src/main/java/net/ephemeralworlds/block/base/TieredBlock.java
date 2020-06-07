package net.ephemeralworlds.block.base;

import net.ephemeralworlds.item.base.ITieredResource;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

import java.util.Objects;

public class TieredBlock extends ModBlock implements ITieredResource {

    protected int tier;

    public TieredBlock(String uname, Block base, int tier) {
        super(uname, base);
        this.tier = tier;
    }

    @Override
    public int getTier() {
        return tier;
    }
}
