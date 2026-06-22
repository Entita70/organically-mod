package net.eravern.organically.entity.client.lionfish;

import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;

import java.util.function.IntFunction;

public enum LionfishEntityVariants implements StringIdentifiable {
    DEFAULT(0, "default"),
    WARM(1, "warm"),
    MONOCHROME(2, "monochrome"),
    REVERSED(3, "reversed");


    private final int id;
    private final String name;
    private static final IntFunction<LionfishEntityVariants> BY_ID = ValueLists.createIdToValueFunction(LionfishEntityVariants::getId, values(), ValueLists.OutOfBoundsHandling.WRAP);

     LionfishEntityVariants(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public static LionfishEntityVariants byId(int id) {
        return BY_ID.apply(id);
    }

    public String asString() {
        return this.name;
    }
}
