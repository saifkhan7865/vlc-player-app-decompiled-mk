package androidx.car.app.connection;

import androidx.lifecycle.LiveData;

final class AutomotiveCarConnectionTypeLiveData extends LiveData<Integer> {
    AutomotiveCarConnectionTypeLiveData() {
    }

    /* access modifiers changed from: protected */
    public void onActive() {
        setValue(1);
    }
}
