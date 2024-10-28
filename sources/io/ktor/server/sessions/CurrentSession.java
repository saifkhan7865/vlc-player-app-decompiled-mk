package io.ktor.server.sessions;

import kotlin.Metadata;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0014\u0010\u0006\u001a\u00020\u00052\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bH&J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0005H&J\u001a\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001H&¨\u0006\f"}, d2 = {"Lio/ktor/server/sessions/CurrentSession;", "", "clear", "", "name", "", "findName", "type", "Lkotlin/reflect/KClass;", "get", "set", "value", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionData.kt */
public interface CurrentSession {
    void clear(String str);

    String findName(KClass<?> kClass);

    Object get(String str);

    void set(String str, Object obj);
}
