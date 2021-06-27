package net.ephemeralworlds.biome.feature;

import net.ephemeralworlds.EphemeralWorlds;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

public class ModFeatures {

//    public static final Feature<TreeFeatureConfig> ILLUSION_TREE = register("illusion_tree", new IllusionTreeFeature(TreeFeatureConfig::deserialize));
//    public static final Feature<TreeFeatureConfig> SPIRIT_TREE = register("spirit_tree", new SpiritTreeFeature(TreeFeatureConfig::deserialize));
//    public static final Feature<TreeFeatureConfig> RIFT_TREE = register("rift_tree", new RiftTreeFeature(TreeFeatureConfig::deserialize));
//
//    public static final Feature<DefaultFeatureConfig> CRATER = register("crater", new CraterFeature(DefaultFeatureConfig::deserialize));



    private static <C extends FeatureConfig, F extends Feature<C>> F register(String string_1, F feature_1) {
        Registry.register(Registry.FEATURE, new Identifier(EphemeralWorlds.MODID, string_1), feature_1);
        return feature_1;
    }
}
