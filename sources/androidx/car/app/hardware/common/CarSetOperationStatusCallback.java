package androidx.car.app.hardware.common;

import androidx.car.app.annotations.RequiresCarApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@RequiresCarApi(5)
public interface CarSetOperationStatusCallback {
    public static final int OPERATION_STATUS_FEATURE_SETTING_NOT_ALLOWED = 4;
    public static final int OPERATION_STATUS_FEATURE_TEMPORARILY_UNAVAILABLE = 3;
    public static final int OPERATION_STATUS_FEATURE_UNIMPLEMENTED = 1;
    public static final int OPERATION_STATUS_FEATURE_UNSUPPORTED = 2;
    public static final int OPERATION_STATUS_ILLEGAL_CAR_HARDWARE_STATE = 7;
    public static final int OPERATION_STATUS_INSUFFICIENT_PERMISSION = 6;
    public static final int OPERATION_STATUS_SUCCESS = 0;
    public static final int OPERATION_STATUS_UNSUPPORTED_VALUE = 5;
    public static final int OPERATION_STATUS_UPDATE_TIMEOUT = 8;

    @Retention(RetentionPolicy.SOURCE)
    public @interface StatusCode {
    }

    void onSetCarClimateStateCabinTemperature(int i);

    void onSetCarClimateStateDefroster(int i);

    void onSetCarClimateStateElectricDefroster(int i);

    void onSetCarClimateStateFanDirection(int i);

    void onSetCarClimateStateFanSpeedLevel(int i);

    void onSetCarClimateStateHvacAc(int i);

    void onSetCarClimateStateHvacAutoMode(int i);

    void onSetCarClimateStateHvacAutoRecirculation(int i);

    void onSetCarClimateStateHvacDualMode(int i);

    void onSetCarClimateStateHvacMaxAcMode(int i);

    void onSetCarClimateStateHvacPower(int i);

    void onSetCarClimateStateHvacRecirculation(int i);

    void onSetCarClimateStateMaxDefroster(int i);

    void onSetCarClimateStateSeatTemperatureLevel(int i);

    void onSetCarClimateStateSeatVentilationLevel(int i);

    void onSetCarClimateStateSteeringWheelHeat(int i);

    /* renamed from: androidx.car.app.hardware.common.CarSetOperationStatusCallback$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onSetCarClimateStateCabinTemperature(CarSetOperationStatusCallback _this, int i) {
        }

        public static void $default$onSetCarClimateStateDefroster(CarSetOperationStatusCallback _this, int i) {
        }

        public static void $default$onSetCarClimateStateElectricDefroster(CarSetOperationStatusCallback _this, int i) {
        }

        public static void $default$onSetCarClimateStateFanDirection(CarSetOperationStatusCallback _this, int i) {
        }

        public static void $default$onSetCarClimateStateFanSpeedLevel(CarSetOperationStatusCallback _this, int i) {
        }

        public static void $default$onSetCarClimateStateHvacAc(CarSetOperationStatusCallback _this, int i) {
        }

        public static void $default$onSetCarClimateStateHvacAutoMode(CarSetOperationStatusCallback _this, int i) {
        }

        public static void $default$onSetCarClimateStateHvacAutoRecirculation(CarSetOperationStatusCallback _this, int i) {
        }

        public static void $default$onSetCarClimateStateHvacDualMode(CarSetOperationStatusCallback _this, int i) {
        }

        public static void $default$onSetCarClimateStateHvacMaxAcMode(CarSetOperationStatusCallback _this, int i) {
        }

        public static void $default$onSetCarClimateStateHvacPower(CarSetOperationStatusCallback _this, int i) {
        }

        public static void $default$onSetCarClimateStateHvacRecirculation(CarSetOperationStatusCallback _this, int i) {
        }

        public static void $default$onSetCarClimateStateMaxDefroster(CarSetOperationStatusCallback _this, int i) {
        }

        public static void $default$onSetCarClimateStateSeatTemperatureLevel(CarSetOperationStatusCallback _this, int i) {
        }

        public static void $default$onSetCarClimateStateSeatVentilationLevel(CarSetOperationStatusCallback _this, int i) {
        }

        public static void $default$onSetCarClimateStateSteeringWheelHeat(CarSetOperationStatusCallback _this, int i) {
        }

        public static String toString(int i) {
            switch (i) {
                case 0:
                    return "OPERATION_STATUS_SUCCESS";
                case 1:
                    return "OPERATION_STATUS_FEATURE_UNIMPLEMENTED";
                case 2:
                    return "OPERATION_STATUS_FEATURE_UNSUPPORTED";
                case 3:
                    return "OPERATION_STATUS_FEATURE_TEMPORARILY_UNAVAILABLE";
                case 4:
                    return "OPERATION_STATUS_FEATURE_SETTING_NOT_ALLOWED";
                case 5:
                    return "OPERATION_STATUS_UNSUPPORTED_VALUE";
                case 6:
                    return "OPERATION_STATUS_INSUFFICIENT_PERMISSION";
                case 7:
                    return "OPERATION_STATUS_ILLEGAL_CAR_HARDWARE_STATE";
                case 8:
                    return "OPERATION_STATUS_UPDATE_TIMEOUT";
                default:
                    throw new IllegalArgumentException("Invalid status code");
            }
        }
    }
}
