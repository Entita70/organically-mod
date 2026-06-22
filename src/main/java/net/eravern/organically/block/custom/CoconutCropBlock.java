package net.eravern.organically.block.custom;

import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.item.OrganicallyModItems;
import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class CoconutCropBlock extends CocoaBlock{
    public static final int GROW_CHANCE = 6;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final IntProperty AGE = Properties.AGE_2;
    protected static final VoxelShape[] AGE_TO_WEST_SHAPE = new VoxelShape[]{Block.createCuboidShape(0.0, 6.0, 6.0, 4.0, 12.0, 10.0), Block.createCuboidShape(0.0, 5.0, 5.0, 6.0, 12.0, 11.0), Block.createCuboidShape(0.0, 4.0, 4.0, 8.0, 12.0, 12.0)};
    protected static final VoxelShape[] AGE_TO_EAST_SHAPE = new VoxelShape[]{Block.createCuboidShape(12.0, 6.0, 6.0, 16.0, 12.0, 10.0), Block.createCuboidShape(10.0, 5.0, 5.0, 16.0, 12.0, 11.0), Block.createCuboidShape(8.0, 4.0, 4.0, 16.0, 12.0, 12.0)};
    protected static final VoxelShape[] AGE_TO_NORTH_SHAPE = new VoxelShape[]{Block.createCuboidShape(6.0, 6.0, 0.0, 10.0, 12.0, 4.0), Block.createCuboidShape(5.0, 5.0, 0.0, 11.0, 12.0, 6.0), Block.createCuboidShape(4.0, 4.0, 0.0, 12.0, 12.0, 8.0)};
    protected static final VoxelShape[] AGE_TO_SOUTH_SHAPE = new VoxelShape[]{Block.createCuboidShape(6.0, 6.0, 12.0, 10.0, 12.0, 16.0), Block.createCuboidShape(5.0, 5.0, 10.0, 11.0, 12.0, 16.0), Block.createCuboidShape(4.0, 4.0, 8.0, 12.0, 12.0, 16.0)};

    public CoconutCropBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(AGE, 0));
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int i = state.get(AGE);
        switch (state.get(FACING)) {
            case SOUTH:
                return AGE_TO_SOUTH_SHAPE[i];
            case WEST:
                return AGE_TO_WEST_SHAPE[i];
            case EAST:
                return AGE_TO_EAST_SHAPE[i];
            default:
                return AGE_TO_NORTH_SHAPE[i];
        }
    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return OrganicallyModItems.COCONUT.getDefaultStack();
    }

    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.random.nextInt(GROW_CHANCE) == 0) {
            int i = (Integer)state.get(AGE);
            if (i < 2) {
                world.setBlockState(pos, (BlockState)state.with(AGE, i + 1), 2);
            }
        }

    }

    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos.offset(state.get(FACING)));
        return blockState.isOf(OrganicallyModBlocks.PALM_CROWN);
    }

    protected void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        if (!world.isClient) {
            BlockPos blockPos = hit.getBlockPos();
            if (projectile.getVelocity().length() > 1.0 && projectile.getVelocity().length() < 4.0) {
                world.breakBlock(blockPos, true);
            }else if (projectile.getVelocity().length() > 4.0){
                if (state.get(AGE) == 2) {
                    ItemEntity itemEntity = new ItemEntity(world, blockPos.getX() + 0.5, blockPos.getY() + 0.5, blockPos.getZ() + 0.5, new ItemStack(OrganicallyModItems.COCONUT_SLICE, 2));
                    itemEntity.setVelocity(0, 0, 0);
                    world.addBlockBreakParticles(blockPos, OrganicallyModBlocks.COCONUT.getDefaultState());
                    world.setBlockState(blockPos, Blocks.AIR.getDefaultState());
                    world.playSound(null, blockPos, SoundEvents.BLOCK_BAMBOO_BREAK, SoundCategory.BLOCKS, 1.2F, 0.5F);
                    world.spawnEntity(itemEntity);
                }else {
                    world.breakBlock(blockPos, true);
                }
            }

        }
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, AGE);
    }
}
