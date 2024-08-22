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
        Optional<LivingEntity> optionalTarget = allay.getBrain().getOptionalRegisteredMemory(MemoryModuleType.ATTACK_TARGET);
        if (optionalTarget.isPresent()) {
            // If we found a hostile entity in the last 10 seconds, we will not look for new hostile entities
            LivingEntity target = optionalTarget.get();
            if (allay.getBrain().getMemoryExpiry(MemoryModuleType.ATTACK_TARGET) <= 40 && target.isAlive()) {
                // If the previous target is still alive, refresh reset timer
                allay.getBrain().remember(MemoryModuleType.ATTACK_TARGET, target, 200);
                PortableAllays.LOGGER.info("Target is still alive, refreshing timer");
            } else {
                PortableAllays.LOGGER.info("Remembering attack target for {} ticks", allay.getBrain().getMemoryExpiry(MemoryModuleType.ATTACK_TARGET));
            }
            return;
        }


        super.sense(world, allay);
        findNearestTarget(allay, (entity) -> entity instanceof HostileEntity)
                .ifPresentOrElse(entity -> {
                    allay.getBrain().remember(MemoryModuleType.ATTACK_TARGET, entity, 200);
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
