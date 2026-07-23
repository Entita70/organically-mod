package net.eravern.organically.farmers_delight.item;

import net.eravern.organically.mob_effect.OrganicallyModEffects;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Items;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class FDFoodComponents {

    public static final FoodComponent LIONFISH_SLICE = (new FoodComponent.Builder()).nutrition(1).saturationModifier(0.2f).build();
    public static final FoodComponent COOKED_LIONFISH_SLICE = (new FoodComponent.Builder()).nutrition(3).saturationModifier(0.5f).build();
    public static final FoodComponent MESA_STYLE_LIONFISH = (new FoodComponent.Builder()).nutrition(8).saturationModifier(0.8f).statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 600, 0), 1.0F).build();
    public static final FoodComponent RICH_MANS_SALAD = (new FoodComponent.Builder()).nutrition(6).saturationModifier(0.7f).statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 400, 0), 1.0F).usingConvertsTo(Items.BOWL).build();
    public static final FoodComponent SEAFARERS_CHOW = (new FoodComponent.Builder()).nutrition(6).saturationModifier(0.7F).statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, 4200, 0, false, false), 1.0F).usingConvertsTo(Items.BOWL).build();
    public static final FoodComponent ELECTRIFIED_STEW = (new FoodComponent.Builder()).nutrition(5).saturationModifier(0.8F).statusEffect(new StatusEffectInstance(OrganicallyModEffects.ELECTRIFIED, 600, 0), 1.0F).statusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 0), 1.0F).usingConvertsTo(Items.BOWL).build();
    public static final FoodComponent MUSHROOM_AND_TOMATO_SOUP = (new FoodComponent.Builder()).nutrition(7).saturationModifier(0.7F).statusEffect(new StatusEffectInstance(ModEffects.NOURISHMENT, 3600, 0, false, false), 1.0F).usingConvertsTo(Items.BOWL).build();

}