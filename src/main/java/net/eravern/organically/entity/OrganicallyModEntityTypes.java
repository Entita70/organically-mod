package net.eravern.organically.entity;

import net.eravern.organically.OrganicallyMod;
import net.eravern.organically.entity.custom.GnawerEntity;
import net.eravern.organically.entity.custom.LionfishEntity;
import net.eravern.organically.entity.custom.LionfishSpikeEntity;
import net.eravern.organically.entity.custom.SandStriderEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class OrganicallyModEntityTypes {


    public static final EntityType<LionfishSpikeEntity> LIONFISH_SPIKE = Registry.register(Registries.ENTITY_TYPE,
    Identifier.of(OrganicallyMod.MOD_ID, "lionfish_spike"),
    EntityType.Builder.<LionfishSpikeEntity>create(LionfishSpikeEntity::new, SpawnGroup.MISC).dimensions(0.5f, 0.5f)
            .eyeHeight(0.13F).maxTrackingRange(4).trackingTickInterval(20).build());

    public static final EntityType<LionfishEntity> LIONFISH = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(OrganicallyMod.MOD_ID, "lionfish"),
            EntityType.Builder.create(LionfishEntity::new, SpawnGroup.WATER_AMBIENT).dimensions(0.7f, 0.5f).eyeHeight(0.3F).maxTrackingRange(3).build());

    public static final EntityType<SandStriderEntity> SANDSTRIDER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(OrganicallyMod.MOD_ID, "sandstrider"),
            EntityType.Builder.create(SandStriderEntity::new, SpawnGroup.MONSTER).dimensions(1.4f, 0.7f).eyeHeight(0.5F).maxTrackingRange(20).build());

    public static final EntityType<GnawerEntity> GNAWER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(OrganicallyMod.MOD_ID, "gnawer"),
            EntityType.Builder.create(GnawerEntity::new, SpawnGroup.CREATURE).dimensions(1.4f, 1.2f).eyeHeight(1f).passengerAttachments(1.5f).nameTagAttachment(2f).maxTrackingRange(10).build());



    public static void registerModEntityTypes(){
        OrganicallyMod.LOGGER.info("Entity Types registry for " + OrganicallyMod.MOD_ID);
    }
}
