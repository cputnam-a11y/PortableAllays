package portableallays.screenhandler;

import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.StonecutterScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class AllayStonecutterScreenHandler extends StonecutterScreenHandler {
    private static final Text TITLE = Text.translatable("container.portableallays.stonecutter");
    final AllayEntity allay;
    public AllayStonecutterScreenHandler(int syncId, PlayerInventory playerInventory, AllayEntity allay) {
        super(syncId, playerInventory, ScreenHandlerContext.create(allay.getWorld(), allay.getBlockPos()) );
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
                return new AllayStonecutterScreenHandler(syncId, playerInventory, allay);
            }
        });
    }
}
