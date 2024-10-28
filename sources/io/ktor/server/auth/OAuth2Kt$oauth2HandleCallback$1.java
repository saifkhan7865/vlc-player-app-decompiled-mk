package io.ktor.server.auth;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.OAuth2Kt", f = "OAuth2.kt", i = {}, l = {427}, m = "oauth2HandleCallback", n = {}, s = {})
/* compiled from: OAuth2.kt */
final class OAuth2Kt$oauth2HandleCallback$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    OAuth2Kt$oauth2HandleCallback$1(Continuation<? super OAuth2Kt$oauth2HandleCallback$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return OAuth2Kt.oauth2HandleCallback((ApplicationCall) null, this);
    }
}
