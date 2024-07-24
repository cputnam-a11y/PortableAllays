package portableallays.util;

import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.item.ItemStack;
@FunctionalInterface
public interface AllayTickProvider {
    void tick(ItemStack stack, AllayEntity allay);
}
