package net.eravern.organically.entity.client.lionfish_spike;

import net.eravern.organically.OrganicallyMod;
import net.eravern.organically.entity.custom.LionfishSpikeEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class LionfishSpikeRenderer extends ProjectileEntityRenderer<LionfishSpikeEntity> {
    public static final Identifier TEXTURE = Identifier.of(OrganicallyMod.MOD_ID, "textures/entity/projectiles/lionfish_spike.png");

    public LionfishSpikeRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(LionfishSpikeEntity entity) {
        return TEXTURE;
    }
}
