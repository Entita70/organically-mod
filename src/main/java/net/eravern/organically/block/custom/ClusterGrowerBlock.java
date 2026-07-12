package net.eravern.organically.block.custom;

import net.eravern.organically.block.OrganicallyModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class ClusterGrowerBlock extends Block {
    private final Block block;
    public static final IntProperty AGE = Properties.AGE_2;
    public static final int AGE_CHANCE = 10;
    public static final int GROWTH_CHANCE = 15;
    public static final int CLUSTER_GROWTH_CHANCE = 20;


    protected static final VoxelShape[] SHAPES = new VoxelShape[]{Block.createCuboidShape(4.0, 0.0, 4.0, 12.0, 9.0, 12.0), Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 12.0, 14.0), Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};

    public ClusterGrowerBlock(Block block, Settings settings) {
        super(settings);
        if (!(block instanceof ClusterBlock)){
            block = OrganicallyModBlocks.DESERT_ROSE_CLUSTER;
        }
        this.block = block;
        this.setDefaultState(this.getStateManager().getDefaultState().with(AGE, 2));
    }

    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        int n, a = state.get(AGE);
        for(n = 1; world.getBlockState(pos.down(n)).isOf(this); ++n) {
        }

        boolean grow = false, growC = false;

        if (state.get(AGE) == 2){
            growC = true;
            if (world.getBlockState(pos.up()) == Blocks.AIR.getDefaultState()){
                    if (n < 3){
                        grow = true;
                    }
            }
        }

        if(world.random.nextInt(CLUSTER_GROWTH_CHANCE) == 0 && growC){
            int r = world.random.nextInt(5);
            switch (r){
                case 0:
                    if (world.getBlockState(pos.north()) == Blocks.AIR.getDefaultState()){
                        world.setBlockState(pos.north(), block.getDefaultState());
                    }
                    break;
                case 1:
                    if (world.getBlockState(pos.east()) == Blocks.AIR.getDefaultState()){
                        world.setBlockState(pos.east(), block.getDefaultState().with(Properties.FACING, Direction.EAST));
                    }
                    break;
                case 3:
                    if (world.getBlockState(pos.west()) == Blocks.AIR.getDefaultState()){
                        world.setBlockState(pos.west(), block.getDefaultState().with(Properties.FACING, Direction.WEST));
                    }
                    break;
                case 4:
                    if (world.getBlockState(pos.south()) == Blocks.AIR.getDefaultState()){
                        world.setBlockState(pos.south(), block.getDefaultState().with(Properties.FACING, Direction.SOUTH));
                    }
                    break;
                default:
                    if (world.getBlockState(pos.up()) == Blocks.AIR.getDefaultState()){
                        world.setBlockState(pos.up(), block.getDefaultState().with(Properties.FACING, Direction.UP));
                    }
                    break;
            }
        }

        if (a < 2){
            if(world.random.nextInt(AGE_CHANCE) == 0){
            world.setBlockState(pos, state.with(AGE, a + 1), 2);
            }
        }

        if (grow){
            if (world.random.nextInt(GROWTH_CHANCE) == 0){
                world.setBlockState(pos.up(), state.with(AGE, 0));
            }
        }

    }

    @Override
    public ItemStack getPickStack(WorldView world, BlockPos pos, BlockState state) {
        return block.asItem().getDefaultStack();
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return Block.sideCoversSmallSquare(world, pos.down(), Direction.UP);
    }

    @Override
    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!Block.sideCoversSmallSquare(world, pos.down(), Direction.UP) && state.get(AGE) != 2){
                world.breakBlock(pos, true);
        }
    }

    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int i = state.get(AGE);
                return SHAPES[i];
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
