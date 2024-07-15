package portableallays.util;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import portableallays.item.ModItems;

import static portableallays.PortableAllaysConstants.ALLAY_DATA_KEY;

public class PickupHandler {
    public static void handlePickup(PlayerEntity player, AllayEntity allay) {
        pickupAllay(allay, player);
    }
    public static void pickupAllay(AllayEntity allayEntity, LivingEntity entity) {
        PlayerEntity player = (PlayerEntity)entity;
        if (isAllayPickable(allayEntity, player)) {
            ItemStack stack = new ItemStack(ModItems.ALLAY_ITEM);
            stack.apply(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT, (nbtComponent -> {
                NbtCompound nbt = nbtComponent.copyNbt();
                NbtCompound tag = nbt.getCompound(ALLAY_DATA_KEY);
                allayEntity.writeCustomDataToNbt(tag);
                nbt.put(ALLAY_DATA_KEY, tag);
                return NbtComponent.of(nbt);
            }));
            if (allayEntity.hasCustomName()) {
                stack.set(DataComponentTypes.CUSTOM_NAME, allayEntity.getCustomName());
            }
            player.getStackInHand(Hand.MAIN_HAND).decrementUnlessCreative(1, player);
            if (!player.giveItemStack(stack)) {
                player.dropItem(stack, true);
            }
                allayEntity.remove(Entity.RemovalReason.DISCARDED);
                allayEntity.playSound(SoundEvents.ENTITY_ALLAY_ITEM_GIVEN, 1.0F, allayEntity.getSoundPitch());
        }
    }
    private static boolean isAllayPickable(AllayEntity allayEntity, PlayerEntity player) {
        if (!allayEntity.isAlive()) {
            player.sendMessage(Text.translatable("message.pickableallays.dead"), true);
            return false;
        }
        return true;
    }
}
