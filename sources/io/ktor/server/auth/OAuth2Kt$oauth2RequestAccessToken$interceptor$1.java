package io.ktor.server.auth;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.server.auth.OAuthServerSettings;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<no name provided>", "", "Lio/ktor/client/request/HttpRequestBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: OAuth2.kt */
final class OAuth2Kt$oauth2RequestAccessToken$interceptor$1 extends Lambda implements Function1<HttpRequestBuilder, Unit> {
    final /* synthetic */ Function1<HttpRequestBuilder, Unit> $configure;
    final /* synthetic */ OAuthServerSettings.OAuth2ServerSettings $settings;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OAuth2Kt$oauth2RequestAccessToken$interceptor$1(OAuthServerSettings.OAuth2ServerSettings oAuth2ServerSettings, Function1<? super HttpRequestBuilder, Unit> function1) {
        super(1);
        this.$settings = oAuth2ServerSettings;
        this.$configure = function1;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((HttpRequestBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(HttpRequestBuilder httpRequestBuilder) {
        Intrinsics.checkNotNullParameter(httpRequestBuilder, "<this>");
        this.$settings.getAccessTokenInterceptor().invoke(httpRequestBuilder);
        this.$configure.invoke(httpRequestBuilder);
    }
}
