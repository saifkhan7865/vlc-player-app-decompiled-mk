package io.ktor.server.sessions;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B/\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00018\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0002\u0010\tJ\u0010\u0010\u0015\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0016\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\bHÆ\u0003JF\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00018\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bHÆ\u0001¢\u0006\u0002\u0010\u001aJ\u0013\u0010\u001b\u001a\u00020\u00062\b\u0010\u001c\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020 HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\u0004\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0003\u001a\u0004\u0018\u00018\u0000X\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, d2 = {"Lio/ktor/server/sessions/SessionProviderData;", "S", "", "oldValue", "newValue", "incoming", "", "provider", "Lio/ktor/server/sessions/SessionProvider;", "(Ljava/lang/Object;Ljava/lang/Object;ZLio/ktor/server/sessions/SessionProvider;)V", "getIncoming", "()Z", "getNewValue", "()Ljava/lang/Object;", "setNewValue", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "getOldValue", "setOldValue", "getProvider", "()Lio/ktor/server/sessions/SessionProvider;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Object;Ljava/lang/Object;ZLio/ktor/server/sessions/SessionProvider;)Lio/ktor/server/sessions/SessionProviderData;", "equals", "other", "hashCode", "", "toString", "", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionData.kt */
public final class SessionProviderData<S> {
    private final boolean incoming;
    private S newValue;
    private S oldValue;
    private final SessionProvider<S> provider;

    public static /* synthetic */ SessionProviderData copy$default(SessionProviderData sessionProviderData, S s, S s2, boolean z, SessionProvider<S> sessionProvider, int i, Object obj) {
        if ((i & 1) != 0) {
            s = sessionProviderData.oldValue;
        }
        if ((i & 2) != 0) {
            s2 = sessionProviderData.newValue;
        }
        if ((i & 4) != 0) {
            z = sessionProviderData.incoming;
        }
        if ((i & 8) != 0) {
            sessionProvider = sessionProviderData.provider;
        }
        return sessionProviderData.copy(s, s2, z, sessionProvider);
    }

    public final S component1() {
        return this.oldValue;
    }

    public final S component2() {
        return this.newValue;
    }

    public final boolean component3() {
        return this.incoming;
    }

    public final SessionProvider<S> component4() {
        return this.provider;
    }

    public final SessionProviderData<S> copy(S s, S s2, boolean z, SessionProvider<S> sessionProvider) {
        Intrinsics.checkNotNullParameter(sessionProvider, "provider");
        return new SessionProviderData<>(s, s2, z, sessionProvider);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SessionProviderData)) {
            return false;
        }
        SessionProviderData sessionProviderData = (SessionProviderData) obj;
        return Intrinsics.areEqual((Object) this.oldValue, (Object) sessionProviderData.oldValue) && Intrinsics.areEqual((Object) this.newValue, (Object) sessionProviderData.newValue) && this.incoming == sessionProviderData.incoming && Intrinsics.areEqual((Object) this.provider, (Object) sessionProviderData.provider);
    }

    public int hashCode() {
        S s = this.oldValue;
        int i = 0;
        int hashCode = (s == null ? 0 : s.hashCode()) * 31;
        S s2 = this.newValue;
        if (s2 != null) {
            i = s2.hashCode();
        }
        int i2 = (hashCode + i) * 31;
        boolean z = this.incoming;
        if (z) {
            z = true;
        }
        return ((i2 + (z ? 1 : 0)) * 31) + this.provider.hashCode();
    }

    public String toString() {
        return "SessionProviderData(oldValue=" + this.oldValue + ", newValue=" + this.newValue + ", incoming=" + this.incoming + ", provider=" + this.provider + ')';
    }

    public SessionProviderData(S s, S s2, boolean z, SessionProvider<S> sessionProvider) {
        Intrinsics.checkNotNullParameter(sessionProvider, "provider");
        this.oldValue = s;
        this.newValue = s2;
        this.incoming = z;
        this.provider = sessionProvider;
    }

    public final S getOldValue() {
        return this.oldValue;
    }

    public final void setOldValue(S s) {
        this.oldValue = s;
    }

    public final S getNewValue() {
        return this.newValue;
    }

    public final void setNewValue(S s) {
        this.newValue = s;
    }

    public final boolean getIncoming() {
        return this.incoming;
    }

    public final SessionProvider<S> getProvider() {
        return this.provider;
    }
}
