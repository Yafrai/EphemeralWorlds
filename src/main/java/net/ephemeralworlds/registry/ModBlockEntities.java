package net.ephemeralworlds.registry;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.block.entity.*;
import net.ephemeralworlds.block.entity.effigy.EffigyProjectionBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {

    public static BlockEntityType<InkJarBlockEntity> INK_JAR_ENTITY;
    public static BlockEntityType<RunicForgeBlockEntity> RUNIC_FORGE_BLOCK_ENTITY;
//    public static BlockEntityType<ChromiumGrateHopperBlockEntity> CHROMIUM_HOPPER_ENTITY;
//    public static BlockEntityType<EaselBlockEntity> EASEL_ENTITY;
    public static BlockEntityType<InkDrawBlockEntity> INK_DRAW_ENTITY;
    public static BlockEntityType<TinkeringTableBlockEntity> TINKERING_TABLE_ENTITY;
    public static BlockEntityType<ChromiumOreBlockEntity> RESOURCE_BLOCK_ENTITY_CHROMIUM;
    public static BlockEntityType<DiachroniumOreBlockEntity> RESOURCE_BLOCK_ENTITY_DIACHRONIUM;
    public static BlockEntityType<Gem1OreBlockEntity> RESOURCE_BLOCK_ENTITY_GEM1;
    public static BlockEntityType<Gem2OreBlockEntity> RESOURCE_BLOCK_ENTITY_GEM2;
    public static BlockEntityType<Gem3OreBlockEntity> RESOURCE_BLOCK_ENTITY_GEM3;
    public static BlockEntityType<RuneStoneBlockEntity> RESOURCE_BLOCK_ENTITY_RUNESTONE;
//    public static BlockEntityType<ColorTreeBlockEntity> RESOURCE_BLOCK_ENTITY_COLOR_TREE;
    public static BlockEntityType<SpiritTreeBlockEntity> RESOURCE_BLOCK_ENTITY_SPIRIT_TREE;
    public static BlockEntityType<RiftTreeBlockEntity> RESOURCE_BLOCK_ENTITY_RIFT_TREE;

    public static BlockEntityType<EffigyProjectionBlockEntity> EFFIGY_PROJECTION_BLOCK_ENTITY;

    public static void registerBlockEntities() {

        INK_JAR_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(EphemeralWorlds.MODID, "ink_jar"), FabricBlockEntityTypeBuilder.create(InkJarBlockEntity::new, ModBlocks.INK_JAR).build(null));
        RUNIC_FORGE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(EphemeralWorlds.MODID, "runic_forge"), FabricBlockEntityTypeBuilder.create(RunicForgeBlockEntity::new, ModBlocks.RUNIC_FORGE).build(null));
        INK_DRAW_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(EphemeralWorlds.MODID, "ink_draw"), FabricBlockEntityTypeBuilder.create(InkDrawBlockEntity::new, ModBlocks.INK_DRAW).build(null));
        TINKERING_TABLE_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(EphemeralWorlds.MODID, "tinkering_table"), FabricBlockEntityTypeBuilder.create(TinkeringTableBlockEntity::new, ModBlocks.TINKERING_TABLE).build(null));

        RESOURCE_BLOCK_ENTITY_CHROMIUM = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(EphemeralWorlds.MODID, "resource_chromium"), FabricBlockEntityTypeBuilder.create(ChromiumOreBlockEntity::new, ModBlocks.CHROMIUM_ORE).build(null));
        RESOURCE_BLOCK_ENTITY_DIACHRONIUM = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(EphemeralWorlds.MODID, "resource_diachronium"), FabricBlockEntityTypeBuilder.create(DiachroniumOreBlockEntity::new, ModBlocks.DIACHRONIUM_ORE).build(null));
//        RESOURCE_BLOCK_ENTITY_GEM1 = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(EphemeralWorlds.MODID, "resource_gem_1"), FabricBlockEntityTypeBuilder.create(Gem1OreBlockEntity::new, ModBlocks.GEM_ORE1).build(null));
//        RESOURCE_BLOCK_ENTITY_GEM2 = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(EphemeralWorlds.MODID, "resource_gem_2"), FabricBlockEntityTypeBuilder.create(Gem2OreBlockEntity::new, ModBlocks.GEM_ORE2).build(null));
//        RESOURCE_BLOCK_ENTITY_GEM3 = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(EphemeralWorlds.MODID, "resource_gem_3"), FabricBlockEntityTypeBuilder.create(Gem3OreBlockEntity::new, ModBlocks.GEM_ORE3).build(null));
        RESOURCE_BLOCK_ENTITY_RUNESTONE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(EphemeralWorlds.MODID, "resource_runestone"), FabricBlockEntityTypeBuilder.create(RuneStoneBlockEntity::new, ModBlocks.RUNE_STONE_RESOURCE).build(null));
//        RESOURCE_BLOCK_ENTITY_COLOR_TREE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(EphemeralWorlds.MODID, "resource_color_tree"), FabricBlockEntityTypeBuilder.create(ColorTreeBlockEntity::new, ModBlocks.COLOR_TREE_RESOURCE).build(null));
        RESOURCE_BLOCK_ENTITY_SPIRIT_TREE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(EphemeralWorlds.MODID, "resource_spirit_tree"), FabricBlockEntityTypeBuilder.create(SpiritTreeBlockEntity::new, ModBlocks.SPIRIT_TREE_RESOURCE).build(null));
        RESOURCE_BLOCK_ENTITY_RIFT_TREE = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(EphemeralWorlds.MODID, "resource_rift_tree"), FabricBlockEntityTypeBuilder.create(RiftTreeBlockEntity::new, ModBlocks.RIFT_TREE_RESOURCE).build(null));

        EFFIGY_PROJECTION_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(EphemeralWorlds.MODID, "effigy_projection"), FabricBlockEntityTypeBuilder.create(EffigyProjectionBlockEntity::new, ModBlocks.TOTEM_EFFIGY_PROJECTION).build(null));

    }
}
