package net.eravern.organically.world.gen.trunks;

import com.google.common.collect.ImmutableList;
import com.google.common.io.Closer;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.eravern.organically.OrganicallyMod;
import net.eravern.organically.block.OrganicallyModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class PalmTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<PalmTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return fillTrunkPlacerFields(instance).apply(instance, PalmTrunkPlacer::new);
    });

    public PalmTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return OrganicallyMod.PALM_TRUNK_PLACER;
    }

    protected static void setToCrown(BiConsumer<BlockPos, BlockState> replacer, BlockPos pos) {
            replacer.accept(pos, OrganicallyModBlocks.PALM_CROWN.getDefaultState());
    }


    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer,
                                                 Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
        setToDirt(world, replacer, random, startPos.down(), config);

        for(int i = 0; i < height; ++i) {
            this.getAndSetState(world, replacer, random, startPos.up(i), config);
        }
        setToCrown(replacer, startPos.up(height-1));

        return ImmutableList.of(new FoliagePlacer.TreeNode(startPos.up(height), 0, false));
    }
}
