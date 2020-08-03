package net.ephemeralworlds.biome;

import net.ephemeralworlds.biome.base.ModBiome;
import net.ephemeralworlds.biome.base.ModSurfaceBuilder;
import net.ephemeralworlds.biome.base.ModSurfaceConfig;
import net.ephemeralworlds.biome.feature.ModFeatures;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.RandomFeatureConfig;

public class IllusionForestBiome extends ModBiome {

    public IllusionForestBiome() {
//        SurfaceConfig sc = getSurfaceConfig();
        super(new Settings()
                .category(Category.FOREST)
                .precipitation(Precipitation.RAIN)
                .configureSurfaceBuilder(ModSurfaceBuilder.DEFAULT, ModSurfaceConfig.DEFAULT)

                .depth(1F)
                .downfall(1F)
                .scale(.45F)
                .waterColor(256)
                .waterFogColor(128)
                .temperature(1F)
                .parent(null)
        );

        addIllusionTree();
    }

    public void addIllusionTree() {
        this.addFeature(
                GenerationStep.Feature.VEGETAL_DECORATION,
                Biome.configureFeature(
                        Feature.RANDOM_SELECTOR,
                        new RandomFeatureConfig(new Feature[]{ModFeatures.ILLUSION_TREE, ModFeatures.ILLUSION_TREE},
                            new FeatureConfig[]{FeatureConfig.DEFAULT, FeatureConfig.DEFAULT}, new float[]{0.2F, 0.1F}, ModFeatures.ILLUSION_TREE, FeatureConfig.DEFAULT),
                         Decorator.COUNT_EXTRA_HEIGHTMAP, new CountExtraChanceDecoratorConfig(1, 0.1F, 1)));
    }
}
