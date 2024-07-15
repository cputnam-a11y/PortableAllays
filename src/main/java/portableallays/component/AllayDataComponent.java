package portableallays.component;

import com.mojang.serialization.Codec;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;

public class AllayDataComponent {
    private final NbtCompound allayData;
    static Codec<AllayDataComponent> CODEC = NbtCompound.CODEC.xmap(AllayDataComponent::new, AllayDataComponent::toNbtCompound);
    static PacketCodec<PacketByteBuf, AllayDataComponent> PACKET_CODEC =
            PacketCodec.ofStatic(
                (buf, component) -> buf.writeNbt(component.getAllayData()),
                (buf) -> new AllayDataComponent(buf.readNbt())
            );
    AllayDataComponent(NbtCompound allayData) {
        this.allayData = allayData;
    }
    private static NbtCompound toNbtCompound(AllayDataComponent comp) {
        return comp.allayData.copy();
    }

    public NbtCompound getAllayData() {
        return allayData.copy();
    }
}
