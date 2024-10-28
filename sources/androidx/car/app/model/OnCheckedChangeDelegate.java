package androidx.car.app.model;

import androidx.car.app.OnDoneCallback;

public interface OnCheckedChangeDelegate {
    void sendCheckedChange(boolean z, OnDoneCallback onDoneCallback);
}
