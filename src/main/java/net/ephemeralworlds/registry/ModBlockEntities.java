package net.ephemeralworlds.registry;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.block.entity.*;
import net.ephemeralworlds.block.entity.effigy.EffigyProjectionBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {

    public static BlockEntityType<InkJarBlockEntity> INK_JAR_ENTITY;
    public static BlockEntityType<ChromiumGrateHopperBlockEntity> CHROMIUM_HOPPER_ENTITY;
//    public static BlockEntityType<EaselBlockEntity> EASEL_ENTITY;
    public static BlockEntityType<InkDrawBlockEntity> INK_DRAW_ENTITY;
    public static BlockEntityType<TinkeringTableBlockEntity> TINKERING_TABLE_ENTITY;

    public static BlockEntityType<EffigyProjectionBlockEntity> EFFIGY_PROJECTION_BLOCK_ENTITY;

    public static void registerBlockEntities() {

        INK_JAR_ENTITY = Registry.register(Registry.BLOCK_ENTITY, new Identifier(EphemeralWorlds.MODID, "ink_jar"), BlockEntityType.Builder.create(InkJarBlockEntity::new, ModBlocks.INK_JAR).build(null));
        CHROMIUM_HOPPER_ENTITY = Registry.register(Registry.BLOCK_ENTITY, new Identifier(EphemeralWorlds.MODID, "chromium_hopper"), BlockEntityType.Builder.create(ChromiumGrateHopperBlockEntity::new, ModBlocks.CHROMIUM_GRATE_HOPPER).build(null));
//        EASEL_ENTITY = Registry.register(Registry.BLOCK_ENTITY, new Identifier(EphemeralWorlds.MODID, "easel"), BlockEntityType.Builder.create(EaselBlockEntity::new, ModBlocks.EASEL).build(null));
        INK_DRAW_ENTITY = Registry.register(Registry.BLOCK_ENTITY, new Identifier(EphemeralWorlds.MODID, "ink_draw"), BlockEntityType.Builder.create(InkDrawBlockEntity::new, ModBlocks.INK_DRAW).build(null));
        TINKERING_TABLE_ENTITY = Registry.register(Registry.BLOCK_ENTITY, new Identifier(EphemeralWorlds.MODID, "tinkering_table"), BlockEntityType.Builder.create(TinkeringTableBlockEntity::new, ModBlocks.TINKERING_TABLE).build(null));

        EFFIGY_PROJECTION_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY, new Identifier(EphemeralWorlds.MODID, "effigy_projection"), BlockEntityType.Builder.create(EffigyProjectionBlockEntity::new, ModBlocks.TOTEM_EFFIGY_PROJECTION).build(null));

    }
}
