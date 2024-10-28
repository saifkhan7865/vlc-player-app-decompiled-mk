package io.ktor.server.sessions;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0006J\u0015\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lio/ktor/server/sessions/SessionSerializer;", "T", "", "deserialize", "text", "", "(Ljava/lang/String;)Ljava/lang/Object;", "serialize", "session", "(Ljava/lang/Object;)Ljava/lang/String;", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionSerializer.kt */
public interface SessionSerializer<T> {
    T deserialize(String str);

    String serialize(T t);
}
