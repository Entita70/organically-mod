package net.eravern.organically.world.gen.features.feature_files;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Iterator;

public class SeaPillarFeature extends Feature<SingleStateFeatureConfig> {
    public SeaPillarFeature(Codec<SingleStateFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<SingleStateFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin().withY(structureWorldAccess.getTopY()-20);
        Random random = context.getRandom();
        int waterY = 0;

        SingleStateFeatureConfig singleStateFeatureConfig;
        for(singleStateFeatureConfig = context.getConfig(); blockPos.getY() > structureWorldAccess.getBottomY() + 2; blockPos = blockPos.down()) {
            if (!structureWorldAccess.isAir(blockPos.down())) {
                BlockState blockState = structureWorldAccess.getBlockState(blockPos.down());
                BlockState upstate = structureWorldAccess.getBlockState(blockPos);
                if (upstate.isOf(Blocks.WATER)){
                    waterY += 1;
                }
                if (isSoil(blockState) || isStone(blockState) || isSand(blockState)) {
                    if(upstate.isOf(Blocks.WATER)){
                        break;
                    }
                }
            }
        }

        if (blockPos.getY() <= structureWorldAccess.getBottomY() + 2) {
            return false;
        } else {
            placePillar(waterY, blockPos, structureWorldAccess, singleStateFeatureConfig.state, random);
            return true;
        }
    }

    public static void placePillar(int h, BlockPos pos, StructureWorldAccess world, BlockState block, Random random){
        BlockPos mid = new BlockPos(pos.down(1));
        int height = h + 3;
        BlockState place;

        for (int i = 0; i < height; i++){
            for (int o = 0; o < 8; o++){
                place = block;
                BlockPos p = mid.offset(intToDirection(o));
                world.setBlockState(p, place, 2);
                int r = random.nextInt(4);
                world.setBlockState(p.offset(intToDirection(r)), place, 2);
            }
            world.setBlockState(mid, block, 2);
            mid = mid.up();
        }
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

    public boolean isSand(BlockState blockState){
        return blockState.isIn(BlockTags.SAND) || blockState.isOf(Blocks.GRAVEL);
    }
}
