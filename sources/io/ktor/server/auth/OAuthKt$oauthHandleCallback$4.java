package io.ktor.server.auth;

import io.ktor.client.request.HttpRequestBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: OAuth.kt */
final class OAuthKt$oauthHandleCallback$4 extends Lambda implements Function1<HttpRequestBuilder, Unit> {
    public static final OAuthKt$oauthHandleCallback$4 INSTANCE = new OAuthKt$oauthHandleCallback$4();

    OAuthKt$oauthHandleCallback$4() {
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
