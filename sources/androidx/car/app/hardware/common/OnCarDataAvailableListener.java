package androidx.car.app.hardware.common;

import androidx.car.app.annotations.RequiresCarApi;

@RequiresCarApi(3)
public interface OnCarDataAvailableListener<T> {
    void onCarDataAvailable(T t);
}
