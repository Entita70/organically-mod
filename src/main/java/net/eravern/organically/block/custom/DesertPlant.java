package net.eravern.organically.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.PlantBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class DesertPlant extends PlantBlock {
    public static final MapCodec<DesertPlant> CODEC = createCodec(DesertPlant::new);

    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return CODEC;
    }

    protected DesertPlant(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.SAND) || floor.isIn(BlockTags.DIRT);
    }
}
