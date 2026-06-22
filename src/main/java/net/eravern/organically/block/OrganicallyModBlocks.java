package net.eravern.organically.block;

import net.eravern.organically.OrganicallyMod;
import net.eravern.organically.block.custom.*;
import net.eravern.organically.world.gen.features.OrganicallyModConfiguredFeatures;
import net.eravern.organically.world.gen.tree.OrganicallyModSaplingProvider;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class OrganicallyModBlocks {

    public static final Block SPROUTED_COCONUT = registerItemlessBlock("sprouted_coconut",
            new PalmSaplingBlock(OrganicallyModSaplingProvider.PALM, AbstractBlock.Settings.create().mapColor(MapColor.BROWN)
                    .ticksRandomly().nonOpaque().strength(0.3f)
                    .burnable().sounds(BlockSoundGroup.BAMBOO)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block COCONUT_CROP = registerItemlessBlock("coconut_crop",
            new CoconutCropBlock(AbstractBlock.Settings.create().mapColor(MapColor.BROWN)
                    .ticksRandomly().nonOpaque().strength(0.5f)
                    .burnable().sounds(BlockSoundGroup.BAMBOO)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block COCONUT = registerItemlessBlock("coconut",
            new CoconutBlock(AbstractBlock.Settings.create().mapColor(MapColor.BROWN)
                    .ticksRandomly().nonOpaque().strength(0.5f)
                    .burnable().sounds(BlockSoundGroup.BAMBOO)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block COCONUT_LAMP = registerBlock("coconut_lamp",
            new CoconutLampBlock(AbstractBlock.Settings.create().mapColor(MapColor.BROWN)
                    .nonOpaque().strength(0.2f).luminance((state) -> 14)
                    .sounds(BlockSoundGroup.BAMBOO)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block PALM_CROWN = registerBlock("palm_crown",
            new PalmCrownBlock(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_BROWN)
                    .ticksRandomly().instrument(NoteBlockInstrument.BASS).strength(2.0F)
                    .sounds(BlockSoundGroup.WOOD).burnable()));

    public static final Block PALM_LOG = registerBlock("palm_log",
            createLogBlock(MapColor.TERRACOTTA_YELLOW, MapColor.BROWN));

    public static final Block STRIPPED_PALM_LOG = registerBlock("stripped_palm_log",
            createLogBlock(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_YELLOW));

    public static final Block PALM_WOOD = registerBlock("palm_wood",
            createLogBlock(MapColor.BROWN, MapColor.BROWN));

    public static final Block STRIPPED_PALM_WOOD = registerBlock("stripped_palm_wood",
            createLogBlock(MapColor.TERRACOTTA_YELLOW, MapColor.TERRACOTTA_YELLOW));

    public static final Block PALM_PLANKS = registerBlock("palm_planks",
            createPlanksBlock(MapColor.TERRACOTTA_YELLOW, BlockSoundGroup.WOOD));

    public static final Block PALM_SLAB = registerBlock("palm_slab",
            new SlabBlock(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_YELLOW).instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable()));

    public static final Block PALM_STAIRS = registerBlock("palm_stairs",
            new StairsBlock(PALM_PLANKS.getDefaultState(), AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_YELLOW)
                    .instrument(NoteBlockInstrument.BASS).strength(2.0f, 3.0f)
                    .sounds(BlockSoundGroup.WOOD).burnable()));

    public static final Block PALM_FENCE = registerBlock("palm_fence",
            new FenceBlock(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_YELLOW)
                    .solid().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.WOOD).burnable()));

    public static final Block PALM_FENCE_GATE = registerBlock("palm_fence_gate",
            new FenceGateBlock(OrganicallyMod.PALM ,AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_YELLOW)
                    .solid().instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.WOOD).burnable()));

    public static final Block PALM_DOOR = registerBlock("palm_door",
            new DoorBlock(OrganicallyMod.PALM_SET, AbstractBlock.Settings.create().strength(2f, 3f)
                    .requiresTool().burnable().sounds(BlockSoundGroup.WOOD).mapColor(MapColor.TERRACOTTA_YELLOW)
                    .instrument(NoteBlockInstrument.BASS).nonOpaque().pistonBehavior(PistonBehavior.DESTROY)
                    .allowsSpawning(Blocks::never)));

    public static final Block PALM_TRAPDOOR = registerBlock("palm_trapdoor",
            new TrapdoorBlock(OrganicallyMod.PALM_SET, AbstractBlock.Settings.create().strength(2f, 3f)
                    .requiresTool().burnable().sounds(BlockSoundGroup.WOOD).mapColor(MapColor.TERRACOTTA_YELLOW)
                    .instrument(NoteBlockInstrument.BASS).nonOpaque().allowsSpawning(Blocks::never)));

    public static final Block PALM_BUTTON = registerBlock("palm_button",
            createWoodenButtonBlock(OrganicallyMod.PALM_SET));

    public static final Block PALM_PRESSURE_PLATE = registerBlock("palm_pressure_plate",
            new PressurePlateBlock(OrganicallyMod.PALM_SET, AbstractBlock.Settings.create()
                    .mapColor(MapColor.TERRACOTTA_YELLOW).solid().instrument(NoteBlockInstrument.BASS)
                    .noCollision().strength(0.5F).burnable().pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block PALM_SIGN = registerItemlessBlock("palm_sign",
            new SignBlock(OrganicallyMod.PALM, AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_YELLOW).solid()
                    .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).burnable()));

    public static final Block PALM_WALL_SIGN = registerItemlessBlock("palm_wall_sign",
            new WallSignBlock(OrganicallyMod.PALM, AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_YELLOW).solid()
                    .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).dropsLike(PALM_SIGN).burnable()));

    public static final Block PALM_HANGING_SIGN = registerItemlessBlock("palm_hanging_sign",
            new HangingSignBlock(OrganicallyMod.PALM, AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_YELLOW).solid()
                    .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).burnable()));

    public static final Block PALM_WALL_HANGING_SIGN = registerItemlessBlock("palm_wall_hanging_sign",
            new WallHangingSignBlock(OrganicallyMod.PALM, AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_YELLOW).solid()
                    .instrument(NoteBlockInstrument.BASS).noCollision().strength(1.0F).dropsLike(PALM_HANGING_SIGN).burnable()));

    public static final Block PALM_LEAVES = registerBlock("palm_leaves",
            createLeavesBlock(BlockSoundGroup.GRASS));

    public static final Block DESERT_MARIGOLDS = registerBlock("desert_marigolds",
            new SandFlowerBlock(StatusEffects.HASTE, 5.0f, AbstractBlock.Settings.create()
                    .mapColor(MapColor.PALE_YELLOW).noCollision().breakInstantly()
                    .sounds(BlockSoundGroup.GRASS).burnable()
                    .offset(AbstractBlock.OffsetType.XZ)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block POTTED_DESERT_MARIGOLDS = registerItemlessBlock("potted_desert_marigolds",
            createFlowerPotBlock(DESERT_MARIGOLDS));

    public static final Block BRITTLEBUSH = registerBlock("brittlebush",
            new BrittleBushBlock(StatusEffects.UNLUCK, 10.0f, AbstractBlock.Settings.create()
                    .mapColor(MapColor.PALE_YELLOW).noCollision().breakInstantly()
                    .sounds(BlockSoundGroup.GRASS).nonOpaque().burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block DESERT_SHAGGY_MANE = registerBlock("desert_shaggy_mane",
            new DesertMushroomPlantBlock(OrganicallyModConfiguredFeatures.HUGE_DESERT_SHAGGY_MANE,AbstractBlock.Settings.create()
                    .mapColor(MapColor.DIRT_BROWN).noCollision().ticksRandomly()
                    .breakInstantly().sounds(BlockSoundGroup.GRASS).postProcess(Blocks::always)
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block POTTED_DESERT_SHAGGY_MANE = registerItemlessBlock("potted_desert_shaggy_mane",
            createFlowerPotBlock(DESERT_SHAGGY_MANE));

    public static final Block DESERT_SHAGGY_MANE_BLOCK = registerBlock("desert_shaggy_mane_block",
            new MushroomBlock(AbstractBlock.Settings.create().mapColor(MapColor.DIRT_BROWN).instrument(NoteBlockInstrument.BASS)
                    .strength(0.2F).sounds(BlockSoundGroup.WOOD).burnable()));

    public static final Block BARREL_CACTUS = registerBlock("barrel_cactus",
            new BarrelCactusBlock(AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN)
                    .strength(0.4F).sounds(BlockSoundGroup.WOOL).pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block POTTED_BARREL_CACTUS = registerItemlessBlock("potted_barrel_cactus",
            createFlowerPotBlock(BARREL_CACTUS));

    public static final Block TRIODIA = registerBlock("triodia",
            new ShortDesertPlant(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PALE_GREEN).noCollision().breakInstantly()
                    .sounds(BlockSoundGroup.GRASS).replaceable()
                    .offset(AbstractBlock.OffsetType.XYZ).burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block POTTED_TRIODIA = registerItemlessBlock("potted_triodia",
            createFlowerPotBlock(TRIODIA));

    public static final Block SNAKE_PLANT = registerBlock("snake_plant",
            new DecorativeDesertPlant(AbstractBlock.Settings.create()
                        .mapColor(MapColor.DARK_GREEN).noCollision().breakInstantly()
                    .sounds(BlockSoundGroup.GRASS).burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)));

    public static final Block POTTED_SNAKE_PLANT = registerItemlessBlock("potted_snake_plant",
            createFlowerPotBlock(SNAKE_PLANT));

    public static final Block DESERT_ROSE_CLUSTER_BLOCK = registerBlock("desert_rose_cluster_block",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW)
                    .strength(1.0F).sounds(BlockSoundGroup.CORAL)
                    .requiresTool()));

    public static final Block DESERT_ROSE_CLUSTER = registerBlock("desert_rose_cluster",
            new RoseClusterBlock(AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW)
                    .strength(0.4F).sounds(BlockSoundGroup.CORAL).pistonBehavior(PistonBehavior.DESTROY)
                    .noCollision().nonOpaque()));

    public static final Block ACTIVE_DESERT_ROSE_CLUSTER = registerItemlessBlock("active_desert_rose_cluster",
            new ActiveRoseClusterBlock(DESERT_ROSE_CLUSTER,  AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW)
                    .strength(1.0F).sounds(BlockSoundGroup.CORAL)
                    .pistonBehavior(PistonBehavior.DESTROY).ticksRandomly()
                    .requiresTool()));

    public static final Block MESA_ROSE_CLUSTER_BLOCK = registerBlock("mesa_rose_cluster_block",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.ORANGE)
                    .strength(1.0F).sounds(BlockSoundGroup.CORAL)
                    .requiresTool()));

    public static final Block MESA_ROSE_CLUSTER = registerBlock("mesa_rose_cluster",
            new RoseClusterBlock(AbstractBlock.Settings.create().mapColor(MapColor.ORANGE)
                    .strength(0.4F).sounds(BlockSoundGroup.CORAL).pistonBehavior(PistonBehavior.DESTROY)
                    .noCollision().nonOpaque()));

    public static final Block ACTIVE_MESA_ROSE_CLUSTER = registerItemlessBlock("active_mesa_rose_cluster",
            new ActiveRoseClusterBlock(MESA_ROSE_CLUSTER,  AbstractBlock.Settings.create().mapColor(MapColor.ORANGE)
                    .strength(1.0F).sounds(BlockSoundGroup.CORAL)
                    .pistonBehavior(PistonBehavior.DESTROY).ticksRandomly()
                    .requiresTool()));

    public static final Block FOSSIL_CLUMP = registerBlock("fossil_clump",
            new ExperienceDroppingBlock(UniformIntProvider.create(1, 3), AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW)
                    .strength(2f, 1.5f).requiresTool()
                    .sounds(BlockSoundGroup.STONE)));

    public static final Block HELIX_FOSSIL = registerBlock("helix_fossil",
            new HelixFossilBlock(AbstractBlock.Settings.create().mapColor(MapColor.DEEPSLATE_GRAY)
                    .strength(1.5F).sounds(BlockSoundGroup.DEEPSLATE).pistonBehavior(PistonBehavior.DESTROY)
                    .nonOpaque()));

    public static final Block POINTY_FOSSIL = registerBlock("pointy_fossil",
            new PointyFossilBlock(AbstractBlock.Settings.create().mapColor(MapColor.DIRT_BROWN)
                    .strength(1.5F).sounds(BlockSoundGroup.DEEPSLATE).pistonBehavior(PistonBehavior.DESTROY)
                    .nonOpaque()));

    public static final Block GNAWER_EGG = registerBlock("gnawer_egg",
            new GnawerEggBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PALE_GREEN)
                    .strength(0.5F).sounds(BlockSoundGroup.METAL)
                    .nonOpaque()));

    public static final Block FLUORESCENT_BLOCK = registerBlock("fluorescent_block",
            new FluorescentBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PALE_GREEN).breakInstantly().ticksRandomly()
                    .sounds(BlockSoundGroup.SLIME).luminance(Blocks.createLightLevelFromLitBlockState(8))
                    .nonOpaque()));



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

    public static void registerBlocks() {
        OrganicallyMod.LOGGER.info("Block Registry For " + OrganicallyMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Items.ACACIA_LOG, PALM_LOG);
            entries.addAfter(PALM_LOG, PALM_CROWN);
            entries.addAfter(Items.ACACIA_LEAVES, PALM_LEAVES);
            entries.addAfter(Items.LILY_OF_THE_VALLEY, DESERT_MARIGOLDS);
            entries.addAfter(DESERT_MARIGOLDS, BRITTLEBUSH);
            entries.addAfter(Items.CACTUS, BARREL_CACTUS);
            entries.addAfter(Items.BROWN_MUSHROOM, DESERT_SHAGGY_MANE);
            entries.addAfter(Items.BROWN_MUSHROOM_BLOCK, DESERT_SHAGGY_MANE_BLOCK);
            entries.addAfter(Items.POINTED_DRIPSTONE, DESERT_ROSE_CLUSTER);
            entries.addAfter(DESERT_ROSE_CLUSTER, DESERT_ROSE_CLUSTER_BLOCK);
            entries.addAfter(DESERT_ROSE_CLUSTER_BLOCK, MESA_ROSE_CLUSTER);
            entries.addAfter(MESA_ROSE_CLUSTER, MESA_ROSE_CLUSTER_BLOCK);
            entries.addBefore(Items.COAL_ORE, FOSSIL_CLUMP);
            entries.addAfter(Items.FERN, TRIODIA);
            entries.addAfter(TRIODIA, SNAKE_PLANT);
            entries.addAfter(Items.SNIFFER_EGG, GNAWER_EGG);
            entries.addAfter(Items.HONEY_BLOCK, FLUORESCENT_BLOCK);

        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(Items.ACACIA_BUTTON, PALM_LOG);
            entries.addAfter(PALM_LOG, PALM_WOOD);
            entries.addAfter(PALM_WOOD, STRIPPED_PALM_LOG);
            entries.addAfter(STRIPPED_PALM_LOG, STRIPPED_PALM_WOOD);
            entries.addAfter(STRIPPED_PALM_WOOD, PALM_PLANKS);
            entries.addAfter(PALM_PLANKS, PALM_STAIRS);
            entries.addAfter(PALM_STAIRS, PALM_SLAB);
            entries.addAfter(PALM_SLAB, PALM_FENCE);
            entries.addAfter(PALM_FENCE, PALM_FENCE_GATE);
            entries.addAfter(PALM_FENCE_GATE, PALM_DOOR);
            entries.addAfter(PALM_DOOR, PALM_TRAPDOOR);
            entries.addAfter(PALM_TRAPDOOR, PALM_PRESSURE_PLATE);
            entries.addAfter(PALM_PRESSURE_PLATE, PALM_BUTTON);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.SNORT_POTTERY_SHERD, HELIX_FOSSIL);
            entries.addAfter(HELIX_FOSSIL, POINTY_FOSSIL);;


        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addAfter(Items.SOUL_LANTERN, COCONUT_LAMP);


        });

    }
}
