package portableallays.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.passive.AllayEntity;
import portableallays.util.PickupHandler;

public class ModNetworkHandler {
    static {
        PayloadTypeRegistry.playC2S().register(AllayPickupPayload.getID(), AllayPickupPayload.getCodec());
        ServerPlayNetworking.registerGlobalReceiver(
                AllayPickupPayload.getID(),
                (payload, context) ->
                    context
                        .player()
                        .getWorld()
                        .getEntitiesByClass(
                                AllayEntity.class,
                                context
                                        .player()
                                        .getBoundingBox()
                                        .expand(5.0),
                                (allay) -> allay
                                        .getUuid()
                                        .equals(payload.uuid())
                        )
                        .stream()
                        .findAny()
                        .ifPresent(
                                (allay) -> PickupHandler.handlePickup(context.player(), allay)
                        )
        );
    }
    public static void init() {

    }
}
