package net.ephemeralworlds.registry;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.block.*;
import net.ephemeralworlds.block.base.*;
import net.ephemeralworlds.item.base.ColorBlockItem;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    private static List<Pair<String, Block>> BLOCKS = new ArrayList<>();

    // Crafted
    public static final Block MAGIC_MUSHROOM_BLOCK = new MagicMushroomBlock("magic_mushroom_block");
    public static final Block TERIUM_BLOCK = new ModBlock("terium_block", Blocks.IRON_BLOCK);
    public static final Block XERIUM_BLOCK = new ModBlock("xerium_block", Blocks.IRON_BLOCK);
//    public static final Block XERIUM_INK_BOWL = new InkJar("xerium_ink_bowl", Blocks.GLASS);
    public static final Block INK_JAR = new InkJar("ink_jar", Blocks.GLASS);
    public static final Block XERIUM_PISTON = new XeriumPiston("xerium_piston", Blocks.PISTON);
    public static final Block TERIUM_GRATE_HOPPER = new TeriumGratedHopper("terium_grate_hopper", Blocks.HOPPER);

    // Tiered
    public static final Block COLOR_STONE = new ColorBlock("color_stone", Blocks.STONE);
    public static final Block RUNE_STONE = new ModBlock("rune_stone", Blocks.STONE);

    public static final Block COLOR_LOG = new ColorPillarBlock("color_log", Blocks.OAK_LOG);
    public static final Block SPIRIT_LOG = new ModPillarBlock("spirit_log", Blocks.OAK_LOG);
    public static final Block RIFT_LOG = new ModPillarBlock("rift_log", Blocks.OAK_LOG);


    // Color
    public static final Block COLOR_DIRT = new ColorBlock("color_dirt", Blocks.DIRT);
    public static final Block COLOR_GRASS = new ColorBlockCutoutMipped("color_grass", Blocks.GRASS_BLOCK);

    public static final Block COLOR_LEAVES = new ColorBlock("color_leaves", Blocks.OAK_LEAVES);
    public static final Block COLOR_PLANKS = new ColorBlock("color_planks", Blocks.OAK_PLANKS);
    public static final Block SPIRIT_LEAVES = new ModBlock("spirit_leaves", Blocks.OAK_LEAVES);
    public static final Block SPIRIT_PLANKS = new ModBlock("spirit_planks", Blocks.OAK_PLANKS);
    public static final Block RIFT_LEAVES = new ModBlock("rift_leaves", Blocks.OAK_LEAVES);
    public static final Block RIFT_PLANKS = new ModBlock("rift_planks", Blocks.OAK_PLANKS);

    // Dimension
    public static final Block GEM_ORE = new GemOre("gem_ore");
    public static final Block TERIUM_ORE = new ModBlock("terium_ore", Blocks.IRON_ORE);
    public static final Block XERIUM_ORE = new ModBlock("xerium_ore", Blocks.IRON_ORE);
    public static final Block DROPLESS_PLATFORM = new CutoutMippedBlock("dropless_platform", Blocks.GLASS);

    // Advanced
    public static final Block EASEL = new Easel("easel", Blocks.OAK_PLANKS);


    public static void registerBlocks() {
        for (Pair<String, Block> pair: BLOCKS) {
            String name = pair.getLeft();
            Block block = pair.getRight();
            boolean colored = block instanceof BlockColorProvider;
            BlockItem blockItem = colored?new ColorBlockItem(block, new Item.Settings().group(EphemeralWorlds.GROUP)):new BlockItem(block, new Item.Settings().group(EphemeralWorlds.GROUP));

            Registry.register(Registry.BLOCK, new Identifier(EphemeralWorlds.MODID, name), block);
            Registry.register(Registry.ITEM, new Identifier(EphemeralWorlds.MODID, name), blockItem);

            if (colored) {
                ColorProviderRegistry.BLOCK.register(((BlockColorProvider) block), block);

                ColorProviderRegistry.ITEM.register(((ColorBlockItem) blockItem)::getColor, blockItem);
            }
        }
    }

    public static void addBlockForRegistration(String uname, Block block) {
        BLOCKS.add(new Pair<>(uname, block));
    }
}
