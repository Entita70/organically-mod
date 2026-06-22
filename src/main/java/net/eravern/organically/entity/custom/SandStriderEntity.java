package net.eravern.organically.entity.custom;

import net.eravern.organically.entity.client.sandstrider.SandStriderVariants;
import net.eravern.organically.item.OrganicallyModItems;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

public class SandStriderEntity extends HostileEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private final int SCALE_CHANCE = 50;
    private int idleAnimationCooldown = 0;
    private final int MAX_OUT_OF_DANGER = 500;
    private int OUT_OF_DANGER = MAX_OUT_OF_DANGER;

    public SandStriderEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    private static final TrackedData<Integer> VARIANT = DataTracker.registerData(SandStriderEntity.class, TrackedDataHandlerRegistry.INTEGER);

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new MoveToTargetPosGoal(this, 1.0f, 10){
            @Override
            protected boolean isTargetPos(WorldView world, BlockPos pos) {
                return false;
            }
        });
        this.goalSelector.add(3, new AttackGoal(this));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(6, new LookAtEntityGoal(this, SandStriderEntity.class, 8.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this, new Class[0]));
        this.targetSelector.add(2, new TargetGoal<>(this, PlayerEntity.class));

    }

    public static DefaultAttributeContainer.Builder createSandStriderAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0f);
    }

    private void setUpAnimationStates() {
        if (this.idleAnimationCooldown <= 0) {
            this.idleAnimationCooldown = 40;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationCooldown;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getHealth() < this.getMaxHealth()/2 && this.OUT_OF_DANGER == 0){
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 300, 1));
            this.OUT_OF_DANGER = MAX_OUT_OF_DANGER;
        }else{
            this.OUT_OF_DANGER -= 1;
        }
        if(this.getWorld().isClient()) {
            this.setUpAnimationStates();
        }
    }


    @Override
    public boolean damage(DamageSource source, float amount) {
        if (this.getWorld().isClient) {
            return false;
        }else{
            this.OUT_OF_DANGER = MAX_OUT_OF_DANGER;
            this.removeStatusEffect(StatusEffects.REGENERATION);
            if (random.nextInt(SCALE_CHANCE) == 0){
                ItemStack itemStack = new ItemStack(OrganicallyModItems.SANDSTRIDER_SCALE);
                ItemEntity itemEntity = new ItemEntity(this.getWorld(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), itemStack);
                itemEntity.setToDefaultPickupDelay();
                this.getWorld().spawnEntity(itemEntity);
            }
            return super.damage(source, amount);
        }
    }


    private static class AttackGoal extends MeleeAttackGoal {
        public AttackGoal(SandStriderEntity strider) {
            super(strider, 2.0, true);
        }
        public boolean canStart() {
            return super.canStart();
        }

        public boolean shouldContinue() {
                return super.shouldContinue();
        }
    }

    private static class TargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
        public TargetGoal(SandStriderEntity strider, Class<T> targetEntityClass) {
            super(strider, targetEntityClass, true);
        }

        public boolean canStart() {
            return super.canStart();
        }
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, 0);
    }


    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getSandStriderVariant());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setSandStriderVariant(nbt.getInt("Variant"));
    }

    private void setSandStriderVariant(int variant) {
        this.dataTracker.set(VARIANT, variant);
    }

    private int getSandStriderVariant() {
        return this.dataTracker.get(VARIANT);
    }

    public SandStriderVariants getVariant() {
        return SandStriderVariants.byId(this.getSandStriderVariant() & 255);
    }

    public void setVariant(SandStriderVariants sandStriderVariant) {
        this.setSandStriderVariant(sandStriderVariant.getId() & 255);
    }

    @Override
    public @Nullable EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                                           @Nullable EntityData entityData) {
        SandStriderVariants variant = SandStriderVariants.DEFAULT;
        if (world.getBiome(this.getBlockPos()).isIn(BiomeTags.IS_BADLANDS)){
            variant = SandStriderVariants.MESA;
        }
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }
}
