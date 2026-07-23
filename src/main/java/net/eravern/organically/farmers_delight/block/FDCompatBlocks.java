package net.eravern.organically.farmers_delight.block;

import net.eravern.organically.OrganicallyMod;
import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.item.OrganicallyModItems;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import vectorwing.farmersdelight.common.block.CabinetBlock;
import vectorwing.farmersdelight.common.block.MushroomColonyBlock;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class FDCompatBlocks {



    public static final Block PALM_CABINET = registerBlock("palm_cabinet",
            new CabinetBlock(AbstractBlock.Settings.copy(Blocks.BARREL)));

    public static final Block DESERT_SHAGGY_MANE_COLONY = registerItemlessBlock("desert_shaggy_mane_colony",
            new MushroomColonyBlock(OrganicallyModBlocks.DESERT_SHAGGY_MANE.asItem().getRegistryEntry(), AbstractBlock.Settings.copy(OrganicallyModBlocks.DESERT_SHAGGY_MANE)));




    public static Block createLogBlock(MapColor topMapColor, MapColor sideMapColor) {
        return new PillarBlock(AbstractBlock.Settings.create().mapColor((state) -> {
            return state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor;
        }).instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable());
    }

    public static Block createFlowerPotBlock(Block flower) {
        return new FlowerPotBlock(flower, AbstractBlock.Settings.create().breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).mapColor(MapColor.BROWN));
    }

    public static Block createPlanksBlock(MapColor mapColor, BlockSoundGroup soundGroup) {
        return new Block(AbstractBlock.Settings.create().mapColor(mapColor).instrument(NoteBlockInstrument.BASS).strength(2.0f, 3.0f)
                .sounds(soundGroup).burnable());
    }

    public static Block createWoodenButtonBlock(BlockSetType blockSetType) {
        return new ButtonBlock(blockSetType, 30, AbstractBlock.Settings.create().noCollision().strength(0.5F).pistonBehavior(PistonBehavior.DESTROY));
    }

    public static Block createLeavesBlock(BlockSoundGroup soundGroup) {
        return new LeavesBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN)
                .strength(0.2F).ticksRandomly().sounds(soundGroup)
                .nonOpaque().allowsSpawning(Blocks::canSpawnOnLeaves)
                .suffocates(Blocks::never).blockVision(Blocks::never).burnable()
                .pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never));
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(OrganicallyMod.MOD_ID, name), block);
    }

    private static Block registerItemlessBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(OrganicallyMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(OrganicallyMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerFDBlocks() {
        OrganicallyMod.LOGGER.info("FD Compat Blocks Registry For " + OrganicallyMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {


        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {

        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {

        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addAfter(Blocks.BARREL, PALM_CABINET);

        });

    }
}
