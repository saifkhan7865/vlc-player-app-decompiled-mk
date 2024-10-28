package androidx.window.area;

import androidx.window.extensions.core.util.function.Consumer;
import kotlinx.coroutines.channels.ProducerScope;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class WindowAreaControllerImpl$rearDisplayStatus$1$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ WindowAreaControllerImpl f$0;
    public final /* synthetic */ ProducerScope f$1;

    public /* synthetic */ WindowAreaControllerImpl$rearDisplayStatus$1$$ExternalSyntheticLambda0(WindowAreaControllerImpl windowAreaControllerImpl, ProducerScope producerScope) {
        this.f$0 = windowAreaControllerImpl;
        this.f$1 = producerScope;
    }

    public final void accept(Object obj) {
        WindowAreaControllerImpl$rearDisplayStatus$1.invokeSuspend$lambda$0(this.f$0, this.f$1, (Integer) obj);
    }
}
