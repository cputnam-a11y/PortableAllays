package portableallays.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.potion.Potion;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AllayPotionItem extends Item {
    public AllayPotionItem() {
        super(new Settings().maxCount(1).maxDamage(5));
    }

    @Override
    public String getTranslationKey(ItemStack stack) {
        return Potion.finishTranslationKey(stack.getOrDefault(DataComponentTypes.POTION_CONTENTS, PotionContentsComponent.DEFAULT).potion(), this.getTranslationKey() + ".effect.");
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) {
            PotionEntity potionEntity = new PotionEntity(world, user);
            potionEntity.setItem(itemStack);
            potionEntity.setVelocity(user, user.getPitch(), user.getYaw(), -20.0F, 0.5F, 1.0F);
            world.spawnEntity(potionEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
//        itemStack.decrementUnlessCreative(1, user);
        itemStack.damage(1, user, LivingEntity.getSlotForHand(hand));
        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        PotionContentsComponent potionContentsComponent = (PotionContentsComponent) stack.get(DataComponentTypes.POTION_CONTENTS);
        if (potionContentsComponent != null) {
            Objects.requireNonNull(tooltip);
            potionContentsComponent.buildTooltip(tooltip::add, 1.0F, context.getUpdateTickRate());
        }
    }

    public <T> T getRegistryEntryValue(RegistryEntry<T> entry) {
        return switch (entry) {
            case RegistryEntry.Direct<T> entry1 -> entry1.value();

            case RegistryBuilder.LazyReferenceEntry<T> entry1 -> entry1.value();
            case RegistryEntry.Reference<T> entry1 -> entry1.value();
            default -> throw new IllegalStateException("Unexpected value: " + entry);
        };
    }
}
