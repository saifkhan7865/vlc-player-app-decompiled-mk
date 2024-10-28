package io.ktor.server.auth;

import io.ktor.server.auth.OAuthCallback;
import io.ktor.server.auth.OAuthServerSettings;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.OAuthProcedureKt", f = "OAuthProcedure.kt", i = {0, 0}, l = {115}, m = "oauth2RequestToken", n = {"authProviderName", "context"}, s = {"L$0", "L$1"})
/* compiled from: OAuthProcedure.kt */
final class OAuthProcedureKt$oauth2RequestToken$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    OAuthProcedureKt$oauth2RequestToken$1(Continuation<? super OAuthProcedureKt$oauth2RequestToken$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return OAuthProcedureKt.oauth2RequestToken((OAuthAuthenticationProvider) null, (String) null, (OAuthServerSettings.OAuth2ServerSettings) null, (String) null, (OAuthCallback.TokenSingle) null, (AuthenticationContext) null, this);
    }
}
