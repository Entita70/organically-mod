package net.eravern.organically.entity.custom;

import net.eravern.organically.entity.OrganicallyModEntityTypes;
import net.eravern.organically.item.OrganicallyModItems;
import net.eravern.organically.mob_effect.OrganicallyModEffects;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class LionfishSpikeEntity extends PersistentProjectileEntity {
    private int duration = 220;

    public LionfishSpikeEntity(EntityType<? extends  LionfishSpikeEntity> entityType, World world) {
        super(entityType, world);
    }

    public LionfishSpikeEntity(World world, double x, double y, double z, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(OrganicallyModEntityTypes.LIONFISH_SPIKE, x, y, z, world, stack, shotFrom);
    }

    public LionfishSpikeEntity(World world, LivingEntity owner, ItemStack stack, @Nullable ItemStack shotFrom) {
        super(OrganicallyModEntityTypes.LIONFISH_SPIKE, owner, world, stack, shotFrom);
    }

    protected void onHit(LivingEntity target) {
        super.onHit(target);
        StatusEffectInstance statusEffectInstance = new StatusEffectInstance(OrganicallyModEffects.ELECTRIFIED, this.duration, 0);
        target.addStatusEffect(statusEffectInstance, this.getEffectCause());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Duration")) {
            this.duration = nbt.getInt("Duration");
        }

    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Duration", this.duration);
    }



    @Override
    public void setDamage(double damage) {
        super.setDamage(2);
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(OrganicallyModItems.LIONFISH_SPIKE);
    }

    @Override
    protected float getDragInWater() {
        return 0.99f;
    }
}
