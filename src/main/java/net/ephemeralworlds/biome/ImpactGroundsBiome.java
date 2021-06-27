package net.ephemeralworlds.biome;

//import net.ephemeralworlds.biome.base.ModBiome;
//import net.ephemeralworlds.biome.base.ModSurfaceBuilder;
//import net.ephemeralworlds.biome.base.ModSurfaceConfig;
//import net.ephemeralworlds.biome.feature.CraterFeature;
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.gen.GenerationStep;
//import net.minecraft.world.gen.decorator.Decorator;
//import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
//import net.minecraft.world.gen.feature.DefaultFeatureConfig;
//
//public class ImpactGroundsBiome extends ModBiome {
//
//    public ImpactGroundsBiome() {
////        SurfaceConfig sc = getSurfaceConfig();
//        super(new Settings()
//                .category(Category.PLAINS)
//                .precipitation(Precipitation.RAIN)
//                .configureSurfaceBuilder(ModSurfaceBuilder.DEFAULT, ModSurfaceConfig.IMPACT)
//
//                .depth(1F)
//                .downfall(1F)
//                .scale(.45F)
//                .waterColor(256)
//                .waterFogColor(128)
//                .temperature(1F)
//                .parent(null)
//        );
//
//        // Features
//        addTreeFeatures(0.15F, 0, 0);
//        addCraterFeature();
//    }
//
//    protected void addCraterFeature() {
////        addFeature( // todo
////                GenerationStep.Feature.SURFACE_STRUCTURES,
////                Biome.configureFeature(
////                        new CraterFeature(DefaultFeatureConfig::deserialize),
////                        new DefaultFeatureConfig(),
////                        Decorator.COUNT_RANGE,
////                        new RangeDecoratorConfig(
////                                2, // count
////                                32, // bot offset
////                                0, // top offset
////                                40 // max
////                        )));  // y = random_1.nextInt(rangeDecoratorConfig_1.maximum - rangeDecoratorConfig_1.topOffset) + rangeDecoratorConfig_1.bottomOffset
//    }
//}
