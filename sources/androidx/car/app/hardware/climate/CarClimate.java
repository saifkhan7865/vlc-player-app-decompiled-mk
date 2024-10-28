package androidx.car.app.hardware.climate;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.hardware.common.CarSetOperationStatusCallback;
import java.util.concurrent.Executor;

@RequiresCarApi(5)
public interface CarClimate {
    void fetchClimateProfile(Executor executor, ClimateProfileRequest climateProfileRequest, CarClimateProfileCallback carClimateProfileCallback);

    void registerClimateStateCallback(Executor executor, RegisterClimateStateRequest registerClimateStateRequest, CarClimateStateCallback carClimateStateCallback);

    <E> void setClimateState(Executor executor, ClimateStateRequest<E> climateStateRequest, CarSetOperationStatusCallback carSetOperationStatusCallback);

    void unregisterClimateStateCallback(CarClimateStateCallback carClimateStateCallback);
}
