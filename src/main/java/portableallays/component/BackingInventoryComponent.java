package portableallays.component;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;

// a DataComponent to store the current backing inventory
public record BackingInventoryComponent(Inventory backingInventory) implements Inventory {
    public static BackingInventoryComponent of(Inventory backingInventory) {
        return new BackingInventoryComponent(backingInventory);
    }
    public static BackingInventoryComponent getOrDefault(ItemStack stack, Supplier<Inventory> deferredBackingInventory) {
        BackingInventoryComponent comp = stack.get(ModDataComponentTypes.BACKING_INVENTORY_COMPONENT);
        if (comp != null && comp.backingInventory != null)
            return comp;
        return new BackingInventoryComponent(Objects.requireNonNull(deferredBackingInventory.get()));
    }
    public static BackingInventoryComponent DEFAULT = new BackingInventoryComponent(null);
    // we delegate everything from inventory to the backingInventory
    @Override
    public void clear() {
        backingInventory.clear();
    }

    @Override
    public int size() {
        return backingInventory.size();
    }

    @Override
    public boolean isEmpty() {
        return backingInventory.isEmpty();
    }

    @Override
    public ItemStack getStack(int slot) {
        return backingInventory.getStack(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        return backingInventory.removeStack(slot, amount);
    }

    @Override
    public ItemStack removeStack(int slot) {
        return backingInventory.removeStack(slot);
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        backingInventory.setStack(slot, stack);
    }

    @Override
    public int getMaxCountPerStack() {
        return backingInventory.getMaxCountPerStack();
    }

    @Override
    public int getMaxCount(ItemStack stack) {
        return backingInventory.getMaxCount(stack);
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return backingInventory.canPlayerUse(player);
    }

    @Override
    public void onOpen(PlayerEntity player) {
        backingInventory.onOpen(player);
    }

    @Override
    public void onClose(PlayerEntity player) {
        Inventory.super.onClose(player);
    }

    @Override
    public boolean isValid(int slot, ItemStack stack) {
        return backingInventory.isValid(slot, stack);
    }

    @Override
    public boolean canTransferTo(Inventory hopperInventory, int slot, ItemStack stack) {
        return backingInventory.canTransferTo(hopperInventory, slot, stack);
    }

    @Override
    public int count(Item item) {
        return backingInventory.count(item);
    }

    @Override
    public boolean containsAny(Set<Item> items) {
        return backingInventory.containsAny(items);
    }

    @Override
    public boolean containsAny(Predicate<ItemStack> predicate) {
        return backingInventory.containsAny(predicate);
    }
}
