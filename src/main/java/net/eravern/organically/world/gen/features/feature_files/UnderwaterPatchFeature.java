package net.eravern.organically.world.gen.features.feature_files;

import com.mojang.serialization.Codec;
import net.eravern.organically.block.custom.ClusterBlock;
import net.eravern.organically.block.custom.ScallopBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.screen.Property;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;


public class UnderwaterPatchFeature extends Feature<SingleStateFeatureConfig> {
    public UnderwaterPatchFeature(Codec<SingleStateFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<SingleStateFeatureConfig> context) {
        boolean bl = false;
        Random random = context.getRandom();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin().down();
        BlockState block = context.getConfig().state;
        if (block.getBlock() instanceof ClusterBlock){
            block = block.with(Properties.FACING, Direction.UP);
        }else if (block.getBlock() instanceof ScallopBlock){
            block = block.with(Properties.HORIZONTAL_FACING, intToDirection(random.nextInt(4)));
        }
        block = block.with(Properties.WATERLOGGED, true);
        int i = random.nextInt(5) - random.nextInt(5);
        int j = random.nextInt(5) - random.nextInt(5);
        int k = structureWorldAccess.getTopY(Heightmap.Type.OCEAN_FLOOR, blockPos.getX() + i, blockPos.getZ() + j);
        BlockPos blockPos2 = new BlockPos(blockPos.getX() + i, k, blockPos.getZ() + j);
        if (structureWorldAccess.getBlockState(blockPos2).isOf(Blocks.WATER)) {
            if (isPlaceable(structureWorldAccess.getBlockState(blockPos))) {
                structureWorldAccess.setBlockState(blockPos2, block, 2);
                bl = true;
            }
        }

        return bl;

    }

    public static Direction intToDirection(int o){
        switch (o){
            case 0:
                return Direction.EAST;
            case 1:
                return Direction.WEST;
            case 2:
                return Direction.SOUTH;
            default:
                return Direction.NORTH;
        }
    }

    public boolean isPlaceable(BlockState blockState){
        return blockState.isOf(Blocks.GRAVEL) || blockState.isIn(BlockTags.SAND) || blockState.isIn(BlockTags.STONE_ORE_REPLACEABLES) || blockState.isIn(BlockTags.CORAL_BLOCKS);
    }
}
