package net.ephemeralworlds.biome.feature;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;
import net.ephemeralworlds.registry.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.predicate.block.BlockPredicate;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ModOreFeatureConfig extends OreFeatureConfig {

    public ModOreFeatureConfig(OreFeatureConfig.Target oreFeatureConfig$Target_1, BlockState blockState_1, int int_1) {
        super(oreFeatureConfig$Target_1, blockState_1, int_1);
    }

    public <T> Dynamic<T> serialize(DynamicOps<T> dynamicOps_1) {
        return new Dynamic(dynamicOps_1, dynamicOps_1.createMap(ImmutableMap.of(dynamicOps_1.createString("size"), dynamicOps_1.createInt(this.size), dynamicOps_1.createString("target"), dynamicOps_1.createString(this.target.getName()), dynamicOps_1.createString("state"), BlockState.serialize(dynamicOps_1, this.state).getValue())));
    }

    public static OreFeatureConfig deserialize(Dynamic<?> dynamic_1) {
        int int_1 = dynamic_1.get("size").asInt(0);
        net.minecraft.world.gen.feature.OreFeatureConfig.Target oreFeatureConfig$Target_1 = net.minecraft.world.gen.feature.OreFeatureConfig.Target.byName(dynamic_1.get("target").asString(""));
        BlockState blockState_1 = (BlockState)dynamic_1.get("state").map(BlockState::deserialize).orElse(Blocks.AIR.getDefaultState());
        return new net.minecraft.world.gen.feature.OreFeatureConfig(oreFeatureConfig$Target_1, blockState_1, int_1);
    }

    public static enum Target {

        NETHERRACK("netherrack", new BlockPredicate(ModBlocks.COLOR_STONE));

        private static final Map<String, Target> nameMap = (Map) Arrays.stream(values()).collect(Collectors.toMap(Target::getName, (oreFeatureConfig$Target_1) -> {
            return oreFeatureConfig$Target_1;
        }));
        private final String name;
        private final Predicate<BlockState> predicate;

        private Target(String string_1, Predicate<BlockState> predicate_1) {
            this.name = string_1;
            this.predicate = predicate_1;
        }

        public String getName() {
            return this.name;
        }

        public Target byName(String string_1) {
            return (Target)nameMap.get(string_1);
        }

        public Predicate<BlockState> getCondition() {
            return this.predicate;
        }
    }
}
