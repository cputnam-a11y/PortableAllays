package portableallays.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.item.ItemStack;

public class AllayEvents {
    public static final Event<ItemGivenCallback> ITEM_GIVEN = EventFactory.createArrayBacked(ItemGivenCallback.class, (callbacks) -> (stack) -> {
        for (ItemGivenCallback callback : callbacks)
            callback.onItemGiven(stack);
    });
    public interface ItemGivenCallback {
        void onItemGiven(ItemStack stack);
    }
}
