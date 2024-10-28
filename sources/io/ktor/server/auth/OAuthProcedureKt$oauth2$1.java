package io.ktor.server.auth;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.OAuthProcedureKt", f = "OAuthProcedure.kt", i = {0, 0, 0, 0, 0, 1, 1, 1}, l = {78, 83}, m = "oauth2", n = {"$this$oauth2", "authProviderName", "context", "call", "provider", "context", "provider", "callbackRedirectUrl"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2"})
/* compiled from: OAuthProcedure.kt */
final class OAuthProcedureKt$oauth2$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    OAuthProcedureKt$oauth2$1(Continuation<? super OAuthProcedureKt$oauth2$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return OAuthProcedureKt.oauth2((OAuthAuthenticationProvider) null, (String) null, (AuthenticationContext) null, this);
    }
}
