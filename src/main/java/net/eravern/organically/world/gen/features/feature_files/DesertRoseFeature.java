package net.eravern.organically.world.gen.features.feature_files;

import com.mojang.serialization.Codec;
import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.block.custom.ClusterBlock;
import net.eravern.organically.block.custom.ClusterGrowerBlock;
import net.eravern.organically.util.OrganicallyModBlockTags;
import net.eravern.organically.world.gen.features.feature_config.DesertRoseFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class DesertRoseFeature extends Feature<DesertRoseFeatureConfig> {
    public DesertRoseFeature(Codec<DesertRoseFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DesertRoseFeatureConfig> context) {
        float CHANCE = 0.25f;

        StructureWorldAccess world = context.getWorld();
        BlockPos startPos = context.getOrigin();
        BlockPos pos = new BlockPos(startPos);
        Random random = context.getRandom();
        DesertRoseFeatureConfig config = context.getConfig();
        BlockState block = config.block().get(random, pos);
        if (!(block.getBlock() instanceof ClusterGrowerBlock)){
            block = OrganicallyModBlocks.ACTIVE_DESERT_ROSE_CLUSTER.getDefaultState();}
        BlockState cluster = config.cluster().get(random, pos);
        if (!(cluster.getBlock() instanceof ClusterBlock)){
            cluster = OrganicallyModBlocks.DESERT_ROSE_CLUSTER.getDefaultState();}

        if (block == null){
            throw new IllegalStateException(config.cluster() + " is not a valid blockStateProvider");
        }

        for (int i = 50; i < world.getHeight(); i++) {
            pos = pos.up();
            if (world.getBlockState(pos.down(2)).isIn(OrganicallyModBlockTags.DESERT_PLANT_BLOCK)){
                if (world.getBlockState(pos.down()).isOf(Blocks.AIR)){
                    int n = random.nextInt(2) + 2;
                    pos = pos.down();
                    for (int o = 0; o < n; o++){
                        if (o == n-1 && o != 0){
                            int age = random.nextInt(3);
                            world.setBlockState(pos, block.with(Properties.AGE_2, age), 2);
                            if (age == 2){
                                if (random.nextFloat() < CHANCE){
                                    world.setBlockState(pos.up(), cluster.with(Properties.FACING, Direction.UP), 2);
                                }
                                for (int p = 0; p < 4; p++){
                                    if (random.nextFloat() < CHANCE){
                                        Direction direction = intToDirection(p);
                                        world.setBlockState(pos.offset(direction), cluster.with(Properties.FACING, direction), 2);
                                    }
                                }
                            }
                        }else{
                            world.setBlockState(pos, block, 2);
                            for (int p = 0; p < 4; p++){
                                if (random.nextFloat() < CHANCE){
                                    Direction direction = intToDirection(p);
                                    world.setBlockState(pos.offset(direction), cluster.with(Properties.FACING, direction), 2);
                                }
                            }
                        }
                        pos = pos.up();
                    }
                    return true;
                }
            }
            if (pos.getY() >= world.getHeight()){
                break;
            }
        }
            return false;
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
}
