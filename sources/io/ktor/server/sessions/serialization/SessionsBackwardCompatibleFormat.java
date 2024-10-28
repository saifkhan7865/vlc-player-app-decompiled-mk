package io.ktor.server.sessions.serialization;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.StringFormat;
import kotlinx.serialization.modules.SerializersModule;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J)\u0010\u0007\u001a\u0002H\b\"\u0004\b\u0000\u0010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\b0\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\rJ)\u0010\u000e\u001a\u00020\f\"\u0004\b\u0000\u0010\b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\b0\u00102\u0006\u0010\u0011\u001a\u0002H\bH\u0016¢\u0006\u0002\u0010\u0012R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lio/ktor/server/sessions/serialization/SessionsBackwardCompatibleFormat;", "Lkotlinx/serialization/StringFormat;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "(Lkotlinx/serialization/modules/SerializersModule;)V", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "decodeFromString", "T", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "string", "", "(Lkotlinx/serialization/DeserializationStrategy;Ljava/lang/String;)Ljava/lang/Object;", "encodeToString", "serializer", "Lkotlinx/serialization/SerializationStrategy;", "value", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)Ljava/lang/String;", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: KotlinxSessionSerializer.kt */
public final class SessionsBackwardCompatibleFormat implements StringFormat {
    private final SerializersModule serializersModule;

    public SessionsBackwardCompatibleFormat(SerializersModule serializersModule2) {
        Intrinsics.checkNotNullParameter(serializersModule2, "serializersModule");
        this.serializersModule = serializersModule2;
    }

    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    public <T> T decodeFromString(DeserializationStrategy<? extends T> deserializationStrategy, String str) {
        Intrinsics.checkNotNullParameter(deserializationStrategy, "deserializer");
        Intrinsics.checkNotNullParameter(str, TypedValues.Custom.S_STRING);
        return new SessionsBackwardCompatibleDecoder(getSerializersModule(), str).decodeSerializableValue(deserializationStrategy);
    }

    public <T> String encodeToString(SerializationStrategy<? super T> serializationStrategy, T t) {
        Intrinsics.checkNotNullParameter(serializationStrategy, "serializer");
        SessionsBackwardCompatibleEncoder sessionsBackwardCompatibleEncoder = new SessionsBackwardCompatibleEncoder(getSerializersModule());
        sessionsBackwardCompatibleEncoder.encodeSerializableValue(serializationStrategy, t);
        return sessionsBackwardCompatibleEncoder.result();
    }
}
