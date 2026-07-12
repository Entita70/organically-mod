package net.eravern.organically.world.gen.features.feature_files;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Iterator;

public class RockFeature extends Feature<SingleStateFeatureConfig> {
    public RockFeature(Codec<SingleStateFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<SingleStateFeatureConfig> context) {
        BlockPos blockPos = context.getOrigin();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        Random random = context.getRandom();

        SingleStateFeatureConfig singleStateFeatureConfig;
        for(singleStateFeatureConfig = context.getConfig(); blockPos.getY() > structureWorldAccess.getBottomY() + 2; blockPos = blockPos.down()) {
            if (!structureWorldAccess.isAir(blockPos.down())) {
                BlockState blockState = structureWorldAccess.getBlockState(blockPos.down());
                if (isSoil(blockState) || isStone(blockState) || isSand(blockState)) {
                        break;
                }
            }
        }

        if (blockPos.getY() <= structureWorldAccess.getBottomY() + 2) {
            return false;
        } else {
            for(int i = 0; i < 2; ++i) {
                int j = random.nextInt(2)+1;
                int k = random.nextInt(3)+1;
                int l = random.nextInt(2)+1;
                float f = (float)(j + k + l) * 0.35f + 0.55f;
                Iterator<BlockPos> var11 = BlockPos.iterate(blockPos.add(-j, -k, -l), blockPos.add(j, k, l)).iterator();

                while(var11.hasNext()) {
                    BlockPos blockPos2 = var11.next();
                    if (blockPos2.getSquaredDistance(blockPos) <= (double)(f * f)) {
                        structureWorldAccess.setBlockState(blockPos2.offset(Direction.Axis.Y, -1), singleStateFeatureConfig.state, 3);
                    }
                }

                blockPos = blockPos.add(-1 + random.nextInt(2), -random.nextInt(2), -1 + random.nextInt(2));
            }

            return true;
        }
    }

    public boolean isSand(BlockState blockState){
        return blockState.isIn(BlockTags.SAND);
    }
}
