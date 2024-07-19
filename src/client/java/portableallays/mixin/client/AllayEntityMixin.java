package portableallays.mixin.client;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import portableallays.network.ModClientNetworkHandler;
import portableallays.tag.ModTags;
@SuppressWarnings("unused")
@Mixin(AllayEntity.class)
public class AllayEntityMixin extends PathAwareEntity {

    protected AllayEntityMixin(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }
    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void onRightClickClient(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if (player.isSneaking()
                && player.getStackInHand(Hand.MAIN_HAND).isIn(ModTags.PICKING_ITEMS)
                && this.getWorld().isClient) {
            ModClientNetworkHandler.sendPickup((AllayEntity) (Object) this);
            cir.setReturnValue(ActionResult.SUCCESS);
        }
    }
}
