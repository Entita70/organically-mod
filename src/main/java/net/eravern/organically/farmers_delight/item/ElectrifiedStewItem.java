package net.eravern.organically.farmers_delight.item;

import net.eravern.organically.damagetypes.OrganicallyModDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class ElectrifiedStewItem extends ConsumableItem {
    public ElectrifiedStewItem(Settings properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        user.damage(OrganicallyModDamageTypes.of(world, OrganicallyModDamageTypes.ELECTRIC), 1f);
        return super.finishUsing(stack, world, user);
    }
}
