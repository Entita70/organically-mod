package net.eravern.organically.util;

import net.eravern.organically.OrganicallyMod;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class OrganicallyModBiomeTags {


    private static TagKey<Biome> createTag(String name) {
        return TagKey.of(RegistryKeys.BIOME, Identifier.of(OrganicallyMod.MOD_ID, name));
    }
}