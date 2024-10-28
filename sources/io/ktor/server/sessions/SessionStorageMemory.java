package io.ktor.server.sessions;

import io.ktor.util.collections.ConcurrentMap;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0019\u0010\n\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\tJ!\u0010\u000b\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\rR\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"Lio/ktor/server/sessions/SessionStorageMemory;", "Lio/ktor/server/sessions/SessionStorage;", "()V", "sessions", "Lio/ktor/util/collections/ConcurrentMap;", "", "invalidate", "", "id", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "read", "write", "value", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionStorageMemory.kt */
public final class SessionStorageMemory implements SessionStorage {
    private final ConcurrentMap<String, String> sessions = new ConcurrentMap<>(0, 1, (DefaultConstructorMarker) null);

    public Object write(String str, String str2, Continuation<? super Unit> continuation) {
        this.sessions.put(str, str2);
        return Unit.INSTANCE;
    }

    public Object read(String str, Continuation<? super String> continuation) {
        String str2 = this.sessions.get(str);
        if (str2 != null) {
            return str2;
        }
        throw new NoSuchElementException("Session " + str + " not found");
    }

    public Object invalidate(String str, Continuation<? super Unit> continuation) {
        this.sessions.remove(str);
        return Unit.INSTANCE;
    }
}
