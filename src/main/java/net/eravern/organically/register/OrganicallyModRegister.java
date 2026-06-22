package net.eravern.organically.register;

import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.entity.OrganicallyModEntityTypes;
import net.eravern.organically.world.gen.OrganicallyModFeaturesSpawns;
import net.eravern.organically.item.OrganicallyModItems;
import net.eravern.organically.mob_effect.OrganicallyModEffects;
import net.eravern.organically.particle.OrganicallyModParticleTypes;
import net.eravern.organically.world.gen.features.OrganicallyModFeatures;

public class OrganicallyModRegister {
    public static void registerAll(){

        OrganicallyModItems.registerItems();
        OrganicallyModBlocks.registerBlocks();
        OrganicallyModEffects.registerEffects();
        OrganicallyModParticleTypes.registerModParticleTypes();
        OrganicallyModEntityTypes.registerModEntityTypes();
        OrganicallyModFeatures.registerModFeatures();

        OrganicallyModFeaturesSpawns.registerMobSpawns();
        OrganicallyModFeaturesSpawns.registerTreesSpawns();
        OrganicallyModFeaturesSpawns.registerFlowersSpawns();
        OrganicallyModFeaturesSpawns.registerStonySpawns();
        OrganicallyModFeaturesSpawns.registerWeedsSpawns();

        OrganicallyModLootTableModifier.registerLootTables();

    }
}
