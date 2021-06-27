package net.ephemeralworlds.utils.handlers;

import net.ephemeralworlds.EphemeralWorlds;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.util.Identifier;

public class ParticleHandler {

    public static final Identifier DRIP_INK_PARTICLE = new Identifier(EphemeralWorlds.MODID, "textures/particle/drip_ink");


    public static void registerTextures(ClientSpriteRegistryCallback.Registry registry) {
        registry.register(DRIP_INK_PARTICLE);

//        FabricParticleManager#loadParticle(manager, DRIP_INK_PARTICLE);
//        indigo (renderer system)
    }

}
