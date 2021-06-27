package net.ephemeralworlds.block.entity;

import net.ephemeralworlds.block.entity.base.StructureResourceBlockEntity;
import net.ephemeralworlds.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;


public class RiftTreeBlockEntity extends StructureResourceBlockEntity {

    public RiftTreeBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RESOURCE_BLOCK_ENTITY_RIFT_TREE, pos, state);
    }

//    @Override
//    protected ModTreeFeature getFeature() {
//        return new RiftTreeFeature(null);
//    }
}
