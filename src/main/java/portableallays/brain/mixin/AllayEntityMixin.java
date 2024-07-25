package portableallays.brain.mixin;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.passive.AllayEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import portableallays.brain.memorymodule.ModMemoryModuleTypes;
import portableallays.brain.sensor.ModSensorTypes;

import java.util.ArrayList;
import java.util.List;

@Mixin(AllayEntity.class)
public class AllayEntityMixin {
    @ModifyExpressionValue(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList;of(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList;"))
    private static <E> ImmutableList<E> addCustomModSensors(ImmutableList<E> original) {
        List<E> sensorList = new ArrayList<>(original);
        // add custom sensors here
        sensorList.add((E) ModSensorTypes.COOKIE_SENSOR_SENSOR_TYPE);
        return ImmutableList.copyOf(sensorList);
    }
    @ModifyExpressionValue(method = "<clinit>", at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList;of(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList;"))
    private static <E> ImmutableList<E> addCustomModMemoryModules(ImmutableList<E> original) {
        List<E> memoryModuleList = new ArrayList<>(original);
        // add custom memory modules here
        memoryModuleList.add((E) ModMemoryModuleTypes.COOKIE);
        return ImmutableList.copyOf(memoryModuleList);
    }
}
