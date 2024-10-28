package androidx.car.app;

import androidx.car.app.serialization.Bundleable;

public interface OnDoneCallback {

    /* renamed from: androidx.car.app.OnDoneCallback$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onFailure(OnDoneCallback _this, Bundleable bundleable) {
        }

        public static void $default$onSuccess(OnDoneCallback _this, Bundleable bundleable) {
        }
    }

    void onFailure(Bundleable bundleable);

    void onSuccess(Bundleable bundleable);
}
