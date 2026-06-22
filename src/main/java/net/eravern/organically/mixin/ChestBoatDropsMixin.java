package net.eravern.organically.mixin;

import net.eravern.organically.item.OrganicallyModItems;
import net.eravern.organically.register.OrganicallyModBoatTypes;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.entity.vehicle.ChestBoatEntity;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ChestBoatEntity.class)
public class ChestBoatDropsMixin {

	// Credits to nyuppo's fabric-boat-example GitHub repository (https://github.com/nyuppo/fabric-boat-example)

	@Inject(method = "asItem", at = @At("HEAD"), cancellable = true)
	public void asItem(CallbackInfoReturnable<Item> ci) {
		if (((ChestBoatEntity)(Object)this).getVariant() == OrganicallyModBoatTypes.PALM) {
			ci.setReturnValue(OrganicallyModItems.PALM_CHEST_BOAT);
		}
	}
}