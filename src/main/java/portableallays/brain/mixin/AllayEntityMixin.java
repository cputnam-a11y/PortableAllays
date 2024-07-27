package portableallays.brain.mixin;

import com.google.common.collect.ImmutableList;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.entity.passive.AllayEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import portableallays.PortableAllays;
import portableallays.brain.memorymodule.ModMemoryModuleTypes;
import portableallays.brain.sensor.ModSensorTypes;

import java.util.ArrayList;
import java.util.List;

@Mixin(AllayEntity.class)
public class AllayEntityMixin {
    @ModifyExpressionValue(method = "<clinit>", remap = false, at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList;of(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList;"))
    private static ImmutableList<SensorType<? extends Sensor<AllayEntity>>> addCustomModSensors(ImmutableList<SensorType<? extends Sensor<AllayEntity>>> original) {
        PortableAllays.LOGGER.info("Adding custom sensors");
        List<SensorType<? extends Sensor<AllayEntity>>> sensorList = new ArrayList<>(original);
        // add custom sensors here
        sensorList.add(ModSensorTypes.COOKIE_SENSOR_SENSOR_TYPE);
        sensorList.add(ModSensorTypes.ANVIL_SENSOR_SENSOR_TYPE);
        return ImmutableList.copyOf(sensorList);
    }
    @ModifyExpressionValue(method = "<clinit>", remap = false, at = @At(value = "INVOKE", target = "Lcom/google/common/collect/ImmutableList;of(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;[Ljava/lang/Object;)Lcom/google/common/collect/ImmutableList;"))
    private static ImmutableList<MemoryModuleType<?>> addCustomModMemoryModules(ImmutableList<MemoryModuleType<?>> original) {
        PortableAllays.LOGGER.info("Adding custom memory modules");
        List<MemoryModuleType<?>> memoryModuleList = new ArrayList<>(original);
        // add custom memory modules here
        memoryModuleList.add(ModMemoryModuleTypes.COOKIE);
        memoryModuleList.add(ModMemoryModuleTypes.ANVIL);
        return ImmutableList.copyOf(memoryModuleList);
    }
}
