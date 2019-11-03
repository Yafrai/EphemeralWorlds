package net.ephemeralworlds.registry;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.biome.IllusionForestBiome;
import net.ephemeralworlds.dimension.IllusionDimension;
import net.ephemeralworlds.dimension.ModDimension;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.dimension.DimensionType;


public class ModBiomes {

    public static Biome forest;

    public static void registerBiomes() {
        forest = Registry.register(Registry.BIOME, new Identifier(EphemeralWorlds.MODID, "forest"), new IllusionForestBiome());
    }
}
