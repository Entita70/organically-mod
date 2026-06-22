package net.eravern.organically.mob_effect;

import net.eravern.organically.OrganicallyMod;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class OrganicallyModEffects {


    public static final RegistryEntry<StatusEffect> ELECTRIFIED = registerStatusEffect("electrified",
            new ElectrifiedStatusEffect(StatusEffectCategory.HARMFUL, 15662989));


    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect){
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(OrganicallyMod.MOD_ID, name), statusEffect);
    }

    public static void registerEffects(){
        OrganicallyMod.LOGGER.info("Effects registry for " + OrganicallyMod.MOD_ID);
    }

}
