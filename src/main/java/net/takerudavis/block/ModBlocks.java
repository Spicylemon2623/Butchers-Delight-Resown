package net.takerudavis.block;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.takerudavis.ButchersDelightRechopped;

import java.util.ArrayList;
import java.util.List;

public class ModBlocks {

    private static final List<Block> BLOCKS = new ArrayList<>();
    private static Block registerBlock(String name, Block block) {
        BLOCKS.add(block);
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(ButchersDelightRechopped.MOD_ID, name), block);
    }

    private static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String name, BlockEntityType<T> type) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(ButchersDelightRechopped.MOD_ID, name), type);
    }

    public static final BlockEntityType<>

}
