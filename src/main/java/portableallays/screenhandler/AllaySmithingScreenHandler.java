package portableallays.screenhandler;

import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SmithingScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class AllaySmithingScreenHandler extends SmithingScreenHandler {
    private static final Text CONTAINER_NAME = Text.translatable("container.portableallays.upgrade");
    private final AllayEntity allay;
    public AllaySmithingScreenHandler(int syncId, PlayerInventory playerInventory, ServerPlayerEntity player, AllayEntity allay) {
        super(syncId, playerInventory, ScreenHandlerContext.create(allay.getWorld(), allay.getBlockPos()));
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
                return CONTAINER_NAME;
            }

            @Nullable
            @Override
            public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
                if (!(player instanceof ServerPlayerEntity))
                    return null;
                player.incrementStat(Stats.INTERACT_WITH_SMITHING_TABLE);
                PiglinBrain.onGuardedBlockInteracted(player, true);
                return new AllaySmithingScreenHandler(syncId, playerInventory, (ServerPlayerEntity) player, allay);
            }
        });
    }
}
