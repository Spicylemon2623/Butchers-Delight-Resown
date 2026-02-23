package net.takerudavis.butchers_delight_rechopped;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.takerudavis.butchers_delight_rechopped.block.AbstractCarcassBlock;
import net.takerudavis.butchers_delight_rechopped.block.ModBlocks;
import net.takerudavis.butchers_delight_rechopped.block.ProcessingStage;
import net.takerudavis.butchers_delight_rechopped.item.CleaverItem;
import net.takerudavis.butchers_delight_rechopped.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ButchersDelightRechopped implements ModInitializer {
	public static final String MOD_ID = "butchers_delight_rechopped";

	public static final Logger LOGGER = LoggerFactory.getLogger("Butcher's Delight Resown");

	@Override
	public void onInitialize() {
		LOGGER.info("Initialising Butcher's Delight Resown");
		ModCreativeTabs.init();
		ModItems.init();
		ModBlocks.init();
	}
}