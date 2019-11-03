package net.ephemeralworlds.dimension;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;

import java.util.function.BiFunction;

public class ModDimension extends DimensionType {

    public ModDimension(int id, String suffix, String saveDir, BiFunction<World, DimensionType, ? extends Dimension> factory, boolean hasSkyLight) {
        super(id, suffix, saveDir, factory, hasSkyLight);
    }
}
