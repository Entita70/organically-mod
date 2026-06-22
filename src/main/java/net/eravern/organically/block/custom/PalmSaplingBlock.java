package net.eravern.organically.block.custom;

import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.item.OrganicallyModItems;
import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class PalmSaplingBlock extends SaplingBlock {
    protected static final VoxelShape COCONUT = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);

    public PalmSaplingBlock(SaplingGenerator generator, Settings settings) {
        super(generator, settings);
    }



    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return COCONUT;
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.SAND);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.isOf(Items.SHEARS)){
            world.addBlockBreakParticles(pos, OrganicallyModBlocks.COCONUT.getDefaultState());
            world.setBlockState(pos, OrganicallyModBlocks.COCONUT.getDefaultState());
            world.playSound(null, pos, SoundEvents.ENTITY_SHEEP_SHEAR, SoundCategory.BLOCKS, 1.0F, 1.0F);
            stack.damage(1, player, LivingEntity.getSlotForHand(hand));
        }else{
            return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
        }
        return ItemActionResult.success(world.isClient);
    }
}
