package portableallays.brain.sensor;

import net.minecraft.entity.ai.brain.sensor.Sensor;
import net.minecraft.entity.ai.brain.sensor.SensorType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.Supplier;

public class ModSensorTypes {
    public static final SensorType<CookieSensor> COOKIE_SENSOR_SENSOR_TYPE = register("cookie_sensor", CookieSensor::new);
    public static final SensorType<AnvilSensor> ANVIL_SENSOR_SENSOR_TYPE = register("anvil_sensor", AnvilSensor::new);
    public static final SensorType<HostileEntitiesSensor> HOSTILE_ENTITIES_SENSOR = register("hostile_entities_sensor", HostileEntitiesSensor::new);

    @SuppressWarnings("SameParameterValue")
    private static <U extends Sensor<?>> SensorType<U> register(String id, Supplier<U> factory) {
        return Registry.register(Registries.SENSOR_TYPE, Identifier.ofVanilla(id), new SensorType<>(factory));
    }
    public static void init() {}
}
