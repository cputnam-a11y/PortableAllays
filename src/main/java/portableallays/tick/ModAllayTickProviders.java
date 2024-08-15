package portableallays.tick;

import net.minecraft.item.Items;
import portableallays.util.AllayTickableItemRegistry;

public class ModAllayTickProviders {
    static {
        AllayTickableItemRegistry.register(Items.FURNACE, ((stack, allay) -> allay.setFireTicks(20)));
    }
    public static void init() {}
}
