package net.ephemeralworlds;

import net.ephemeralworlds.utils.handlers.SpriteHandler;
import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.texture.SpriteAtlasTexture;


public class EphemeralWorldsClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		// Example
//		BlockEntityRendererRegistry.INSTANCE.register(MyBlockBlockEntity.class, new MyBlockRenderer());

		ClientSpriteRegistryCallback.event(SpriteAtlasTexture.PARTICLE_ATLAS_TEX).register((atlasTexture, registry) ->
		{
			SpriteHandler.registerSprites(registry);
		});

	}
}
