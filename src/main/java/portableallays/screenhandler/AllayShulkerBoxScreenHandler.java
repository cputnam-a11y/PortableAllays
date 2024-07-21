package portableallays.screenhandler;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ShulkerBoxScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;

public class AllayShulkerBoxScreenHandler extends ShulkerBoxScreenHandler {
    private static final Text CONTAINER_NAME = Text.translatable("container.portableallays.shulkerbox");
    private final AllayEntity allay;
    @SuppressWarnings("unused")
    private final ItemStack shulkerBox;

    public AllayShulkerBoxScreenHandler(int syncId, PlayerInventory playerInventory, AllayEntity allay, ItemStack shulkerBox) {
        super(syncId, playerInventory, new SimpleInventory(3 * 9));
        this.allay = allay;
        this.shulkerBox = shulkerBox;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return allay.distanceTo(player) < player.getAttributes().getValue(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE);
    }

    public static void open(ServerPlayerEntity player, AllayEntity allay) {
        ItemStack shulkerBox = allay.getMainHandStack(); // Assuming Shulker Box is held in the main hand
        player.openHandledScreen(new NamedScreenHandlerFactory() {

            @Override
            public Text getDisplayName() {
                return CONTAINER_NAME;
            }

        @Nullable
        @Override
        public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
                if (!(player instanceof ServerPlayerEntity))
                    return null;
                player.incrementStat(Stats.OPEN_SHULKER_BOX);
                PiglinBrain.onGuardedBlockInteracted(player, true);
                return new AllayShulkerBoxScreenHandler(syncId, playerInventory, allay, shulkerBox);
            }
        });
    }
}