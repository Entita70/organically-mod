package net.eravern.organically.particle;

import net.eravern.organically.OrganicallyMod;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class OrganicallyModParticleTypes {


    public static final SimpleParticleType ELECTRIFIED = registerParticle("electrified", FabricParticleTypes.simple(false));



    public static SimpleParticleType registerParticle(String name, SimpleParticleType simpleParticleType){
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(OrganicallyMod.MOD_ID, name), simpleParticleType);
    }


    public static void registerModParticleTypes(){
        OrganicallyMod.LOGGER.info("Particle Types Registry for " + OrganicallyMod.MOD_ID);
    }

}
