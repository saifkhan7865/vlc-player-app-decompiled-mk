package androidx.car.app.hardware.info;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.OnCarDataAvailableListener;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.Executor;

@RequiresCarApi(3)
public interface CarSensors {
    public static final int UPDATE_RATE_FASTEST = 3;
    public static final int UPDATE_RATE_NORMAL = 1;
    public static final int UPDATE_RATE_UI = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface UpdateRate {
    }

    void addAccelerometerListener(int i, Executor executor, OnCarDataAvailableListener<Accelerometer> onCarDataAvailableListener);

    void addCarHardwareLocationListener(int i, Executor executor, OnCarDataAvailableListener<CarHardwareLocation> onCarDataAvailableListener);

    void addCompassListener(int i, Executor executor, OnCarDataAvailableListener<Compass> onCarDataAvailableListener);

    void addGyroscopeListener(int i, Executor executor, OnCarDataAvailableListener<Gyroscope> onCarDataAvailableListener);

    void removeAccelerometerListener(OnCarDataAvailableListener<Accelerometer> onCarDataAvailableListener);

    void removeCarHardwareLocationListener(OnCarDataAvailableListener<CarHardwareLocation> onCarDataAvailableListener);

    void removeCompassListener(OnCarDataAvailableListener<Compass> onCarDataAvailableListener);

    void removeGyroscopeListener(OnCarDataAvailableListener<Gyroscope> onCarDataAvailableListener);
}
