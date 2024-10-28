package androidx.car.app;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class AppManager$$ExternalSyntheticLambda6 implements HostCall {
    public final /* synthetic */ CharSequence f$0;
    public final /* synthetic */ int f$1;

    public /* synthetic */ AppManager$$ExternalSyntheticLambda6(CharSequence charSequence, int i) {
        this.f$0 = charSequence;
        this.f$1 = i;
    }

    public final Object dispatch(Object obj) {
        return ((IAppHost) obj).showToast(this.f$0, this.f$1);
    }
}
