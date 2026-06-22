package net.eravern.organically.item.custom;

import net.minecraft.component.type.FoodComponent;

public class OrganicallyFoodComponents {
    public static final FoodComponent LIONFISH = (new FoodComponent.Builder()).nutrition(2).saturationModifier(0.2f).build();
    public static final FoodComponent COOKED_LIONFISH = (new FoodComponent.Builder()).nutrition(5).saturationModifier(0.6f).build();
    public static final FoodComponent COCONUT_SLICE = (new FoodComponent.Builder()).nutrition(2).saturationModifier(0.3f).build();
    public static final FoodComponent PALM_SALAD = (new FoodComponent.Builder()).nutrition(3).saturationModifier(0.5f).build();

}
