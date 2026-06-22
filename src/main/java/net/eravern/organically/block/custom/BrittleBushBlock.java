package net.eravern.organically.block.custom;

import net.eravern.organically.entity.OrganicallyModEntityTypes;
import net.eravern.organically.util.OrganicallyModBlockTags;
import net.eravern.organically.util.OrganicallyModItemTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class BrittleBushBlock extends SandFlowerBlock{
    protected static final VoxelShape BRITTLEBUSH = Block.createCuboidShape(2.0, 0.0, 2.0, 14.0, 8.0, 14.0);
    public BrittleBushBlock(RegistryEntry<StatusEffect> stewEffect, float effectLengthInSeconds, Settings settings) {
        super(stewEffect, effectLengthInSeconds, settings);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BRITTLEBUSH;
    }

    protected void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && entity.getType() != EntityType.RABBIT && entity.getType() != EntityType.BEE && entity.getType() != EntityType.CAMEL  && entity.getType() != OrganicallyModEntityTypes.SANDSTRIDER) {
            boolean affected = true;
            if (entity instanceof PlayerEntity player){
                if(player.getEquippedStack(EquipmentSlot.FEET).isIn(OrganicallyModItemTags.BRITTLEBUSH_IMMUNE)){
                    affected = false;
                }
            }
            if (affected){
                entity.slowMovement(state, new Vec3d(0.95, 0.80, 0.95));
            }
        }
    }

}
