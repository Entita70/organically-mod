package net.eravern.organically.item.custom;

import net.eravern.organically.OrganicallyMod;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class OrganicallyModArmorMaterials {
    public static final RegistryEntry<ArmorMaterial> SANDSTRIDER = registerMaterial("sandstrider" ,
            () -> new ArmorMaterial(Util.make(new EnumMap(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 1);
                map.put(ArmorItem.Type.LEGGINGS, 3);
                map.put(ArmorItem.Type.CHESTPLATE, 5);
                map.put(ArmorItem.Type.HELMET, 1);
                map.put(ArmorItem.Type.BODY, 8);
            }), 10, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, () -> Ingredient.ofItems(Items.LEATHER),
                    List.of(new ArmorMaterial.Layer(Identifier.of(OrganicallyMod.MOD_ID, "sandstrider"))),
                    0, 0));


    public static RegistryEntry<ArmorMaterial> registerMaterial(String name, Supplier<ArmorMaterial> armorMaterial){
        return Registry.registerReference(Registries.ARMOR_MATERIAL, Identifier.of(OrganicallyMod.MOD_ID, name), armorMaterial.get());
    }
}
