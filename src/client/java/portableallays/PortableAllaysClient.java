package portableallays;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.impl.client.rendering.BuiltinItemRendererRegistryImpl;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.util.math.ColorHelper;
import portableallays.item.ModItems;
import portableallays.render.AllayItemRenderer;

public class PortableAllaysClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
//		BuiltinItemRendererRegistryImpl.INSTANCE.register(ModItems.ALLAY_ITEM, new AllayItemRenderer());
		ColorProviderRegistry.ITEM.register(
				(stack, tintIndex) -> tintIndex > 0 ?
						-1 :
						ColorHelper.Argb.fullAlpha(
								stack.getOrDefault(
										DataComponentTypes.POTION_CONTENTS,
										PotionContentsComponent.DEFAULT
								).getColor()
						),
				ModItems.ALLAY_POTION_ITEM
		);
	}
}