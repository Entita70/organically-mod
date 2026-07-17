package net.eravern.organically.item.custom;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Items;

public class OrganicallyFoodComponents {
    public static final FoodComponent LIONFISH = (new FoodComponent.Builder()).nutrition(2).saturationModifier(0.2f).build();
    public static final FoodComponent COOKED_LIONFISH = (new FoodComponent.Builder()).nutrition(5).saturationModifier(0.6f).build();
    public static final FoodComponent COCONUT_SLICE = (new FoodComponent.Builder()).nutrition(2).saturationModifier(0.3f).build();
    public static final FoodComponent PALM_SALAD = (new FoodComponent.Builder()).nutrition(3).saturationModifier(0.5f).build();
    public static final FoodComponent SEAFARERS_CHOW = (new FoodComponent.Builder()).nutrition(7).saturationModifier(0.7F).usingConvertsTo(Items.BOWL).build();
    public static final FoodComponent ROASTED_DESERT_SHAGGY_MANE = (new FoodComponent.Builder()).nutrition(4).saturationModifier(0.4f).build();
}
