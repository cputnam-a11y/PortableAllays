package portableallays;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import portableallays.datagen.ModEnglishLangProvider;
import portableallays.datagen.ModItemModelProvider;
import portableallays.datagen.ModItemTagProvider;

public class PortableAllaysDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
		pack.addProvider(ModItemTagProvider::new);
		pack.addProvider(ModItemModelProvider::new);
		pack.addProvider(ModEnglishLangProvider::new);
	}
}
