package portableallays.screenhandler;

import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;

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
