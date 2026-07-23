package net.eravern.organically.entity.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.eravern.organically.OrganicallyMod;
import net.eravern.organically.entity.client.sandstrider.SandStriderVariants;
import net.eravern.organically.item.OrganicallyModItems;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
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
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

import java.util.function.IntFunction;

public class SandStriderEntity extends HostileEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private final int SCALE_CHANCE = 60;
    private final int FED_SCALE_CHANCE = 40;
    private int idleAnimationCooldown = 0;
    private final int MAX_OUT_OF_DANGER = 500;
    private int OUT_OF_DANGER = MAX_OUT_OF_DANGER;
    private static final TrackedData<Integer> VARIANT = DataTracker.registerData(SandStriderEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Integer> HUNGER = DataTracker.registerData(SandStriderEntity.class, TrackedDataHandlerRegistry.INTEGER);

    private static final AttachmentType<SandStriderEntity.State> STATE = AttachmentRegistry.create(
            Identifier.of(OrganicallyMod.MOD_ID, "sandstrider_state"),
            dataTrackerBuilder -> dataTrackerBuilder
                    .initializer(() -> State.HUNGRY)
                    .persistent(State.CODEC)
                    .syncWith(State.PACKET_CODEC, AttachmentSyncPredicate.all()));

    public SandStriderEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }


    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(3, new MoveToTargetPosGoal(this, 1.0f, 10){
            @Override
            protected boolean isTargetPos(WorldView world, BlockPos pos) {
                return false;
            }
        });
        this.goalSelector.add(2, new AttackGoal(this, 2.0));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAtEntityGoal(this, SandStriderEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this, new Class[0]));
        this.targetSelector.add(2, new TargetGoal<>(this, PlayerEntity.class));
        this.targetSelector.add(3, new TargetGoal<>(this, MerchantEntity.class));
        this.targetSelector.add(3, new TargetGoal<>(this, RabbitEntity.class));

    }

    public static DefaultAttributeContainer.Builder createSandStriderAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.16f)
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
        int H = getHunger();
        if (this.getHealth() < this.getMaxHealth() && this.OUT_OF_DANGER == 0 && !isAngry() && !isHungry()){
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 1));
            this.OUT_OF_DANGER = MAX_OUT_OF_DANGER;
        }else{
            this.OUT_OF_DANGER -= 1;
        }
        if (this.isSanded()){
            this.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20, 0, false, false));
        }
        if(this.getWorld().isClient()) {
            this.setUpAnimationStates();
        }
        if (getHunger() > 0){
            if (this.hasStatusEffect(StatusEffects.HUNGER)){
                setHunger(H-1);
            }
            setHunger(H-1);
        }else{
            if(isAngry()){
                setState(State.HUNGRY_AND_ANGERED);
            }else{
                hunger();
            }
        }
    }


    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source.getAttacker() != null && !source.isSourceCreativePlayer()){
            if (isHungry()){
                setState(State.HUNGRY_AND_ANGERED);
            }else if (isSatiated()){
                setState(State.FED_AND_ANGERED);
            }
        }
        if (this.getWorld().isClient) {
            return false;
        }else{
            this.OUT_OF_DANGER = MAX_OUT_OF_DANGER;
            this.removeStatusEffect(StatusEffects.REGENERATION);
            int CHANCE = SCALE_CHANCE;
            if (this.isSatiated() || this.isSatiatedAngry()){
                CHANCE = FED_SCALE_CHANCE;
            }
            if (random.nextInt(CHANCE) == 0){
                ItemStack itemStack = new ItemStack(OrganicallyModItems.SANDSTRIDER_SCALE);
                ItemEntity itemEntity = new ItemEntity(this.getWorld(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), itemStack);
                itemEntity.setToDefaultPickupDelay();
                this.getWorld().spawnEntity(itemEntity);
            }
            return super.damage(source, amount);
        }
    }

    @Override
    public boolean onKilledOther(ServerWorld world, LivingEntity other) {
        int H = getHunger();

        if (other instanceof RabbitEntity){
            satiate();
            setHunger(H+2400);
        }else if (other instanceof MerchantEntity){
            satiate();
            setHunger(H+20000);
        } else if (other instanceof PlayerEntity){
            satiate();
            setHunger(H+24000);
        }else{
            satiate();
            setHunger(H+4000);
        }

        return super.onKilledOther(world, other);
    }

    private static class AttackGoal extends MeleeAttackGoal {
        public SandStriderEntity entity;
        public AttackGoal(SandStriderEntity strider,double speed) {
            super(strider, speed, true);
            entity = strider;
        }

        public boolean isHungry(){
            return entity.isHungry();
        }

        public boolean isAngry(){
            return entity.isAngry();
        }

        public boolean canStart() {
            return isHungry() || isAngry();
        }

        public boolean shouldContinue() {
                return super.shouldContinue() && (isHungry() || isAngry());
        }
    }

    public boolean isSanded(){
        return this.getEntityWorld().getBlockState(this.getBlockPos().down()).isIn(BlockTags.SAND) && !this.isInsideWaterOrBubbleColumn();
    }

    public void hunger(){
        setState(State.HUNGRY);
    }

    public void satiate(){
        setState(State.FED);
    }

    public boolean isHungry() {
        return getState().equals(State.HUNGRY);
    }

    public boolean isSatiated() {
        return getState().equals(State.FED);
    }

    public boolean isSatiatedAngry() {
        return getState().equals(State.FED_AND_ANGERED);
    }

    public boolean isHungryAngry() {
        return getState().equals(State.HUNGRY_AND_ANGERED);
    }

    public boolean isAngry() {
        return isSatiatedAngry() || isHungryAngry();
    }

    private static class TargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
        VillagerEntity villagerEntity;
        public TargetGoal(SandStriderEntity strider, Class<T> targetEntityClass) {
            super(strider, targetEntityClass, true);
            if (targetEntity instanceof VillagerEntity villager){
                villagerEntity = villager;
            }
        }

        public boolean canStart() {
            boolean baby = true;
            if (villagerEntity != null){
                if (villagerEntity.isBaby()){
                    baby = false;
                }
            }
            return super.canStart() && baby;
        }
    }


    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, 0);
        builder.add(HUNGER, 0);
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

    private void setHunger(int variant) {
        this.dataTracker.set(HUNGER, variant);
    }

    private int getHunger() {
        return this.dataTracker.get(HUNGER);
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

    private SandStriderEntity.State getState() {
        return this.getAttachedOrCreate(STATE);
    }

    private SandStriderEntity setState(SandStriderEntity.State state) {
        this.setAttached(STATE, state);
        return this;
    }

    public enum State {
        FED(0),
        HUNGRY(1),
        FED_AND_ANGERED(2),
        HUNGRY_AND_ANGERED(3);

        public static final IntFunction<SandStriderEntity.State> INDEX_TO_VALUE = ValueLists.createIdToValueFunction(SandStriderEntity.State::getIndex, values(), ValueLists.OutOfBoundsHandling.ZERO);
        public static final PacketCodec<ByteBuf, SandStriderEntity.State> PACKET_CODEC = PacketCodecs.indexed(INDEX_TO_VALUE, SandStriderEntity.State::getIndex);
        public static final Codec<SandStriderEntity.State> CODEC = RecordCodecBuilder.create(instance -> instance.group(
                Codec.INT.fieldOf("sandstrider_state").forGetter(SandStriderEntity.State::getIndex)).apply(instance, INDEX_TO_VALUE::apply));
        private final int index;

        private State(final int index) {
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }
    }


}
