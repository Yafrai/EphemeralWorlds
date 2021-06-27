package net.ephemeralworlds.client.renderer;

import net.ephemeralworlds.block.entity.InkDrawBlockEntity;
import net.ephemeralworlds.block.entity.parts.ACircle;
import net.ephemeralworlds.block.entity.parts.AInventoryCircle;
import net.ephemeralworlds.block.entity.parts.ISerializablePart;
import net.ephemeralworlds.client.renderer.base.ModBlockEntityRenderer;
import net.ephemeralworlds.utils.handlers.SpriteHandler;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3d;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Direction;

public class InkDrawRenderer extends ModBlockEntityRenderer<InkDrawBlockEntity> {

    public InkDrawRenderer(BlockEntityRenderDispatcher dispatcher) {
        super(dispatcher);
    }

    public void render(InkDrawBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
//  public void render(InkDrawBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
//    public void render(InkDrawBlockEntity entity, double renderX, double renderY, double renderZ, float tickDelta, int destroyStage) {

        if (entity != null) {

            for (Direction face: Direction.values()) {

                if (entity.isFaceDrawn(face)) {
                    renderElement(entity, tickDelta, face);
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

    public void renderElement(InkDrawBlockEntity entity, float partialTicks, Direction face) {
        ISerializablePart part = entity.getFaceContents(face);
        if (part instanceof ACircle) {
            renderCircle(entity, partialTicks, face, (ACircle)part);
        }
    }

    public void renderCircle(InkDrawBlockEntity entity, float partialTicks, Direction face, ACircle circle) {
        float e = 0.01F;
        float alpha = circle.isFainting() ? (1F - circle.getFaintingRate()) * .9F : 0.9F;
        float rotation = 360 * circle.getProcessingRatio();
        renderSprite(entity.getPos().getX(), entity.getPos().getY(), entity.getPos().getZ(), e, e, e, 1-e, 1-e, 1-e, SpriteHandler.getCircle(circle), circle.getColor().getColorValue(), alpha, face, rotation);

        if (circle instanceof AInventoryCircle) {
            AInventoryCircle invCircle = (AInventoryCircle)circle;
            int count = invCircle.getItemCount();

            if (count > 0) {

                Vector3d centerPos = new Vector3d(
                        entity.getPos().getX()+ 0.5 + 0.4F * face.getOffsetX(),
                        entity.getPos().getY() + 0.5 + 0.4F * face.getOffsetY(),
                        entity.getPos().getZ() + 0.5 + 0.4F * face.getOffsetZ()
                );
                Vector3d ortho1 = new Vector3d(face.getOffsetZ(), face.getOffsetX(), face.getOffsetY());
                Vector3d ortho2 = new Vector3d(face.getOffsetY(), face.getOffsetZ(), face.getOffsetX());

                renderStack(entity.getWorld(), invCircle.getStack(0), centerPos.x, centerPos.y, centerPos.z, 0.5F);

                count--;
                if (count > 0)
                for (int i=0; i<count; i++) {
                    ItemStack stack = invCircle.getStack(i+1);

                    Vector3d itemPos = new Vector3d(centerPos.x, centerPos.y, centerPos.z);
                    float angle = (float)(2 * Math.PI * i / count) + invCircle.getItemRotation();
                    float c = invCircle.getItemRadius() * (float)Math.cos(angle);
                    float s = invCircle.getItemRadius() * (float)Math.sin(angle);

                    Vector3d orthoC = new Vector3d(ortho1.x*c, ortho1.y*c, ortho1.z*c);

                    Vector3d orthoS = new Vector3d(ortho2.x*c, ortho2.y*c, ortho2.z*c);

                    Vector3d finalPos = new Vector3d(
                            itemPos.x - orthoC.x - orthoS.x,
                            itemPos.y - orthoC.y - orthoS.y,
                            itemPos.z - orthoC.z - orthoS.z
                    );

                    renderStack(entity.getWorld(), stack, finalPos.x, finalPos.y, finalPos.z, 0.5F);
                }
            }
        }

//        if (circle.hasGlyph()) {
//            renderSprite(renderX, renderY, renderZ, e, e, e, 1-e, 1-e, 1-e, SpriteHandler.getGlyph(circle.glyph), circle.color.getColorValue(), 0.9F, face);
//        }

    }

}
