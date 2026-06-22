package net.eravern.organically.world.gen.tree;


import net.eravern.organically.OrganicallyMod;
import net.eravern.organically.world.gen.features.OrganicallyModConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class OrganicallyModSaplingProvider {
    public static SaplingGenerator PALM = new SaplingGenerator((OrganicallyMod.MOD_ID + ":palm"),
            Optional.empty(), Optional.of(OrganicallyModConfiguredFeatures.COCONUTLESS_PALM), Optional.empty());

}
