package portableallays.component;

import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import portableallays.PortableAllays;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final ComponentType<AllayDataComponent> ALLAYDATACOMPONENT =
            register(
                    Identifier.of(PortableAllays.MOD_ID, "allay_data"),
                (builder) -> builder
                        .codec(AllayDataComponent.CODEC)
                        .packetCodec(AllayDataComponent.PACKET_CODEC)
            );
    private static <T> ComponentType<T> register(Identifier id, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return  Registry.register(Registries.DATA_COMPONENT_TYPE, id, (builderOperator.apply(ComponentType.builder())).build());
    }
    public static void init() {

    }
}
