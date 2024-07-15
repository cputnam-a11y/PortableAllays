package portableallays.item;

import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import portableallays.PortableAllays;

import java.util.List;

import static portableallays.PortableAllaysConstants.ALLAY_DATA_KEY;

public class AllayItem extends Item {
    public AllayItem(Settings settings) {
        super(settings);
    }
    public AllayEntity getAllay(World world, ItemStack stack) {
        NbtCompound itemTag = stack
                .getOrDefault(
                        DataComponentTypes.CUSTOM_DATA,
                        NbtComponent.DEFAULT
                )
                .copyNbt()
                .getCompound(ALLAY_DATA_KEY);
        AllayEntity allayEntity = new AllayEntity(EntityType.ALLAY, world);
        allayEntity.readCustomDataFromNbt(itemTag);
        if (stack.get(DataComponentTypes.CUSTOM_NAME) != null) {
            allayEntity.setCustomName(stack.getName());
        }
        return allayEntity;
    }
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else if (context.getHand() != Hand.MAIN_HAND) {
            return ActionResult.FAIL;
        } else {
            ItemStack stack = context.getStack();
            NbtComponent nbtComponent = stack.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT);
            NbtCompound itemTag = nbtComponent.copyNbt();
            if (!itemTag.contains(ALLAY_DATA_KEY)) {
                return ActionResult.FAIL;
            } else {
                BlockPos blockPos = context.getBlockPos();
                Direction direction = context.getSide();
                BlockState blockState = world.getBlockState(blockPos);
                if (!blockState.getCollisionShape(world, blockPos).isEmpty()) {
                    if (direction == Direction.DOWN) {
                        blockPos = blockPos.offset(direction);
                    } else {
                        blockPos = blockPos.offset(direction);
                    }
                }

                AllayEntity allayEntity = this.getAllay(world, stack);
                allayEntity.setPosition((double)blockPos.getX() + 0.5, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5);
                allayEntity.initialize((ServerWorldAccess)world, world.getLocalDifficulty(blockPos), SpawnReason.SPAWN_EGG, (EntityData)null);

                if (context.getWorld().spawnEntity(allayEntity)) {
                    context.getStack().decrement(1);
                }

                return ActionResult.CONSUME;
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        NbtComponent nbtComponent = stack
                .getOrDefault(
                        DataComponentTypes.CUSTOM_DATA,
                        NbtComponent.DEFAULT
                );
        NbtCompound tag = nbtComponent.copyNbt();
        if (!tag.contains(ALLAY_DATA_KEY)) {
            tooltip.add(Text.translatable("tooltip.pickableallays.empty").formatted(Formatting.RED));
        } else {
            this.addItem(context.getRegistryLookup(), tag.getCompound(ALLAY_DATA_KEY), tooltip);
        }
    }
    private void addItem(RegistryWrapper.WrapperLookup lookup, NbtCompound tag, List<Text> tooltip) {
        if (tag.contains("Inventory", 9)) {
            SimpleInventory i = new SimpleInventory();
            i.readNbtList(tag.getList("Inventory", 10), lookup);
            PortableAllays.LOGGER.info("{}", i);
            for (var item : i.heldStacks) {
                if (item.getCount() == 0)
                    continue;
                tooltip.add(item.getName().copy().append(Text.literal("x " + item.getCount())));
            }
        }
    }
}
