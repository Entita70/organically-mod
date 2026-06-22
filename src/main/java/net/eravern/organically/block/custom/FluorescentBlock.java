package net.eravern.organically.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class FluorescentBlock extends Block {
    public static final BooleanProperty LIT = Properties.LIT;
    protected static final VoxelShape OUTER = Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape INNER = Block.createCuboidShape(3.0, 3.0, 3.0, 13.0, 13.0, 13.0);

    public FluorescentBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(LIT, false));
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return OUTER;
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return INNER;
    }

    @Override
    protected boolean hasRandomTicks(BlockState state) {
        return state.get(LIT);
    }

    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (entity.bypassesLandingEffects()) {
            super.onLandedUpon(world, state, pos, entity, fallDistance);
        } else {
            entity.handleFallDamage(fallDistance, 0.5f, world.getDamageSources().fall());
        }

    }

    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
            world.setBlockState(pos, state.with(LIT, false));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT);
    }

    @Override
    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        double d = Math.abs(entity.getVelocity().y);
        if (d < 0.1) {
            world.setBlockState(pos, state.with(LIT, true), 2);
            double e = 0.6 + d * 0.2;
            entity.setVelocity(entity.getVelocity().multiply(e, 1.0, e));
        }
        if (entity instanceof LivingEntity livingEntity){
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 10, 0, true, false));
        }
        super.onSteppedOn(world, pos, state, entity);
    }
}
