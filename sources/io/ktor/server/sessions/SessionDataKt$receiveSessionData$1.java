package io.ktor.server.sessions;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.sessions.SessionDataKt", f = "SessionData.kt", i = {0, 0}, l = {137}, m = "receiveSessionData", n = {"$this$receiveSessionData", "receivedValue"}, s = {"L$0", "L$1"})
/* compiled from: SessionData.kt */
final class SessionDataKt$receiveSessionData$1<S> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    SessionDataKt$receiveSessionData$1(Continuation<? super SessionDataKt$receiveSessionData$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return SessionDataKt.receiveSessionData((SessionProvider) null, (ApplicationCall) null, this);
    }
}
