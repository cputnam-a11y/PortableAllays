package portableallays.brain.memorymodule;

import com.mojang.serialization.Codec;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import portableallays.PortableAllays;

import java.util.Optional;

public class ModMemoryModuleTypes {
    public static final MemoryModuleType<Boolean> COOKIE = register("cookie", Codec.BOOL);
    public static void init() {}
    private static <U> MemoryModuleType<U> register(String id) {
        return  Registry.register(Registries.MEMORY_MODULE_TYPE, Identifier.of(PortableAllays.MOD_ID, id), new MemoryModuleType<>(Optional.empty()));
    }
    @SuppressWarnings("SameParameterValue")
    private static <U> MemoryModuleType<U> register(String id, Codec<U> codec) {
        return Registry.register(Registries.MEMORY_MODULE_TYPE, Identifier.of(PortableAllays.MOD_ID, id), new MemoryModuleType<>(Optional.of(codec)));
    }
}
