package net.ephemeralworlds.dimension.generation;

import com.google.common.collect.Sets;
import net.ephemeralworlds.biome.feature.ModFeatures;
import net.ephemeralworlds.biome.feature.ModTreeFeature;
import net.ephemeralworlds.block.base.ColorBlock;
import net.ephemeralworlds.registry.ModBlocks;
import net.ephemeralworlds.registry.ModDimensions;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.instanceHandler.InstanceOptions;
import net.ephemeralworlds.utils.tools.BlockAndPos;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IslandGenerator {

    private static BlockState STONE;
    private static BlockState DIRT;
    private static BlockState GRASS;
    private static BlockState SAND;

    public static void createIsland(InstanceOptions options, MinecraftServer server) {

        if (server == null)
            return;

        World world = server.getWorld(ModDimensions.ephemerium);

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
                generateGround(options, blocks, DIRT, STONE, STONE, 3, random, world);
                break;

            case FOREST:
                generateGround(options, blocks, GRASS, DIRT, STONE, 3, random, world);

                generateTrees(options, 5, 1/16F, random, world);
                break;

            case LAKE:
                generateGround(options, blocks, DIRT, SAND, STONE, 2, random, world);
                break;

            case RUINS:
                generateGround(options, blocks, GRASS, DIRT, STONE, 3, random, world);
                break;

            case VILLAGE:
                generateGround(options, blocks, GRASS, DIRT, STONE, 3, random, world);
                break;

            case VOLCANO:
                generateGround(options, blocks, STONE, STONE, STONE, 3, random, world);
                break;
        }


        generate(blocks, world);
    }

    public static void generateGround(InstanceOptions options, List<BlockAndPos> blocks, BlockState surface, BlockState under, BlockState deep, int reduction, Random random, World world) {
        int r = options.getRadius();
        int y = options.getCenterY();

        float terium_density = 1/16F;
        float xerium_density = 1/16F;
        float gem1_density = 1/32F;
        float gem2_density = options.getIslandLevel()>=2?1/32F:0F;
        float gem3_density = options.getIslandLevel()>=3?1/32F:0F;

        BlockState state;

        while (r>0) {
            for (int x = -r; x <= r; x++) {
                int zm = (int) Math.round(Math.sqrt(r * r - x * x));
                for (int z = -zm; z <= zm; z++) {

                    boolean terium = random.nextFloat() < terium_density;
                    boolean xerium = random.nextFloat() < xerium_density;
                    boolean gem1 = random.nextFloat() < gem1_density;
                    boolean gem2 = random.nextFloat() < gem2_density;
                    boolean gem3 = random.nextFloat() < gem3_density;

                    if (y == options.getCenterY())
                        state = surface;
                    else if (y <= options.getCenterY()-3 || Math.abs(x) == r || Math.abs(z) == zm)
                        state = deep;
                    else
                        state = under;

                    if (terium || xerium || gem1 || gem2 || gem3 && state.getBlock().equals(ModBlocks.COLOR_STONE)) {
                        if (gem3)
                            state = ColorBlock.getStateWithColor(ModBlocks.GEM_ORE3.getDefaultState(), options.getColor());
                        else if (gem2)
                            state = ColorBlock.getStateWithColor(ModBlocks.GEM_ORE2.getDefaultState(), options.getColor());
                        else if (gem1)
                            state = ColorBlock.getStateWithColor(ModBlocks.GEM_ORE1.getDefaultState(), options.getColor());
                        else {
                            if (terium && xerium) {
                                Block ore = random.nextBoolean()?ModBlocks.TERIUM_ORE:ModBlocks.XERIUM_ORE;
                                state = ColorBlock.getStateWithColor(ore.getDefaultState(), options.getColor());
                            }
                            else if (terium)
                                state = ColorBlock.getStateWithColor(ModBlocks.TERIUM_ORE.getDefaultState(), options.getColor());
                            else
                                state = ColorBlock.getStateWithColor(ModBlocks.XERIUM_ORE.getDefaultState(), options.getColor());
                        }
                    }

                    blocks.add(new BlockAndPos(state, new BlockPos(x + options.getCenterX(), y, z + options.getCenterZ()), false));
                }
            }

            r -= reduction;
            y--;
        }

        generate(blocks, world);
    }

    // Densities are in average nb of tress per chunk
    public static void generateTrees(InstanceOptions options, float illusionTreeDensity, float spiritTreeDensity, Random random, World world) {
        float c = (float)Math.PI * options.getRadius() * options.getRadius() / 256;
        float ci = c * illusionTreeDensity;
        float cs = c * spiritTreeDensity;

        generateTreeClass(options, cs, (ModTreeFeature)ModFeatures.SPIRIT_TREE, random, world);
        generateTreeClass(options, ci, (ModTreeFeature)ModFeatures.ILLUSION_TREE, random, world);
    }

    public static void generateTreeClass(InstanceOptions options, float density, ModTreeFeature feature, Random random, World world) {

        boolean up = random.nextFloat() <= density % 1;

        int count = Math.round((int)Math.floor(density)) + (up?1:0);
        while (count > 0) {
            BlockPos position = getRandomPosition(options, random);
            feature.generate(Sets.newHashSet(), world, random, position, MutableIntBoundingBox.empty());
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

    public static void generate(List<BlockAndPos> blocks, World world) {
        for (BlockAndPos bp: blocks) {
            world.setBlockState(bp.pos, bp.state);
        }

        blocks.clear();
    }
}
