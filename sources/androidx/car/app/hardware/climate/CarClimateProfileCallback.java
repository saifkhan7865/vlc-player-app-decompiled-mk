package androidx.car.app.hardware.climate;

import androidx.car.app.annotations.RequiresCarApi;

@RequiresCarApi(5)
public interface CarClimateProfileCallback {

    /* renamed from: androidx.car.app.hardware.climate.CarClimateProfileCallback$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onCabinTemperatureProfileAvailable(CarClimateProfileCallback _this, CabinTemperatureProfile cabinTemperatureProfile) {
        }

        public static void $default$onCarZoneMappingInfoProfileAvailable(CarClimateProfileCallback _this, CarZoneMappingInfoProfile carZoneMappingInfoProfile) {
        }

        public static void $default$onDefrosterProfileAvailable(CarClimateProfileCallback _this, DefrosterProfile defrosterProfile) {
        }

        public static void $default$onElectricDefrosterProfileAvailable(CarClimateProfileCallback _this, ElectricDefrosterProfile electricDefrosterProfile) {
        }

        public static void $default$onFanDirectionProfileAvailable(CarClimateProfileCallback _this, FanDirectionProfile fanDirectionProfile) {
        }

        public static void $default$onFanSpeedLevelProfileAvailable(CarClimateProfileCallback _this, FanSpeedLevelProfile fanSpeedLevelProfile) {
        }

        public static void $default$onHvacAcProfileAvailable(CarClimateProfileCallback _this, HvacAcProfile hvacAcProfile) {
        }

        public static void $default$onHvacAutoModeProfileAvailable(CarClimateProfileCallback _this, HvacAutoModeProfile hvacAutoModeProfile) {
        }

        public static void $default$onHvacAutoRecirculationProfileAvailable(CarClimateProfileCallback _this, HvacAutoRecirculationProfile hvacAutoRecirculationProfile) {
        }

        public static void $default$onHvacDualModeProfileAvailable(CarClimateProfileCallback _this, HvacDualModeProfile hvacDualModeProfile) {
        }

        public static void $default$onHvacMaxAcModeProfileAvailable(CarClimateProfileCallback _this, HvacMaxAcModeProfile hvacMaxAcModeProfile) {
        }

        public static void $default$onHvacPowerProfileAvailable(CarClimateProfileCallback _this, HvacPowerProfile hvacPowerProfile) {
        }

        public static void $default$onHvacRecirculationProfileAvailable(CarClimateProfileCallback _this, HvacRecirculationProfile hvacRecirculationProfile) {
        }

        public static void $default$onMaxDefrosterProfileAvailable(CarClimateProfileCallback _this, MaxDefrosterProfile maxDefrosterProfile) {
        }

        public static void $default$onSeatTemperatureLevelProfileAvailable(CarClimateProfileCallback _this, SeatTemperatureProfile seatTemperatureProfile) {
        }

        public static void $default$onSeatVentilationLevelProfileAvailable(CarClimateProfileCallback _this, SeatVentilationProfile seatVentilationProfile) {
        }

        public static void $default$onSteeringWheelHeatProfileAvailable(CarClimateProfileCallback _this, SteeringWheelHeatProfile steeringWheelHeatProfile) {
        }
    }

    void onCabinTemperatureProfileAvailable(CabinTemperatureProfile cabinTemperatureProfile);

    void onCarZoneMappingInfoProfileAvailable(CarZoneMappingInfoProfile carZoneMappingInfoProfile);

    void onDefrosterProfileAvailable(DefrosterProfile defrosterProfile);

    void onElectricDefrosterProfileAvailable(ElectricDefrosterProfile electricDefrosterProfile);

    void onFanDirectionProfileAvailable(FanDirectionProfile fanDirectionProfile);

    void onFanSpeedLevelProfileAvailable(FanSpeedLevelProfile fanSpeedLevelProfile);

    void onHvacAcProfileAvailable(HvacAcProfile hvacAcProfile);

    void onHvacAutoModeProfileAvailable(HvacAutoModeProfile hvacAutoModeProfile);

    void onHvacAutoRecirculationProfileAvailable(HvacAutoRecirculationProfile hvacAutoRecirculationProfile);

    void onHvacDualModeProfileAvailable(HvacDualModeProfile hvacDualModeProfile);

    void onHvacMaxAcModeProfileAvailable(HvacMaxAcModeProfile hvacMaxAcModeProfile);

    void onHvacPowerProfileAvailable(HvacPowerProfile hvacPowerProfile);

    void onHvacRecirculationProfileAvailable(HvacRecirculationProfile hvacRecirculationProfile);

    void onMaxDefrosterProfileAvailable(MaxDefrosterProfile maxDefrosterProfile);

    void onSeatTemperatureLevelProfileAvailable(SeatTemperatureProfile seatTemperatureProfile);

    void onSeatVentilationLevelProfileAvailable(SeatVentilationProfile seatVentilationProfile);

    void onSteeringWheelHeatProfileAvailable(SteeringWheelHeatProfile steeringWheelHeatProfile);
}
