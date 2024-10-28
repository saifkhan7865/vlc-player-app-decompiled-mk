package androidx.car.app.model;

import androidx.car.app.annotations.RequiresCarApi;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@RequiresCarApi(5)
public interface AlertCallback {
    public static final int REASON_NOT_SUPPORTED = 3;
    public static final int REASON_TIMEOUT = 1;
    public static final int REASON_USER_ACTION = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Reason {
    }

    void onCancel(int i);

    void onDismiss();
}
