package androidx.window.layout.adapter.sidecar;

import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.adapter.sidecar.SidecarWindowBackend;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class SidecarWindowBackend$WindowLayoutChangeCallbackWrapper$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ SidecarWindowBackend.WindowLayoutChangeCallbackWrapper f$0;
    public final /* synthetic */ WindowLayoutInfo f$1;

    public /* synthetic */ SidecarWindowBackend$WindowLayoutChangeCallbackWrapper$$ExternalSyntheticLambda0(SidecarWindowBackend.WindowLayoutChangeCallbackWrapper windowLayoutChangeCallbackWrapper, WindowLayoutInfo windowLayoutInfo) {
        this.f$0 = windowLayoutChangeCallbackWrapper;
        this.f$1 = windowLayoutInfo;
    }

    public final void run() {
        SidecarWindowBackend.WindowLayoutChangeCallbackWrapper.accept$lambda$0(this.f$0, this.f$1);
    }
}
