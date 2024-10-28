package io.ktor.network.tls;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/network/tls/TLSConfigBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSCommon.kt */
final class TLSCommonKt$tls$5 extends Lambda implements Function1<TLSConfigBuilder, Unit> {
    public static final TLSCommonKt$tls$5 INSTANCE = new TLSCommonKt$tls$5();

    TLSCommonKt$tls$5() {
        super(1);
    }

    public final void invoke(TLSConfigBuilder tLSConfigBuilder) {
        Intrinsics.checkNotNullParameter(tLSConfigBuilder, "$this$tls");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((TLSConfigBuilder) obj);
        return Unit.INSTANCE;
    }
}
