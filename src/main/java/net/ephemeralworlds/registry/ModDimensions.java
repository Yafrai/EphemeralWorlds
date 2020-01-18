package net.ephemeralworlds.registry;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.dimension.IllusionDimension;
import net.ephemeralworlds.dimension.ModDimensionType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.DimensionType;


public class ModDimensions {

    public static DimensionType ephemerium;
    public static DimensionType trial;

    public static void registerDimensions() {
        ephemerium = Registry.register(
                Registry.DIMENSION,
                4,
                EphemeralWorlds.MODID + ":" + "ephemerium",
                new ModDimensionType(4, "_ephemerium", "EPHEMERIUM", IllusionDimension::new, true)
        );

        trial = Registry.register(
                Registry.DIMENSION,
                5,
                EphemeralWorlds.MODID + ":" + "trial",
                new ModDimensionType(5, "_trial", "TRIAL", IllusionDimension::new, true)
        );
    }

}
