package net.eravern.organically.block.custom;

import net.eravern.organically.entity.OrganicallyModEntityTypes;
import net.eravern.organically.entity.custom.GnawerEntity;
import net.eravern.organically.util.OrganicallyModBlockTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.SnifferEggBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SnifferEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class GnawerEggBlock extends SnifferEggBlock {
    protected static final VoxelShape SHAPE = Block.createCuboidShape(3.0, 0.0, 3.0, 13.0, 10.0, 13.0);


    public GnawerEggBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    private boolean isReadyToHatch(BlockState state) {
        return this.getHatchStage(state) == 2;
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!this.isReadyToHatch(state)) {
            world.playSound(null, pos, SoundEvents.BLOCK_SNIFFER_EGG_CRACK, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
            world.setBlockState(pos, state.with(HATCH, this.getHatchStage(state) + 1), 2);
        } else {
            world.playSound(null, pos, SoundEvents.BLOCK_SNIFFER_EGG_HATCH, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
            world.breakBlock(pos, false);
            GnawerEntity gnawerEntity =  OrganicallyModEntityTypes.GNAWER.create(world);
            if (gnawerEntity != null) {
                Vec3d vec3d = pos.toCenterPos();
                gnawerEntity.setBaby(true);
                gnawerEntity.refreshPositionAndAngles(vec3d.getX(), vec3d.getY(), vec3d.getZ(), MathHelper.wrapDegrees(world.random.nextFloat() * 360.0F), 0.0F);
                world.spawnEntity(gnawerEntity);
            }

        }
    }

    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
        boolean bl = isAboveHatchBooster(world, pos);
        if (!world.isClient() && bl) {
            world.syncWorldEvent(3009, pos, 0);
        }

        int i = bl ? 12000 : 24000;
        int j = i / 3;
        world.emitGameEvent(GameEvent.BLOCK_PLACE, pos, GameEvent.Emitter.of(state));
        world.scheduleBlockTick(pos, this, j + world.random.nextInt(300));
    }

    public static boolean isAboveHatchBooster(BlockView world, BlockPos pos) {
        return world.getBlockState(pos.down()).isIn(OrganicallyModBlockTags.GNAWER_HATCH_BOOST);
    }

}
