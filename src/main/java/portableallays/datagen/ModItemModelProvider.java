package portableallays.datagen;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import portableallays.PortableAllays;
import portableallays.item.ModItems;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ModItemModelProvider extends FabricModelProvider {
    public ModItemModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.ALLAY_ITEM, Models.GENERATED);
        JsonObject object = new JsonObject();
        object.addProperty("parent", "minecraft:item/generated");
        JsonObject textures = new JsonObject();
        // "layer0": "item/potion_overlay",
        //        "layer1": "item/potion"
        textures.addProperty("layer0", PortableAllays.MOD_ID + ":item/allay_potion_overlay");
        textures.addProperty("layer1", PortableAllays.MOD_ID + ":item/allay_potion");
        object.add("textures", textures);
        itemModelGenerator.writer.accept(ModelIds.getItemModelId(ModItems.ALLAY_POTION_ITEM) , () -> object);
    }
    public final void register(Item item, Identifier layer0, Identifier layer1, Model model, BiConsumer<Identifier, Supplier<JsonElement>> writer) {
        model.upload(ModelIds.getItemModelId(item), TextureMap.layered(layer0, layer1), writer);
    }
    private static final Identifier of(String path) {
        return Identifier.of(PortableAllays.MOD_ID, path);
    }
}
