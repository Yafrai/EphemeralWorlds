package net.ephemeralworlds.block.entity;

import net.ephemeralworlds.block.entity.base.ResourceBlockEntity;
import net.ephemeralworlds.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;


public class RuneStoneBlockEntity extends ResourceBlockEntity {

    public RuneStoneBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.RESOURCE_BLOCK_ENTITY_RUNESTONE, pos, state);
    }
}