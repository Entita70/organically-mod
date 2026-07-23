package net.eravern.organically.farmers_delight.item;

import net.eravern.organically.OrganicallyMod;
import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.farmers_delight.block.FDCompatBlocks;
import net.eravern.organically.item.OrganicallyModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import vectorwing.farmersdelight.common.item.ConsumableItem;
import vectorwing.farmersdelight.common.item.MushroomColonyItem;

public class FDCompatItems {

    public static final Item LIONFISH_SLICE = registerItem("lionfish_slice", new Item(new Item.Settings().food(FDFoodComponents.LIONFISH_SLICE)));
    public static final Item COOKED_LIONFISH_SLICE = registerItem("cooked_lionfish_slice", new Item(new Item.Settings().food(FDFoodComponents.COOKED_LIONFISH_SLICE)));
    public static final Item DESERT_SHAGGY_MANE_COLONY = registerItem("desert_shaggy_mane_colony", new MushroomColonyItem(FDCompatBlocks.DESERT_SHAGGY_MANE_COLONY, new Item.Settings()));
    public static final Item MESA_STYLE_LIONFISH = registerItem("mesa_style_lionfish", new ConsumableItem(new Item.Settings().food(FDFoodComponents.MESA_STYLE_LIONFISH)));
    public static final Item RICH_MANS_SALAD = registerItem("rich_mans_salad", createStewItem(FDFoodComponents.RICH_MANS_SALAD));
    public static final Item MUSHROOM_AND_TOMATO_SOUP = registerItem("mushroom_and_tomato_soup", createStewItem(FDFoodComponents.MUSHROOM_AND_TOMATO_SOUP));
    public static final Item ELECTRIFIED_STEW = registerItem("electrified_stew", new ElectrifiedStewItem(new Item.Settings().food(FDFoodComponents.ELECTRIFIED_STEW).recipeRemainder(Items.BOWL).maxCount(16)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(OrganicallyMod.MOD_ID, name), item);
    }

    private static Item createStewItem(FoodComponent foodComponent){
            return new ConsumableItem(new Item.Settings().food(foodComponent).maxCount(16).recipeRemainder(Items.BOWL));
    }

    public static void registerFDItems() {
        OrganicallyMod.LOGGER.info("FD compat Items Registry for " + OrganicallyMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {

        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(OrganicallyModBlocks.DESERT_SHAGGY_MANE, DESERT_SHAGGY_MANE_COLONY);

        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.addAfter(OrganicallyModItems.LIONFISH, LIONFISH_SLICE);
            entries.addAfter(OrganicallyModItems.COOKED_LIONFISH, COOKED_LIONFISH_SLICE);
            entries.addAfter(COOKED_LIONFISH_SLICE, MESA_STYLE_LIONFISH);
            entries.addAfter(OrganicallyModItems.SEAFARERS_CHOW, RICH_MANS_SALAD);
            entries.addAfter(RICH_MANS_SALAD, MUSHROOM_AND_TOMATO_SOUP);
            entries.addAfter(MUSHROOM_AND_TOMATO_SOUP, ELECTRIFIED_STEW);

        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {


        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {


        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {

        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {


        });
    }
}
