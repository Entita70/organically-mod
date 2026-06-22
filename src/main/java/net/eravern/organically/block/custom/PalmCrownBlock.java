package net.eravern.organically.block.custom;

import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.item.OrganicallyModItems;
import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class PalmCrownBlock extends Block{
    public static final int GROW_CHANCE = 12;


    public PalmCrownBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if(world.random.nextInt(GROW_CHANCE) == 0){
            int r = world.random.nextInt(4);
            switch (r){
                case 0:
                    if (world.getBlockState(pos.north()) == Blocks.AIR.getDefaultState()){
                        world.setBlockState(pos.north(), OrganicallyModBlocks.COCONUT_CROP.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.SOUTH));
                    }
                    break;
                case 1:
                    if (world.getBlockState(pos.east()) == Blocks.AIR.getDefaultState()){
                        world.setBlockState(pos.east(), OrganicallyModBlocks.COCONUT_CROP.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.WEST));
                    }
                    break;
                case 3:
                    if (world.getBlockState(pos.west()) == Blocks.AIR.getDefaultState()){
                        world.setBlockState(pos.west(), OrganicallyModBlocks.COCONUT_CROP.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.EAST));
                    }
                    break;
                default:
                    if (world.getBlockState(pos.south()) == Blocks.AIR.getDefaultState()){
                    world.setBlockState(pos.south(), OrganicallyModBlocks.COCONUT_CROP.getDefaultState());
                }
                    break;
            }
        }


    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!stack.isOf(Items.SHEARS)){
            return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
        }else {
            ItemEntity itemEntity = new ItemEntity(world, pos.getX()+0.5, pos.getY()+1, pos.getZ()+0.5, new ItemStack(OrganicallyModItems.PALM_SALAD, world.random.nextInt(2)+1));
            itemEntity.setVelocity(0, 0.15, 0);
            world.addBlockBreakParticles(pos, OrganicallyModBlocks.PALM_CROWN.getDefaultState());
            world.setBlockState(pos, OrganicallyModBlocks.PALM_LOG.getDefaultState());
            world.playSound(null, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            world.spawnEntity(itemEntity);
            stack.damage(1, player, LivingEntity.getSlotForHand(hand));
        }
        return ItemActionResult.success(world.isClient);
    }
}
