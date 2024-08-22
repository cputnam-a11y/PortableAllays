package portableallays.brain.task;

import net.minecraft.entity.ai.brain.MemoryModuleState;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.MultiTickTask;
import net.minecraft.entity.passive.AllayEntity;
import portableallays.brain.memorymodule.ModMemoryModuleTypes;

import java.util.Map;

public class GoToAnvilTask<E extends AllayEntity> extends MultiTickTask<E> {
    public GoToAnvilTask(Map<MemoryModuleType<?>, MemoryModuleState> requiredMemoryState) {
        super(Map.of(ModMemoryModuleTypes.ANVIL, MemoryModuleState.REGISTERED));
    }

    // Not final
}
