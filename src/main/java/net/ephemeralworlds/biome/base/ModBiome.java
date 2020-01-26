package net.ephemeralworlds.biome.base;

import net.ephemeralworlds.biome.feature.ModOreFeature;
import net.ephemeralworlds.registry.ModBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public abstract class ModBiome extends Biome {

    protected ModBiome(Settings biome$Settings_1) {
        super(biome$Settings_1);

        // Gems
        this.addFeature(
            GenerationStep.Feature.UNDERGROUND_ORES,
            Biome.configureFeature(
                    new ModOreFeature(OreFeatureConfig::deserialize),
                    new OreFeatureConfig(
                            OreFeatureConfig.Target.NATURAL_STONE,
                            ModBlocks.GEM_ORE1.getDefaultState(),
                            6 //Ore vein size
                    ),
                    Decorator.COUNT_RANGE,
                    new RangeDecoratorConfig(
                            8, //Number of veins per chunk
                            32, //Bottom Offset
                            1, //Min y level
                            16 //Max y level
        )));

        this.addFeature(
            GenerationStep.Feature.UNDERGROUND_ORES,
            Biome.configureFeature(
                    new ModOreFeature(OreFeatureConfig::deserialize),
                    new OreFeatureConfig(
                            OreFeatureConfig.Target.NATURAL_STONE,
                            ModBlocks.TERIUM_ORE.getDefaultState(),
                            4 //Ore vein size
                    ),
                    Decorator.COUNT_RANGE,
                    new RangeDecoratorConfig(
                            8, //Number of veins per chunk
                            32, //Bottom Offset
                            1, //Min y level
                            32 //Max y level
        )));

        this.addFeature(
            GenerationStep.Feature.UNDERGROUND_ORES,
            Biome.configureFeature(
                    new ModOreFeature(OreFeatureConfig::deserialize),
                    new OreFeatureConfig(
                            OreFeatureConfig.Target.NATURAL_STONE,
                            ModBlocks.XERIUM_ORE.getDefaultState(),
                            8 //Ore vein size
                    ),
                    Decorator.COUNT_RANGE,
                    new RangeDecoratorConfig(
                            4, //Number of veins per chunk
                            32, //Bottom Offset
                            1, //Min y level
                            32 //Max y level
        )));
    }
}
