package net.eravern.organically.particle.custom;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class ElectrifiedParticle extends SpriteBillboardParticle {
    protected ElectrifiedParticle(ClientWorld clientWorld, double x, double y, double z,
                                  SpriteProvider spriteProvider, double velocityX, double velocityY, double velocityZ) {
        super(clientWorld, x, y, z, velocityX, velocityY, velocityZ);


        this.velocityMultiplier = 1.0f;
        this.maxAge = 20;
        this.setSpriteForAge(spriteProvider);


        this.red = 0.8f;
        this.green = 0.8f;
        this.blue = 0.8f;

        this.velocityX *= 0.32;
        this.velocityY *= 0.32;
        this.velocityZ *= 0.32;

        this.scale = 1f;
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    public void move(double dx, double dy, double dz) {
        this.setBoundingBox(this.getBoundingBox().offset(dx*2, dy*2, dz*2));
        this.repositionFromBoundingBox();
    }

    @Override
    public float getSize(float tickDelta) {
        return this.scale * 0.15f;
    }

    public static class factory implements ParticleFactory<SimpleParticleType> {
        private static SpriteProvider spriteProvider;

        public factory(SpriteProvider spriteProvider){
            factory.spriteProvider = spriteProvider;
        }


        @Override
        public @Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world,
               double x, double y, double z, double velocityX, double velocityY, double velocityZ) {

            return new ElectrifiedParticle(world, x, y, z, spriteProvider, velocityX, velocityY, velocityZ);
        }
    }

}
