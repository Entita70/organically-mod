package net.eravern.organically.world.gen.features.feature_config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.block.custom.ClusterBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryCodecs;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.util.Util;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.List;
import java.util.Objects;


public class ClusterPlaceFeatureConfig implements FeatureConfig {
    public static final Codec<ClusterPlaceFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(
                Registries.BLOCK.getCodec().fieldOf("block").flatXmap(ClusterPlaceFeatureConfig::validateBlock, DataResult::success).orElse((ClusterBlock) OrganicallyModBlocks.MOLLUSKS).forGetter((config) -> {
            return config.block;
        }), Codec.intRange(1, 64).fieldOf("search_range").orElse(10).forGetter((config) -> {
            return config.searchRange;
        }), Codec.BOOL.fieldOf("can_place_on_floor").orElse(false).forGetter((config) -> {
            return config.placeOnFloor;
        }), Codec.BOOL.fieldOf("can_place_on_ceiling").orElse(false).forGetter((config) -> {
            return config.placeOnCeiling;
        }), Codec.BOOL.fieldOf("can_place_on_wall").orElse(false).forGetter((config) -> {
            return config.placeOnWalls;
        }), RegistryCodecs.entryList(RegistryKeys.BLOCK).fieldOf("can_be_placed_on").forGetter((config) -> {
            return config.canPlaceOn;
        })).apply(instance, ClusterPlaceFeatureConfig::new);
    });

    public final ClusterBlock block;
    public final int searchRange;
    public final boolean placeOnFloor;
    public final boolean placeOnCeiling;
    public final boolean placeOnWalls;
    public final RegistryEntryList<Block> canPlaceOn;
    private final ObjectArrayList<Direction> directions;

    public ClusterPlaceFeatureConfig(ClusterBlock block, int searchRange, boolean placeOnFloor, boolean placeOnCeiling, boolean placeOnWalls, RegistryEntryList<Block> canPlaceOn) {
        this.block = block;
        this.searchRange = searchRange;
        this.placeOnFloor = placeOnFloor;
        this.placeOnCeiling = placeOnCeiling;
        this.placeOnWalls = placeOnWalls;
        this.canPlaceOn = canPlaceOn;
        this.directions = new ObjectArrayList<>(6);
        if (placeOnCeiling) {
            this.directions.add(Direction.UP);
        }

        if (placeOnFloor) {
            this.directions.add(Direction.DOWN);
        }

        if (placeOnWalls) {
            Direction.Type var10000 = Direction.Type.HORIZONTAL;
            ObjectArrayList<Direction> var10001 = this.directions;
            Objects.requireNonNull(var10001);
            var10000.forEach(var10001::add);
        }
    }

    private static DataResult<ClusterBlock> validateBlock(Block block) {
        DataResult var10000;
        if (block instanceof ClusterBlock clusterBlock) {
            var10000 = DataResult.success(clusterBlock);
        } else {
            var10000 = DataResult.error(() -> {
                return "Block should be a cluster block";
            });
        }

        return var10000;
    }

    public List<Direction> shuffleDirections(Random random, Direction excluded) {
        return Util.copyShuffled(this.directions.stream().filter((direction) -> {
            return direction != excluded;
        }), random);
    }

    public List<Direction> shuffleDirections(Random random) {
        return Util.copyShuffled(this.directions, random);
    }
}
