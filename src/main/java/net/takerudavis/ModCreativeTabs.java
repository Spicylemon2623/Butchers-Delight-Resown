package net.takerudavis;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.takerudavis.item.ModItems;

public class ModCreativeTabs {

    public static final ResourceKey<CreativeModeTab> BUTCHERS_DELIGHT_TAB = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), new ResourceLocation("butchers_delight_rechopped"));

    public static CreativeModeTab TAB = Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            BUTCHERS_DELIGHT_TAB.location(),
            FabricItemGroup.builder().title(Component.translatable("itemGroup.butchers_delight_rechopped")).icon(() -> new ItemStack(ModItems.CLEAVER)).build()
    );

    public static void init() {}
}
