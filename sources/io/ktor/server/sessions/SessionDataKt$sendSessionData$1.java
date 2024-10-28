package io.ktor.server.sessions;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.sessions.SessionDataKt", f = "SessionData.kt", i = {0, 0}, l = {147, 153}, m = "sendSessionData", n = {"$this$sendSessionData", "call"}, s = {"L$0", "L$1"})
/* compiled from: SessionData.kt */
final class SessionDataKt$sendSessionData$1<S> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    SessionDataKt$sendSessionData$1(Continuation<? super SessionDataKt$sendSessionData$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return SessionDataKt.sendSessionData((SessionProviderData) null, (ApplicationCall) null, this);
    }
}
