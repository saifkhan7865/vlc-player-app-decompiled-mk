package androidx.car.app;

import java.util.List;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CarContext$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ OnRequestPermissionsListener f$0;
    public final /* synthetic */ List f$1;
    public final /* synthetic */ List f$2;

    public /* synthetic */ CarContext$1$$ExternalSyntheticLambda0(OnRequestPermissionsListener onRequestPermissionsListener, List list, List list2) {
        this.f$0 = onRequestPermissionsListener;
        this.f$1 = list;
        this.f$2 = list2;
    }

    public final void run() {
        this.f$0.onRequestPermissionsResult(this.f$1, this.f$2);
    }
}
