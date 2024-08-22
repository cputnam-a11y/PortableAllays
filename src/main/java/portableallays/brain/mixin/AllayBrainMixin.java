package portableallays.brain.mixin;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.entity.ai.brain.task.WalkTowardsPosTask;
import net.minecraft.entity.passive.AllayBrain;
import net.minecraft.entity.passive.AllayEntity;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import portableallays.PortableAllays;
import portableallays.brain.memorymodule.ModMemoryModuleTypes;

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

    @Unique
    private static void addFightActivities(Brain<AllayEntity> brain) {
        brain.setTaskList(Activity.FIGHT, 0, ImmutableList.of(WalkTowardsPosTask.create(ModMemoryModuleTypes.ANVIL, 1, 2.0f)));
    }

    @Inject(method = "create", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/AllayBrain;addIdleActivities(Lnet/minecraft/entity/ai/brain/Brain;)V"))
    private static void createCustomBrain(Brain<AllayEntity> brain, CallbackInfoReturnable<Brain<?>> cir) {
        addFightActivities(brain);
    }

    @ModifyExpressionValue(method = "updateActivities", remap = false, at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList;of(Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList;"))
    private static ImmutableList<Activity> resetCustomActivities(ImmutableList<Activity> original) {
        ArrayList<Activity> mutList = new ArrayList<>(original);
        mutList.add(Activity.FIGHT);
        return ImmutableList.copyOf(mutList);
    }
}
