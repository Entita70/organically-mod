package net.eravern.organically.world.gen.features;

import net.eravern.organically.OrganicallyMod;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class OrganicallyModConfiguredFeatures {
    public static final RegistryKey<ConfiguredFeature<?, ?>> TALL_PALM = registerKey("tall_palm");
    public static final RegistryKey<ConfiguredFeature<?, ?>> PALM = registerKey("palm");
    public static final RegistryKey<ConfiguredFeature<?, ?>> COCONUTLESS_PALM = registerKey("coconutless_palm");
    public static final RegistryKey<ConfiguredFeature<?, ?>> HUGE_DESERT_SHAGGY_MANE = registerKey("huge_desert_shaggy_mane");




    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, Identifier.of(OrganicallyMod.MOD_ID, name));
    }
}
