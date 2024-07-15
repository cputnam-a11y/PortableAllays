package portableallays;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.impl.client.rendering.BuiltinItemRendererRegistryImpl;
import portableallays.item.ModItems;
import portableallays.render.AllayItemRenderer;

public class PortableAllaysClient implements ClientModInitializer {
	@Override
    @SuppressWarnings({"internal", "UnstableApiUsage"})
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		BuiltinItemRendererRegistryImpl.INSTANCE.register(ModItems.ALLAY_ITEM, new AllayItemRenderer());
	}
}