package net.eravern.organically.entity.client.sandstrider;

import net.eravern.organically.OrganicallyMod;
import net.eravern.organically.entity.custom.SandStriderEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class SandStriderRenderer extends MobEntityRenderer<SandStriderEntity, SandStriderModel<SandStriderEntity>> {
    public static final Identifier DESERT = Identifier.of(OrganicallyMod.MOD_ID, "textures/entity/sandstrider/sandstrider.png");
    public static final Identifier DESERT_ANGRY = Identifier.of(OrganicallyMod.MOD_ID, "textures/entity/sandstrider/angry/sandstrider.png");
    public static final Identifier MESA = Identifier.of(OrganicallyMod.MOD_ID, "textures/entity/sandstrider/sandstrider_mesa.png");
    public static final Identifier MESA_ANGRY = Identifier.of(OrganicallyMod.MOD_ID, "textures/entity/sandstrider/angry/sandstrider_mesa.png");
    public static final Identifier NIKO = Identifier.of(OrganicallyMod.MOD_ID, "textures/entity/sandstrider/niko.png");
    public static final Identifier NIKO_ANGRY = Identifier.of(OrganicallyMod.MOD_ID, "textures/entity/sandstrider/angry/niko.png");



    public SandStriderRenderer(EntityRendererFactory.Context context) {
        super(context, new SandStriderModel<>(context.getPart(SandStriderModel.SANDSTRIDER)), 0.7f);
    }

    @Override
    public void render(SandStriderEntity livingEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.scale(1f, 1f, 1f);
        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(SandStriderEntity entity) {
        Identifier TEXTURE;
        if (Objects.requireNonNull(entity.getVariant()) == SandStriderVariants.MESA) {
            if (entity.isAngry()){
                TEXTURE = MESA_ANGRY;
            }else{
                TEXTURE = MESA;
            }

        } else {
            if (entity.isAngry()){
                TEXTURE = DESERT_ANGRY;
            }else{
                TEXTURE = DESERT;
            }
        }
        String string = Formatting.strip(entity.getName().getString()).toLowerCase();
        if (string.equals("niko") || string.equals("xeno") || string.equals("not a cat")){
            if (entity.isAngry()){
                TEXTURE = NIKO_ANGRY;
            }else{
                TEXTURE = NIKO;
            }
        }
        return TEXTURE;
    }
}
