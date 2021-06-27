package net.ephemeralworlds.biome.base;

import net.ephemeralworlds.biome.feature.ModFeatures;
import net.ephemeralworlds.registry.ModBlocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
//import net.minecraft.world.gen.decorator.CountExtraChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;

//public abstract class ModBiome extends Biome {
//
//    protected ModBiome(Biome.Settings biome$Settings_1) {
//        super(biome$Settings_1);
//
//        // Gems
////        this.addFeature(
////            GenerationStep.Feature.UNDERGROUND_ORES,
////            Biome.configureFeature(
////                    new ModOreFeature(OreFeatureConfig::deserialize),
////                    new OreFeatureConfig(
////                            OreFeatureConfig.Target.NATURAL_STONE,
////                            ModBlocks.GEM_ORE1.getDefaultState(),
////                            6 //Ore vein size
////                    ),
////                    Decorator.COUNT_RANGE,
////                    new RangeDecoratorConfig(
////                            8, //Number of veins per chunk
////                            32, //Bottom Offset
////                            1, //Min y level
////                            16 //Max y level
////        )));
//
//        // Chromium
////        this.addFeature( // todo
////            GenerationStep.Feature.UNDERGROUND_ORES,
////            Biome.configureFeature(
////                    new ModOreFeature(OreFeatureConfig::deserialize),
////                    new OreFeatureConfig(
////                            OreFeatureConfig.Target.NATURAL_STONE,
////                            ModBlocks.CHROMIUM_ORE.getDefaultState(),
////                            4 //Ore vein size
////                    ),
////                    Decorator.COUNT_RANGE,
////                    new RangeDecoratorConfig(
////                            8, //Number of veins per chunk
////                            32, //Bottom Offset
////                            1, //Min y level
////                            32 //Max y level
////        )));
//
//        // Diachronium
////        this.addFeature( // todo
////            GenerationStep.Feature.UNDERGROUND_ORES,
////            Biome.configureFeature(
////                    new ModOreFeature(OreFeatureConfig::deserialize),
////                    new OreFeatureConfig(
////                            OreFeatureConfig.Target.NATURAL_STONE,
////                            ModBlocks.DIACHRONIUM_ORE.getDefaultState(),
////                            8 //Ore vein size
////                    ),
////                    Decorator.COUNT_RANGE,
////                    new RangeDecoratorConfig(
////                            4, //Number of veins per chunk
////                            32, //Bottom Offset
////                            1, //Min y level
////                            32 //Max y level
////        )));
//    }
//
//    protected void addTreeFeatures(float illusionTree, float spiritTree, float riftTree) {
//        addTreeFeature(riftTree, ModFeatures.RIFT_TREE);
//        addTreeFeature(spiritTree, ModFeatures.SPIRIT_TREE);
//        addTreeFeature(illusionTree, ModFeatures.ILLUSION_TREE);
//    }
//
//    private void addTreeFeature(float probability, Feature<TreeFeatureConfig> feature) {
//          // todo
////        CountExtraChanceDecoratorConfig chance;
////        chance = new CountExtraChanceDecoratorConfig((int)Math.round(Math.floor(probability)), probability - (float)(Math.floor(probability)), (int)Math.round(Math.floor(probability)) + 1);
//
////        this.addFeature( // todo
////                GenerationStep.Feature.VEGETAL_DECORATION,
////                Biome.configureFeature(
////                        Feature.RANDOM_SELECTOR,
////                        new RandomFeatureConfig(
////                                new Feature[]{feature},
////                                new FeatureConfig[]{FeatureConfig.DEFAULT},
////                                new float[]{probability},
////                                feature,
////                                FeatureConfig.DEFAULT),
////                        Decorator.COUNT_EXTRA_HEIGHTMAP, chance)
////        );
//    }
//}
