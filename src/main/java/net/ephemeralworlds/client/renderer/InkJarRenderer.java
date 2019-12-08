package net.ephemeralworlds.client.renderer;

import net.ephemeralworlds.block.entity.InkJarBlockEntity;
import net.ephemeralworlds.client.renderer.base.ModBlockEntityRenderer;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.handlers.SpriteHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class InkJarRenderer extends ModBlockEntityRenderer<InkJarBlockEntity> {

    public void render(InkJarBlockEntity entity, double renderX, double renderY, double renderZ, float partialTicks, int destroyStage) {
        super.render(entity, renderX, renderY, renderZ, partialTicks, destroyStage);

        if (entity != null) {
            if (!entity.isEmpty()) {
                Identifier sprite = SpriteHandler.INK;
                renderParallelepiped(renderX, renderY, renderZ, 0.25F, 1/16F, 0.25F, 0.75F, 7/16F * entity.getInkRatio() + 1/16F, 0.75F, sprite, entity.getColor().getColorValue(), 0.9F);
            }
        }
    }
}
