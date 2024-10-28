package androidx.car.app.model;

import androidx.car.app.OnDoneCallback;
import androidx.car.app.annotations.RequiresCarApi;

@RequiresCarApi(6)
public interface TabCallbackDelegate {
    void sendTabSelected(String str, OnDoneCallback onDoneCallback);
}
