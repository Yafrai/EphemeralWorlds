package net.ephemeralworlds.registry;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.dimension.IllusionDimension;
import net.ephemeralworlds.dimension.ModDimension;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.DimensionType;


public class ModDimensions {

    public static DimensionType illusion;

    public static void registerDimensions() {
        illusion = Registry.register(
                Registry.DIMENSION,
                4,
                EphemeralWorlds.MODID + ":" + "illusion",
                new ModDimension(4, "_ephemeraldim", "EPHEMERALDIM", IllusionDimension::new, true)
        );
    }
}
