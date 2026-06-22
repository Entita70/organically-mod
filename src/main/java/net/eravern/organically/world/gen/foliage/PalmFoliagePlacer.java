package net.eravern.organically.world.gen.foliage;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.eravern.organically.OrganicallyMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class PalmFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<PalmFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return fillFoliagePlacerFields(instance).apply(instance, PalmFoliagePlacer::new);
    });

    public PalmFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return OrganicallyMod.PALM_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BlockPlacer placer,
                            Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {

        boolean bl = treeNode.isGiantTrunk();
        BlockPos blockPos = treeNode.getCenter().up(offset);

        this.generatePalm(world, placer, random, config, blockPos, radius, trunkHeight);
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return 0;
    }

    protected void generatePalm(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, BlockPos centerPos, int radius, int trunkHeight) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        mutable.set(centerPos, 0, 0,0);
        placeFoliageBlock(world, placer, random, config, mutable);
        for (int i = 0; i < radius-1; i++){
            mutable.move(Direction.NORTH, 1);
            placeFoliageBlock(world, placer, random, config, mutable);
        }
        mutable.move(Direction.DOWN, 1);
        placeFoliageBlock(world, placer, random, config, mutable);
        for (int i = 0; i < radius-2; i++){
            mutable.move(Direction.NORTH, 1);
            placeFoliageBlock(world, placer, random, config, mutable);
        }
        mutable.move(Direction.DOWN, 1);
        placeFoliageBlock(world, placer, random, config, mutable);
        mutable.set(centerPos, 0, 0,0);

        for (int i = 0; i < radius-1; i++){
            mutable.move(Direction.SOUTH, 1);
            placeFoliageBlock(world, placer, random, config, mutable);
        }
        mutable.move(Direction.DOWN, 1);
        placeFoliageBlock(world, placer, random, config, mutable);
        for (int i = 0; i < radius-2; i++){
            mutable.move(Direction.SOUTH, 1);
            placeFoliageBlock(world, placer, random, config, mutable);
        }
        mutable.move(Direction.DOWN, 1);
        placeFoliageBlock(world, placer, random, config, mutable);
        mutable.set(centerPos, 0, 0,0);

        for (int i = 0; i < radius-1; i++){
            mutable.move(Direction.EAST, 1);
            placeFoliageBlock(world, placer, random, config, mutable);
        }
        mutable.move(Direction.DOWN, 1);
        placeFoliageBlock(world, placer, random, config, mutable);
        for (int i = 0; i < radius-2; i++){
            mutable.move(Direction.EAST, 1);
            placeFoliageBlock(world, placer, random, config, mutable);
        }
        mutable.move(Direction.DOWN, 1);
        placeFoliageBlock(world, placer, random, config, mutable);
        mutable.set(centerPos, 0, 0,0);

        for (int i = 0; i < radius-1; i++){
            mutable.move(Direction.WEST, 1);
            placeFoliageBlock(world, placer, random, config, mutable);
        }
        mutable.move(Direction.DOWN, 1);
        placeFoliageBlock(world, placer, random, config, mutable);
        for (int i = 0; i < radius-2; i++){
            mutable.move(Direction.WEST, 1);
            placeFoliageBlock(world, placer, random, config, mutable);
        }
        mutable.move(Direction.DOWN, 1);
        placeFoliageBlock(world, placer, random, config, mutable);
    }

    protected static boolean canReplaceAir(TestableWorld world, BlockPos pos){
        return world.testBlockState(pos, (state) -> {
            return state.isAir() || state.isIn(BlockTags.REPLACEABLE_BY_TREES);
        });
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return false;
    }
}
