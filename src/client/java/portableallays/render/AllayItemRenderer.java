package portableallays.render;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import portableallays.component.AllayDataComponent;
import portableallays.component.ModDataComponentTypes;

public class AllayItemRenderer implements BuiltinItemRendererRegistry.DynamicItemRenderer {
    private MinecraftClient mc;
    private EntityRenderDispatcher entityRenderDispatcher;

    public AllayItemRenderer() {
    }

    /**
     * Renders an item stack.
     *
     * @param stack           the rendered item stack
     * @param mode            the model transformation mode
     * @param matrices        the matrix stack
     * @param vertexConsumers the vertex consumer provider
     * @param light           packed lightmap coordinates
     * @param overlay         the overlay UV passed to {@link VertexConsumer#overlay(int)}
     */
    @Override
    public void render(ItemStack stack, ModelTransformationMode mode, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (this.mc == null) {
            this.mc = MinecraftClient.getInstance();
        }
        if (this.entityRenderDispatcher == null) {
            this.entityRenderDispatcher = this.mc.getEntityRenderDispatcher();
        }
        matrices.translate(0.5, 0.25, 0.5);
        if (mode != ModelTransformationMode.GUI)
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180));
        AllayDataComponent allayData = stack.get(ModDataComponentTypes.ALLAYDATACOMPONENT);
        if (allayData == null) {
            assert this.mc.world != null;
            allayData = AllayDataComponent.fromAllay(new AllayEntity(EntityType.ALLAY, this.mc.world));
        }
        this.entityRenderDispatcher.render(allayData.getAllay(this.mc.world, stack), 0.0, 0.0, 0.0, 0.0F, 0, matrices, vertexConsumers, light);
    }
}
