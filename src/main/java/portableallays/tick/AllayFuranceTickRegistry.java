package portableallays.tick;

import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import portableallays.util.AllayTickProvider;
import portableallays.util.AllayTickableItemRegistry;

public class AllayFuranceTickRegistry implements AllayTickProvider {
        @Override
        public void tick(ItemStack stack, AllayEntity allay) {
            try {
                AllayTickableItemRegistry.register(Items.FURNACE, new AllayFuranceTickRegistry());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
}

