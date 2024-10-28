package io.ktor.server.auth;

import io.ktor.server.auth.OAuthCallback;
import io.ktor.server.auth.OAuthServerSettings;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.OAuthKt", f = "OAuth.kt", i = {0, 0}, l = {53}, m = "oauth1RequestToken", n = {"authProviderName", "context"}, s = {"L$0", "L$1"})
/* compiled from: OAuth.kt */
final class OAuthKt$oauth1RequestToken$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    OAuthKt$oauth1RequestToken$1(Continuation<? super OAuthKt$oauth1RequestToken$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return OAuthKt.oauth1RequestToken((OAuthAuthenticationProvider) null, (String) null, (OAuthServerSettings.OAuth1aServerSettings) null, (OAuthCallback.TokenPair) null, (AuthenticationContext) null, this);
    }
}
