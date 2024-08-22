package portableallays.brain.sensor;

import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Activity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import portableallays.PortableAllays;
import portableallays.brain.memorymodule.ModMemoryModuleTypes;

import java.util.Optional;
import java.util.Set;

public class AnvilSensor extends Sensor<AllayEntity> {
    Logger LOGGER = PortableAllays.LOGGER;

    @Override
    protected void sense(ServerWorld world, AllayEntity allay) {
        LOGGER.info(String.valueOf(allay.getBrain().hasActivity(Activity.FIGHT)));
        LOGGER.info(allay.getBrain().getPossibleActivities().toString());
        Optional<LivingEntity> optionalHostile = allay.getBrain().getOptionalRegisteredMemory(MemoryModuleType.ATTACK_TARGET);
        if (optionalHostile.isEmpty()) {
            return;  // If no hostile entity nearby, don't look for anvils.
        }

        BlockPos allayPos = allay.getBlockPos();

        int radius = 6;
        Iterable<BlockPos> blocksInRange = BlockPos.iterate(
                allayPos.add(-radius, -radius, -radius),
                allayPos.add(radius, radius, radius)
        );

        for (BlockPos blockPos : blocksInRange) {
            if (world.getBlockState(blockPos).isOf(Blocks.ANVIL)) {
                allay.getBrain().remember(ModMemoryModuleTypes.ANVIL, blockPos);
            }
        }
    }

    @Override
    public Set<MemoryModuleType<?>> getOutputMemoryModules() {
        return Set.of();
    }
}
