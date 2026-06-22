package net.eravern.organically.world.gen.features.feature_files;

import com.mojang.serialization.Codec;
import net.eravern.organically.util.OrganicallyModBlockTags;
import net.eravern.organically.world.gen.features.feature_config.DesertRoseFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
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
        StructureWorldAccess world = context.getWorld();
        BlockPos startPos = context.getOrigin();
        BlockPos pos = new BlockPos(startPos);
        Random random = context.getRandom();
        DesertRoseFeatureConfig config = context.getConfig();
        BlockState block = config.cluster().get(random, pos);

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
                            world.setBlockState(pos, block.with(Properties.AGE_2, random.nextInt(3)), 2);
                        }else{
                            world.setBlockState(pos, block, 2);
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
}
