package portableallays.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import portableallays.PortableAllays;

public class ModItems {
    public static final Item ALLAY_ITEM =
            registerItem(
                    new AllayItem(
                            new Item.Settings()
                                    .maxCount(1)
                    ),
                    "allay"

            );
    public static final Item ALLAY_POTION_ITEM = registerItem(new AllayPotionItem(), "allay_potion");
    @SuppressWarnings("EmptyMethod")
    public static void init() {

    }
    private static Item registerItem(Item item, String name) {
        return Registry.register(Registries.ITEM, Identifier.of(PortableAllays.MOD_ID,  name), item);
    }
}
