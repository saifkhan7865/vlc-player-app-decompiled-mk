package androidx.car.app;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AppManager$$ExternalSyntheticLambda2 implements HostCall {
    public final /* synthetic */ int f$0;

    public /* synthetic */ AppManager$$ExternalSyntheticLambda2(int i) {
        this.f$0 = i;
    }

    public final Object dispatch(Object obj) {
        return ((IAppHost) obj).dismissAlert(this.f$0);
    }
}
