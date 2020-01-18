package net.ephemeralworlds.dimension.generation;

import com.google.common.collect.Sets;
import com.mojang.datafixers.Dynamic;
import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.biome.feature.ModFeatures;
import net.ephemeralworlds.biome.feature.ModTreeFeature;
import net.ephemeralworlds.biome.feature.SpiritTreeFeature;
import net.ephemeralworlds.block.base.ColorBlock;
import net.ephemeralworlds.registry.ModBlocks;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.instanceHandler.InstanceOptions;
import net.ephemeralworlds.utils.tools.BlockAndPos;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class IslandGenerator {

    private static BlockState STONE;
    private static BlockState DIRT;
    private static BlockState GRASS;
    private static BlockState SAND;

    public static void createIsland(InstanceOptions options) {

        EnumColor color = options.getColor();

        Random random = new Random();

        STONE = ColorBlock.getStateWithColor(ModBlocks.COLOR_STONE.getDefaultState(), color);
        DIRT = ColorBlock.getStateWithColor(ModBlocks.COLOR_DIRT.getDefaultState(), color);
        GRASS = ColorBlock.getStateWithColor(ModBlocks.COLOR_GRASS.getDefaultState(), color);
        SAND = ColorBlock.getStateWithColor(ModBlocks.COLOR_SAND.getDefaultState(), color);

//        Set<BlockState> blocks = Sets.newHashSet();
        List<BlockAndPos> blocks = new ArrayList<>();

        IslandTypes type = IslandTypes.FOREST;

        switch (type) {
            case WASTELAND:
            default:
                generateGround(options, blocks, DIRT, STONE, STONE, 3);
                break;

            case FOREST:
                generateGround(options, blocks, GRASS, DIRT, STONE, 3);

                generateTrees(options, 5, 1/16F, random);
                break;

            case LAKE:
                generateGround(options, blocks, SAND, DIRT, STONE, 2);
                break;

            case RUINS:
                generateGround(options, blocks, GRASS, DIRT, STONE, 3);
                break;

            case VILLAGE:
                generateGround(options, blocks, GRASS, DIRT, STONE, 3);
                break;

            case VOLCANO:
                generateGround(options, blocks, STONE, STONE, STONE, 3);
                break;
        }


        generate(blocks);
    }

    public static void generateGround(InstanceOptions options, List<BlockAndPos> blocks, BlockState surface, BlockState under, BlockState deep, int reduction) {
        int r = options.getRadius();
        int y = options.getCenterY();

        BlockState state = surface;

        while (r>0) {
            for (int x = -r; x <= r; x++) {
                int zm = (int) Math.round(Math.sqrt(r * r - x * x));
                for (int z = -zm; z <= zm; z++) {
                    blocks.add(new BlockAndPos(state, new BlockPos(x + options.getCenterX(), y, z + options.getCenterZ()), false));
                }
            }

            r -= reduction;
            y--;
            state = under;
        }

        generate(blocks);
    }

    // Densities are in average nb of tress per chunk
    public static void generateTrees(InstanceOptions options, float illusionTreeDensity, float spiritTreeDensity, Random random) {
        float c = (float)Math.PI * options.getRadius() * options.getRadius() / 256;
        float ci = c * illusionTreeDensity;
        float cs = c * spiritTreeDensity;

        generateTreeClass(options, cs, (ModTreeFeature)ModFeatures.SPIRIT_TREE, random);
        generateTreeClass(options, ci, (ModTreeFeature)ModFeatures.ILLUSION_TREE, random);
    }

    public static void generateTreeClass(InstanceOptions options, float density, ModTreeFeature feature, Random random) {

        boolean up = random.nextFloat() <= density % 1;

        int count = Math.round((int)Math.floor(density)) + (up?1:0);
        while (count > 0) {
            BlockPos position = getRandomPosition(options, random);
            feature.generate(Sets.newHashSet(), EphemeralWorlds.ILLUSION_WORLD, random, position, MutableIntBoundingBox.empty());
            count--;
        }

    }

    public static void generateWastelandDecorations(InstanceOptions options, List<BlockAndPos> blocks) {

    }

    public static BlockPos getRandomPosition(InstanceOptions options, Random random) {
        BlockPos pos;
        while (true) {

            int x = random.nextInt(options.getRadius() * 2) + options.getCenterX() - options.getRadius();
            int z = random.nextInt(options.getRadius() * 2) + options.getCenterZ() - options.getRadius();

            pos = new BlockPos(x, options.getCenterY(), z);

            if (pos.isWithinDistance(options.getCenter(), options.getRadius()))
                return pos.up();
        }
    }

    public static void generate(List<BlockAndPos> blocks) {

        for (BlockAndPos bp: blocks) {
            EphemeralWorlds.ILLUSION_WORLD.setBlockState(bp.pos, bp.state);
        }

        blocks.clear();
    }
}
