package portableallays.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import portableallays.screenhandler.ModScreenHandlers;
import portableallays.tag.ModTags;
import portableallays.util.AllayItemTransferModificationProvider;
import portableallays.util.AllayItemTransferModifyHelperRegistry;
import portableallays.util.AllayTickableItemRegistry;

@SuppressWarnings("unused")
@Mixin(AllayEntity.class)
public abstract class AllayEntityMixin extends PathAwareEntity {
    protected AllayEntityMixin(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void handleAllayPickupOnRightClick(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (player.isSneaking() && player.getStackInHand(Hand.MAIN_HAND).isIn(ModTags.PICKING_ITEMS) && !this.getWorld().isClient) {
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void handleCraftingOnRightClick(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack mainHandStack = this.getMainHandStack();
        if (!mainHandStack.isIn(ModTags.ALLAY_VALID_CRAFTING_ITEMS) || player.isSneaking()) {
            return;
        }
        if (!player.getWorld().isClient)
            ModScreenHandlers.openCrafting(mainHandStack, (ServerPlayerEntity) player, (AllayEntity) (Object) this);
        cir.setReturnValue(ActionResult.SUCCESS);
    }
    @Inject(method = "tick", at = @At("TAIL"))
    private void onItemTick(CallbackInfo ci) {
        AllayTickableItemRegistry
                .getProvider(this.getMainHandStack().getItem())
                .ifPresent(
                        provider -> provider.tick(
                                this.getMainHandStack(),
                                (AllayEntity) (Object) this
                        )
                );
    }
    @ModifyArg(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;giveItemStack(Lnet/minecraft/item/ItemStack;)Z"))
    private ItemStack onStackTakenFromAllay(ItemStack stack) {
        for (AllayItemTransferModificationProvider modifier : AllayItemTransferModifyHelperRegistry.getModificationProviders(stack.getItem())) {
            modifier.onItemTaken(stack);
        }
        return stack;
    }
    @ModifyArg(method = "interactMob", slice = @Slice(from = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;copyWithCount(I)Lnet/minecraft/item/ItemStack;")), at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/AllayEntity;setStackInHand(Lnet/minecraft/util/Hand;Lnet/minecraft/item/ItemStack;)V", ordinal = 0))
    private ItemStack onAllayGiveStack(ItemStack stack) {
        for (AllayItemTransferModificationProvider modifier : AllayItemTransferModifyHelperRegistry.getModificationProviders(stack.getItem())) {
            modifier.onItemGiven(stack);
        }
        return stack;
    }
}
