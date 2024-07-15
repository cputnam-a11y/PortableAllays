package portableallays.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import portableallays.PortableAllays;

import java.util.UUID;

public record AllayPickupPayload(UUID uuid) implements CustomPayload {
    static Id<AllayPickupPayload> ID = new Id<>(Identifier.of(PortableAllays.MOD_ID, "allay_pickup"));
    static PacketCodec<PacketByteBuf, AllayPickupPayload> CODEC = PacketCodec.ofStatic(AllayPickupPayload::write, AllayPickupPayload::_new);
    private static AllayPickupPayload _new(PacketByteBuf buf) {
        return new AllayPickupPayload(buf.readUuid());
    }
    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

    public static Id<AllayPickupPayload> getID() {
        return ID;
    }

    public static PacketCodec<PacketByteBuf, AllayPickupPayload> getCodec() {
        return CODEC;
    }

    private static void write(PacketByteBuf buf, AllayPickupPayload payload) {
        buf.writeUuid(payload.uuid());
    }
}
