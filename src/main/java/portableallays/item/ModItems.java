package portableallays.item;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import portableallays.PortableAllays;

public class ModItems {
    public static final Item ALLAY_ITEM =
            Registry.register(
                    Registries.ITEM,
                    Identifier.of(
                            PortableAllays.MOD_ID,
                            "allay"
                    ),
                    new AllayItem(
                            new Item.Settings()
                                    .maxCount(1)
                    )
            );
    public static void init() {

    }
}
