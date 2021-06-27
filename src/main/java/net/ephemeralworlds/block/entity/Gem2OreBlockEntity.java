package net.ephemeralworlds.block.entity;

import net.ephemeralworlds.block.entity.base.ResourceBlockEntity;
import net.ephemeralworlds.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;


public class Gem2OreBlockEntity extends ResourceBlockEntity {

    public Gem2OreBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RESOURCE_BLOCK_ENTITY_GEM2, pos, state);
    }
}
