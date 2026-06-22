package net.eravern.organically.register;

import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.item.OrganicallyModItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;

public class OrganicallyModVillagerTradeRegister {
    public static void registerVillagerTrades(){
        TradeOfferHelper.registerWanderingTraderOffers(2, factories ->{
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 3),
                    new ItemStack(OrganicallyModItems.LIONFISH_BUCKET, 1),
                    4, 0, 1f
            ));
        });

        TradeOfferHelper.registerWanderingTraderOffers(1, factories ->{
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 4),
                    new ItemStack(OrganicallyModItems.COCONUT, 1),
                    6, 0, 1f
            ));
        });

        TradeOfferHelper.registerWanderingTraderOffers(2, factories ->{
            factories.add((entity, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 9),
                    new ItemStack(OrganicallyModBlocks.DESERT_ROSE_CLUSTER, 3),
                    2, 0, 1f
            ));
        });

    }
}
