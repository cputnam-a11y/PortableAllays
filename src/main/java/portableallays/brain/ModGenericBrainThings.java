package portableallays.brain;

import portableallays.PortableAllays;
import portableallays.brain.memorymodule.ModMemoryModuleTypes;
import portableallays.brain.sensor.ModSensorTypes;

public class ModGenericBrainThings {
    public static void init() {
        PortableAllays.LOGGER.info("Initializing generic brain things");
        ModSensorTypes.init();
        ModMemoryModuleTypes.init();
    }
}
