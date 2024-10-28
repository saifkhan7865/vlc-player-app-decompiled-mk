package androidx.window.area;

import androidx.window.area.WindowAreaControllerImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WindowAreaControllerImpl$RearDisplaySessionConsumer$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ WindowAreaControllerImpl.RearDisplaySessionConsumer f$0;
    public final /* synthetic */ WindowAreaSession f$1;

    public /* synthetic */ WindowAreaControllerImpl$RearDisplaySessionConsumer$$ExternalSyntheticLambda0(WindowAreaControllerImpl.RearDisplaySessionConsumer rearDisplaySessionConsumer, WindowAreaSession windowAreaSession) {
        this.f$0 = rearDisplaySessionConsumer;
        this.f$1 = windowAreaSession;
    }

    public final void run() {
        WindowAreaControllerImpl.RearDisplaySessionConsumer.onSessionStarted$lambda$1$lambda$0(this.f$0, this.f$1);
    }
}
