package androidx.car.app.model;

import androidx.car.app.OnDoneCallback;

public interface SearchCallbackDelegate {
    void sendSearchSubmitted(String str, OnDoneCallback onDoneCallback);

    void sendSearchTextChanged(String str, OnDoneCallback onDoneCallback);
}
