package net.eravern.organically.mixin;


import net.eravern.organically.mob_effect.OrganicallyModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class ElectrifiedMixin {

    @Shadow public abstract boolean addStatusEffect(StatusEffectInstance effect, @Nullable Entity source);

    @Inject(method = "damage", at = @At("TAIL"))
    public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        if (source.isDirect()){
            if (source.getSource() instanceof LivingEntity livingEntity){
                if (livingEntity.hasStatusEffect(OrganicallyModEffects.ELECTRIFIED)){
                    int duration = (int)(livingEntity.getStatusEffect(OrganicallyModEffects.ELECTRIFIED).getDuration()/2);
                    if (duration <= 0){
                        duration = 1225;
                    }
                    int amplifier = livingEntity.getStatusEffect(OrganicallyModEffects.ELECTRIFIED).getAmplifier();
                    if (amplifier > 0){
                        amplifier -= 1;
                    }
                    this.addStatusEffect(new StatusEffectInstance(OrganicallyModEffects.ELECTRIFIED, duration, amplifier), source.getSource());
                }
            }
        }
    }
}
