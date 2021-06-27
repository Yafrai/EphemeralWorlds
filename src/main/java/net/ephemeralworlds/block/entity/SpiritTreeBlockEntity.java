package net.ephemeralworlds.block.entity;

import net.ephemeralworlds.block.entity.base.StructureResourceBlockEntity;
import net.ephemeralworlds.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;


public class SpiritTreeBlockEntity extends StructureResourceBlockEntity {

    public SpiritTreeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RESOURCE_BLOCK_ENTITY_SPIRIT_TREE, pos, state);
    }

//    @Override
//    protected ModTreeFeature getFeature() {
//        return new SpiritTreeFeature(null);
//    }
}
