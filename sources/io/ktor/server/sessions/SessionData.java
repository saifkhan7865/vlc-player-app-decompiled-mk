package io.ktor.server.sessions;

import io.ktor.http.ContentDisposition;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0003¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0016J\r\u0010\u000e\u001a\u00020\fH\u0000¢\u0006\u0002\b\u000fJ\u0019\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0003HÆ\u0003J#\u0010\u0011\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\u0014\u0010\u0015\u001a\u00020\u00042\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u0017H\u0016J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00142\u0006\u0010\r\u001a\u00020\u0004H\u0016J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\u001a\u0010\u001b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\b\u0010\u001c\u001a\u0004\u0018\u00010\u0014H\u0016J*\u0010\u001d\u001a\u00020\f\"\b\b\u0000\u0010\u001e*\u00020\u00142\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001e0\u00052\b\u0010\u001c\u001a\u0004\u0018\u00010\u0014H\u0002J\t\u0010 \u001a\u00020\u0004HÖ\u0001R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R!\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u0004\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006!"}, d2 = {"Lio/ktor/server/sessions/SessionData;", "Lio/ktor/server/sessions/CurrentSession;", "providerData", "", "", "Lio/ktor/server/sessions/SessionProviderData;", "(Ljava/util/Map;)V", "committed", "", "getProviderData", "()Ljava/util/Map;", "clear", "", "name", "commit", "commit$ktor_server_sessions", "component1", "copy", "equals", "other", "", "findName", "type", "Lkotlin/reflect/KClass;", "get", "hashCode", "", "set", "value", "setTyped", "S", "data", "toString", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionData.kt */
public final class SessionData implements CurrentSession {
    private boolean committed;
    private final Map<String, SessionProviderData<?>> providerData;

    public static /* synthetic */ SessionData copy$default(SessionData sessionData, Map<String, SessionProviderData<?>> map, int i, Object obj) {
        if ((i & 1) != 0) {
            map = sessionData.providerData;
        }
        return sessionData.copy(map);
    }

    public final Map<String, SessionProviderData<?>> component1() {
        return this.providerData;
    }

    public final SessionData copy(Map<String, ? extends SessionProviderData<?>> map) {
        Intrinsics.checkNotNullParameter(map, "providerData");
        return new SessionData(map);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof SessionData) && Intrinsics.areEqual((Object) this.providerData, (Object) ((SessionData) obj).providerData);
    }

    public int hashCode() {
        return this.providerData.hashCode();
    }

    public String toString() {
        return "SessionData(providerData=" + this.providerData + ')';
    }

    public SessionData(Map<String, ? extends SessionProviderData<?>> map) {
        Intrinsics.checkNotNullParameter(map, "providerData");
        this.providerData = map;
    }

    public final Map<String, SessionProviderData<?>> getProviderData() {
        return this.providerData;
    }

    public final void commit$ktor_server_sessions() {
        this.committed = true;
    }

    public String findName(KClass<?> kClass) {
        Object obj;
        Intrinsics.checkNotNullParameter(kClass, "type");
        Iterator it = this.providerData.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual((Object) ((SessionProviderData) ((Map.Entry) obj).getValue()).getProvider().getType(), (Object) kClass)) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) obj;
        if (entry != null) {
            return ((SessionProviderData) entry.getValue()).getProvider().getName();
        }
        throw new IllegalArgumentException("Session data for type `" + kClass + "` was not registered");
    }

    public void set(String str, Object obj) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        if (!this.committed) {
            SessionProviderData sessionProviderData = this.providerData.get(str);
            if (sessionProviderData != null) {
                setTyped(sessionProviderData, obj);
                return;
            }
            throw new IllegalStateException("Session data for `" + str + "` was not registered");
        }
        throw new TooLateSessionSetException();
    }

    private final <S> void setTyped(SessionProviderData<S> sessionProviderData, Object obj) {
        if (obj != null) {
            sessionProviderData.getProvider().getTracker().validate(obj);
        }
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type S of io.ktor.server.sessions.SessionData.setTyped");
        sessionProviderData.setNewValue(obj);
    }

    public Object get(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        SessionProviderData sessionProviderData = this.providerData.get(str);
        if (sessionProviderData != null) {
            Object newValue = sessionProviderData.getNewValue();
            return newValue == null ? sessionProviderData.getOldValue() : newValue;
        }
        throw new IllegalStateException("Session data for `" + str + "` was not registered");
    }

    public void clear(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        SessionProviderData sessionProviderData = this.providerData.get(str);
        if (sessionProviderData != null) {
            sessionProviderData.setOldValue(null);
            sessionProviderData.setNewValue(null);
            return;
        }
        throw new IllegalStateException("Session data for `" + str + "` was not registered");
    }
}
