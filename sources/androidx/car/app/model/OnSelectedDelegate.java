package androidx.car.app.model;

import androidx.car.app.OnDoneCallback;

public interface OnSelectedDelegate {
    void sendSelected(int i, OnDoneCallback onDoneCallback);
}
