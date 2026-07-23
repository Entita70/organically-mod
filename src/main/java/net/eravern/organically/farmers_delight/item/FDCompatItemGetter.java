package net.eravern.organically.farmers_delight.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import vectorwing.farmersdelight.common.item.ConsumableItem;

public class FDCompatItemGetter {

    public static Item getFDStewItem(FoodComponent foodComponent){
        return new ConsumableItem(new Item.Settings().food(foodComponent).maxCount(16).recipeRemainder(Items.BOWL));
    }

}
