package net.ephemeralworlds.utils.handlers;

import net.ephemeralworlds.EphemeralWorlds;
import net.ephemeralworlds.block.entity.parts.*;
import net.ephemeralworlds.utils.enums.EnumGlyph;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class SpriteHandler {

    public static final Identifier INK = new Identifier(EphemeralWorlds.MODID, "textures/sprite/ink.png");
    public static final Identifier DRIP_INK_PARTICLE = new Identifier(EphemeralWorlds.MODID, "textures/particle/drip_ink.png");
    public static final Identifier CIRCLE = new Identifier(EphemeralWorlds.MODID, "textures/sprite/circle.png");
    public static final Identifier BASE_CIRCLE = new Identifier(EphemeralWorlds.MODID, "textures/sprite/generic_circle.png");
    public static final Identifier FUSION_CIRCLE = new Identifier(EphemeralWorlds.MODID, "textures/sprite/fusion_circle.png");
    public static final Identifier FIRE_CIRCLE = new Identifier(EphemeralWorlds.MODID, "textures/sprite/fire_circle.png");
    public static final Identifier INK_CIRCLE = new Identifier(EphemeralWorlds.MODID, "textures/sprite/ink_circle.png");
    public static final Identifier SEAL_CIRCLE = new Identifier(EphemeralWorlds.MODID, "textures/sprite/seal_circle.png");
    public static final Identifier REWIND_CIRCLE = new Identifier(EphemeralWorlds.MODID, "textures/sprite/rewind_circle.png");
    public static final Identifier ACTIVATION_CIRCLE = new Identifier(EphemeralWorlds.MODID, "textures/sprite/activation_circle.png");

//    public static final Identifier GLYPH_INK = new Identifier(EphemeralWorlds.MODID, "textures/sprite/glyph_ink.png");
//    public static final Identifier GLYPH_ENERGY = new Identifier(EphemeralWorlds.MODID, "textures/sprite/glyph_energy.png");
//    public static final Identifier GLYPH_SOUL = new Identifier(EphemeralWorlds.MODID, "textures/sprite/glyph_soul.png");
//    public static final Identifier GLYPH_MARK = new Identifier(EphemeralWorlds.MODID, "textures/sprite/glyph_mark.png");
//    public static final Identifier GLYPH_CHARM = new Identifier(EphemeralWorlds.MODID, "textures/sprite/glyph_charm.png");

    public static void registerSprites(ClientSpriteRegistryCallback.Registry registry) {
//        registry.register(DRIP_INK_PARTICLE);

//        FabricParticleManager#loadParticle(manager, DRIP_INK_PARTICLE);
//        indigo (renderer system)
    }

    public static Sprite getSprite(Identifier identifier) {
        Function<Identifier, Sprite> spriteAtlas = MinecraftClient.getInstance().getSpriteAtlas(identifier);
        return spriteAtlas.apply(identifier);
    }

    public static Identifier getGlyph(EnumGlyph glyph) {

        switch (glyph) {
//            case INK:
//                return GLYPH_INK;
//            case ENERGY:
//                return GLYPH_ENERGY;
//            case SOUL:
//                return GLYPH_SOUL;
//            case MARK:
//                return GLYPH_MARK;
//            case CHARM:
//                return GLYPH_CHARM;

            default:
                return null;
        }
    }

    public static Identifier getCircle(ACircle circle) {
        if (circle instanceof BaseCircle)
            return BASE_CIRCLE;
        else if (circle instanceof FusionCircle)
            return FUSION_CIRCLE;
        else if (circle instanceof InkCircle)
            return INK_CIRCLE;
        else if (circle instanceof FireCircle)
            return FIRE_CIRCLE;
        else if (circle instanceof SealCircle)
            return SEAL_CIRCLE;
        else if (circle instanceof RewindCircle)
            return REWIND_CIRCLE;
        else if (circle instanceof ActivationCircle)
            return ACTIVATION_CIRCLE;


        else return null;
    }
}
