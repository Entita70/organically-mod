package net.eravern.organically.block.custom;

import net.eravern.organically.farmers_delight.block.FDCompatBlocks;
import net.eravern.organically.util.OrganicallyModBlockTags;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import vectorwing.farmersdelight.common.registry.ModBlockEntityTypes;
import vectorwing.farmersdelight.common.registry.ModBlocks;

public class DesertShaggyManePlantBlock extends DesertMushroomPlantBlock {
    public DesertShaggyManePlantBlock(RegistryKey<ConfiguredFeature<?, ?>> featureKey, Settings settings) {
        super(featureKey, settings);
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        super.randomTick(state, world, pos, random);
        if (FabricLoader.getInstance().isModLoaded("farmersdelight")){
            if (world.getBlockState(pos.down()).isOf(ModBlocks.RICH_SOIL.get().getRegistryEntry())){
                world.setBlockState(pos, FDCompatBlocks.DESERT_SHAGGY_MANE_COLONY.getDefaultState().with(Properties.AGE_3, 0));
            }
        }
    }
}