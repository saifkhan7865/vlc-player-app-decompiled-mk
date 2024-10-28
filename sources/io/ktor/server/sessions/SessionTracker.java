package io.ktor.server.sessions;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002J\u0019\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0007J%\u0010\b\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ!\u0010\f\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\r\u001a\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0015\u0010\u000f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0010\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lio/ktor/server/sessions/SessionTracker;", "S", "", "clear", "", "call", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/server/application/ApplicationCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", "transport", "", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "store", "value", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "validate", "(Ljava/lang/Object;)V", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionTracker.kt */
public interface SessionTracker<S> {
    Object clear(ApplicationCall applicationCall, Continuation<? super Unit> continuation);

    Object load(ApplicationCall applicationCall, String str, Continuation<? super S> continuation);

    Object store(ApplicationCall applicationCall, S s, Continuation<? super String> continuation);

    void validate(S s);
}
