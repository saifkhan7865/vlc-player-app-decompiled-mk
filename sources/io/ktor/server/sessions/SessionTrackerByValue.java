package io.ktor.server.sessions;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.slf4j.Logger;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B!\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\u0019\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J%\u0010\u0012\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H@ø\u0001\u0000¢\u0006\u0002\u0010\u0015J!\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u0014H\u0016J\u0015\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001bR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lio/ktor/server/sessions/SessionTrackerByValue;", "S", "", "Lio/ktor/server/sessions/SessionTracker;", "type", "Lkotlin/reflect/KClass;", "serializer", "Lio/ktor/server/sessions/SessionSerializer;", "(Lkotlin/reflect/KClass;Lio/ktor/server/sessions/SessionSerializer;)V", "getSerializer", "()Lio/ktor/server/sessions/SessionSerializer;", "getType", "()Lkotlin/reflect/KClass;", "clear", "", "call", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/server/application/ApplicationCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "load", "transport", "", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "store", "value", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toString", "validate", "(Ljava/lang/Object;)V", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionTrackerByValue.kt */
public final class SessionTrackerByValue<S> implements SessionTracker<S> {
    private final SessionSerializer<S> serializer;
    private final KClass<S> type;

    public SessionTrackerByValue(KClass<S> kClass, SessionSerializer<S> sessionSerializer) {
        Intrinsics.checkNotNullParameter(kClass, "type");
        Intrinsics.checkNotNullParameter(sessionSerializer, "serializer");
        this.type = kClass;
        this.serializer = sessionSerializer;
    }

    public final KClass<S> getType() {
        return this.type;
    }

    public final SessionSerializer<S> getSerializer() {
        return this.serializer;
    }

    public Object load(ApplicationCall applicationCall, String str, Continuation<? super S> continuation) {
        if (str == null) {
            return null;
        }
        try {
            return this.serializer.deserialize(str);
        } catch (Throwable th) {
            Logger log = ApplicationKt.getLog(applicationCall.getApplication());
            log.debug("Failed to deserialize session: " + str, th);
            return null;
        }
    }

    public Object store(ApplicationCall applicationCall, S s, Continuation<? super String> continuation) {
        return this.serializer.serialize(s);
    }

    public void validate(S s) {
        Intrinsics.checkNotNullParameter(s, "value");
        if (!this.type.isInstance(s)) {
            throw new IllegalArgumentException("Value for this session tracker expected to be of type " + this.type + " but was " + s);
        }
    }

    public Object clear(ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }

    public String toString() {
        return "SessionTrackerByValue";
    }
}
