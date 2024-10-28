package androidx.window.area;

import androidx.window.area.WindowAreaControllerImpl;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WindowAreaControllerImpl$RearDisplaySessionConsumer$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ WindowAreaControllerImpl.RearDisplaySessionConsumer f$0;

    public /* synthetic */ WindowAreaControllerImpl$RearDisplaySessionConsumer$$ExternalSyntheticLambda1(WindowAreaControllerImpl.RearDisplaySessionConsumer rearDisplaySessionConsumer) {
        this.f$0 = rearDisplaySessionConsumer;
    }

    public final void run() {
        WindowAreaControllerImpl.RearDisplaySessionConsumer.onSessionFinished$lambda$2(this.f$0);
    }
}
