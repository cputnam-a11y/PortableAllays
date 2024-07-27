package portableallays.brain.sensor;

import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import portableallays.PortableAllays;
import portableallays.brain.memorymodule.ModMemoryModuleTypes;

import java.util.Set;

public class AnvilSensor extends Sensor<AllayEntity> {

    @Override
    protected void sense(ServerWorld world, AllayEntity allay) {
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
