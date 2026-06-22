package net.eravern.organically.mob_effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class GenericModEffect extends StatusEffect {
    protected GenericModEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }
}
