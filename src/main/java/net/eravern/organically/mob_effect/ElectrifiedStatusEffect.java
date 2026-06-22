package net.eravern.organically.mob_effect;


import net.eravern.organically.damagetypes.OrganicallyModDamageTypes;
import net.eravern.organically.particle.OrganicallyModParticleTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class ElectrifiedStatusEffect extends StatusEffect {
    protected ElectrifiedStatusEffect(StatusEffectCategory category, int color) {
        super(category, color, OrganicallyModParticleTypes.ELECTRIFIED);
    }


    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (entity.isWet()){
            entity.damage(OrganicallyModDamageTypes.of(entity.getWorld(), OrganicallyModDamageTypes.ELECTRIC), 1+amplifier);
        }
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 10;
        return duration % i == 0;
    }
}
