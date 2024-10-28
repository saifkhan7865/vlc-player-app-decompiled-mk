package io.ktor.server.sessions;

import androidx.core.app.NotificationCompat;
import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n¢\u0006\u0002\u0010\u000bJ\b\u0010\u0014\u001a\u00020\u0004H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Lio/ktor/server/sessions/SessionProvider;", "S", "", "name", "", "type", "Lkotlin/reflect/KClass;", "transport", "Lio/ktor/server/sessions/SessionTransport;", "tracker", "Lio/ktor/server/sessions/SessionTracker;", "(Ljava/lang/String;Lkotlin/reflect/KClass;Lio/ktor/server/sessions/SessionTransport;Lio/ktor/server/sessions/SessionTracker;)V", "getName", "()Ljava/lang/String;", "getTracker", "()Lio/ktor/server/sessions/SessionTracker;", "getTransport", "()Lio/ktor/server/sessions/SessionTransport;", "getType", "()Lkotlin/reflect/KClass;", "toString", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionProvider.kt */
public final class SessionProvider<S> {
    private final String name;
    private final SessionTracker<S> tracker;
    private final SessionTransport transport;
    private final KClass<S> type;

    public SessionProvider(String str, KClass<S> kClass, SessionTransport sessionTransport, SessionTracker<S> sessionTracker) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(kClass, "type");
        Intrinsics.checkNotNullParameter(sessionTransport, NotificationCompat.CATEGORY_TRANSPORT);
        Intrinsics.checkNotNullParameter(sessionTracker, "tracker");
        this.name = str;
        this.type = kClass;
        this.transport = sessionTransport;
        this.tracker = sessionTracker;
    }

    public final String getName() {
        return this.name;
    }

    public final KClass<S> getType() {
        return this.type;
    }

    public final SessionTransport getTransport() {
        return this.transport;
    }

    public final SessionTracker<S> getTracker() {
        return this.tracker;
    }

    public String toString() {
        return "SessionProvider(name = " + this.name + ", type = " + this.type + ", transport = " + this.transport + ", tracker = " + this.tracker + ')';
    }
}
