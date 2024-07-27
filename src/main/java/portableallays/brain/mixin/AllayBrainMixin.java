package portableallays.brain.mixin;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.passive.AllayBrain;
import net.minecraft.entity.passive.AllayEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;

@Mixin(AllayBrain.class)
public class AllayBrainMixin {
    @ModifyExpressionValue(method = "addCoreActivities", remap = false, at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList;of(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList;"))
    private static ImmutableList<Task<AllayEntity>> addCustomCoreActivities(ImmutableList<Task<AllayEntity>> original) {
        ArrayList<Task<AllayEntity>> mutList = new ArrayList<>(original);
        // add custom core activities here
        return ImmutableList.copyOf(mutList);
    }
    @ModifyExpressionValue(method = "addIdleActivities", remap = false, at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList;of(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList;"))
    private static ImmutableList<Task<AllayEntity>> addCustomIdleActivities(ImmutableList<Task<AllayEntity>> original) {
        ArrayList<Task<AllayEntity>> mutList = new ArrayList<>(original);
        // add custom idle activities here
        return ImmutableList.copyOf(mutList);
    }
}
