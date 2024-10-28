package androidx.car.app.model;

import androidx.car.app.OnDoneCallback;
import androidx.car.app.annotations.RequiresCarApi;

@RequiresCarApi(5)
public interface OnContentRefreshDelegate {
    void sendContentRefreshRequested(OnDoneCallback onDoneCallback);
}
