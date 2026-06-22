package net.eravern.organically.world.gen.features;

import net.eravern.organically.OrganicallyMod;
import net.eravern.organically.world.gen.features.feature_config.DesertFormationFeatureConfig;
import net.eravern.organically.world.gen.features.feature_config.DesertRoseFeatureConfig;
import net.eravern.organically.world.gen.features.feature_files.DesertFormationFeature;
import net.eravern.organically.world.gen.features.feature_files.DesertRoseFeature;
import net.eravern.organically.world.gen.features.feature_files.HugeDesertShaggyManeMushroomFeature;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;

public class OrganicallyModFeatures {


    public static final Identifier HUGE_DESERT_SHAGGY_MANE_ID = Identifier.of(OrganicallyMod.MOD_ID, "huge_desert_shaggy_mane");
    public static final HugeDesertShaggyManeMushroomFeature HUGE_DESERT_SHAGGY_MANE = new HugeDesertShaggyManeMushroomFeature(HugeMushroomFeatureConfig.CODEC);

    public static final Identifier DESERT_ROSE_ID = Identifier.of(OrganicallyMod.MOD_ID, "desert_rose");
    public static final DesertRoseFeature DESERT_ROSE = new DesertRoseFeature(DesertRoseFeatureConfig.CODEC);

    public static final Identifier DESERT_FORMATION_ID = Identifier.of(OrganicallyMod.MOD_ID, "desert_formation");
    public static final DesertFormationFeature DESERT_FORMATION = new DesertFormationFeature(DesertFormationFeatureConfig.CODEC);



    public static void registerModFeatures(){
        OrganicallyMod.LOGGER.info("Features Registry for " + OrganicallyMod.MOD_ID);
    }
}
