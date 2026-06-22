package net.eravern.organically.entity.client.sandstrider;

import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;

import java.util.function.IntFunction;

public enum SandStriderVariants implements StringIdentifiable {
    DEFAULT(0, "default"),
    MESA(1, "mesa");

    private final int id;
    private final String name;
    private static final IntFunction<SandStriderVariants> BY_ID = ValueLists.createIdToValueFunction(SandStriderVariants::getId, values(), ValueLists.OutOfBoundsHandling.WRAP);

     SandStriderVariants(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public static SandStriderVariants byId(int id) {
        return BY_ID.apply(id);
    }

    public String asString() {
        return this.name;
    }
}
