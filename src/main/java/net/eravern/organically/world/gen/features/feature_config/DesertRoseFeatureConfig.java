package net.eravern.organically.world.gen.features.feature_config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record DesertRoseFeatureConfig(BlockStateProvider block, BlockStateProvider cluster) implements FeatureConfig {
    public static final Codec<DesertRoseFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(
                BlockStateProvider.TYPE_CODEC.fieldOf("block").forGetter((desertRoseFeatureConfig) -> {
                    return desertRoseFeatureConfig.block;}),

                BlockStateProvider.TYPE_CODEC.fieldOf("cluster").forGetter((desertRoseFeatureConfig) -> {
                    return desertRoseFeatureConfig.cluster;})

        ).apply(instance, DesertRoseFeatureConfig::new);
    });

}