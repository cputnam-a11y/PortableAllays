package portableallays.tick;

import net.minecraft.item.Items;
import portableallays.util.AllayTickableItemRegistry;

public class ModAllayTickProviders {
    static {
        AllayTickableItemRegistry.register(Items.FURNACE, ((stack, allay) -> {
        }));
    }
    public static void init() {}
}
