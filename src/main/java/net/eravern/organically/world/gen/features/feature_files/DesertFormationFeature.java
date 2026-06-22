package net.eravern.organically.world.gen.features.feature_files;

import com.mojang.serialization.Codec;
import net.eravern.organically.util.OrganicallyModBlockTags;
import net.eravern.organically.world.gen.features.feature_config.DesertFormationFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class DesertFormationFeature extends Feature<DesertFormationFeatureConfig> {
    public DesertFormationFeature(Codec<DesertFormationFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DesertFormationFeatureConfig> context) {
        DesertFormationFeatureConfig config = context.getConfig();
        Random random = context.getRandom();
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        BlockPos pos = new BlockPos(origin);

        float chance = config.chance();
        BlockState rare = config.rare().get(random, origin);
        BlockState main = config.main().get(random, origin);

        if (main == null){
            throw new IllegalStateException(config.main() + " is not a valid blockStateProvider");
        }else if (rare == null){
            throw new IllegalStateException(config.rare() + " is not a valid blockStateProvider");
        }else if (chance < 0 || chance > 1){
            throw new IllegalStateException(config.chance() + " is not a valid chance");
        }


        for (int i = 0; i < world.getHeight(); i++) {
            pos = pos.up();
            if (world.getBlockState(pos.down(2)).isIn(OrganicallyModBlockTags.DESERT_SPAWN_BLOCKS) || (world.getBlockState(pos.down(3)).isIn(OrganicallyModBlockTags.DESERT_SPAWN_BLOCKS)) && world.getBlockState(pos.down(2)).isOf(Blocks.AIR)){
                if (world.getBlockState(pos.down()).isOf(Blocks.AIR)){
                    placeFormation(pos, world, main, rare, chance, random);
                    placeFormation(pos.north(random.nextInt(3)+1), world, main, rare, chance, random);
                    placeFormation(pos.south(random.nextInt(3)+1), world, main, rare, chance, random);
                    placeFormation(pos.west(random.nextInt(3)+1), world, main, rare, chance, random);
                    placeFormation(pos.west(random.nextInt(3)+1), world, main, rare, chance, random);
                    return true;
                }
            }
            if (pos.getY() >= world.getHeight()){
                break;
            }
        }
        return true;
    }


    public static void placeFormation(BlockPos pos, StructureWorldAccess world, BlockState block, BlockState rare, float chance, Random random){
        BlockPos mid = new BlockPos(pos.down(14));
        int height = random.nextInt(10) + 10;
        float underground;
        boolean under;
        BlockState place;

        for (int i = 0; i < height; i++){
            if (i < height/2){
                underground = chance*1.5f;
            }else{
                underground = chance;
            }
            for (int o = 0; o < 4; o++){
                under = i < height/2;
                place = getBlock(block, rare, underground, random);
                BlockPos p = mid.offset(intToDirection(o));
                world.setBlockState(p, place, 2);
                int r = random.nextInt(4);
                if (under){
                    place = getBlockDeep(block, rare, underground, random);
                }else{
                    place = getBlock(block, rare, underground, random);
                }
                world.setBlockState(p.offset(intToDirection(r)), place, 2);
            }
            place = getBlock(block, rare, underground, random);
            world.setBlockState(mid, place, 2);
            mid = mid.up();
        }
    }


    public static BlockState getBlock(BlockState block, BlockState rare, float chance, Random random){
        if (random.nextFloat() < chance){
            return rare;
        }else{
            return block;
        }
    }

    public static BlockState getBlockDeep(BlockState block, BlockState rare, float chance, Random random){
        BlockState bone = Blocks.BONE_BLOCK.getDefaultState();

        if (random.nextFloat() < chance){
            if (random.nextBoolean()){
                return bone;
            }else{
                return rare;
            }
        }else{
            return block;
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


}
