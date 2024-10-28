package androidx.window.layout;

import androidx.core.util.Consumer;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WindowInfoTrackerImpl$windowLayoutInfo$1$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ ProducerScope f$0;

    public /* synthetic */ WindowInfoTrackerImpl$windowLayoutInfo$1$$ExternalSyntheticLambda0(ProducerScope producerScope) {
        this.f$0 = producerScope;
    }

    public final void accept(Object obj) {
        WindowInfoTrackerImpl$windowLayoutInfo$1.invokeSuspend$lambda$0(this.f$0, (WindowLayoutInfo) obj);
    }
}
