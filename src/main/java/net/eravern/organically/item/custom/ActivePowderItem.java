package net.eravern.organically.item.custom;

import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.util.OrganicallyModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
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
                spawnParticlesAroundBlock((ServerWorld) world, pos.getX(), pos.getY(), pos.getZ(), ParticleTypes.HAPPY_VILLAGER, 12);
                if (block.isOf(OrganicallyModBlocks.DESERT_ROSE_BLOCK)) {
                    world.setBlockState(pos, OrganicallyModBlocks.ACTIVE_DESERT_ROSE_CLUSTER.getDefaultState());
                } else if (block.isOf(OrganicallyModBlocks.MESA_ROSE_BLOCK)) {
                    world.setBlockState(pos, OrganicallyModBlocks.ACTIVE_MESA_ROSE_CLUSTER.getDefaultState());
                }else if (block.isOf(Blocks.AMETHYST_BLOCK)){
                    world.setBlockState(pos, Blocks.BUDDING_AMETHYST.getDefaultState());
                }
                int c = context.getStack().getCount();
                context.getStack().setCount(c - 1);
                world.playSound(null, pos, SoundEvents.ITEM_BONE_MEAL_USE, SoundCategory.BLOCKS, 1.5f, 0.2f);
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }

    public static void spawnParticlesAroundBlock(ServerWorld world, double x, double y, double z, ParticleEffect effect, int count){
        Random r = world.getRandom();
        for(int i = 0; i < count; i++){
            world.spawnParticles(effect, x+randomDouble(r), y+1, z+randomDouble(r), 1, 0, 0, 0,0);
        }
        for(int i = 0; i < count; i++){
            world.spawnParticles(effect, x+randomDouble(r), y, z+randomDouble(r), 1, 0, 0, 0,0);
        }
        for(int i = 0; i < count; i++){
            world.spawnParticles(effect, x, y+randomDouble(r), z+randomDouble(r), 1, 0, 0, 0,0);
        }
        for(int i = 0; i < count; i++){
            world.spawnParticles(effect, x+randomDouble(r), y+randomDouble(r), z, 1, 0, 0, 0,0);
        }
        for(int i = 0; i < count; i++){
            world.spawnParticles(effect, x+1, y+randomDouble(r), z+randomDouble(r), 1, 0, 0, 0,0);
        }
        for(int i = 0; i < count; i++){
            world.spawnParticles(effect, x+randomDouble(r), y+randomDouble(r), z+1, 1, 0, 0, 0,0);
        }
    }


    public static double randomDouble(Random random){
        double number = random.nextDouble();
        if (number < 0.1){
            number = 0.1;
        }else if (number > 0.9){
            number = 0.9;
        }
        return number;
    }
}
