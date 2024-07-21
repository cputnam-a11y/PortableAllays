package portableallays.screenhandler;

import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;

public class ModScreenHandlers {
    public static void openCrafting(ItemStack craftingStack, ServerPlayerEntity player, AllayEntity allay) {
        if (craftingStack.isOf(Items.CRAFTING_TABLE))
            AllayCraftingScreenHandler.open(player, allay);
        if (craftingStack.isOf(Items.STONECUTTER))
            AllayStonecutterScreenHandler.open(player, allay);
        if (craftingStack.isOf(Items.ENDER_CHEST))
            AllayEnderChestScreenHandler.open(player, allay);
    }
}
