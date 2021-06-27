package net.ephemeralworlds.client.renderer;

import net.ephemeralworlds.block.entity.InkJarBlockEntity;
import net.ephemeralworlds.client.renderer.base.ModBlockEntityRenderer;
import net.ephemeralworlds.utils.handlers.SpriteHandler;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class InkJarRenderer extends ModBlockEntityRenderer<InkJarBlockEntity> {

    public InkJarRenderer(BlockEntityRenderDispatcher ctx) {
        super(ctx);
    }

    public void render(InkJarBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        if (entity != null) {
            if (!entity.isEmpty()) {
                Identifier sprite = SpriteHandler.INK;
                renderParallelepiped(entity.getPos().getX(), entity.getPos().getY(), entity.getPos().getZ(), 0.25F, 1 / 16F, 0.25F, 0.75F, 7 / 16F * entity.getInkRatio() + 1 / 16F, 0.75F, sprite, entity.getColor().getColorValue(), 0.9F);
            }
        }
    }

}
