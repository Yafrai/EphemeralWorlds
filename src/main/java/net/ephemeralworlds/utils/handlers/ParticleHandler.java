package net.ephemeralworlds.utils.handlers;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.block.entity.parts.ACircle;
import net.ephemeralworlds.block.entity.parts.FusionCircle;
import net.ephemeralworlds.block.entity.parts.GenericCircle;
import net.ephemeralworlds.utils.enums.EnumGlyph;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.util.Identifier;

public class ParticleHandler {

    public static final Identifier DRIP_INK_PARTICLE = new Identifier(EphemeralWorlds.MODID, "textures/particle/drip_ink.png");


    public static void registerTextures(ClientSpriteRegistryCallback.Registry registry) {
        registry.register(DRIP_INK_PARTICLE);

//        FabricParticleManager#loadParticle(manager, DRIP_INK_PARTICLE);
//        indigo (renderer system)
    }

}
