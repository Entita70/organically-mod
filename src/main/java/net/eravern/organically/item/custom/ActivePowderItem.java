package net.eravern.organically.item.custom;

import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.item.OrganicallyModItems;
import net.eravern.organically.util.OrganicallyModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;

public class ActivePowderItem extends Item {
    public ActivePowderItem(Settings settings) {
        super(settings);
    }


    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState block = world.getBlockState(pos);

        if (block.isIn(OrganicallyModBlockTags.ACTIVABLE)) {
            if (!world.isClient) {
                if (block.isOf(OrganicallyModBlocks.DESERT_ROSE_CLUSTER_BLOCK)) {
                    world.setBlockState(pos, OrganicallyModBlocks.ACTIVE_DESERT_ROSE_CLUSTER.getDefaultState());
                } else if (block.isOf(OrganicallyModBlocks.MESA_ROSE_CLUSTER_BLOCK)) {
                    world.setBlockState(pos, OrganicallyModBlocks.ACTIVE_MESA_ROSE_CLUSTER.getDefaultState());
                }
                int c = context.getStack().getCount();
                context.getStack().setCount(c - 1);
                world.playSound(null, pos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.5f, 0.2f);
                world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, OrganicallyModItems.ACTIVE_POWDER.getDefaultStack()),
                        pos.getX(), pos.getY(), pos.getZ(), 0.0, 0.0, 0.0);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }
}
