package io.ktor.server.sessions;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.sessions.SessionTrackerById", f = "SessionTrackerById.kt", i = {0}, l = {92}, m = "store", n = {"sessionId"}, s = {"L$0"})
/* compiled from: SessionTrackerById.kt */
final class SessionTrackerById$store$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SessionTrackerById<S> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SessionTrackerById$store$1(SessionTrackerById<S> sessionTrackerById, Continuation<? super SessionTrackerById$store$1> continuation) {
        super(continuation);
        this.this$0 = sessionTrackerById;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.store((ApplicationCall) null, null, this);
    }
}
