package net.eravern.organically.entity.custom;

import net.eravern.organically.entity.client.lionfish.LionfishEntityVariants;
import net.eravern.organically.item.OrganicallyModItems;
import net.eravern.organically.mob_effect.OrganicallyModEffects;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class LionfishEntity extends FishEntity {
    private final int SPIKE_CHANCE = 15;
    private final int ELECTRIC_CHANCE = 100;
    private int cooldown = 0;
    public LionfishEntity(EntityType<? extends FishEntity> entityType, World world) {
        super(entityType, world);
    }
    private static final TrackedData<Integer> VARIANT = DataTracker.registerData(LionfishEntity.class, TrackedDataHandlerRegistry.INTEGER);


    @Override
    public void onPlayerCollision(PlayerEntity player) {
        if (!player.isInCreativeMode() && this.cooldown == 0){
            this.cooldown = 30;
            if (player instanceof ServerPlayerEntity && player.damage(player.getDamageSources().mobAttack(this), 1f)){
                player.addStatusEffect(new StatusEffectInstance(OrganicallyModEffects.ELECTRIFIED, ELECTRIC_CHANCE, 0), this);
                if (random.nextInt(SPIKE_CHANCE) == 0){
                    ItemStack itemStack = new ItemStack(OrganicallyModItems.LIONFISH_SPIKE);
                    ItemEntity itemEntity = new ItemEntity(this.getWorld(), this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), itemStack);
                    itemEntity.setToDefaultPickupDelay();
                    this.getWorld().spawnEntity(itemEntity);
                }
            }
        }
    }

    @Override
    public void tick() {
        if (this.cooldown != 0){
            this.cooldown -= 1;
        }
        super.tick();
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PUFFER_FISH_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PUFFER_FISH_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_PUFFER_FISH_HURT;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_PUFFER_FISH_FLOP;
    }


    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(OrganicallyModItems.LIONFISH_BUCKET);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, 0);
    }


    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getLionfishVariant());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setLionfishVariant(nbt.getInt("Variant"));
    }

    private void setLionfishVariant(int variant) {
        this.dataTracker.set(VARIANT, variant);
    }

    private int getLionfishVariant() {
        return this.dataTracker.get(VARIANT);
    }

    public LionfishEntityVariants getVariant() {
        return LionfishEntityVariants.byId(this.getLionfishVariant() & 255);
    }

    public void setVariant(LionfishEntityVariants lionfishEntityVariants) {
        this.setLionfishVariant(lionfishEntityVariants.getId() & 255);
    }

    @Override
    public @Nullable EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason,
                                           @Nullable EntityData entityData) {

        LionfishEntityVariants variant = Util.getRandom(LionfishEntityVariants.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }
}
