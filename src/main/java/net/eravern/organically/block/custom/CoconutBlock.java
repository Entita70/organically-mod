package net.eravern.organically.block.custom;

import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.item.OrganicallyModItems;
import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class CoconutBlock extends Block implements Fertilizable {
    public static int GROW_CHANCE = 15;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    protected static final VoxelShape COCONUT = Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 8.0, 12.0);
    public CoconutBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false));
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return true;
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBlockState(pos.down()).isIn(BlockTags.SAND)){
            if (world.random.nextInt(GROW_CHANCE) == 0){
                world.setBlockState(pos, OrganicallyModBlocks.SPROUTED_COCONUT.getDefaultState());
                world.addBlockBreakParticles(pos, OrganicallyModBlocks.COCONUT.getDefaultState());
            }
        }
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return Block.sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        WorldAccess worldAccess = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        return this.getDefaultState().with(WATERLOGGED, worldAccess.getFluidState(blockPos).getFluid() == Fluids.WATER);
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return COCONUT;
    }

    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        BlockPos blockPos = pos.down();
        return this.canTurnIntoSapling(world.getBlockState(blockPos), world, blockPos);
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos, OrganicallyModBlocks.SPROUTED_COCONUT.getDefaultState());
    }

    protected boolean canTurnIntoSapling(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isIn(BlockTags.SAND);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (stack.isIn(ItemTags.AXES)){
            ItemEntity itemEntity = new ItemEntity(world, pos.getX()+0.5, pos.getY()+0.2, pos.getZ()+0.5, new ItemStack(OrganicallyModItems.COCONUT_SLICE, 2));
            itemEntity.setVelocity(0, 0.15, 0);
            world.addBlockBreakParticles(pos, OrganicallyModBlocks.COCONUT.getDefaultState());
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
            world.playSound(null, pos, SoundEvents.BLOCK_BAMBOO_BREAK, SoundCategory.BLOCKS, 1.2F, 0.5F);
            world.spawnEntity(itemEntity);
            stack.damage(1, player, LivingEntity.getSlotForHand(hand));
        }else{
            return super.onUseWithItem(stack, state, world, pos, player, hand, hit);
        }
        return ItemActionResult.success(world.isClient);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }
}
