package net.takerudavis.butchers_delight_rechopped;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.takerudavis.butchers_delight_rechopped.block.ModBlocks;

public class ButchersDelightRechoppedClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
//        BlockEntityRendererRegistry.register(
//                ModBlocks.CARCASS_BLOCK_ENTITY,
//                CarcassBlockEntityRenderer::new
//        );

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CHICKEN_CARCASS, RenderType.cutout());
    }

}
