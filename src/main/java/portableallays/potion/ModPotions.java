package portableallays.potion;

import net.minecraft.item.Items;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import portableallays.item.ModItems;

public class ModPotions {
    static {
        BrewingRecipeRegistry.Builder.BUILD.register(builder -> {
            builder.registerPotionType(ModItems.ALLAY_POTION_ITEM);
            builder.registerItemRecipe(Items.SPLASH_POTION, Ingredient.fromTag(TagKey.of(RegistryKeys.ITEM, Identifier.of("c:music_discs"))), ModItems.ALLAY_POTION_ITEM);
        });
    }
    public static void init() {}
}
