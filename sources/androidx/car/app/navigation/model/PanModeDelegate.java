package androidx.car.app.navigation.model;

import androidx.car.app.OnDoneCallback;
import androidx.car.app.annotations.RequiresCarApi;

@RequiresCarApi(2)
public interface PanModeDelegate {
    void sendPanModeChanged(boolean z, OnDoneCallback onDoneCallback);
}
