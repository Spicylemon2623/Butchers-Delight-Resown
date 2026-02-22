package net.takerudavis.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.takerudavis.ButchersDelightRechopped;
import net.takerudavis.ModCreativeTabs;

import java.util.ArrayList;
import java.util.List;


public class ModItems {

    private static final List<Item> ITEMS = new ArrayList<>();
    private static Item registerItem(String name, Item item) {
        ITEMS.add(item);
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(ButchersDelightRechopped.MOD_ID, name), item);
    }

    public static final Item CLEAVER = registerItem("cleaver", new CleaverItem(Tiers.IRON, 6f, -3.1f, new Item.Properties()));

    public static void addItemsToTab() {
        ItemGroupEvents.modifyEntriesEvent(ModCreativeTabs.BUTCHERS_DELIGHT_TAB).register(entries -> ITEMS.forEach(entries::accept));
    }

    public static void init() {};

}
