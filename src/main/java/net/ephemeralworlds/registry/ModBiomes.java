package net.ephemeralworlds.registry;

import net.ephemeralworlds.EphemeralWorlds;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;


public class ModBiomes {

    public static Biome everflowingSands;
    public static Biome spiritForest;
    public static Biome impactGrounds;

    public static void registerBiomes() {
//        everflowingSands = Registry.register(Registry.BIOME, new Identifier(EphemeralWorlds.MODID, "everflowing_sands"), new EverflowingSandsBiome());
//        spiritForest = Registry.register(Registry.BIOME, new Identifier(EphemeralWorlds.MODID, "spirit_forest"), new SpiritForestBiome());
//        impactGrounds = Registry.register(Registry.BIOME, new Identifier(EphemeralWorlds.MODID, "impact_grounds"), new ImpactGroundsBiome());
    }

    public static boolean isBiome(Biome biome) {
        return biome == everflowingSands;
    }
}
