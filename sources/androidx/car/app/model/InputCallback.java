package androidx.car.app.model;

import androidx.car.app.annotations.RequiresCarApi;

@RequiresCarApi(2)
public interface InputCallback {

    /* renamed from: androidx.car.app.model.InputCallback$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onInputSubmitted(InputCallback _this, String str) {
        }

        public static void $default$onInputTextChanged(InputCallback _this, String str) {
        }
    }

    void onInputSubmitted(String str);

    void onInputTextChanged(String str);
}
