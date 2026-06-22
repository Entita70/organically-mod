package net.eravern.organically.item.custom;

import net.eravern.organically.util.OrganicallyModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;



public class TrowelItem extends Item {
    public TrowelItem(Settings settings) {
        super(settings);
    }

    public static ToolComponent createToolComponent() {
        return new ToolComponent(List.of(ToolComponent.Rule.ofAlwaysDropping(OrganicallyModBlockTags.TROWEL_MINEABLE, 4.0f)), 1.0f, 1);
    }

    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient && !state.isIn(BlockTags.FIRE)) {
            stack.damage(1, miner, EquipmentSlot.MAINHAND);
        }

        return state.isIn(OrganicallyModBlockTags.TROWEL_MINEABLE);
    }


}
