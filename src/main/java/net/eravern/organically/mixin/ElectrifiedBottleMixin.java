package net.eravern.organically.mixin;


import net.eravern.organically.item.OrganicallyModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.potion.Potions;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(PotionItem.class)
public class ElectrifiedBottleMixin {

    @Inject(method = "use", at = @At("HEAD"))
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        if (user.isSneaking()){
            if (Objects.requireNonNull(user.getStackInHand(hand).getComponents().get(DataComponentTypes.POTION_CONTENTS)).matches(Potions.WATER)){
                if (user.getOffHandStack().isOf(OrganicallyModItems.LIONFISH_SPIKE)){
                    ItemStack itemStack = OrganicallyModItems.ELECTRIFIED_BOTTLE.getDefaultStack();
                    int count = user.getOffHandStack().getCount();
                    user.getOffHandStack().setCount(count-1);
                    world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    if (user.getStackInHand(hand).getCount() > 1){
                        int c = user.getStackInHand(hand).getCount();
                        user.getStackInHand(hand).setCount(c-1);
                        user.giveItemStack(itemStack);
                    }else{
                        user.setStackInHand(hand, itemStack);
                    }
                }
            }
        }
    }
}
