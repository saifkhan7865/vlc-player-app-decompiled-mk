package androidx.car.app.hardware.climate;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarValue;

@RequiresCarApi(5)
public interface CarClimateStateCallback {

    /* renamed from: androidx.car.app.hardware.climate.CarClimateStateCallback$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onCabinTemperatureStateAvailable(CarClimateStateCallback _this, CarValue carValue) {
        }

        public static void $default$onDefrosterStateAvailable(CarClimateStateCallback _this, CarValue carValue) {
        }

        public static void $default$onElectricDefrosterStateAvailable(CarClimateStateCallback _this, CarValue carValue) {
        }

        public static void $default$onFanDirectionStateAvailable(CarClimateStateCallback _this, CarValue carValue) {
        }

        public static void $default$onFanSpeedLevelStateAvailable(CarClimateStateCallback _this, CarValue carValue) {
        }

        public static void $default$onHvacAcStateAvailable(CarClimateStateCallback _this, CarValue carValue) {
        }

        public static void $default$onHvacAutoModeStateAvailable(CarClimateStateCallback _this, CarValue carValue) {
        }

        public static void $default$onHvacAutoRecirculationStateAvailable(CarClimateStateCallback _this, CarValue carValue) {
        }

        public static void $default$onHvacDualModeStateAvailable(CarClimateStateCallback _this, CarValue carValue) {
        }

        public static void $default$onHvacMaxAcModeStateAvailable(CarClimateStateCallback _this, CarValue carValue) {
        }

        public static void $default$onHvacPowerStateAvailable(CarClimateStateCallback _this, CarValue carValue) {
        }

        public static void $default$onHvacRecirculationStateAvailable(CarClimateStateCallback _this, CarValue carValue) {
        }

        public static void $default$onMaxDefrosterStateAvailable(CarClimateStateCallback _this, CarValue carValue) {
        }

        public static void $default$onSeatTemperatureLevelStateAvailable(CarClimateStateCallback _this, CarValue carValue) {
        }

        public static void $default$onSeatVentilationLevelStateAvailable(CarClimateStateCallback _this, CarValue carValue) {
        }

        public static void $default$onSteeringWheelHeatStateAvailable(CarClimateStateCallback _this, CarValue carValue) {
        }
    }

    void onCabinTemperatureStateAvailable(CarValue<Float> carValue);

    void onDefrosterStateAvailable(CarValue<Boolean> carValue);

    void onElectricDefrosterStateAvailable(CarValue<Boolean> carValue);

    void onFanDirectionStateAvailable(CarValue<Integer> carValue);

    void onFanSpeedLevelStateAvailable(CarValue<Integer> carValue);

    void onHvacAcStateAvailable(CarValue<Boolean> carValue);

    void onHvacAutoModeStateAvailable(CarValue<Boolean> carValue);

    void onHvacAutoRecirculationStateAvailable(CarValue<Boolean> carValue);

    void onHvacDualModeStateAvailable(CarValue<Boolean> carValue);

    void onHvacMaxAcModeStateAvailable(CarValue<Boolean> carValue);

    void onHvacPowerStateAvailable(CarValue<Boolean> carValue);

    void onHvacRecirculationStateAvailable(CarValue<Boolean> carValue);

    void onMaxDefrosterStateAvailable(CarValue<Boolean> carValue);

    void onSeatTemperatureLevelStateAvailable(CarValue<Integer> carValue);

    void onSeatVentilationLevelStateAvailable(CarValue<Integer> carValue);

    void onSteeringWheelHeatStateAvailable(CarValue<Boolean> carValue);
}
