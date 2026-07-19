package net.eravern.organically.entity.custom;

import io.netty.buffer.ByteBuf;
import net.eravern.organically.OrganicallyMod;
import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.entity.OrganicallyModEntityTypes;
import net.eravern.organically.item.OrganicallyModItems;
import net.eravern.organically.util.OrganicallyModItemTags;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.function.ValueLists;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.function.IntFunction;

public class GnawerEntity extends AnimalEntity {
    public final AnimationState gnawingAnimationState = new AnimationState();
    private final float MUCUS_CHANCE = 0.6f;
    private final int GNAW_TIME = 1200;
    private int GNAW = GNAW_TIME;
    private static final TrackedData<State> STATE = DataTracker.registerData(GnawerEntity.class, OrganicallyMod.GNAWER_STATE);


    public GnawerEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }



    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 1.5));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(3, new TemptGoal(this, 1.25, (stack) -> {
            return stack.isIn(OrganicallyModItemTags.GNAWER_FOOD) || stack.isIn(OrganicallyModItemTags.GNAWABLE);
        }, false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    public static DefaultAttributeContainer.Builder createGnawerAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.15)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 14.0);
    }

    @Override
    public void breed(ServerWorld world, AnimalEntity other) {
        ItemStack itemStack = new ItemStack(OrganicallyModBlocks.GNAWER_EGG.asItem());
        ItemEntity itemEntity = new ItemEntity(world, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), itemStack);
        itemEntity.setToDefaultPickupDelay();
        this.breed(world, other, null);
        this.playSound(SoundEvents.BLOCK_SNIFFER_EGG_PLOP, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 0.5F);
        world.spawnEntity(itemEntity);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(STATE, State.IDLING);
    }

    @Override
    public void tick() {
        if (isGnawing()){
            if (GNAW <= 0){
                ItemStack itemStack = new ItemStack(Items.SLIME_BALL);
                if (isGnawingFossils()){
                    int count = random.nextInt(4)+4;
                    itemStack = new ItemStack(OrganicallyModItems.CALCIUM, count);
                }else if (isGnawingBones()){
                    if (random.nextFloat() < MUCUS_CHANCE){
                        itemStack = new ItemStack(OrganicallyModItems.FLUORESCENT_MUCUS);
                    }
                }
                ItemEntity itemEntity = new ItemEntity(this.getWorld(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), itemStack, this.lookDirection*0.0035, 0.3, this.lookDirection*0.0035);
                itemEntity.setToDefaultPickupDelay();
                this.getWorld().spawnEntity(itemEntity);
                startIdling();
                GNAW = GNAW_TIME;
            }else{
                GNAW -= 1;
            }
        }
        super.tick();
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        if (STATE.equals(data)){
            GnawerEntity.State state = this.getState();
            this.stopAnimations();
            switch (state.ordinal()) {
                case 1, 2:
                    this.gnawingAnimationState.startIfNotRunning(this.age);
                    break;
            }
        }
        super.onTrackedDataSet(data);
    }

    private void stopAnimations() {
        this.gnawingAnimationState.stop();
    }

    public void startGnawingFossils(){
        setState(State.GNAWING_FOSSILS);
    }

    public void startGnawingBones(){
        setState(State.GNAWING_BONES);
    }

    public void startIdling(){
        setState(State.IDLING);
    }

    public boolean isGnawingFossils() {
        return getState().equals(State.GNAWING_FOSSILS);
    }
    public boolean isGnawingBones() {
        return getState().equals(State.GNAWING_BONES);
    }
    public boolean isGnawing() {
        return isGnawingBones() || isGnawingFossils();
    }

    public void onDeath(DamageSource damageSource) {
        startIdling();
        super.onDeath(damageSource);
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isIn(OrganicallyModItemTags.GNAWABLE) && !this.isBaby()){
            if (!this.isGnawing()){
                if (itemStack.isIn(OrganicallyModItemTags.FOSSILS)){
                    startGnawingFossils();
                }else{
                    startGnawingBones();
                }
                int c = itemStack.getCount();
                player.getStackInHand(hand).setCount(c-1);
                this.getWorld().playSoundFromEntity(null, this, this.getEatSound(itemStack), SoundCategory.NEUTRAL, 1.0F, MathHelper.nextBetween(this.getWorld().random, 0.8F, 1.2F));
                return ActionResult.success(this.getWorld().isClient);
            }
        }
        return super.interactMob(player, hand);
    }

    public SoundEvent getEatSound(ItemStack stack) {
        return SoundEvents.ENTITY_SNIFFER_EAT;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(OrganicallyModItemTags.GNAWER_FOOD);
    }

    public void setBaby(boolean baby) {
        this.setBreedingAge(baby ? -24000 : 0);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return OrganicallyModEntityTypes.GNAWER.create(world);
    }

    public boolean canBreedWith(AnimalEntity other) {
        if (!(other instanceof GnawerEntity gnawerEntity)) {
            return false;
        }else {
            Set<GnawerEntity.State> set = Set.of(GnawerEntity.State.IDLING);
            return !gnawerEntity.isGnawing() && set.contains(this.getState()) && set.contains(gnawerEntity.getState()) && super.canBreedWith(other);
        }
    }

    private GnawerEntity.State getState() {
        return this.dataTracker.get(STATE);
    }

    private GnawerEntity setState(GnawerEntity.State state) {
        this.dataTracker.set(STATE, state);
        return this;
    }

    public enum State {
        IDLING(0),
        GNAWING_FOSSILS(1),
        GNAWING_BONES(2);

        public static final IntFunction<GnawerEntity.State> INDEX_TO_VALUE = ValueLists.createIdToValueFunction(GnawerEntity.State::getIndex, values(), ValueLists.OutOfBoundsHandling.ZERO);
        public static final PacketCodec<ByteBuf, GnawerEntity.State> PACKET_CODEC = PacketCodecs.indexed(INDEX_TO_VALUE, GnawerEntity.State::getIndex);
        private final int index;

        private State(final int index) {
            this.index = index;
        }

        public int getIndex() {
            return this.index;
        }
    }

}
