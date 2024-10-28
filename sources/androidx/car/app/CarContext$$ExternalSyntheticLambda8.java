package androidx.car.app;

import android.content.Intent;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class CarContext$$ExternalSyntheticLambda8 implements HostCall {
    public final /* synthetic */ Intent f$0;

    public /* synthetic */ CarContext$$ExternalSyntheticLambda8(Intent intent) {
        this.f$0 = intent;
    }

    public final Object dispatch(Object obj) {
        return ((ICarHost) obj).startCarApp(this.f$0);
    }
}
