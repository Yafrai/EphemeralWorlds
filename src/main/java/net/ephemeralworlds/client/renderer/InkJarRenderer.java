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
//                renderFace(renderX, renderY, renderZ, sprite, Direction.NORTH, EnumColor.BLUE.getColorValue(), (int)getWorld().getTime(), 0);
//                renderFace(renderX, renderY, renderZ, sprite, Direction.DOWN, EnumColor.RED.getColorValue(), (int)getWorld().getTime(), 0);
//                renderFace(renderX, renderY, renderZ, sprite, Direction.UP, EnumColor.GREEN.getColorValue(), (int)getWorld().getTime(), 0);

//                float x1 = 0.5F * (float)Math.cos(0.3*getWorld().getTime());
//                float x2 = 1F + 0.5F * (float)Math.cos(0.07*getWorld().getTime());

//                float y1 = 0.5F * (float)Math.cos(0.04*getWorld().getTime());
//                float y2 = 1F + 0.5F * (float)Math.cos(0.08*getWorld().getTime());

//                float z1 = 0.5F * (float)Math.cos(0.1*getWorld().getTime());
//                float z2 = 1F + 0.5F * (float)Math.cos(0.01*getWorld().getTime());

                renderParallelepiped(renderX, renderY, renderZ, 0.25F, 1/16F, 0.25F, 0.75F, 0.6F * entity.getInkRatio() + 1/16F, 0.75F, sprite, entity.getColor().getColorValue(), 0.9F);
            }
        }
    }
}
