package androidx.car.app.model;

import androidx.car.app.OnDoneCallback;

public interface OnClickDelegate {
    boolean isParkedOnly();

    void sendClick(OnDoneCallback onDoneCallback);
}
