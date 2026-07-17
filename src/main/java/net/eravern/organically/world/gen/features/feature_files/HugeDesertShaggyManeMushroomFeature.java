package net.eravern.organically.world.gen.features.feature_files;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.HugeMushroomFeature;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;

public class HugeDesertShaggyManeMushroomFeature extends HugeMushroomFeature {
    public HugeDesertShaggyManeMushroomFeature(Codec<HugeMushroomFeatureConfig> codec) {
        super(codec);
    }

    @Override
    protected void generateCap(WorldAccess world, Random random, BlockPos start, int y, BlockPos.Mutable mutable, HugeMushroomFeatureConfig config) {
        BlockState blockState = config.capProvider.get(random, start);
        int r = (config.foliageRadius - 1)*2 + 3;
        int distance = config.foliageRadius - 1;
        int startingY = 2;
        int finalY = y+1;
        if (y > 5 && y <= 7){
            startingY = 3;
        }else if (y > 7 && y <= 9){
            startingY = 4;
        }else if (y > 9 && y <= 11){
            startingY = 5;
        }else if (y > 11){
            startingY = 6;
        }

        mutable.set(start, 0, finalY ,0);
        this.setBlockState(world, mutable, blockState);
        mutable.set(start, 0, y ,0);
        this.setBlockState(world, mutable, blockState);

        for (int o = startingY; o < finalY; o++){
            mutable.set(start, distance + 1, o, distance + 1);
            for (int i = 1; i < r; i++){
                mutable.move(Direction.WEST, 1);
                this.setBlockState(world, mutable, blockState);
            }
            for (int i = 1; i < r; i++){
                mutable.move(Direction.NORTH, 1);
                this.setBlockState(world, mutable, blockState);
            }
            for (int i = 1; i < r; i++){
                mutable.move(Direction.EAST, 1);
                this.setBlockState(world, mutable, blockState);
            }
            for (int i = 1; i < r; i++){
                mutable.move(Direction.SOUTH, 1);
                this.setBlockState(world, mutable, blockState);
            }
        }


    }

    @Override
    protected int getCapSize(int i, int j, int capSize, int y) {
        return capSize;
    }

}
