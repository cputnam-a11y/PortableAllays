package portableallays.screenhandler;

import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.FurnaceScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;


public class AllayFurnaceScreenHandler extends FurnaceScreenHandler {
    private static final Text TITLE = Text.translatable("container.portableallays.furnace");
    final AllayEntity allay;

    public AllayFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, AllayEntity allay) {
        // needs int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate
        super(syncId, playerInventory );
        this.allay = allay;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return allay.distanceTo(player) < player.getAttributes().getValue(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE);
    }

    public static void open(ServerPlayerEntity player, AllayEntity allay) {
        player.openHandledScreen(new NamedScreenHandlerFactory() {
            @Override
            public Text getDisplayName() {
                return TITLE;
            }

            @Nullable
            @Override
            public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
                if (!(player instanceof ServerPlayerEntity))
                    return null;
                player.incrementStat(Stats.INTERACT_WITH_FURNACE);
                PiglinBrain.onGuardedBlockInteracted(player, true);
                return new AllayFurnaceScreenHandler(syncId, playerInventory, allay);
            }
        });
    }
}