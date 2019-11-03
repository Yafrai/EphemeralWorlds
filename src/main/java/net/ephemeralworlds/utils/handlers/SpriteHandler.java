package net.ephemeralworlds.utils.handlers;

import net.ephemeralworlds.EphemeralWorlds;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.impl.client.particle.FabricParticleManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;

public class SpriteHandler {

    public static final Identifier INK = new Identifier(EphemeralWorlds.MODID, "textures/sprite/ink.png");
    public static final Identifier DRIP_INK_PARTICLE = new Identifier(EphemeralWorlds.MODID, "textures/particle/drip_ink.png");

    public static void registerSprites(ClientSpriteRegistryCallback.Registry registry) {
        registry.register(DRIP_INK_PARTICLE);

//        FabricParticleManager#loadParticle(manager, DRIP_INK_PARTICLE);
//        indigo (renderer system)
    }

    public static Sprite getSprite(Identifier identifier) {
        SpriteAtlasTexture spriteAtlas = MinecraftClient.getInstance().getSpriteAtlas();
        return spriteAtlas.getSprite(identifier);
    }
}
