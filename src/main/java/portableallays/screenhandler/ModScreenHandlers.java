package portableallays.screenhandler;

import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import portableallays.tag.ModTags;

public class ModScreenHandlers {
    public static void openCrafting(ItemStack craftingStack, ServerPlayerEntity player, AllayEntity allay) {
        if (craftingStack.isOf(Items.CRAFTING_TABLE))
            AllayCraftingScreenHandler.open(player, allay);
        if (craftingStack.isOf(Items.STONECUTTER))
            AllayStonecutterScreenHandler.open(player, allay);
        if (craftingStack.isOf(Items.ENDER_CHEST))
            AllayEnderChestScreenHandler.open(player, allay);
        if (craftingStack.isIn(ModTags.SHULKER_BOXES))
            AllayShulkerBoxScreenHandler.open(player, allay);
        if (craftingStack.isOf(Items.SMITHING_TABLE))
            AllaySmithingScreenHandler.open(player, allay);
    }
}
