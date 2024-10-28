package androidx.car.app;

import androidx.activity.result.ActivityResultCallback;
import java.util.Map;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CarAppPermissionActivity$$ExternalSyntheticLambda1 implements ActivityResultCallback {
    public final /* synthetic */ CarAppPermissionActivity f$0;
    public final /* synthetic */ IOnRequestPermissionsListener f$1;

    public /* synthetic */ CarAppPermissionActivity$$ExternalSyntheticLambda1(CarAppPermissionActivity carAppPermissionActivity, IOnRequestPermissionsListener iOnRequestPermissionsListener) {
        this.f$0 = carAppPermissionActivity;
        this.f$1 = iOnRequestPermissionsListener;
    }

    public final void onActivityResult(Object obj) {
        this.f$0.m17lambda$requestPermissions$0$androidxcarappCarAppPermissionActivity(this.f$1, (Map) obj);
    }
}
