package org.videolan.vlc.webserver;

import io.ktor.server.auth.Principal;
import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/videolan/vlc/webserver/UserSession;", "Lio/ktor/server/auth/Principal;", "id", "", "maxAge", "", "(Ljava/lang/String;J)V", "getId", "()Ljava/lang/String;", "getMaxAge", "()J", "setMaxAge", "(J)V", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessSession.kt */
public final class UserSession implements Principal {
    private final String id;
    private long maxAge;

    public static /* synthetic */ UserSession copy$default(UserSession userSession, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = userSession.id;
        }
        if ((i & 2) != 0) {
            j = userSession.maxAge;
        }
        return userSession.copy(str, j);
    }

    public final String component1() {
        return this.id;
    }

    public final long component2() {
        return this.maxAge;
    }

    public final UserSession copy(String str, long j) {
        Intrinsics.checkNotNullParameter(str, "id");
        return new UserSession(str, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserSession)) {
            return false;
        }
        UserSession userSession = (UserSession) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) userSession.id) && this.maxAge == userSession.maxAge;
    }

    public int hashCode() {
        return (this.id.hashCode() * 31) + UInt$$ExternalSyntheticBackport0.m(this.maxAge);
    }

    public String toString() {
        return "UserSession(id=" + this.id + ", maxAge=" + this.maxAge + ')';
    }

    public UserSession(String str, long j) {
        Intrinsics.checkNotNullParameter(str, "id");
        this.id = str;
        this.maxAge = j;
    }

    public final String getId() {
        return this.id;
    }

    public final long getMaxAge() {
        return this.maxAge;
    }

    public final void setMaxAge(long j) {
        this.maxAge = j;
    }
}
