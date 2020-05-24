package net.ephemeralworlds.client.renderer.base;


import com.mojang.blaze3d.platform.GlStateManager;
import net.ephemeralworlds.utils.enums.EnumColor;
import net.ephemeralworlds.utils.handlers.SpriteHandler;
import net.ephemeralworlds.utils.helpers.ColorHelper;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public abstract class ModBlockEntityRenderer<T extends BlockEntity> extends BlockEntityRenderer<T> {

    public void renderStack(World world, ItemStack stack, double x, double y, double z) {
        renderStack(world, stack, x, y, z, 1F);
    }
    public void renderStack(World world, ItemStack stack, double x, double y, double z, float scale) {

        if (!stack.isEmpty()) {
//            GlStateManager.enableRescaleNormal();
//            GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1f);
//            GlStateManager.enableBlend();
//            RenderHelper.enableStandardItemLighting();
//            GlStateManager.blendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0);
            GlStateManager.pushMatrix();
            GlStateManager.translated(x, y, z);
            GlStateManager.scalef(scale, scale, scale);

            MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Type.GROUND);

            GlStateManager.popMatrix();
//            GlStateManager.disableRescaleNormal();
//            GlStateManager.disableBlend();
        }

    }

    public void renderFullFace(double renderX, double renderY, double renderZ, Identifier sprite, Direction dir, int color, float alpha, float rotation, float recess) {

        renderFace(renderX, renderY, renderZ, sprite, dir, color, alpha, rotation, recess, 0, 0, 1, 1);
//        GlStateManager.pushMatrix();
//        MinecraftClient.getInstance().getTextureManager().bindTexture(sprite);
//        GlStateManager.disableCull();
////        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
//        Tessellator tess = Tessellator.getInstance();
//        BufferBuilder builder = tess.getBufferBuilder();
//
//        GlStateManager.translated(renderX+.5, renderY+.5, renderZ+.5);
//
//        // Facing
//        switch (dir) {
//            case DOWN:
//                GlStateManager.rotatef(-90, 1, 0, 0);
//                break;
//            case UP:
//                GlStateManager.rotatef(90, 1, 0, 0);
//                break;
//            default:
//                GlStateManager.rotatef(dir.asRotation(), 0, 1, 0);
//        }
//
//        // Texture rotation
//        GlStateManager.rotatef(rotation, 0, 0, 1);
//
//        GlStateManager.translated(-.5, -.5, -.5);
//        GlStateManager.translatef(0, 0, recess);
//
//        double x1 = 0;
//        double y1 = 0;
//        double z1 = 0;
//
//        double x2 = x1 + 1;
//        double y2 = y1 + 1;
//
//        builder.begin(GL11.GL_QUADS, VertexFormats.POSITION_UV_COLOR);
//
//        Color col = new Color(color);
//
//        builder.vertex(x1, y1, z1).texture(1, 1).color(col.getRed(), col.getGreen(), col.getBlue(), 255).next();
//        builder.vertex(x2, y1, z1).texture(0, 1).color(col.getRed(), col.getGreen(), col.getBlue(), 255).next();
//        builder.vertex(x2, y2, z1).texture(0, 0).color(col.getRed(), col.getGreen(), col.getBlue(), 255).next();
//        builder.vertex(x1, y2, z1).texture(1, 0).color(col.getRed(), col.getGreen(), col.getBlue(), 255).next();
//
//        tess.draw();
//
//        GlStateManager.enableCull();
//        GlStateManager.popMatrix();
    }

    public void renderFace(double renderX, double renderY, double renderZ, Identifier sprite, Direction dir, int color, float alpha, float rotation, float recess, float leftCrop, float rightCrop, float bottomCrop, float topCrop) {

        GlStateManager.pushMatrix();
        MinecraftClient.getInstance().getTextureManager().bindTexture(sprite);
        GlStateManager.disableCull();

        //        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        Tessellator tess = Tessellator.getInstance();
        BufferBuilder builder = tess.getBufferBuilder();

        GlStateManager.translated(renderX+.5, renderY+.5, renderZ+.5);

        float x1 = 1-leftCrop;
        float y1 = topCrop;

        float x2 = 1-rightCrop;
        float y2 = bottomCrop;

        float u0 = 1-x1;
        float u1 = 1-x2;
        float v0 = 1 - y1;
        float v1 = 1 - y2;

        // Facing
        switch (dir) {
            case DOWN:
                GlStateManager.rotatef(180, 0, 1, 0);
                GlStateManager.rotatef(-90, 1, 0, 0);
//                v0 = 1 - y1;
//                v1 = 1 - y2;
                break;
            case UP:
                GlStateManager.rotatef(90, 1, 0, 0);
//                v0 = 1 - y1;
//                v1 = 1 - y2;
                break;
            default:
                GlStateManager.rotatef(dir.asRotation() + 90, 0, 1, 0);
//                v0 = 1 - y1;
//                v1 = 1 - y2;
        }

        // Texture rotation
        GlStateManager.rotatef(rotation, 0, 0, 1);

        GlStateManager.translated(-.5, -.5, -.5);
        GlStateManager.translatef(0, 0, recess);



        builder.begin(GL11.GL_QUADS, VertexFormats.POSITION_UV_COLOR_NORMAL);

        Color col = new Color(color);

        float normalX = dir.getOffsetX();
        float normalY = dir.getOffsetY();
        float normalZ = dir.getOffsetZ();

        builder.vertex(x1, y1, 0).texture(u0, v0).color(col.getRed() / 255F, col.getGreen() / 255F, col.getBlue() / 255F, alpha).normal(normalX, normalY, normalZ).next();
        builder.vertex(x2, y1, 0).texture(u1, v0).color(col.getRed() / 255F, col.getGreen() / 255F, col.getBlue() / 255F, alpha).normal(normalX, normalY, normalZ).next();
        builder.vertex(x2, y2, 0).texture(u1, v1).color(col.getRed() / 255F, col.getGreen() / 255F, col.getBlue() / 255F, alpha).normal(normalX, normalY, normalZ).next();
        builder.vertex(x1, y2, 0).texture(u0, v1).color(col.getRed() / 255F, col.getGreen() / 255F, col.getBlue() / 255F, alpha).normal(normalX, normalY, normalZ).next();

        tess.draw();


        GlStateManager.enableCull();
        GlStateManager.popMatrix();
    }

    public void renderParallelepiped(double renderX, double renderY, double renderZ, float x1, float y1, float z1, float x2, float y2, float z2, Identifier sprite, int color, float alpha) {

//        for (Direction dir: Direction.values()) {
//            this.renderFace(renderX, renderY, renderZ, sprite, dir, color, 0, 0, 0.5F, 0.9F, 0.1F, 0.9F);
//        }
        this.renderFace(renderX, renderY, renderZ, sprite, Direction.NORTH, color, alpha, 0, 1-x2, 1-z1, 1-z2, y1, y2);
        this.renderFace(renderX, renderY, renderZ, sprite, Direction.SOUTH, color, alpha, 0, x1, z2, z1, y1, y2);

        this.renderFace(renderX, renderY, renderZ, sprite, Direction.EAST, color, alpha, 0, z1, 1-x1, 1-x2, y1, y2);
        this.renderFace(renderX, renderY, renderZ, sprite, Direction.WEST, color, alpha, 0, 1-z2, x2, x1, y1, y2);

        this.renderFace(renderX, renderY, renderZ, sprite, Direction.DOWN, color, alpha, 0, y1, x1, x2, z2, z1);
        this.renderFace(renderX, renderY, renderZ, sprite, Direction.UP, color, alpha, 0, 1-y2, 1-x1, 1-x2, z2, z1);
    }

    public void renderSprite(double renderX, double renderY, double renderZ, float x1, float y1, float z1, float x2, float y2, float z2, Identifier sprite, int color, float alpha, Direction side) {
        renderSprite(renderX, renderY, renderZ, x1, y1, z1, x2, y2, z2, sprite, color, alpha, side, 0);
    }

    public void renderSprite(double renderX, double renderY, double renderZ, float x1, float y1, float z1, float x2, float y2, float z2, Identifier sprite, int color, float alpha, Direction side, float rotation) {
        switch (side) {
            case NORTH:
                this.renderFace(renderX, renderY, renderZ, sprite, Direction.EAST, color, alpha, rotation, 1-x2, 1-z1, 1-z2, y1, y2);
                break;
            case SOUTH:
                this.renderFace(renderX, renderY, renderZ, sprite, Direction.WEST, color, alpha, rotation, x1, z2, z1, y1, y2);
                break;

            case EAST:
                this.renderFace(renderX, renderY, renderZ, sprite, Direction.NORTH, color, alpha, rotation, z1, 1-x1, 1-x2, y1, y2);
                break;
            case WEST:
                this.renderFace(renderX, renderY, renderZ, sprite, Direction.SOUTH, color, alpha, rotation, 1-z2, x2, x1, y1, y2);
                break;

            case DOWN:
                this.renderFace(renderX, renderY, renderZ, sprite, Direction.DOWN, color, alpha, rotation, y1, x1, x2, z2, z1);
                break;
            case UP:
                this.renderFace(renderX, renderY, renderZ, sprite, Direction.UP, color, alpha, rotation, 1-y2, 1-x1, 1-x2, z2, z1);
                break;
        }
    }

}
