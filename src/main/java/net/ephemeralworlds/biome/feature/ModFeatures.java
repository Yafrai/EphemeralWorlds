package net.ephemeralworlds.biome.feature;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;

public class ModFeatures {

    public static final Feature<DefaultFeatureConfig> ILLUSION_TREE = register("illusion_tree", new IllusionTreeFeature(DefaultFeatureConfig::deserialize, false, false));

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String string_1, F feature_1) {
        return Registry.register(Registry.FEATURE, string_1, feature_1);
    }
}
