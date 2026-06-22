package net.eravern.organically.block.custom;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class PointyFossilBlock extends Block implements Waterloggable {

    public static final DirectionProperty FACING = Properties.FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    protected final VoxelShape NORTH = Block.createCuboidShape(6.0, 6.0, 2.0, 10.0, 10.0, 16.0);
    protected final VoxelShape SOUTH = Block.createCuboidShape(6.0, 6.0, 0.0, 10.0, 10.0, 14.0);
    protected final VoxelShape EAST = Block.createCuboidShape(0.0, 6.0, 6.0, 14.0, 10.0, 10.0);
    protected final VoxelShape WEST = Block.createCuboidShape(2.0, 6.0, 6.0, 16.0, 10.0, 10.0);
    protected final VoxelShape UP = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 14.0, 10.0);
    protected final VoxelShape DOWN = Block.createCuboidShape(6.0, 2.0, 6.0, 10.0, 16.0, 10.0);


    public PointyFossilBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false));
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        switch (direction) {
            case UP:
                return this.UP;
            case DOWN:
                return this.DOWN;
            case SOUTH:
                return this.SOUTH;
            case EAST:
                return this.EAST;
            case WEST:
                return this.WEST;
            default:
                return this.NORTH;
        }
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        BlockState blockState = world.getBlockState(pos);
        return this.getDefaultState().with(FACING, ctx.getSide()).with(WATERLOGGED, blockState == Blocks.WATER.getDefaultState());
    }

    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    protected FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, WATERLOGGED);
    }

}