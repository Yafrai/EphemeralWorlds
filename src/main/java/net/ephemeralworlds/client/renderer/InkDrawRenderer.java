package net.ephemeralworlds.client.renderer;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.block.entity.parts.ACircle;
import net.ephemeralworlds.block.entity.parts.AInventoryCircle;
import net.ephemeralworlds.block.entity.parts.ISerializablePart;
import net.ephemeralworlds.client.renderer.base.ModBlockEntityRenderer;
import net.ephemeralworlds.utils.handlers.SpriteHandler;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;

public class InkDrawRenderer extends ModBlockEntityRenderer<InkDrawBlockEntity> {

    public void render(InkDrawBlockEntity entity, double renderX, double renderY, double renderZ, float partialTicks, int destroyStage) {
        super.render(entity, renderX, renderY, renderZ, partialTicks, destroyStage);

        if (entity != null) {

            for (Direction face: Direction.values()) {

                if (entity.isFaceDrawn(face)) {
                    renderElement(entity, renderX, renderY, renderZ, partialTicks, destroyStage, face);
                }
            }
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
        }
    }

    public void renderElement(InkDrawBlockEntity entity, double renderX, double renderY, double renderZ, float partialTicks, int destroyStage, Direction face) {
        ISerializablePart part = entity.getFaceContents(face);
        if (part instanceof ACircle) {
            renderCircle(entity, renderX, renderY, renderZ, partialTicks, destroyStage, face, (ACircle)part);
        }
    }

    public void renderCircle(InkDrawBlockEntity entity, double renderX, double renderY, double renderZ, float partialTicks, int destroyStage, Direction face, ACircle circle) {
        float e = 0.01F;
        renderSprite(renderX, renderY, renderZ, e, e, e, 1-e, 1-e, 1-e, SpriteHandler.getCircle(circle), circle.getColor().getColorValue(), 0.9F, face);

        if (circle instanceof AInventoryCircle) {
            AInventoryCircle invCircle = (AInventoryCircle)circle;
            int count = invCircle.getItemCount();

            if (count > 0) {

                Vector3f centerPos = new Vector3f((float)renderX + 0.5F, (float)renderY + 0.5F, (float)renderZ + 0.5F);
                Vector3f ortho1 = new Vector3f(face.getOffsetZ(), face.getOffsetX(), face.getOffsetY());
                Vector3f ortho2 = new Vector3f(face.getOffsetY(), face.getOffsetZ(), face.getOffsetX());
                centerPos.add(0.4F * face.getOffsetX(), 0.4F * face.getOffsetY(), 0.4F * face.getOffsetZ());

                renderStack(getWorld(), invCircle.getInvStack(0), centerPos.getX(), centerPos.getY(), centerPos.getZ(), 0.5F);

                count--;
                if (count > 0)
                for (int i=0; i<count; i++) {
                    ItemStack stack = invCircle.getInvStack(i+1);

                    Vector3f itemPos = new Vector3f(centerPos);
                    float angle = (float)(2 * Math.PI * i / count) + invCircle.getItemRotation();
                    float c = invCircle.getItemRadius() * (float)Math.cos(angle);
                    float s = invCircle.getItemRadius() * (float)Math.sin(angle);

                    Vector3f orthoC = new Vector3f(ortho1);
                    orthoC.scale(c);

                    Vector3f orthoS = new Vector3f(ortho2);
                    orthoS.scale(s);

                    itemPos.subtract(orthoC);
                    itemPos.subtract(orthoS);

                    renderStack(getWorld(), stack, itemPos.getX(), itemPos.getY(), itemPos.getZ(), 0.5F);
                }
            }
        }

//        if (circle.hasGlyph()) {
//            renderSprite(renderX, renderY, renderZ, e, e, e, 1-e, 1-e, 1-e, SpriteHandler.getGlyph(circle.glyph), circle.color.getColorValue(), 0.9F, face);
//        }

    }


}
