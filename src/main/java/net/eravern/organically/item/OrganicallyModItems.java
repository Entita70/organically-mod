package net.eravern.organically.item;

import net.eravern.organically.OrganicallyMod;
import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.entity.OrganicallyModEntityTypes;
import net.eravern.organically.item.custom.*;
import net.eravern.organically.register.OrganicallyModBoatTypes;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class OrganicallyModItems {

    public static final Item PALM_SIGN = registerItem("palm_sign", new SignItem(new Item.Settings().maxCount(16), OrganicallyModBlocks.PALM_SIGN, OrganicallyModBlocks.PALM_WALL_SIGN));
    public static final Item PALM_HANGING_SIGN = registerItem("palm_hanging_sign", new HangingSignItem(OrganicallyModBlocks.PALM_HANGING_SIGN, OrganicallyModBlocks.PALM_WALL_HANGING_SIGN, new Item.Settings().maxCount(16)));
    public static final Item PALM_BOAT = registerItem("palm_boat", new BoatItem(false, OrganicallyModBoatTypes.PALM, (new Item.Settings()).maxCount(1)));
    public static final Item PALM_CHEST_BOAT = registerItem("palm_chest_boat", new BoatItem(true, OrganicallyModBoatTypes.PALM, (new Item.Settings()).maxCount(1)));
    public static final Item LIONFISH = registerItem("lionfish", new Item(new Item.Settings().food(OrganicallyFoodComponents.LIONFISH)));
    public static final Item COOKED_LIONFISH = registerItem("cooked_lionfish", new Item(new Item.Settings().food(OrganicallyFoodComponents.COOKED_LIONFISH)));
    public static final Item LIONFISH_SPIKE = registerItem("lionfish_spike", new LionfishSpikeItem(new Item.Settings()));
    public static final Item LIONFISH_BUCKET = registerItem("lionfish_bucket", new EntityBucketItem(OrganicallyModEntityTypes.LIONFISH, Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH,
            new Item.Settings().maxCount(1).component(DataComponentTypes.BUCKET_ENTITY_DATA, NbtComponent.DEFAULT)));
    public static final Item LIONFISH_SPAWN_EGG = registerItem("lionfish_spawn_egg", new SpawnEggItem(OrganicallyModEntityTypes.LIONFISH, 9054491, 15850932, new Item.Settings()));
    public static final Item SANDSTRIDER_SPAWN_EGG = registerItem("sandstrider_spawn_egg", new SpawnEggItem(OrganicallyModEntityTypes.SANDSTRIDER, 16242605, 10829587, new Item.Settings()));
    public static final Item GNAWER_SPAWN_EGG = registerItem("gnawer_spawn_egg", new SpawnEggItem(OrganicallyModEntityTypes.GNAWER, 16769024, 65385, new Item.Settings()));
    public static final Item COCONUT = registerItem("coconut", new AliasedBlockItem(OrganicallyModBlocks.COCONUT, new Item.Settings()));
    public static final Item COCONUT_SLICE = registerItem("coconut_slice", new Item(new Item.Settings().food(OrganicallyFoodComponents.COCONUT_SLICE)));
    public static final Item PALM_SALAD = registerItem("palm_salad", new Item(new Item.Settings().food(OrganicallyFoodComponents.PALM_SALAD)));
    public static final Item SANDSTRIDER_SCALE = registerItem("sandstrider_scale", new Item(new Item.Settings()));
    public static final Item SANDSTRIDER_BOOTS = registerItem("sandstrider_boots", new SandStriderArmorItem(OrganicallyModArmorMaterials.SANDSTRIDER, ArmorItem.Type.BOOTS, new Item.Settings()
            .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(20)).maxCount(1)));
    public static final Item TROWEL = registerItem("trowel", new TrowelItem(new Item.Settings()
            .maxDamage(256).component(DataComponentTypes.TOOL, TrowelItem.createToolComponent()).maxCount(1)));
    public static final Item FLUORESCENT_MUCUS = registerItem("fluorescent_mucus", new FluorescentMucusItem(new Item.Settings()));
    public static final Item CALCIUM = registerItem("calcium", new Item(new Item.Settings()));
    public static final Item ACTIVE_POWDER = registerItem("active_powder", new ActivePowderItem(new Item.Settings()));
    public static final Item SEAFARERS_CHOW = registerItem("seafarers_chow", new Item(new Item.Settings().food(OrganicallyFoodComponents.SEAFARERS_CHOW).maxCount(16)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(OrganicallyMod.MOD_ID, name), item);
    }

    public static void registerItems() {
        OrganicallyMod.LOGGER.info("Item Registry for " + OrganicallyMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.ARMADILLO_SCUTE, SANDSTRIDER_SCALE);
            entries.addAfter(Items.GLOW_INK_SAC, FLUORESCENT_MUCUS);
            entries.addAfter(Items.AMETHYST_SHARD, CALCIUM);
            entries.addAfter(Items.GUNPOWDER, ACTIVE_POWDER);


        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Items.CACTUS, COCONUT);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(entries -> {
            entries.addAfter(Items.COOKED_SALMON, LIONFISH);
            entries.addAfter(LIONFISH, COOKED_LIONFISH);
            entries.addAfter(Items.BEETROOT, COCONUT_SLICE);
            entries.addAfter(COCONUT_SLICE, PALM_SALAD);
            entries.addAfter(Items.BEETROOT_SOUP, SEAFARERS_CHOW);


        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(LIONFISH_SPAWN_EGG);
            entries.add(SANDSTRIDER_SPAWN_EGG);
            entries.add(GNAWER_SPAWN_EGG);

        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.addAfter(Items.SPECTRAL_ARROW, LIONFISH_SPIKE);
            entries.addAfter(Items.TURTLE_HELMET, SANDSTRIDER_BOOTS);

        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.addAfter(Items.PUFFERFISH_BUCKET, LIONFISH_BUCKET);
            entries.addAfter(Items.BRUSH, TROWEL);
            entries.addAfter(Items.ACACIA_CHEST_BOAT, PALM_BOAT);
            entries.addAfter(PALM_BOAT, PALM_CHEST_BOAT);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addAfter(Items.ACACIA_HANGING_SIGN, PALM_SIGN);
            entries.addAfter(PALM_SIGN, PALM_HANGING_SIGN);

        });
    }
}
