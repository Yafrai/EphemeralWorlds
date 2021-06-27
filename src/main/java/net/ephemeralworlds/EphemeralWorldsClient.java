package net.ephemeralworlds;

import net.ephemeralworlds.registry.ModBlockEntityRenderers;
import net.ephemeralworlds.registry.ModBlocks;
import net.ephemeralworlds.utils.handlers.ParticleHandler;
import net.ephemeralworlds.utils.handlers.SpriteHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.texture.SpriteAtlasTexture;


public class EphemeralWorldsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		ModBlockEntityRenderers.registerRenderers();

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.INK_JAR, RenderLayer.getTranslucent());

		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.PARTICLE_ATLAS_TEXTURE).register((atlasTexture, registry) ->
		{
			SpriteHandler.registerSprites(registry);
			ParticleHandler.registerTextures(registry);
		});

	}
}
