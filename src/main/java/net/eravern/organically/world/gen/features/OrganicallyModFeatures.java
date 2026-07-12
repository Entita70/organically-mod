package net.eravern.organically.world.gen.features;

import net.eravern.organically.OrganicallyMod;
import net.eravern.organically.world.gen.features.feature_config.ClusterPlaceFeatureConfig;
import net.eravern.organically.world.gen.features.feature_config.DesertFormationFeatureConfig;
import net.eravern.organically.world.gen.features.feature_config.DesertRoseFeatureConfig;
import net.eravern.organically.world.gen.features.feature_files.*;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;

public class OrganicallyModFeatures {


    public static final Identifier HUGE_DESERT_SHAGGY_MANE_ID = Identifier.of(OrganicallyMod.MOD_ID, "huge_desert_shaggy_mane");
    public static final HugeDesertShaggyManeMushroomFeature HUGE_DESERT_SHAGGY_MANE = new HugeDesertShaggyManeMushroomFeature(HugeMushroomFeatureConfig.CODEC);

    public static final Identifier DESERT_ROSE_ID = Identifier.of(OrganicallyMod.MOD_ID, "desert_rose");
    public static final DesertRoseFeature DESERT_ROSE = new DesertRoseFeature(DesertRoseFeatureConfig.CODEC);

    public static final Identifier DESERT_FORMATION_ID = Identifier.of(OrganicallyMod.MOD_ID, "desert_formation");
    public static final DesertFormationFeature DESERT_FORMATION = new DesertFormationFeature(DesertFormationFeatureConfig.CODEC);

    public static final Identifier ROCK_ID = Identifier.of(OrganicallyMod.MOD_ID, "rock");
    public static final RockFeature ROCK = new RockFeature(SingleStateFeatureConfig.CODEC);

    public static final Identifier SEA_PILLAR_ID = Identifier.of(OrganicallyMod.MOD_ID, "sea_pillar");
    public static final SeaPillarFeature SEA_PILLAR = new SeaPillarFeature(SingleStateFeatureConfig.CODEC);

    public static final Identifier UNDERWATER_PATCH_ID = Identifier.of(OrganicallyMod.MOD_ID, "underwater_patch");
    public static final UnderwaterPatchFeature UNDERWATER_PATCH = new UnderwaterPatchFeature(SingleStateFeatureConfig.CODEC);

    public static final Identifier CLUSTER_PLACER_ID = Identifier.of(OrganicallyMod.MOD_ID, "cluster_placer");
    public static final ClusterPlaceFeature CLUSTER_PLACER = new ClusterPlaceFeature(ClusterPlaceFeatureConfig.CODEC);

    public static void registerModFeatures(){
        OrganicallyMod.LOGGER.info("Features Registry for " + OrganicallyMod.MOD_ID);
    }
}
