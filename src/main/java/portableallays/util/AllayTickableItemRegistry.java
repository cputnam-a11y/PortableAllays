package portableallays.util;

import net.minecraft.item.Item;
import net.minecraft.registry.Registry;

import java.util.HashMap;
import java.util.Optional;

public class AllayTickableItemRegistry {
    private static HashMap<Item, AllayTickProvider> providers = new HashMap<>();
    public static void register(Item item, AllayTickProvider provider) {
        providers.put(item, provider);
    }
    public static Optional<AllayTickProvider> getProvider(Item item) {
        return Optional.ofNullable(providers.get(item));
    }
}
