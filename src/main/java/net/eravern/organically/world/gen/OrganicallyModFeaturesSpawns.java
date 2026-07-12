package net.eravern.organically.world.gen;

import net.eravern.organically.entity.OrganicallyModEntityTypes;
import net.eravern.organically.world.gen.features.OrganicallyModPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;

public class OrganicallyModFeaturesSpawns {
    public static void registerMobSpawns(){
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.WARM_OCEAN),
                SpawnGroup.WATER_AMBIENT, OrganicallyModEntityTypes.LIONFISH, 9, 1, 2);

        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(BiomeKeys.DESERT, BiomeKeys.BADLANDS, BiomeKeys.ERODED_BADLANDS),
                SpawnGroup.MONSTER, OrganicallyModEntityTypes.SANDSTRIDER, 10, 1, 3);

    }

    public static void registerTreesSpawns(){
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BEACH),
                GenerationStep.Feature.VEGETAL_DECORATION, OrganicallyModPlacedFeatures.TREES_BEACH);
    }

    public static void registerFlowersSpawns(){

    }

    public static void registerWeedsSpawns(){

    }

    public static void registerStonySpawns(){
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.DESERT),
                GenerationStep.Feature.TOP_LAYER_MODIFICATION, OrganicallyModPlacedFeatures.DESERT_ROSE_CLUSTER);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.DESERT),
                GenerationStep.Feature.TOP_LAYER_MODIFICATION, OrganicallyModPlacedFeatures.DESERT_FOSSIL_FORMATION);

        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.BADLANDS, BiomeKeys.ERODED_BADLANDS),
                GenerationStep.Feature.TOP_LAYER_MODIFICATION, OrganicallyModPlacedFeatures.MESA_ROSE_CLUSTER);


    }
}
