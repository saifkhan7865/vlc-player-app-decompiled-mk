package io.ktor.server.sessions.serialization;

import io.ktor.server.sessions.SessionSerializer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.StringFormat;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\u0015\u0010\t\u001a\u00028\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\fJ\u0015\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lio/ktor/server/sessions/serialization/KotlinxSessionSerializer;", "T", "", "Lio/ktor/server/sessions/SessionSerializer;", "format", "Lkotlinx/serialization/StringFormat;", "serializer", "Lkotlinx/serialization/KSerializer;", "(Lkotlinx/serialization/StringFormat;Lkotlinx/serialization/KSerializer;)V", "deserialize", "text", "", "(Ljava/lang/String;)Ljava/lang/Object;", "serialize", "session", "(Ljava/lang/Object;)Ljava/lang/String;", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: KotlinxSessionSerializer.kt */
final class KotlinxSessionSerializer<T> implements SessionSerializer<T> {
    private final StringFormat format;
    private final KSerializer<T> serializer;

    public KotlinxSessionSerializer(StringFormat stringFormat, KSerializer<T> kSerializer) {
        Intrinsics.checkNotNullParameter(stringFormat, "format");
        Intrinsics.checkNotNullParameter(kSerializer, "serializer");
        this.format = stringFormat;
        this.serializer = kSerializer;
    }

    public String serialize(T t) {
        Intrinsics.checkNotNullParameter(t, "session");
        return this.format.encodeToString(this.serializer, t);
    }

    public T deserialize(String str) {
        Intrinsics.checkNotNullParameter(str, "text");
        return this.format.decodeFromString(this.serializer, str);
    }
}
