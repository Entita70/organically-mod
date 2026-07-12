package net.eravern.organically.util;

import net.eravern.organically.OrganicallyMod;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class OrganicallyModItemTags {

    public static final TagKey<Item> PALM_LOGS = createTag("palm_logs");
    public static final TagKey<Item> FOSSILS = createTag("fossils");
    public static final TagKey<Item> MOLLUSKS = createTag("mollusks");
    public static final TagKey<Item> COOKED_FISHES = createTag("cooked_fishes");
    public static final TagKey<Item> BONES = createTag("bones");
    public static final TagKey<Item> GNAWER_FOOD = createTag("gnawer_food");
    public static final TagKey<Item> GNAWABLE = createTag("gnawable");
    public static final TagKey<Item> GLOWING_ITEMS = createTag("glowing_items");
    public static final TagKey<Item> DESERT_ROSES = createTag("desert_roses");
    public static final TagKey<Item> BRITTLEBUSH_IMMUNE = createTag("brittlebush_immune");
    public static final TagKey<Item> SANDSTRIDER_ARMOR = createTag("sandstrider_armor");


    private static TagKey<Item> createTag(String name) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(OrganicallyMod.MOD_ID, name));
    }
}