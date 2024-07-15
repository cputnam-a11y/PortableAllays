package portableallays.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.passive.AllayEntity;

public class ModClientNetworkHandler {
    public static void sendPickup(AllayEntity allay) {
        ClientPlayNetworking.send(new AllayPickupPayload(allay.getUuid()));
    }
}
