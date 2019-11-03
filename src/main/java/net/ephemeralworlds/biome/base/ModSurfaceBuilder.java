package net.ephemeralworlds.biome.base;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.biome.surfaceBuilder.DefaultModSurfaceBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class ModSurfaceBuilder {

    public static final SurfaceBuilder<TernarySurfaceConfig> DEFAULT = register(new DefaultModSurfaceBuilder(TernarySurfaceConfig::deserialize));

    private static SurfaceBuilder register(SurfaceBuilder<TernarySurfaceConfig> surfaceConfig) {
        return Registry.register(Registry.SURFACE_BUILDER, new Identifier(EphemeralWorlds.MODID, "default"), surfaceConfig);
    }
}
