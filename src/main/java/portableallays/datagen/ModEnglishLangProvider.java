package portableallays.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import portableallays.PortableAllays;
import portableallays.item.ModItems;

import java.util.HashSet;
import java.util.concurrent.CompletableFuture;

public class ModEnglishLangProvider extends FabricLanguageProvider {
    public ModEnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    /**
     * Implement this method to register languages.
     *
     * <p>Call {@link TranslationBuilder#add(String, String)} to add a translation.
     */
    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add(ModItems.ALLAY_ITEM, "Allay");
        translationBuilder.add(makeArbitrary("message", "dead"), "You Cannot Pickup a Dead Allay");
        translationBuilder.add(makeArbitrary("tooltip", "empty"), "Invalid item! Use spawn egg instead.");
        translationBuilder.add(makeArbitrary("container", "crafting"), "Crafting");
        translationBuilder.add(makeArbitrary("container", "stonecutter"), "Stonecutter");
        translationBuilder.add(makeArbitrary("container", "enderchest"), "Ender Chest");
        HashSet<String> Visited = new HashSet<>();
        registryLookup.getOptionalWrapper(RegistryKeys.POTION).ifPresent((R) -> R.streamKeys().forEach((key) -> {
            ItemStack stack = new ItemStack(ModItems.ALLAY_POTION_ITEM);
            RegistryEntry<Potion> entry = R.getOrThrow(key);
            stack.set(DataComponentTypes.POTION_CONTENTS, new PotionContentsComponent(R.getOrThrow(key)));
            String translationKey = stack.getTranslationKey();
            if (!Visited.contains(translationKey)) {
                Visited.add(translationKey);
                String string = Text.translatable(PotionContentsComponent.createStack(Items.POTION, entry).getTranslationKey()).getString();
                translationBuilder.add(stack.getTranslationKey(), "Allay " + string);
            }
        }));
    }

    private String makeArbitrary(String category, String key) {
        return category + "." + PortableAllays.MOD_ID + "." + key;
    }
}
