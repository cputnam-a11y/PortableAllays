package portableallays.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import portableallays.screenhandler.AllayCraftingScreenHandler;
import portableallays.screenhandler.ModScreenHandlers;
import portableallays.tag.ModTags;
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
}
