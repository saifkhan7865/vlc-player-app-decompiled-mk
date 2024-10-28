package io.ktor.server.sessions;

import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00000\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00028\u0000\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0010\u001a\u00020\u0005J\u0006\u0010\u0011\u001a\u00020\u0012R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\f¨\u0006\u0013"}, d2 = {"Lio/ktor/server/sessions/KeyState;", "K", "Lio/ktor/server/sessions/ListElement;", "key", "timeout", "", "(Ljava/lang/Object;J)V", "Ljava/lang/ref/WeakReference;", "getKey", "()Ljava/lang/ref/WeakReference;", "lastAccess", "getLastAccess", "()J", "setLastAccess", "(J)V", "getTimeout", "timeToWait", "touch", "", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CacheJvm.kt */
final class KeyState<K> extends ListElement<KeyState<K>> {
    private final WeakReference<K> key;
    private long lastAccess = System.currentTimeMillis();
    private final long timeout;

    public KeyState(K k, long j) {
        this.timeout = j;
        this.key = new WeakReference<>(k);
    }

    public final long getTimeout() {
        return this.timeout;
    }

    public final WeakReference<K> getKey() {
        return this.key;
    }

    public final long getLastAccess() {
        return this.lastAccess;
    }

    public final void setLastAccess(long j) {
        this.lastAccess = j;
    }

    public final void touch() {
        this.lastAccess = System.currentTimeMillis();
    }

    public final long timeToWait() {
        return RangesKt.coerceAtLeast(0, (this.lastAccess + this.timeout) - System.currentTimeMillis());
    }
}
