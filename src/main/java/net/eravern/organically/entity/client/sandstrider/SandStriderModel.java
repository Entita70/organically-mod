package net.eravern.organically.entity.client.sandstrider;

import net.eravern.organically.OrganicallyMod;
import net.eravern.organically.entity.custom.SandStriderEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class SandStriderModel <T extends SandStriderEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer SANDSTRIDER = new EntityModelLayer(Identifier.of(OrganicallyMod.MOD_ID, "sandstrider"),
            "main");

    private final ModelPart root;
    private final ModelPart mob;
    private final ModelPart body;
    private final ModelPart head;
    public SandStriderModel(ModelPart root) {
        this.root = root.getChild("root");
        this.mob = this.root.getChild("mob");
        this.body = this.mob.getChild("body");
        this.head = this.mob.getChild("head");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData mob = root.addChild("mob", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 6.0F, 0.0F));

        ModelPartData body = mob.addChild("body", ModelPartBuilder.create().uv(34, 20).cuboid(0.0F, -19.0F, -7.0F, 0.0F, 3.0F, 13.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(-4.0F, -16.0F, -7.0F, 8.0F, 6.0F, 14.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData tail = mob.addChild("tail", ModelPartBuilder.create().uv(0, 20).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 4.0F, 13.0F, new Dilation(0.0F))
                .uv(34, 36).cuboid(0.0F, -5.0F, -1.0F, 0.0F, 3.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -13.0F, 8.0F));

        ModelPartData front_legs = mob.addChild("front_legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData right_leg1 = front_legs.addChild("right_leg1", ModelPartBuilder.create().uv(44, 0).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, -13.0F, -4.0F));

        ModelPartData left_leg1 = front_legs.addChild("left_leg1", ModelPartBuilder.create().uv(0, 48).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(6.0F, -13.0F, -4.0F));

        ModelPartData hind_legs = mob.addChild("hind_legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData right_leg2 = hind_legs.addChild("right_leg2", ModelPartBuilder.create().uv(32, 51).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-6.0F, -13.0F, 4.0F));

        ModelPartData left_leg2 = hind_legs.addChild("left_leg2", ModelPartBuilder.create().uv(16, 48).cuboid(-2.0F, -1.0F, -2.0F, 4.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(6.0F, -13.0F, 4.0F));

        ModelPartData head = mob.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -13.0F, -7.0F));

        ModelPartData upper_jaw = head.addChild("upper_jaw", ModelPartBuilder.create().uv(44, 12).cuboid(-3.0F, -10.0F, -17.0F, 6.0F, 3.0F, 4.0F, new Dilation(0.0F))
                .uv(56, 19).cuboid(-3.0F, -7.0F, -17.0F, 6.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 60).cuboid(3.0F, -7.0F, -17.0F, 0.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(58, 43).cuboid(-3.0F, -7.0F, -17.0F, 0.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 7.0F, 7.0F));

        ModelPartData lower_jaw = head.addChild("lower_jaw", ModelPartBuilder.create().uv(48, 51).cuboid(-3.0F, 0.0F, -4.0F, 6.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(24, 37).cuboid(-3.0F, -1.0F, -4.0F, 0.0F, 1.0F, 4.0F, new Dilation(0.0F))
                .uv(44, 19).cuboid(-3.0F, -1.0F, -4.0F, 6.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(24, 42).cuboid(3.0F, -1.0F, -4.0F, 0.0F, 1.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 1.0F, -6.0F));

        ModelPartData brain = head.addChild("brain", ModelPartBuilder.create().uv(0, 37).cuboid(-3.0F, -19.0F, -11.0F, 6.0F, 5.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 16.0F, 5.0F));

        ModelPartData fans = head.addChild("fans", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 16.0F, 5.0F));

        ModelPartData cube_r1 = fans.addChild("cube_r1", ModelPartBuilder.create().uv(58, 36).cuboid(-5.0F, -4.0F, 0.0F, 6.0F, 7.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, -16.0F, -6.0F, 0.0F, -0.3491F, 0.0F));

        ModelPartData cube_r2 = fans.addChild("cube_r2", ModelPartBuilder.create().uv(48, 56).cuboid(-1.0F, -4.0F, 0.0F, 6.0F, 7.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -16.0F, -6.0F, 0.0F, 0.3491F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }



    @Override
    public ModelPart getPart() {
        return root;
    }

    @Override
    public void setAngles(SandStriderEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(headYaw, headPitch);

        this.animateMovement(SandStriderAnimations.SANDSTRIDER_CHARGE,  limbAngle, limbDistance, 1f, 1.5f);

        this.updateAnimation(entity.idleAnimationState, SandStriderAnimations.SANDSTRIDER_IDLE, animationProgress, 1f);
    }

    private void setHeadAngles(float headYaw, float headPitch){
        headYaw = MathHelper.clamp(headYaw, -20.0f, 20.0f);
        headPitch = MathHelper.clamp(headPitch, -20.0f, 20.0f);

        this.head.yaw = headYaw * ((float)Math.PI / 180);
        this.head.pitch = headPitch * ((float)Math.PI / 180);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        root.render(matrices, vertexConsumer, light, overlay, color);
    }
}