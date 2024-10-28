package io.ktor.client.request;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/client/request/HttpRequestBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 176)
/* compiled from: builders.kt */
public final class BuildersKt$prepareDelete$4 extends Lambda implements Function1<HttpRequestBuilder, Unit> {
    public static final BuildersKt$prepareDelete$4 INSTANCE = new BuildersKt$prepareDelete$4();

    public BuildersKt$prepareDelete$4() {
        super(1);
    }

    public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        Intrinsics.checkNotNullParameter(httpRequestBuilder, "$this$null");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((HttpRequestBuilder) obj);
        return Unit.INSTANCE;
    }
}