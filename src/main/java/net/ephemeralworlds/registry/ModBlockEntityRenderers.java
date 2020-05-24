package net.ephemeralworlds.registry;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.block.entity.InkJarBlockEntity;
import net.ephemeralworlds.block.entity.TeriumGrateHopperBlockEntity;
import net.ephemeralworlds.block.entity.TinkeringTableBlockEntity;
import net.ephemeralworlds.client.renderer.InkDrawRenderer;
import net.ephemeralworlds.client.renderer.InkJarRenderer;
import net.ephemeralworlds.client.renderer.TinkeringTableRenderer;
import net.fabricmc.fabric.api.client.render.BlockEntityRendererRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlockEntityRenderers {

    public static void registerRenderers() {

        BlockEntityRendererRegistry.INSTANCE.register(InkJarBlockEntity.class, new InkJarRenderer());
//        BlockEntityRendererRegistry.INSTANCE.register(EaselBlockEntity.class, new EaselRenderer());
        BlockEntityRendererRegistry.INSTANCE.register(InkDrawBlockEntity.class, new InkDrawRenderer());
        BlockEntityRendererRegistry.INSTANCE.register(TinkeringTableBlockEntity.class, new TinkeringTableRenderer());
    }
}
