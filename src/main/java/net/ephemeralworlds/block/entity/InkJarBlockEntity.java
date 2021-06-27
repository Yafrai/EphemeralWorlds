package net.ephemeralworlds.block.entity;

import net.ephemeralworlds.block.entity.base.ModInkTankBlockEntity;
import net.ephemeralworlds.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;


public class InkJarBlockEntity extends ModInkTankBlockEntity {

    public InkJarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.INK_JAR_ENTITY, pos, state);
    }

    @Override
    protected int getTankSize() {
        return 30;
    }
}
