package portableallays.brain;

import portableallays.brain.memorymodule.ModMemoryModuleTypes;
import portableallays.brain.sensor.ModSensorTypes;

public class ModGenericBrainThings {
    public static void init() {
        ModSensorTypes.init();
        ModMemoryModuleTypes.init();
    }
}
