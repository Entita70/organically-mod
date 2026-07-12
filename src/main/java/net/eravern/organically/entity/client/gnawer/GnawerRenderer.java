package net.eravern.organically.entity.client.gnawer;

import net.eravern.organically.OrganicallyMod;
import net.eravern.organically.entity.custom.GnawerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class GnawerRenderer extends MobEntityRenderer<GnawerEntity, GnawerModel<GnawerEntity>> {
    public static final Identifier DEFAULT = Identifier.of(OrganicallyMod.MOD_ID, "textures/entity/gnawer.png");


    public GnawerRenderer(EntityRendererFactory.Context context) {
        super(context, new GnawerModel<>(context.getPart(GnawerModel.GNAWER)), 0.6f);
    }

    @Override
    public void render(GnawerEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
            matrixStack.scale(1f, 1f, 1f);
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    protected void scale(GnawerEntity entity, MatrixStack matrices, float amount) {
        if (entity.isBaby()){
            matrices.scale(0.7f, 0.7f, 0.7f);
        }else{
            matrices.scale(1f, 1f,1f);
        }
        super.scale(entity, matrices, amount);
    }

    @Override
    public Identifier getTexture(GnawerEntity entity) {
        return DEFAULT;
    }
}