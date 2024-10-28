package androidx.window.layout.adapter.extensions;

import androidx.window.extensions.layout.WindowLayoutInfo;
import androidx.window.layout.adapter.extensions.ExtensionWindowLayoutInfoBackend;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "value", "Landroidx/window/extensions/layout/WindowLayoutInfo;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ExtensionWindowLayoutInfoBackend.kt */
final class ExtensionWindowLayoutInfoBackend$registerLayoutChangeCallback$1$2$consumeWindowLayoutInfo$1 extends Lambda implements Function1<WindowLayoutInfo, Unit> {
    final /* synthetic */ ExtensionWindowLayoutInfoBackend.MulticastConsumer $consumer;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ExtensionWindowLayoutInfoBackend$registerLayoutChangeCallback$1$2$consumeWindowLayoutInfo$1(ExtensionWindowLayoutInfoBackend.MulticastConsumer multicastConsumer) {
        super(1);
        this.$consumer = multicastConsumer;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((WindowLayoutInfo) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(WindowLayoutInfo windowLayoutInfo) {
        Intrinsics.checkNotNullParameter(windowLayoutInfo, "value");
        this.$consumer.accept(windowLayoutInfo);
    }
}
