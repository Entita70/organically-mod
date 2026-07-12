package net.eravern.organically.world.gen.features.feature_files;

import com.mojang.serialization.Codec;
import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.block.custom.ClusterBlock;
import net.eravern.organically.util.OrganicallyModBlockTags;
import net.eravern.organically.world.gen.features.feature_config.ClusterPlaceFeatureConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Iterator;
import java.util.List;

public class ClusterPlaceFeature extends Feature<ClusterPlaceFeatureConfig> {
    public ClusterPlaceFeature(Codec<ClusterPlaceFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<ClusterPlaceFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        ClusterPlaceFeatureConfig clusterPlaceFeatureConfig = context.getConfig();

        if (!isAirOrWater(structureWorldAccess.getBlockState(blockPos)) && !checkBlock(blockPos, structureWorldAccess)) {
            return false;
        } else {
            List<Direction> list = clusterPlaceFeatureConfig.shuffleDirections(random);
            if (generate(structureWorldAccess, blockPos, structureWorldAccess.getBlockState(blockPos), clusterPlaceFeatureConfig, random, list)) {
                return true;
            } else {
                BlockPos.Mutable mutable = blockPos.mutableCopy();
                Iterator<Direction> var8 = list.iterator();
                while(var8.hasNext()) {
                    Direction direction = var8.next();
                    mutable.set(blockPos);
                    List<Direction> list2 = clusterPlaceFeatureConfig.shuffleDirections(random, direction.getOpposite());

                    for(int i = 0; i < clusterPlaceFeatureConfig.searchRange; ++i) {
                        mutable.set(blockPos, direction);
                        BlockState blockState = structureWorldAccess.getBlockState(mutable);
                        if (!isAirOrWater(blockState) && !blockState.isOf(clusterPlaceFeatureConfig.block)) {
                            break;
                        }

                        if (generate(structureWorldAccess, mutable, blockState, clusterPlaceFeatureConfig, random, list2)) {
                            return true;
                        }
                    }
                }

                return false;
            }
        }
    }

    public static boolean checkBlock(BlockPos pos, StructureWorldAccess world){
        TagKey<Block> look = OrganicallyModBlockTags.CLUSTER_PLACEABLE;

        return (world.getBlockState(pos.down()).isIn(look) || world.getBlockState(pos.up()).isIn(look) || world.getBlockState(pos.east()).isIn(look) || world.getBlockState(pos.west()).isIn(look) || world.getBlockState(pos.north()).isIn(look) || world.getBlockState(pos.south()).isIn(look)) && (world.getBlockState(pos).isAir() || world.getBlockState(pos).isOf(Blocks.WATER));
    }

    public static boolean generate(StructureWorldAccess world, BlockPos pos, BlockState state, ClusterPlaceFeatureConfig config, Random random, List<Direction> directions) {
        BlockPos.Mutable mutable = pos.mutableCopy();
        Iterator<Direction> var7 = directions.iterator();

        Direction direction;
        BlockState blockState;
        do {
            if (!var7.hasNext()) {
                return false;
            }

            direction = var7.next();
            blockState = world.getBlockState(mutable.set(pos, direction));
        } while(!blockState.isIn(config.canPlaceOn));

        BlockState blockState2 = config.block.withDirection(state, world, pos, direction);
        if (blockState2 == null) {
            return false;
        } else {
            world.setBlockState(pos, blockState2, 3);
            world.getChunk(pos).markBlockForPostProcessing(pos);

            return true;
        }
    }

    private static boolean isAirOrWater(BlockState state) {
        return state.isAir() || state.isOf(Blocks.WATER);
    }
}
