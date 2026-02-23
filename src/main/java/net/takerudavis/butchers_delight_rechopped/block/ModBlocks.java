package net.takerudavis.butchers_delight_rechopped.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.flag.FeatureFlag;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.takerudavis.butchers_delight_rechopped.ButchersDelightRechopped;
import net.takerudavis.butchers_delight_rechopped.CarcassRegistry;
import net.takerudavis.butchers_delight_rechopped.ModCreativeTabs;
import net.takerudavis.butchers_delight_rechopped.block.carcass.ChickenCarcassBlock;
import net.takerudavis.butchers_delight_rechopped.block.carcass.SheepCarcassBlock;
import net.takerudavis.butchers_delight_rechopped.block.entity.CarcassBlockEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModBlocks {

    private static final List<Block> BLOCKS = new ArrayList<>();
    public static final List<AbstractCarcassBlock> CARCASS_BLOCKS = new ArrayList<>();
    private static Block registerBlock(String name, Block block) {
        BLOCKS.add(block);
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(ButchersDelightRechopped.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(ButchersDelightRechopped.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    private static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String name, BlockEntityType<T> type) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(ButchersDelightRechopped.MOD_ID, name), type);
    }

    protected static Block registerCarcass(String name, AbstractCarcassBlock block) {
        return registerBlock(name, CarcassRegistry.register(block));
    }

    public static final Block HOOK = registerBlock("hook",
            new HookBlock(Block.Properties.of().noOcclusion()));

    public static final Block CHICKEN_CARCASS = registerCarcass("chicken_carcass", new ChickenCarcassBlock(Block.Properties.of().noOcclusion()));

    public static final Block SHEEP_CARCASS = registerCarcass("sheep_carcass", new SheepCarcassBlock(BlockBehaviour.Properties.of().noOcclusion()));

    public static final BlockEntityType<CarcassBlockEntity> CARCASS_BLOCK_ENTITY =
            registerBlockEntity(
                    "carcass_block",
                    FabricBlockEntityTypeBuilder.create(
                            CarcassBlockEntity::new,
                            ModBlocks.CARCASS_BLOCKS.toArray(Block[]::new)
                    ).build(null)
            );


    private static void addBlocksToTab() {
        ItemGroupEvents.modifyEntriesEvent(ModCreativeTabs.BUTCHERS_DELIGHT_TAB).register(entries -> BLOCKS.forEach(entries::accept));
    }

    public static void init() {addBlocksToTab();}
}
