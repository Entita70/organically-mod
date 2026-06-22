package net.eravern.organically.world.gen.tree_decorators;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.eravern.organically.OrganicallyMod;
import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.block.custom.CoconutBlock;
import net.eravern.organically.block.custom.CoconutCropBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CocoaBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.Iterator;
import java.util.List;

public class CoconutsTreeDecorator extends TreeDecorator {
    public static final MapCodec<CoconutsTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(CoconutsTreeDecorator::new, (decorator) -> {
        return decorator.probability;
    });
    private final float probability;

    public CoconutsTreeDecorator(float probability){
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return OrganicallyMod.COCONUTS_TREE_DECORATOR;
    }

    @Override
    public void generate(Generator generator) {
        Random random = generator.getRandom();
        BlockPos pos = generator.getLogPositions().top();
        BlockPos blockPos;
        if (!(random.nextFloat() >= this.probability)) {
            blockPos = pos.south();
            generator.replace(blockPos, OrganicallyModBlocks.COCONUT_CROP.getDefaultState().with(CoconutCropBlock.AGE, random.nextInt(2)+1).with(CoconutCropBlock.FACING, Direction.NORTH));
        }
        if (!(random.nextFloat() >= this.probability)) {
            blockPos = pos.west();
            generator.replace(blockPos, OrganicallyModBlocks.COCONUT_CROP.getDefaultState().with(CoconutCropBlock.AGE, random.nextInt(2)+1).with(CoconutCropBlock.FACING, Direction.EAST));
        }
        if (!(random.nextFloat() >= this.probability)) {
            blockPos = pos.east();
            generator.replace(blockPos, OrganicallyModBlocks.COCONUT_CROP.getDefaultState().with(CoconutCropBlock.AGE, random.nextInt(2)+1).with(CoconutCropBlock.FACING, Direction.WEST));

        }
        if (!(random.nextFloat() >= this.probability)) {
            blockPos = pos.north();
            generator.replace(blockPos, OrganicallyModBlocks.COCONUT_CROP.getDefaultState().with(CoconutCropBlock.AGE, random.nextInt(2)+1).with(CoconutCropBlock.FACING, Direction.SOUTH));
        }


    }
}
