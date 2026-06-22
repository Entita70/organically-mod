package net.eravern.organically.mixin;

import com.mojang.serialization.MapCodec;
import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.register.OrganicallyModBoatTypes;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.vehicle.BoatEntity;

import net.minecraft.item.Items;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(BoatEntity.Type.class)
public class BoatTypeMixin {

	// Credits to nyuppo's fabric-boat-example GitHub repository (https://github.com/nyuppo/fabric-boat-example)

	@SuppressWarnings("InvokerTarget")
	@Invoker("<init>")
	private static BoatEntity.Type newType(String internalName, int internalId, Block baseBlock, String name) {
		throw new AssertionError();
	}

	@SuppressWarnings("ShadowTarget")
	@Shadow
	@Final
	@Mutable
	private static BoatEntity.Type[] field_7724;

	@Inject(method = "<clinit>", at = @At(value = "FIELD",
			opcode = Opcodes.PUTSTATIC,
			target = "Lnet/minecraft/entity/vehicle/BoatEntity$Type;field_7724:[Lnet/minecraft/entity/vehicle/BoatEntity$Type;",
			shift = At.Shift.AFTER))
	private static void addCustomBoatType(CallbackInfo ci) {
		var types = new ArrayList<>(Arrays.asList(field_7724));
		var last = types.get(types.size() - 1);

		var palm = newType("PALM", last.ordinal() + 1, Blocks.OAK_PLANKS, "palm");
		OrganicallyModBoatTypes.PALM = palm;
		types.add(palm);

		field_7724 = types.toArray(new BoatEntity.Type[0]);
	}
}