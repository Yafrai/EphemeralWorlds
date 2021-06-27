package net.ephemeralworlds.registry;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.block.*;
import net.ephemeralworlds.block.base.*;
import net.ephemeralworlds.block.totem.*;
import net.ephemeralworlds.item.base.ColorBlockItem;
import net.ephemeralworlds.utils.enums.EnumColorBrightness;
import net.ephemeralworlds.utils.enums.EnumResourceType;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    private static List<Pair<String, Block>> BLOCKS = new ArrayList<>();


    // Crafted
    public static final Block SOUL_GLASS = new CutoutBlock("soul_glass", Blocks.GLASS);
    public static final Block MAGIC_MUSHROOM_BLOCK = new MagicMushroomBlock("magic_mushroom_block");
    public static final Block CHROMIUM_BLOCK = new ModBlock("chromium_block", Blocks.IRON_BLOCK);
    public static final Block DIACHRONIUM_BLOCK = new ModBlock("diachronium_block", Blocks.IRON_BLOCK);
    public static final Block INK_JAR = new InkJar("ink_jar", Blocks.GLASS);
    public static final Block RUNIC_FORGE = new RunicForge("runic_forge", Blocks.STONE);
//    public static final Block DIACHRONIUM_PISTON = new DiachroniumPiston("diachronium_piston", Blocks.PISTON);
//    public static final Block CHROMIUM_GRATE_HOPPER = new ChromiumGratedHopper("chromium_grate_hopper", Blocks.HOPPER);


    // Tiered and resources
    public static final Block ARCANE_QUARTZ_RESOURCE = new ResourceBlock("arcane_quartz_resource", new Identifier(EphemeralWorlds.MODID, "arcane_quartz"), EnumResourceType.SAND);

    public static final Block RUNE_STONE = new TieredBlock("rune_stone", Blocks.STONE, 3, EnumResourceType.STONE);
    public static final Block RUNE_STONE_RESOURCE = new ResourceBlock("rune_stone_resource", new Identifier(EphemeralWorlds.MODID, "rune_stone"), EnumResourceType.STONE);

    public static final Block CHROMIUM_ORE = new ResourceBlock("chromium_ore_block", ModItems.CHROMIUM_ORE, EnumResourceType.METAL);
    public static final Block DIACHRONIUM_ORE = new ResourceBlock("diachronium_ore_block", ModItems.DIACHRONIUM_ORE, EnumResourceType.METAL);
//    public static final Block GEM_ORE1 = new ResourceBlock("gem_ore_1", ModItems.GEM1, EnumResourceType.PRECIOUS_STONE);
//    public static final Block GEM_ORE2 = new ResourceBlock("gem_ore_2", ModItems.GEM2, EnumResourceType.PRECIOUS_STONE);
//    public static final Block GEM_ORE3 = new ResourceBlock("gem_ore_3", ModItems.GEM3, EnumResourceType.PRECIOUS_STONE);

    public static final Block COLOR_LOG = new TieredColorPillarBlock("color_log", Blocks.OAK_LOG, EnumColorBrightness.ORGANIC, 1, EnumResourceType.WOOD);
    public static final Block COLOR_TREE_RESOURCE = new StructureResourceBlock("color_log_resource", new Identifier(EphemeralWorlds.MODID, "color_log"), EnumResourceType.WOOD);
    public static final Block SPIRIT_LOG = new TieredPillarBlock("spirit_log", Blocks.OAK_LOG, 2, EnumResourceType.WOOD);
    public static final Block SPIRIT_TREE_RESOURCE = new StructureResourceBlock("spirit_log_resource", new Identifier(EphemeralWorlds.MODID, "spirit_log"), EnumResourceType.WOOD);
    public static final Block RIFT_LOG = new TieredPillarBlock("rift_log", Blocks.OAK_LOG, 4, EnumResourceType.WOOD);
    public static final Block RIFT_TREE_RESOURCE = new StructureResourceBlock("rift_log_resource", new Identifier(EphemeralWorlds.MODID, "rift_log"), EnumResourceType.WOOD);


    // Color
    public static final Block COLOR_STONE = new ColorBlock("color_stone", Blocks.STONE, EnumColorBrightness.MINERAL);
//    public static final Block FLOWING_SAND = new FlowingSandBlock("flowing_sand", Blocks.SAND, EnumColorBrightness.ORGANIC);
//    public static final Block FLOWING_SAND_SOURCE = new FlowingSandBlock("flowing_sand", Blocks.SAND, EnumColorBrightness.ORGANIC);
    public static final Block COLOR_DIRT = new ColorBlock("color_dirt", Blocks.DIRT, EnumColorBrightness.MINERAL);
    public static final Block COLOR_GRASS = new DualBrightnessColorBlock("color_grass", Blocks.GRASS_BLOCK, EnumColorBrightness.MINERAL, EnumColorBrightness.ORGANIC);

    public static final Block COLOR_LEAVES = new ColorBlock("color_leaves", Blocks.OAK_LEAVES, EnumColorBrightness.ORGANIC);
    public static final Block COLOR_PLANKS = new ColorBlock("color_planks", Blocks.OAK_PLANKS, EnumColorBrightness.ORGANIC);
    public static final Block SPIRIT_LEAVES = new ModBlock("spirit_leaves", Blocks.OAK_LEAVES);
    public static final Block SPIRIT_PLANKS = new ModBlock("spirit_planks", Blocks.OAK_PLANKS);
    public static final Block RIFT_LEAVES = new ModBlock("rift_leaves", Blocks.OAK_LEAVES);
    public static final Block RIFT_PLANKS = new ModBlock("rift_planks", Blocks.OAK_PLANKS);
    public static final Block FLOWER = new ModFernBlock("flower", Blocks.FERN);


    // Dimension
    public static final Block DROPLESS_PLATFORM = new LayeredBlock("dropless_platform", Blocks.GLASS);
//    public static final Block DECAYED_SOIL = new DecayedSoil("decayed_soil");


    // Advanced
//    public static final Block EASEL = new Easel("easel", Blocks.OAK_PLANKS);
    public static final Block TINKERING_TABLE = new TinkeringTable("tinkering_table", Blocks.STONE);


    // Technical
    public static final Block INK_DRAW = new InkDraw("ink_draw", Blocks.AIR);

    // Totem
    public static final Block TOTEM_PART_BASIC = new PartBasic("totem_part_basic");
    public static final Block TOTEM_PART_DIVINE = new PartDivine("totem_part_divine");
    public static final Block TOTEM_PART_HOLDING = new PartHolding("totem_part_holding");

    public static final Block TOTEM_PLINTH_BASIC = new PlinthBasic("totem_plinth_basic");
    public static final Block TOTEM_PLINTH_STURDY = new PlinthBasic("totem_plinth_sturdy");

    public static final Block TOTEM_EFFIGY_PROJECTION = new EffigyProjection("totem_effigy_projection");


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
