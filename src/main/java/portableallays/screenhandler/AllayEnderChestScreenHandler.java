package portableallays.screenhandler;

import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;

public class AllayEnderChestScreenHandler {
    private static final Text CONTAINER_NAME = Text.translatable("container.portableallays.enderchest");
    public static void open(ServerPlayerEntity player, AllayEntity allay) {
        EnderChestInventory enderChestInventory = player.getEnderChestInventory();
        player.openHandledScreen(new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> GenericContainerScreenHandler.createGeneric9x3(i, playerInventory, enderChestInventory), CONTAINER_NAME));
        player.incrementStat(Stats.OPEN_ENDERCHEST);
        PiglinBrain.onGuardedBlockInteracted(player, true);
    }
}
