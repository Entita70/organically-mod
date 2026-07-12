package net.eravern.organically.world.gen.features.feature_config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.floatprovider.FloatProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record DesertFormationFeatureConfig(BlockStateProvider main, BlockStateProvider rare, float chance) implements FeatureConfig {
    public static final Codec<DesertFormationFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(
                BlockStateProvider.TYPE_CODEC.fieldOf("main").forGetter((desertFormationFeatureConfig) -> {
                    return desertFormationFeatureConfig.main;}),

                BlockStateProvider.TYPE_CODEC.fieldOf("rare").forGetter((desertFormationFeatureConfig) -> {
                    return desertFormationFeatureConfig.rare;}),

                Codec.floatRange(0.0f, 1.0f).fieldOf("chance").forGetter((desertFormationFeatureConfig) -> {
                    return desertFormationFeatureConfig.chance;
                })).apply(instance, DesertFormationFeatureConfig::new);});

}