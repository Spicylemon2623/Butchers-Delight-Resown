package net.takerudavis;

import net.fabricmc.api.ModInitializer;

import net.takerudavis.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ButchersDelightRechopped implements ModInitializer {
	public static final String MOD_ID = "butchers_delight_rechopped";

	public static final Logger LOGGER = LoggerFactory.getLogger("Butcher's Delight Resown");

	@Override
	public void onInitialize() {
		LOGGER.info("Initialising Butcher's Delight Resown");
		ModItems.init();
		ModCreativeTabs.init();
		ModItems.addItemsToTab();
	}
}