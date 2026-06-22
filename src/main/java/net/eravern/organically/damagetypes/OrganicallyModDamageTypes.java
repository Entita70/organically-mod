package net.eravern.organically.damagetypes;

import net.eravern.organically.OrganicallyMod;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class OrganicallyModDamageTypes {

    public static final RegistryKey<DamageType> ELECTRIC = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(OrganicallyMod.MOD_ID, "electric"));


    public static DamageSource of(World world, RegistryKey<DamageType> key){
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
