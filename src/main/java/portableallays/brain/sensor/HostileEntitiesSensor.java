package portableallays.brain.sensor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.NearestLivingEntitiesSensor;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.server.world.ServerWorld;
import portableallays.PortableAllays;
import portableallays.brain.memorymodule.ModMemoryModuleTypes;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class HostileEntitiesSensor extends NearestLivingEntitiesSensor<AllayEntity> {
    protected void sense(ServerWorld world, AllayEntity allay) {
        super.sense(world, allay);
        findNearestTarget(allay, (entity) -> entity instanceof HostileEntity)
                .ifPresentOrElse(entity -> {
                    allay.getBrain().remember(MemoryModuleType.ATTACK_TARGET, entity);
                    allay.getBrain().remember(ModMemoryModuleTypes.CHECK_HOSTILES_COOLDOWN_TICKS, 200);
                    PortableAllays.LOGGER.info("Detected hostile entity: {}", entity);
                }, () -> {
                    allay.getBrain().forget(MemoryModuleType.ATTACK_TARGET);
                });
    }

    private static Optional<LivingEntity> findNearestTarget(AllayEntity allay, Predicate<LivingEntity> predicate) {
        Stream<LivingEntity> var10000 = allay.getBrain().getOptionalRegisteredMemory(MemoryModuleType.MOBS).stream().flatMap(Collection::stream);
        Objects.requireNonNull(allay);
        return var10000.filter(predicate).findFirst();
    }
}
