package io.ktor.server.auth;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.OAuthKt", f = "OAuth.kt", i = {0, 0, 0}, l = {30}, m = "oauth1a", n = {"$this$oauth1a", "context", "provider"}, s = {"L$0", "L$1", "L$2"})
/* compiled from: OAuth.kt */
final class OAuthKt$oauth1a$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    OAuthKt$oauth1a$1(Continuation<? super OAuthKt$oauth1a$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return OAuthKt.oauth1a((OAuthAuthenticationProvider) null, (String) null, (AuthenticationContext) null, this);
    }
}
