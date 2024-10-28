package androidx.car.app.model;

import androidx.car.app.OnDoneCallback;
import androidx.car.app.annotations.RequiresCarApi;

@RequiresCarApi(2)
public interface InputCallbackDelegate {
    void sendInputSubmitted(String str, OnDoneCallback onDoneCallback);

    void sendInputTextChanged(String str, OnDoneCallback onDoneCallback);
}
