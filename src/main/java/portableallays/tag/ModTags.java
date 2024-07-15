package portableallays.tag;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import portableallays.PortableAllays;

public class ModTags {
    public static final TagKey<Item> PICKING_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of(PortableAllays.MOD_ID, "picking_items"));
}
