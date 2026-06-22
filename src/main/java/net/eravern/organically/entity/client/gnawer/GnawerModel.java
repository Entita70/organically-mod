package net.eravern.organically.entity.client.gnawer;

import net.eravern.organically.OrganicallyMod;
import net.eravern.organically.entity.custom.GnawerEntity;
import net.eravern.organically.entity.custom.SandStriderEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class GnawerModel <T extends GnawerEntity> extends SinglePartEntityModel<T> {
    public static final EntityModelLayer GNAWER = new EntityModelLayer(Identifier.of(OrganicallyMod.MOD_ID, "gnawer"),
            "main");

    private final ModelPart root;
    private final ModelPart mob;
    private final ModelPart head;
    private final ModelPart jaws;
    private final ModelPart upper_jaw;
    private final ModelPart lower_jaw;
    private final ModelPart body;
    private final ModelPart tail;
    public GnawerModel(ModelPart root) {
        this.root = root.getChild("root");
        this.mob = this.root.getChild("mob");
        this.head = this.mob.getChild("head");
        this.jaws = this.head.getChild("jaws");
        this.upper_jaw = this.jaws.getChild("upper_jaw");
        this.lower_jaw = this.jaws.getChild("lower_jaw");
        this.body = this.mob.getChild("body");
        this.tail = this.body.getChild("tail");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData mob = root.addChild("mob", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 2.0F));

        ModelPartData head = mob.addChild("head", ModelPartBuilder.create().uv(38, 47).cuboid(-4.0F, -6.0F, -7.0F, 8.0F, 8.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -8.0F, -11.0F));

        ModelPartData jaws = head.addChild("jaws", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 8.0F, 11.0F));

        ModelPartData upper_jaw = jaws.addChild("upper_jaw", ModelPartBuilder.create().uv(38, 36).cuboid(-5.0F, -5.0F, -6.0F, 10.0F, 5.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -11.0F, -18.0F));

        ModelPartData lower_jaw = jaws.addChild("lower_jaw", ModelPartBuilder.create().uv(0, 36).cuboid(-5.0F, 0.0F, -9.0F, 10.0F, 5.0F, 9.0F, new Dilation(0.0F))
                .uv(70, 36).cuboid(-4.0F, -3.0F, -8.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F))
                .uv(70, 41).cuboid(1.0F, -3.0F, -8.0F, 3.0F, 3.0F, 2.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -11.0F, -18.0F));

        ModelPartData body = mob.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -18.0F, -17.0F, 16.0F, 12.0F, 24.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 5.0F));

        ModelPartData tail = body.addChild("tail", ModelPartBuilder.create().uv(0, 50).cuboid(-2.0F, -2.0F, -2.0F, 4.0F, 13.0F, 4.0F, new Dilation(0.0F))
                .uv(66, 47).cuboid(-2.0F, 7.0F, 2.0F, 4.0F, 4.0F, 6.0F, new Dilation(0.0F))
                .uv(0, 67).cuboid(-2.0F, 3.0F, 4.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -13.0F, 9.0F));

        ModelPartData legs = mob.addChild("legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData right_legs = legs.addChild("right_legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData right_leg_2 = right_legs.addChild("right_leg_2", ModelPartBuilder.create().uv(16, 50).cuboid(-2.0F, 0.0F, -3.0F, 5.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, -6.0F, 7.0F));

        ModelPartData right_leg_1 = right_legs.addChild("right_leg_1", ModelPartBuilder.create().uv(16, 61).cuboid(-3.0F, 0.0F, -3.0F, 5.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, -6.0F, -5.0F));

        ModelPartData left_legs = legs.addChild("left_legs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData left_leg_1 = left_legs.addChild("left_leg_1", ModelPartBuilder.create().uv(36, 61).cuboid(-2.0F, 0.0F, -2.0F, 5.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -6.0F, -6.0F));

        ModelPartData left_leg_2 = left_legs.addChild("left_leg_2", ModelPartBuilder.create().uv(56, 61).cuboid(-2.0F, 0.0F, -2.0F, 5.0F, 6.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -6.0F, 6.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }
    @Override
    public void setAngles(GnawerEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.setHeadAngles(netHeadYaw, headPitch);


        this.animateMovement(GnawerAnimations.WALK, limbSwing*3, limbSwingAmount*3, 1f, 1.5f);

        this.updateAnimation(entity.gnawingAnimationState, GnawerAnimations.GNAW, ageInTicks, 1f);
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        root.render(matrices, vertexConsumer, light, overlay, color);
    }

    private void setHeadAngles(float headYaw, float headPitch){
        headYaw = MathHelper.clamp(headYaw, -30.0f, 30.0f);
        headPitch = MathHelper.clamp(headPitch, -15.0f, 15.0f);

        this.head.yaw = headYaw * ((float)Math.PI / 180);
        this.head.pitch = headPitch * ((float)Math.PI / 180);
    }

    @Override
    public ModelPart getPart() {
        return root;
    }
}