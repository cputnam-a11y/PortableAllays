package portableallays;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import portableallays.brain.ModGenericBrainThings;
import portableallays.component.ModDataComponentTypes;
import portableallays.item.ModItems;
import portableallays.network.ModNetworkHandler;
import portableallays.potion.ModPotions;
import portableallays.tick.ModAllayTickProviders;

public class PortableAllays implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final String MOD_ID = "portableallays";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModDataComponentTypes.init();
		ModItems.init();
		ModNetworkHandler.init();
		ModPotions.init();
		ModGenericBrainThings.init();
		ModAllayTickProviders.init();
		LOGGER.info("Hello Fabric world!");
	}
}