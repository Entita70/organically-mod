package net.eravern.organically;

import net.eravern.organically.block.OrganicallyModBlocks;
import net.eravern.organically.entity.OrganicallyModEntityTypes;
import net.eravern.organically.entity.client.gnawer.GnawerModel;
import net.eravern.organically.entity.client.gnawer.GnawerRenderer;
import net.eravern.organically.entity.client.lionfish.LionfishModel;
import net.eravern.organically.entity.client.lionfish.LionfishRenderer;
import net.eravern.organically.entity.client.lionfish_spike.LionfishSpikeRenderer;
import net.eravern.organically.entity.client.sandstrider.SandStriderModel;
import net.eravern.organically.entity.client.sandstrider.SandStriderRenderer;
import net.eravern.organically.particle.OrganicallyModParticleTypes;
import net.eravern.organically.particle.custom.ElectrifiedParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.mixin.client.rendering.BlockEntityRendererFactoriesMixin;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.world.biome.FoliageColors;

public class OrganicallyModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {


        BlockRenderLayerMap.INSTANCE.putBlock(OrganicallyModBlocks.DESERT_ROSE_CLUSTER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(OrganicallyModBlocks.MESA_ROSE_CLUSTER, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(OrganicallyModBlocks.PALM_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(OrganicallyModBlocks.PALM_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(OrganicallyModBlocks.SPROUTED_COCONUT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(OrganicallyModBlocks.DESERT_MARIGOLDS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(OrganicallyModBlocks.POTTED_DESERT_MARIGOLDS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(OrganicallyModBlocks.TRIODIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(OrganicallyModBlocks.POTTED_TRIODIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(OrganicallyModBlocks.SNAKE_PLANT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(OrganicallyModBlocks.POTTED_SNAKE_PLANT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(OrganicallyModBlocks.BRITTLEBUSH, RenderLayer.getCutoutMipped());
        BlockRenderLayerMap.INSTANCE.putBlock(OrganicallyModBlocks.DESERT_SHAGGY_MANE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(OrganicallyModBlocks.POTTED_DESERT_SHAGGY_MANE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(OrganicallyModBlocks.PALM_LEAVES, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(OrganicallyModBlocks.FLUORESCENT_BLOCK, RenderLayer.getTranslucent());



        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getFoliageColor(world, pos) : FoliageColors.getDefaultColor(), OrganicallyModBlocks.PALM_LEAVES);

        ColorProviderRegistry.ITEM.register(((stack, tintIndex) -> 2476036), OrganicallyModBlocks.PALM_LEAVES);


        EntityModelLayerRegistry.registerModelLayer(LionfishModel.LIONFISH, LionfishModel::getTexturedModelData);
        EntityRendererRegistry.register(OrganicallyModEntityTypes.LIONFISH, LionfishRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(SandStriderModel.SANDSTRIDER, SandStriderModel::getTexturedModelData);
        EntityRendererRegistry.register(OrganicallyModEntityTypes.SANDSTRIDER, SandStriderRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(GnawerModel.GNAWER, GnawerModel::getTexturedModelData);
        EntityRendererRegistry.register(OrganicallyModEntityTypes.GNAWER, GnawerRenderer::new);

        EntityRendererRegistry.register(OrganicallyModEntityTypes.LIONFISH_SPIKE, LionfishSpikeRenderer::new);

        ParticleFactoryRegistry.getInstance().register(OrganicallyModParticleTypes.ELECTRIFIED, ElectrifiedParticle.factory::new);

    }
}
