package net.ephemeralworlds.dimension.generation;

import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
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
import net.minecraft.structure.JigsawJunction;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.util.math.noise.OctavePerlinNoiseSampler;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkSection;
import net.minecraft.world.chunk.ProtoChunk;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.ArrayList;
import java.util.Iterator;
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
                generateFlowers(options, 5, random, world);
                generateTrees(options, 5, 1/16F, random, world);
                break;

            case LAKE:
                generateGround(options, blocks, GRASS, DIRT, STONE, 2, random, world);
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

        NoiseGenerator noiseGenerator = new NoiseGenerator(random);

        float terium_density = 1/16F;
        float xerium_density = 1/16F;
        float gem1_density = 1/32F;
        float gem2_density = options.getIslandLevel()>=2?1/32F:0F;
        float gem3_density = options.getIslandLevel()>=3?1/32F:0F;

        BlockState state;

        for (int x = -r; x <= r; x++) {
            int zm = (int) Math.round(Math.sqrt(r * r - x * x));
            for (int z = -zm; z <= zm; z++) {

//                double n = noiseGenerator.sampleNoise(x, z);
                double n = noiseGenerator.sampleNoise(x, z);
                int h = options.getCenterY() + (int)Math.round(75 * n);
                double current_r = Math.sqrt(Math.pow(x, 2) + Math.pow(z, 2));
                double d = (r-current_r)/(1.1*r-current_r) * r / (5 * current_r / r + r/64F);
                int negative_h = options.getCenterY() - (int)Math.round(d)  - random.nextInt(3);

                for (int y=h; y>=negative_h; y--) {

                    boolean terium = random.nextFloat() < terium_density && y < options.getCenterY();
                    boolean xerium = random.nextFloat() < xerium_density && y < options.getCenterY();
                    boolean gem1 = random.nextFloat() < gem1_density && y < options.getCenterY();
                    boolean gem2 = random.nextFloat() < gem2_density && y < options.getCenterY() - 8;
                    boolean gem3 = random.nextFloat() < gem3_density && y < options.getCenterY() - 16;

                    if (y >= h)
                        state = surface;
//                    else if (y <= options.getCenterY() - 3 || Math.abs(x) == r || Math.abs(z) == zm)
                    else if (y <= h - 3)
                        state = deep;
                    else
                        state = under;

                    Block currentBlock = state.getBlock();

                    if ((terium || xerium || gem1 || gem2 || gem3) && currentBlock.equals(ModBlocks.COLOR_STONE)) {
                        if (gem3)
                            state = ColorBlock.getStateWithColor(ModBlocks.GEM_ORE3.getDefaultState(), options.getColor());
                        else if (gem2)
                            state = ColorBlock.getStateWithColor(ModBlocks.GEM_ORE2.getDefaultState(), options.getColor());
                        else if (gem1)
                            state = ColorBlock.getStateWithColor(ModBlocks.GEM_ORE1.getDefaultState(), options.getColor());
                        else {
                            if (terium && xerium) {
                                Block ore = random.nextBoolean() ? ModBlocks.TERIUM_ORE : ModBlocks.XERIUM_ORE;
                                state = ColorBlock.getStateWithColor(ore.getDefaultState(), options.getColor());
                            } else if (terium)
                                state = ColorBlock.getStateWithColor(ModBlocks.TERIUM_ORE.getDefaultState(), options.getColor());
                            else
                                state = ColorBlock.getStateWithColor(ModBlocks.XERIUM_ORE.getDefaultState(), options.getColor());
                        }
                    }
                    blocks.add(new BlockAndPos(state, new BlockPos(x + options.getCenterX(), y, z + options.getCenterZ()), false));
                }
            }
        }

        generate(blocks, world);

        BlockPos spawn = options.getSpawn();
        BlockPos newSpawn = getHeightAtPos(options, random, spawn, world);
        options.setSpawn(newSpawn);

    }

    // Densities are in average nb of tress per chunk
    public static void generateFlowers(InstanceOptions options, float flowerDensity, Random random, World world) {
        float c = (float)Math.PI * options.getRadius() * options.getRadius() / 256;
        float density = c * flowerDensity;

        List<BlockAndPos> flowers = new ArrayList<>();

        generateFlower(options, flowers, ModBlocks.FLOWER.getDefaultState(), density, random, world);

        generate(flowers, world);
    }

    public static void generateFlower(InstanceOptions options,List<BlockAndPos> flowers, BlockState flowerState, float density, Random random, World world) {
        boolean up = random.nextFloat() <= density % 1;
        int count = Math.round((int)Math.floor(density)) + (up?1:0);

        while (count > 0) {
            BlockPos position = getRandomPosition(options, random);

            BlockPos surfacePos = getHeightAtPos(options, random, position, world);

            flowers.add(new BlockAndPos(flowerState, surfacePos, true));
            count--;
        }
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

            BlockPos surfacePos = getHeightAtPos(options, random, position, world);

            feature.generate(Sets.newHashSet(), world, random, surfacePos, MutableIntBoundingBox.empty());
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

            if (pos.isWithinDistance(options.getCenter(), options.getRadius())) {
                return pos.up();
            }
        }
    }

    public static BlockPos getHeightAtPos(InstanceOptions options, Random random, BlockPos position, World world) {

        BlockState state;
        BlockPos pos = position.up(8);
        while (pos.getY() > 1) {
            state = world.getBlockState(pos);

            Block block = state.getBlock();

            if (block.equals(ModBlocks.COLOR_DIRT) || block.equals(ModBlocks.COLOR_GRASS) || block.equals(ModBlocks.COLOR_STONE) || block.equals(ModBlocks.COLOR_SAND)) {
                return pos.up();
            }

            pos = pos.down();
        }

        return position;
    }

    public static void generate(List<BlockAndPos> blocks, World world) {
        for (BlockAndPos bp: blocks) {
            world.setBlockState(bp.pos, bp.state);
        }

        blocks.clear();
    }
}
