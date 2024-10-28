package androidx.car.app.navigation;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class NavigationManager$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ NavigationManagerCallback f$0;

    public /* synthetic */ NavigationManager$$ExternalSyntheticLambda3(NavigationManagerCallback navigationManagerCallback) {
        this.f$0 = navigationManagerCallback;
    }

    public final void run() {
        this.f$0.onAutoDriveEnabled();
    }
}
