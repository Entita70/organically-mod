package net.eravern.organically.mixin;

import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.item.OrganicallyModItems;
import net.eravern.organically.register.OrganicallyModBoatTypes;
import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.Arrays;



@Mixin(BoatEntity.class)
public class BoatDropsMixin {

	// Credits to nyuppo's fabric-boat-example GitHub repository (https://github.com/nyuppo/fabric-boat-example)

	@Inject(method = "asItem", at = @At("HEAD"), cancellable = true)
	public void asItem(CallbackInfoReturnable<Item> ci) {
		if (((BoatEntity)(Object)this).getVariant() == OrganicallyModBoatTypes.PALM) {
			ci.setReturnValue(OrganicallyModItems.PALM_BOAT);
		}
	}
}