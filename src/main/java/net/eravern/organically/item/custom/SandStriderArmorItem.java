package net.eravern.organically.item.custom;

import net.eravern.organically.util.OrganicallyModItemTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.world.World;

public class SandStriderArmorItem extends ArmorItem {
    private final int maxcooldown = 160;
    private int cooldown = maxcooldown;
    public SandStriderArmorItem(RegistryEntry<ArmorMaterial> material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()){
            if (entity instanceof PlayerEntity player){
                if (player.getEquippedStack(EquipmentSlot.FEET).isIn(OrganicallyModItemTags.SANDSTRIDER_ARMOR)){
                    boolean sanded = entity.getSteppingBlockState().isIn(BlockTags.SAND) && !entity.isInsideWaterOrBubbleColumn();
                    if (sanded){
                        if (player.isSprinting()){
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 30, 1, true, false));
                            cooldown -= 1;
                            if (cooldown <= 0){
                                stack.damage(1, player, EquipmentSlot.FEET);
                                cooldown = maxcooldown;
                            }
                        }else{
                            cooldown = maxcooldown;
                        }
                    }
                }
            }
        }
    }

}
