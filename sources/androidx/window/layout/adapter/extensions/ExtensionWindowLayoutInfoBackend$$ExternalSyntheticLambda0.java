package androidx.window.layout.adapter.extensions;

import androidx.window.extensions.core.util.function.Consumer;
import androidx.window.extensions.layout.WindowLayoutInfo;
import androidx.window.layout.adapter.extensions.ExtensionWindowLayoutInfoBackend;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ExtensionWindowLayoutInfoBackend$$ExternalSyntheticLambda0 implements Consumer {
    public final /* synthetic */ ExtensionWindowLayoutInfoBackend.MulticastConsumer f$0;

    public /* synthetic */ ExtensionWindowLayoutInfoBackend$$ExternalSyntheticLambda0(ExtensionWindowLayoutInfoBackend.MulticastConsumer multicastConsumer) {
        this.f$0 = multicastConsumer;
    }

    public final void accept(Object obj) {
        ExtensionWindowLayoutInfoBackend.registerLayoutChangeCallback$lambda$3$lambda$2$lambda$1(this.f$0, (WindowLayoutInfo) obj);
    }
}
