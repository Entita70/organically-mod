package net.eravern.organically.entity.client.lionfish;

import net.eravern.organically.OrganicallyMod;
import net.eravern.organically.entity.custom.LionfishEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;

public class LionfishRenderer extends MobEntityRenderer<LionfishEntity, LionfishModel<LionfishEntity>> {
    public static final Identifier DEFAULT = Identifier.of(OrganicallyMod.MOD_ID, "textures/entity/lionfish/lionfish.png");
    public static final Identifier WARM = Identifier.of(OrganicallyMod.MOD_ID, "textures/entity/lionfish/lionfish_warm.png");
    public static final Identifier MONOCHROME = Identifier.of(OrganicallyMod.MOD_ID, "textures/entity/lionfish/lionfish_monochrome.png");
    public static final Identifier REVERSED = Identifier.of(OrganicallyMod.MOD_ID, "textures/entity/lionfish/lionfish_reversed.png");


    public LionfishRenderer(EntityRendererFactory.Context context) {
        super(context, new LionfishModel<>(context.getPart(LionfishModel.LIONFISH)), 0.5f);
    }

    @Override
    public Identifier getTexture(LionfishEntity entity) {
        Identifier TEXTURE;
        switch (entity.getVariant()){
            case WARM -> TEXTURE = WARM;
            case MONOCHROME -> TEXTURE = MONOCHROME;
            case REVERSED -> TEXTURE = REVERSED;
            default -> TEXTURE = DEFAULT;
        }
        return TEXTURE;
    }

    @Override
    public void render(LionfishEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f, 1f, 1f);
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    protected void setupTransforms(LionfishEntity lionfishEntity, MatrixStack matrixStack, float f, float g, float h, float i) {
        super.setupTransforms(lionfishEntity, matrixStack, f, g, h, i);
        float j = 4.3F * MathHelper.sin(0.6F * f);
        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(j));
        if (!lionfishEntity.isTouchingWater()) {
            matrixStack.translate(0.1F, 0.1F, -0.1F);
            matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(80.0F));
        }

    }

}
