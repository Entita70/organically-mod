package net.eravern.organically.world.gen.features;

import net.eravern.organically.OrganicallyMod;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.PlacedFeature;

public class OrganicallyModPlacedFeatures {

    public static final RegistryKey<PlacedFeature> TREES_BEACH = registerKey("trees_beach");
    public static final RegistryKey<PlacedFeature> PATCH_DESERT_MARIGOLDS = registerKey("patch_desert_marigolds");
    public static final RegistryKey<PlacedFeature> PATCH_BRITTLEBUSH = registerKey("patch_brittlebush");
    public static final RegistryKey<PlacedFeature> PATCH_DESERT_SHAGGY_MANE_WOODED_MESA = registerKey("patch_desert_shaggy_mane_wooded_mesa");
    public static final RegistryKey<PlacedFeature> PATCH_TRIODIA = registerKey("patch_triodia");
    public static final RegistryKey<PlacedFeature> PATCH_SNAKE_PLANT = registerKey("patch_snake_plant");
    public static final RegistryKey<PlacedFeature> PATCH_BARREL_CACTI = registerKey("patch_barrel_cacti");
    public static final RegistryKey<PlacedFeature> DESERT_ROSE_CLUSTER = registerKey("desert_rose_cluster");
    public static final RegistryKey<PlacedFeature> MESA_ROSE_CLUSTER = registerKey("mesa_rose_cluster");
    public static final RegistryKey<PlacedFeature> DESERT_FOSSIL_FORMATION = registerKey("desert_fossil_formation");

    public static RegistryKey<PlacedFeature> registerKey(String name){
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(OrganicallyMod.MOD_ID, name));
    }
}
