package net.ephemeralworlds.client.renderer;

import com.mojang.blaze3d.platform.GlStateManager;
import net.ephemeralworlds.block.entity.TinkeringTableBlockEntity;
import net.ephemeralworlds.client.renderer.base.ModBlockEntityRenderer;
import net.minecraft.client.render.GuiLighting;
import net.minecraft.item.ItemStack;

public class TinkeringTableRenderer extends ModBlockEntityRenderer<TinkeringTableBlockEntity> {

    private final float scale = 0.4f;

    public void render(TinkeringTableBlockEntity entity, double renderX, double renderY, double renderZ, float partialTicks, int destroyStage) {
        super.render(entity, renderX, renderY, renderZ, partialTicks, destroyStage);

        if (entity != null && !entity.isInvEmpty()) {
//            int count = entity.getInvSizeNotEmpty();
            for (int i=entity.getInvSize()-1; i>=0; i--) {
                ItemStack stack = entity.getInvStack(i);
                if (!stack.isEmpty()) {
                    double scale = .3D;

                    int xi = i % 3 == 0 ? -1 : (i+2) % 3 == 0 ? 0 : 1;
                    int zi = i < 3 ? -1 : i > 5 ? 1 : 0;
                    double dx = scale * xi;
                    double dz = scale * zi;

                    GlStateManager.pushMatrix();
//                    GuiLighting.enable();
//                    GlStateManager.translated(renderX + .5 + dx, renderY + 1, renderZ + .5 + dz);
//                    GlStateManager.disableDepthTest();
                    renderStack(getWorld(), stack, renderX + .5 + dx, renderY + 1.1, renderZ + .5 + dz, 0.5F);
//                    GlStateManager.enableDepthTest();
                    GlStateManager.popMatrix();
                }
            }

//            if (!entity.isEmpty()) {
//
//                Color c = new Color(0, 128, 255);
//                GL11.glColor3ub((byte) c.getRed(), (byte) c.getGreen(), (byte) c.getBlue());
//
//                MinecraftClient.getInstance().getItemRenderer().renderItem(stack, ModelTransformation.Type.GROUND);
//
//                GL11.glColor3ub((byte) 255, (byte) 255, (byte) 255);
//
//                GlStateManager.pushMatrix();
//
//                GuiLighting.enable();
//                GlStateManager.enableLighting();
//                GlStateManager.disableRescaleNormal();
//                GlStateManager.translated(renderX + .5, renderY + 0.9 + Math.sin((Math.PI / 180) * (ticks * 4)) / 15, renderZ + .5);
//                GlStateManager.rotated(2 * ticks, 0, 1, 0);
//
//                GlStateManager.popMatrix();
//                Sprite sprite = SpriteHandler.getSprite(SpriteHandler.DRIP_INK_PARTICLE);
//                renderFace(entity.getPos(), sprite, Direction.NORTH, 256, 0, false);
//            }
        }
    }
}
