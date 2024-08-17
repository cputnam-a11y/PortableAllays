package portableallays.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.Item;

import java.util.*;

public class AllayItemTransferModifyHelperRegistry {
    private static final HashMap<Item, List<AllayItemTransferModificationProvider>> providers = new HashMap<>();
    public static void register(Item item, AllayItemTransferModificationProvider provider) {
        if (providers.containsKey(item))
            providers.get(item).add(provider);
        else {
            List<AllayItemTransferModificationProvider> list = new ArrayList<>();
            list.add(provider);
            providers.put(item, list);
        }
    }
    public static List<AllayItemTransferModificationProvider> getModificationProviders(Item item) {
        if (!providers.containsKey(item))
            return List.of();
        return List.copyOf(providers.get(item));
    }
}
