package net.ephemeralworlds.block.entity;

import net.ephemeralworlds.block.entity.base.ModInkTankBlockEntity;
import net.ephemeralworlds.registry.ModBlockEntities;


public class InkJarBlockEntity extends ModInkTankBlockEntity {

    public InkJarBlockEntity() {
        super(ModBlockEntities.INK_JAR_ENTITY);
    }

    @Override
    protected int getTankSize() {
        return 30;
    }
}
