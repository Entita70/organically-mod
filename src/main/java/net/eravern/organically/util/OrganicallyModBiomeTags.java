package net.eravern.organically.util;

import net.eravern.organically.OrganicallyMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class OrganicallyModBiomeTags {

    public static final TagKey<Biome> IS_COLDER_OCEAN = createTag("is_colder_ocean");
    public static final TagKey<Biome> IS_WARMER_OCEAN = createTag("is_warmer_ocean");


    private static TagKey<Biome> createTag(String name) {
        return TagKey.of(RegistryKeys.BIOME, Identifier.of(OrganicallyMod.MOD_ID, name));
    }
}