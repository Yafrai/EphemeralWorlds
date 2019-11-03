package net.ephemeralworlds.registry;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.block.base.ModBlock;
import net.ephemeralworlds.block.entity.EaselBlockEntity;
import net.ephemeralworlds.block.entity.InkJarBlockEntity;
import net.ephemeralworlds.block.entity.TeriumGrateHopperBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntities {

    public static BlockEntityType<InkJarBlockEntity> INK_JAR_ENTITY;
    public static BlockEntityType<TeriumGrateHopperBlockEntity> TERIUM_HOPPER_ENTITY;
    public static BlockEntityType<EaselBlockEntity> EASEL_ENTITY;

    public static void registerBlockEntities() {

        INK_JAR_ENTITY = Registry.register(Registry.BLOCK_ENTITY, new Identifier(EphemeralWorlds.MODID, "ink_jar"), BlockEntityType.Builder.create(InkJarBlockEntity::new, ModBlocks.INK_JAR).build(null));
        TERIUM_HOPPER_ENTITY = Registry.register(Registry.BLOCK_ENTITY, new Identifier(EphemeralWorlds.MODID, "terium_hopper"), BlockEntityType.Builder.create(TeriumGrateHopperBlockEntity::new, ModBlocks.TERIUM_GRATE_HOPPER).build(null));
        EASEL_ENTITY = Registry.register(Registry.BLOCK_ENTITY, new Identifier(EphemeralWorlds.MODID, "easel"), BlockEntityType.Builder.create(EaselBlockEntity::new, ModBlocks.EASEL).build(null));

    }
}
