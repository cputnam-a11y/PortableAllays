package portableallays.util;

import net.minecraft.item.ItemStack;

public interface AllayItemTransferModificationProvider {
    default void onItemGiven(ItemStack stack) {}
    default void onItemTaken(ItemStack stack) {}
}
