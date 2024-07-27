package portableallays.brain.sensor;

import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import portableallays.PortableAllays;
import portableallays.brain.memorymodule.ModMemoryModuleTypes;

import java.util.Set;

public class CookieSensor extends Sensor<AllayEntity> {


    @Override
    protected void sense(ServerWorld world, AllayEntity entity) {
        entity.getBrain().remember(ModMemoryModuleTypes.COOKIE, entity.getMainHandStack().isOf(Items.COOKIE));
    }

    @Override
    public Set<MemoryModuleType<?>> getOutputMemoryModules() {
        return Set.of();
    }
}
