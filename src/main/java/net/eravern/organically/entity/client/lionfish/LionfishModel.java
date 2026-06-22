package net.eravern.organically.entity.client.lionfish;

import net.eravern.organically.OrganicallyMod;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class LionfishModel<T extends Entity> extends SinglePartEntityModel<T>{
    public static final EntityModelLayer LIONFISH = new EntityModelLayer(Identifier.of(OrganicallyMod.MOD_ID, "lionfish"),
            "main");

    private final ModelPart root;
    private final ModelPart mob;
    private final ModelPart fin;
    public LionfishModel(ModelPart root) {
        this.root = root.getChild("root");
        this.mob = this.root.getChild("mob");
        this.fin = this.mob.getChild("fin");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, -1.0F));

        ModelPartData mob = root.addChild("mob", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData body = mob.addChild("body", ModelPartBuilder.create().uv(1, 1).cuboid(-1.5F, -4.0F, -5.0F, 3.0F, 4.0F, 12.0F, new Dilation(0.0F))
                .uv(1, 1).cuboid(-1.5F, -3.0F, -6.0F, 3.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData fins = body.addChild("fins", ModelPartBuilder.create().uv(32, 0).cuboid(0.0F, -8.0F, -4.0F, 0.0F, 4.0F, 11.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData cube_r1 = fins.addChild("cube_r1", ModelPartBuilder.create().uv(0, 28).cuboid(-2.0F, 0.0F, -4.0F, 5.0F, 0.0F, 11.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

        ModelPartData cube_r2 = fins.addChild("cube_r2", ModelPartBuilder.create().uv(0, 17).cuboid(-1.0F, 0.0F, -4.0F, 5.0F, 0.0F, 11.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

        ModelPartData fin = mob.addChild("fin", ModelPartBuilder.create().uv(33, 16).cuboid(0.0F, -6.0F, 1.0F, 0.0F, 4.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.0F, 6.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public ModelPart getPart() {
        return root;
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        float f = 0.5F;
        if (!entity.isTouchingWater()){
            f = 0.4F;
        }
        this.fin.yaw = -f * 0.45F * MathHelper.sin(0.6F * animationProgress);

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        root.render(matrices, vertexConsumer, light, overlay, color);
    }




}