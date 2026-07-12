package net.eravern.organically.util;


import net.eravern.organically.OrganicallyMod;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class OrganicallyModBlockTags {

    public static final TagKey<Block> PALM_LOGS = createTag("palm_logs");
    public static final TagKey<Block> PALM_LEAVES_REPLACEABLE = createTag("palm_leaves_replaceable");
    public static final TagKey<Block> DESERT_MUSHROOMS_GROW = createTag("desert_mushrooms_grow");
    public static final TagKey<Block> DESERT_PLANT_BLOCK = createTag("desert_plant_block");
    public static final TagKey<Block> DESERT_SPAWN_BLOCKS = createTag("desert_spawn_blocks");
    public static final TagKey<Block> FOSSILS = createTag("fossils");
    public static final TagKey<Block> MOLLUSKS = createTag("mollusks");
    public static final TagKey<Block> TROWEL_MINEABLE = createTag("trowel_mineable");
    public static final TagKey<Block> ACTIVABLE = createTag("activable");
    public static final TagKey<Block> GNAWER_HATCH_BOOST = createTag("gnawer_hatch_boost");
    public static final TagKey<Block> CLUSTER_PLACEABLE = createTag("cluster_placeable");


    private static TagKey<Block> createTag(String name) {
        return TagKey.of(RegistryKeys.BLOCK, Identifier.of(OrganicallyMod.MOD_ID, name));
    }
}