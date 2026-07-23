package net.eravern.organically.item.custom;

import net.eravern.organically.damagetypes.OrganicallyModDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.List;

public class ElectrifiedBottleItem extends Item {
    public ElectrifiedBottleItem(Settings properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        user.damage(OrganicallyModDamageTypes.of(world, OrganicallyModDamageTypes.ELECTRIC), 1f);
        return super.finishUsing(stack, world, user);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    public SoundEvent getDrinkSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    public SoundEvent getEatSound() {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.organicallymod.electrified_bottle.tooltip1"));
        tooltip.add(Text.translatable("item.organicallymod.electrified_bottle.tooltip2"));
        tooltip.add(Text.translatable("item.organicallymod.electrified_bottle.tooltip3"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
