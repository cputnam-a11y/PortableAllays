package portableallays.item;

import net.minecraft.block.BlockState;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import portableallays.component.AllayDataComponent;
import portableallays.component.ModDataComponentTypes;

import java.util.List;

public class AllayItem extends Item {
    public AllayItem(Settings settings) {
        super(settings);
    }
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }
        if (context.getHand() != Hand.MAIN_HAND) {
            return ActionResult.FAIL;
        }
        ItemStack stack = context.getStack();
        AllayDataComponent allayData = stack.get(ModDataComponentTypes.ALLAYDATACOMPONENT);
        if (allayData == null)
            return ActionResult.FAIL;
        Direction direction = context.getSide();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        if (!blockState.getCollisionShape(world, blockPos).isEmpty())
            blockPos = blockPos.offset(direction);
        AllayEntity allay = allayData.getAllay(world, stack);
        allay.setPosition((double) blockPos.getX() + 0.5, blockPos.getY(), (double) blockPos.getZ() + 0.5);
        allay.initialize((ServerWorldAccess) world, world.getLocalDifficulty(blockPos), SpawnReason.SPAWN_EGG, null);
        if (context.getWorld().spawnEntity(allay)) {
            context.getStack().decrement(1);
        }
        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);
        AllayDataComponent allayData = stack.get(ModDataComponentTypes.ALLAYDATACOMPONENT);
        if (allayData == null)
            tooltip.add(Text.translatable("tooltip.portableallays.empty").formatted(Formatting.RED));
        else
            this.addItem(context.getRegistryLookup(), allayData, tooltip);
    }

    private void addItem(RegistryWrapper.WrapperLookup lookup, AllayDataComponent data, List<Text> tooltip) {
        NbtCompound tag = data.getAllayData();
        if (tag.contains("Inventory", NbtElement.LIST_TYPE)) {
            SimpleInventory i = new SimpleInventory();
            i.readNbtList(tag.getList("Inventory", NbtElement.COMPOUND_TYPE), lookup);
            for (ItemStack item : i.heldStacks) {
                if (item.isEmpty())
                    continue;
                tooltip.add(item.getName().copy().append(Text.literal("x " + item.getCount())));
            }
        }
        if (tag.contains("HandItems", NbtElement.LIST_TYPE)) {
            NbtList nbtList = tag.getList("HandItems", NbtElement.COMPOUND_TYPE);
            NbtCompound nbtCompound;
            for(int i = 0; i < 2; ++i) {
                nbtCompound = nbtList.getCompound(i);
                ItemStack item = ItemStack.fromNbtOrEmpty(lookup, nbtCompound);
                if (item.isEmpty())
                    continue;
                tooltip.add(item.getName().copy().append(Text.literal(" x " + item.getCount())));
            }
        }
    }
}
