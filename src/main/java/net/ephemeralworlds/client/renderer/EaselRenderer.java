package net.ephemeralworlds.client.renderer;
//
//import com.mojang.blaze3d.platform.GlStateManager;
//import net.ephemeralworlds.block.entity.EaselBlockEntity;
//import net.ephemeralworlds.block.entity.InkJarBlockEntity;
//import net.ephemeralworlds.client.renderer.base.ModBlockEntityRenderer;
//import net.ephemeralworlds.utils.handlers.SpriteHandler;
//import net.minecraft.client.MinecraftClient;
//import net.minecraft.client.render.GuiLighting;
//import net.minecraft.client.render.model.json.ModelTransformation;
//import net.minecraft.client.texture.Sprite;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.math.Direction;
//import org.lwjgl.opengl.GL11;
//
//import java.awt.*;
//
//public class EaselRenderer extends ModBlockEntityRenderer<EaselBlockEntity> {
//
//    private final float scale = 0.4f;
//
//    public void render(EaselBlockEntity entity, double renderX, double renderY, double renderZ, float partialTicks, int destroyStage) {
//        super.render(entity, renderX, renderY, renderZ, partialTicks, destroyStage);
//
//        if (entity != null && !entity.isInvEmpty()) {
//            int count = -1;
//            for (int i=entity.getInvSize()-1; i>=0; i--) {
//                ItemStack stack = entity.getInvStack(i);
//                if (!stack.isEmpty()) {
//
//                    if (count == -1)
//                        count = i;
//
//                    double dh = 0;
//                    double dv = 0;
//                    double radius = 0.4;
//
//                    if (i > 0) {
//                        dh = -radius * Math.sin(2 * (i-1) * Math.PI / count);
//                        dv = radius * Math.cos(2 * (i-1) * Math.PI / count);
//                    }
//
//                    GlStateManager.pushMatrix();
//
//                    GuiLighting.enable();
//                    GlStateManager.enableLighting();
//                    GlStateManager.disableRescaleNormal();
//                    GlStateManager.scalef(scale, scale, scale);
//                    GlStateManager.translated(renderX + .5 + dh, renderY + 1.4 + dv, renderZ + .5);
//                    renderStack(getWorld(), stack, renderX + .5 + dh, renderY + 1.4 + dv, renderZ + .5);
//                    GlStateManager.popMatrix();
//                }
//            }
//
////            if (!entity.isEmpty()) {
////
////                Color c = new Color(0, 128, 255);
////                GL11.glColor3ub((byte) c.getRed(), (byte) c.getGreen(), (byte) c.getBlue());
////
////                MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Type.GROUND);
////
////                GL11.glColor3ub((byte) 255, (byte) 255, (byte) 255);
////
////                GlStateManager.pushMatrix();
////
////                GuiLighting.enable();
////                GlStateManager.enableLighting();
////                GlStateManager.disableRescaleNormal();
////                GlStateManager.translated(renderX + .5, renderY + 0.9 + Math.sin((Math.PI / 180) * (ticks * 4)) / 15, renderZ + .5);
////                GlStateManager.rotated(2 * ticks, 0, 1, 0);
////
////                GlStateManager.popMatrix();
////                Sprite sprite = SpriteHandler.getSprite(SpriteHandler.DRIP_INK_PARTICLE);
////                renderFace(entity.getPos(), sprite, Direction.NORTH, 256, 0, false);
////            }
//        }
//    }
//}
