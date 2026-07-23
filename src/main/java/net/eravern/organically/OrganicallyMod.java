package net.eravern.organically;

import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.entity.OrganicallyModEntityTypes;
import net.eravern.organically.entity.custom.GnawerEntity;
import net.eravern.organically.entity.custom.LionfishEntity;
import net.eravern.organically.entity.custom.SandStriderEntity;
import net.eravern.organically.farmers_delight.block.FDCompatBlocks;
import net.eravern.organically.item.OrganicallyModItems;
import net.eravern.organically.mixin.FoliagePlacerTypeInvokerMixin;
import net.eravern.organically.mixin.TreeDecoratorTypeInvokerMixin;
import net.eravern.organically.mixin.TrunkPlacerTypeInvokerMixin;
import net.eravern.organically.register.OrganicallyModRegister;
import net.eravern.organically.register.OrganicallyModVillagerTradeRegister;
import net.eravern.organically.world.gen.features.OrganicallyModFeatures;
import net.eravern.organically.world.gen.foliage.PalmFoliagePlacer;
import net.eravern.organically.world.gen.tree_decorators.CoconutsTreeDecorator;
import net.eravern.organically.world.gen.trunks.PalmTrunkPlacer;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.WoodType;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.PufferfishEntity;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;

public class OrganicallyMod implements ModInitializer {
	public static final String MOD_ID = "organicallymod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	public static final TrunkPlacerType<PalmTrunkPlacer> PALM_TRUNK_PLACER = TrunkPlacerTypeInvokerMixin.callRegister(OrganicallyMod.MOD_ID + ":palm_trunk_placer", PalmTrunkPlacer.CODEC);
	public static final FoliagePlacerType<PalmFoliagePlacer> PALM_FOLIAGE_PLACER = FoliagePlacerTypeInvokerMixin.callRegister(OrganicallyMod.MOD_ID + ":palm_foliage_placer", PalmFoliagePlacer.CODEC);
	public static final TreeDecoratorType<CoconutsTreeDecorator> COCONUTS_TREE_DECORATOR = TreeDecoratorTypeInvokerMixin.callRegister(OrganicallyMod.MOD_ID + ":coconuts_tree_decorator", CoconutsTreeDecorator.CODEC);

	public static final TrackedDataHandler<GnawerEntity.State> GNAWER_STATE = TrackedDataHandler.create(GnawerEntity.State.PACKET_CODEC);
	public static final TrackedDataHandler<SandStriderEntity.State> SANDSTRIDER_STATE = TrackedDataHandler.create(SandStriderEntity.State.PACKET_CODEC);

	public static final BlockSetType PALM_SET = BlockSetTypeBuilder.copyOf(BlockSetType.OAK).register(Identifier.of(OrganicallyMod.MOD_ID, "palm"));
	public static final WoodType PALM = WoodTypeBuilder.copyOf(WoodType.OAK).register(Identifier.of(OrganicallyMod.MOD_ID, "palm"), PALM_SET);

	@Override
	public void onInitialize() {

		OrganicallyModRegister.registerAll();
		OrganicallyModRegister.registerFD();

		OrganicallyModVillagerTradeRegister.registerVillagerTrades();

		SpawnRestriction.register(OrganicallyModEntityTypes.LIONFISH, SpawnLocationTypes.IN_WATER, Heightmap.Type.OCEAN_FLOOR, PufferfishEntity::canSpawn);
		FabricDefaultAttributeRegistry.register(OrganicallyModEntityTypes.LIONFISH, LionfishEntity.createFishAttributes());

		SpawnRestriction.register(OrganicallyModEntityTypes.SANDSTRIDER, SpawnLocationTypes.ON_GROUND, Heightmap.Type.WORLD_SURFACE, RabbitEntity::canMobSpawn);
		FabricDefaultAttributeRegistry.register(OrganicallyModEntityTypes.SANDSTRIDER, SandStriderEntity.createSandStriderAttributes());

		FabricDefaultAttributeRegistry.register(OrganicallyModEntityTypes.GNAWER, GnawerEntity.createGnawerAttributes());

		FuelRegistry.INSTANCE.add(OrganicallyModBlocks.PALM_CROWN, 400);
		FuelRegistry.INSTANCE.add(OrganicallyModBlocks.PALM_LOG, 300);
		FuelRegistry.INSTANCE.add(OrganicallyModBlocks.PALM_WOOD, 300);
		FuelRegistry.INSTANCE.add(OrganicallyModBlocks.STRIPPED_PALM_WOOD, 300);
		FuelRegistry.INSTANCE.add(OrganicallyModBlocks.STRIPPED_PALM_LOG, 300);
		FuelRegistry.INSTANCE.add(OrganicallyModBlocks.PALM_PLANKS, 300);
		FuelRegistry.INSTANCE.add(OrganicallyModBlocks.PALM_STAIRS, 300);
		FuelRegistry.INSTANCE.add(OrganicallyModBlocks.PALM_SLAB, 150);
		FuelRegistry.INSTANCE.add(OrganicallyModBlocks.PALM_FENCE, 300);
		FuelRegistry.INSTANCE.add(OrganicallyModBlocks.PALM_FENCE_GATE, 300);
		FuelRegistry.INSTANCE.add(OrganicallyModBlocks.PALM_DOOR, 300);
		FuelRegistry.INSTANCE.add(OrganicallyModBlocks.PALM_TRAPDOOR, 300);
		FuelRegistry.INSTANCE.add(OrganicallyModBlocks.PALM_BUTTON, 300);
		FuelRegistry.INSTANCE.add(OrganicallyModBlocks.PALM_PRESSURE_PLATE, 300);
		FuelRegistry.INSTANCE.add(OrganicallyModBlocks.PALM_SIGN, 300);


		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.COCONUT, 2, 5);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.SPROUTED_COCONUT, 2, 5);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.COCONUT_CROP, 2, 5);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.PALM_CROWN, 5, 10);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.PALM_LOG, 5, 10);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.PALM_WOOD, 5, 10);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.STRIPPED_PALM_LOG, 5, 10);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.STRIPPED_PALM_WOOD, 5, 10);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.PALM_PLANKS, 5, 10);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.PALM_STAIRS, 5, 10);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.PALM_SLAB, 5, 10);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.PALM_FENCE, 5, 10);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.PALM_FENCE_GATE, 5, 10);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.PALM_DOOR, 5, 10);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.PALM_TRAPDOOR, 5, 10);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.PALM_BUTTON, 5, 10);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.PALM_SIGN, 5, 10);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.PALM_WALL_SIGN, 5, 10);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.PALM_PRESSURE_PLATE, 5, 10);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.PALM_LEAVES, 20, 50);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.BRITTLEBUSH, 20, 50);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.TRIODIA, 20, 50);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.SALTBUSH, 20, 50);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.SNAKE_PLANT, 20, 50);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.DESERT_MARIGOLDS, 20, 50);
		FlammableBlockRegistry.getDefaultInstance().add(OrganicallyModBlocks.DESERT_SHAGGY_MANE, 20, 50);



		StrippableBlockRegistry.register(OrganicallyModBlocks.PALM_LOG, OrganicallyModBlocks.STRIPPED_PALM_LOG);
		StrippableBlockRegistry.register(OrganicallyModBlocks.PALM_WOOD, OrganicallyModBlocks.STRIPPED_PALM_WOOD);


		CompostingChanceRegistry.INSTANCE.add(OrganicallyModItems.COCONUT, 0.6f);
		CompostingChanceRegistry.INSTANCE.add(OrganicallyModItems.COCONUT_SLICE, 0.6f);
		CompostingChanceRegistry.INSTANCE.add(OrganicallyModItems.PALM_SALAD, 0.8f);
		CompostingChanceRegistry.INSTANCE.add(OrganicallyModItems.LIONFISH, 0.7f);
		CompostingChanceRegistry.INSTANCE.add(OrganicallyModItems.COOKED_LIONFISH, 0.7f);
		CompostingChanceRegistry.INSTANCE.add(OrganicallyModBlocks.PALM_LEAVES, 0.3f);
		CompostingChanceRegistry.INSTANCE.add(OrganicallyModBlocks.DESERT_MARIGOLDS, 0.6f);
		CompostingChanceRegistry.INSTANCE.add(OrganicallyModBlocks.BRITTLEBUSH, 0.6f);
		CompostingChanceRegistry.INSTANCE.add(OrganicallyModBlocks.TRIODIA, 0.3f);
		CompostingChanceRegistry.INSTANCE.add(OrganicallyModBlocks.SNAKE_PLANT, 0.4f);
		CompostingChanceRegistry.INSTANCE.add(OrganicallyModBlocks.SALTBUSH, 0.4f);
		CompostingChanceRegistry.INSTANCE.add(OrganicallyModBlocks.DESERT_SHAGGY_MANE, 0.6f);
		CompostingChanceRegistry.INSTANCE.add(OrganicallyModBlocks.DESERT_SHAGGY_MANE_BLOCK, 0.8f);
		CompostingChanceRegistry.INSTANCE.add(OrganicallyModBlocks.BARREL_CACTUS, 0.7f);

		Registry.register(Registries.FEATURE, OrganicallyModFeatures.HUGE_DESERT_SHAGGY_MANE_ID, OrganicallyModFeatures.HUGE_DESERT_SHAGGY_MANE);
		Registry.register(Registries.FEATURE, OrganicallyModFeatures.DESERT_ROSE_ID, OrganicallyModFeatures.DESERT_ROSE);
		Registry.register(Registries.FEATURE, OrganicallyModFeatures.DESERT_FORMATION_ID, OrganicallyModFeatures.DESERT_FORMATION);
		Registry.register(Registries.FEATURE, OrganicallyModFeatures.ROCK_ID, OrganicallyModFeatures.ROCK);
		Registry.register(Registries.FEATURE, OrganicallyModFeatures.SEA_PILLAR_ID, OrganicallyModFeatures.SEA_PILLAR);
		Registry.register(Registries.FEATURE, OrganicallyModFeatures.CLUSTER_PLACER_ID, OrganicallyModFeatures.CLUSTER_PLACER);
		Registry.register(Registries.FEATURE, OrganicallyModFeatures.UNDERWATER_PATCH_ID, OrganicallyModFeatures.UNDERWATER_PATCH);

		TrackedDataHandlerRegistry.register(GNAWER_STATE);
		TrackedDataHandlerRegistry.register(SANDSTRIDER_STATE);


		BlockEntityType.SIGN.addSupportedBlock(OrganicallyModBlocks.PALM_SIGN);
		BlockEntityType.SIGN.addSupportedBlock(OrganicallyModBlocks.PALM_WALL_SIGN);

		BlockEntityType.HANGING_SIGN.addSupportedBlock(OrganicallyModBlocks.PALM_HANGING_SIGN);
		BlockEntityType.HANGING_SIGN.addSupportedBlock(OrganicallyModBlocks.PALM_WALL_HANGING_SIGN);

		DispenserBlock.registerProjectileBehavior(OrganicallyModItems.LIONFISH_SPIKE);





		if (FabricLoader.getInstance().isModLoaded("farmersdelight")){
			FuelRegistry.INSTANCE.add(FDCompatBlocks.PALM_CABINET, 300);
			FlammableBlockRegistry.getDefaultInstance().add(FDCompatBlocks.PALM_CABINET, 5, 10);
			ModBlockEntityTypes.CABINET.get().addSupportedBlock(FDCompatBlocks.PALM_CABINET);
		}
	}
}