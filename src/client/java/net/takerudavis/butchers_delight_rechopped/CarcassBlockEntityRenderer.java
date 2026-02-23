package net.takerudavis.butchers_delight_rechopped;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.takerudavis.butchers_delight_rechopped.block.entity.CarcassBlockEntity;

public class CarcassBlockEntityRenderer implements BlockEntityRenderer<CarcassBlockEntity> {
    public CarcassBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(CarcassBlockEntity blockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {

    }
}
