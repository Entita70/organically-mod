package net.eravern.organically.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class RoseClusterBlock extends Block {
    public static final DirectionProperty FACING = Properties.FACING;

    protected final VoxelShape UP = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);
    protected final VoxelShape DOWN = Block.createCuboidShape(0.0, 14.0, 0.0, 16.0, 16.0, 16.0);
    protected final VoxelShape WEST = Block.createCuboidShape(14.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected final VoxelShape EAST = Block.createCuboidShape(0.0, 0.0, 0.0, 2.0, 16.0, 16.0);
    protected final VoxelShape SOUTH = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 2.0);
    protected final VoxelShape NORTH = Block.createCuboidShape(0.0, 0.0, 14.0, 16.0, 16.0, 16.0);


    public RoseClusterBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction direction = state.get(FACING);
        switch (direction) {
            case NORTH:
                return this.NORTH;
            case SOUTH:
                return this.SOUTH;
            case EAST:
                return this.EAST;
            case WEST:
                return this.WEST;
            case DOWN:
                return this.DOWN;
            case UP:
            default:
                return this.UP;
        }
    }

    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        BlockPos blockPos = pos.offset(direction.getOpposite());
        return world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, direction);
    }

    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return direction == (state.get(FACING)).getOpposite() && !state.canPlaceAt(world, pos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getSide());
    }

    protected BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    protected BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }


    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
