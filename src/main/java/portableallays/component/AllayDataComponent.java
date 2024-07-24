package portableallays.component;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.world.World;
import portableallays.item.AllayItem;

public class AllayDataComponent {
    private final NbtCompound allayData;
    static final Codec<AllayDataComponent> CODEC = NbtCompound.CODEC.xmap(AllayDataComponent::new, AllayDataComponent::getAllayData);
    static final PacketCodec<ByteBuf, AllayDataComponent> PACKET_CODEC = PacketCodecs.codec(CODEC);

    AllayDataComponent(NbtCompound allayData) {
        this.allayData = allayData;
    }

    public static AllayDataComponent fromAllay(AllayEntity allay) {
        NbtCompound allayNbt = new NbtCompound();
        allay.writeCustomDataToNbt(allayNbt);
        return new AllayDataComponent(allayNbt);
    }

    public AllayEntity getAllay(World world, ItemStack allayStack) {
        assert allayStack.getItem() instanceof AllayItem;
        AllayEntity allay = new AllayEntity(EntityType.ALLAY, world);
        allay.readCustomDataFromNbt(this.getAllayData());
        if (allayStack.get(DataComponentTypes.CUSTOM_NAME) != null)
            allay.setCustomName(allayStack.getName());
        return allay;
    }

    public NbtCompound getAllayData() {
        return allayData.copy();
    }
}
