package net.eravern.organically.world.gen.features;

import net.eravern.organically.OrganicallyMod;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;

public class OrganicallyModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> TREES_BEACH = registerKey("trees_beach");
    public static final RegistryKey<PlacedFeature> DESERT_ROSE_CLUSTER = registerKey("desert_rose_cluster");
    public static final RegistryKey<PlacedFeature> MESA_ROSE_CLUSTER = registerKey("mesa_rose_cluster");
    public static final RegistryKey<PlacedFeature> DESERT_FOSSIL_FORMATION = registerKey("desert_fossil_formation");

    public static RegistryKey<PlacedFeature> registerKey(String name){
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(OrganicallyMod.MOD_ID, name));
    }
}
