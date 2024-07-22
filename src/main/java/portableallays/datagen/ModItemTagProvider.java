package portableallays.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import portableallays.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.PICKING_ITEMS)
                .add(
                        Items.ANVIL,
                        Items.PAPER
                );
        getOrCreateTagBuilder(ModTags.ALLAY_VALID_CRAFTING_ITEMS)
                .add(
                        Items.CRAFTING_TABLE,
                        Items.STONECUTTER,
                        Items.ENDER_CHEST,
                        Items.FURNACE
                        
                )
                .addOptionalTag(
                        ModTags.SHULKER_BOXES
                );
    }
}
