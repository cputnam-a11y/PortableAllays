package portableallays.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import portableallays.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class DynamicRegistryProvider extends net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider {
    public DynamicRegistryProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        registries.getWrapperOrThrow(RegistryKeys.ITEM).getOrThrow(ModTags.PICKING_ITEMS);
    }

    @Override
    public String getName() {
        return "";
    }
}
