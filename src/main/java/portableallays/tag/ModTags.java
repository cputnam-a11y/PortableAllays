package portableallays.tag;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import portableallays.PortableAllays;

public class ModTags {
    // a tag containing all the items that can be used to turn an allay into a scroll
    // this is safe to modify without reviewing the rest of the code
    public static final TagKey<Item> PICKING_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of(PortableAllays.MOD_ID, "picking_items"));
    // this tag is not safe to add items to...
    public static final TagKey<Item> ALLAY_VALID_CRAFTING_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of(PortableAllays.MOD_ID, "allay_valid_crafting_items"));
}
