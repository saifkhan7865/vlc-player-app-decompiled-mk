package io.ktor.server.sessions.serialization;

import io.ktor.server.sessions.SessionSerializer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.StringFormat;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleBuildersKt;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a.\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u001a%\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\b\u001a,\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\t\u001a\u00020\n\u001a#\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\b¨\u0006\u000b"}, d2 = {"KotlinxBackwardCompatibleSessionSerializer", "Lio/ktor/server/sessions/SessionSerializer;", "T", "", "serializer", "Lkotlinx/serialization/KSerializer;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "KotlinxSessionSerializer", "format", "Lkotlinx/serialization/StringFormat;", "ktor-server-sessions"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: KotlinxSessionSerializer.kt */
public final class KotlinxSessionSerializerKt {
    public static final /* synthetic */ <T> SessionSerializer<T> KotlinxSessionSerializer(StringFormat stringFormat) {
        Intrinsics.checkNotNullParameter(stringFormat, "format");
        Intrinsics.reifiedOperationMarker(6, "T");
        MagicApiIntrinsics.voidMagicApiCall((Object) "kotlinx.serialization.serializer.simple");
        return KotlinxSessionSerializer(SerializersKt.serializer((KType) null), stringFormat);
    }

    public static final <T> SessionSerializer<T> KotlinxSessionSerializer(KSerializer<T> kSerializer, StringFormat stringFormat) {
        Intrinsics.checkNotNullParameter(kSerializer, "serializer");
        Intrinsics.checkNotNullParameter(stringFormat, "format");
        return new KotlinxSessionSerializer<>(stringFormat, kSerializer);
    }

    public static /* synthetic */ SessionSerializer KotlinxBackwardCompatibleSessionSerializer$default(SerializersModule serializersModule, int i, Object obj) {
        if ((i & 1) != 0) {
            serializersModule = SerializersModuleBuildersKt.EmptySerializersModule();
        }
        Intrinsics.checkNotNullParameter(serializersModule, "serializersModule");
        Intrinsics.reifiedOperationMarker(6, "T");
        MagicApiIntrinsics.voidMagicApiCall((Object) "kotlinx.serialization.serializer.simple");
        return KotlinxBackwardCompatibleSessionSerializer(SerializersKt.serializer((KType) null), serializersModule);
    }

    public static final /* synthetic */ <T> SessionSerializer<T> KotlinxBackwardCompatibleSessionSerializer(SerializersModule serializersModule) {
        Intrinsics.checkNotNullParameter(serializersModule, "serializersModule");
        Intrinsics.reifiedOperationMarker(6, "T");
        MagicApiIntrinsics.voidMagicApiCall((Object) "kotlinx.serialization.serializer.simple");
        return KotlinxBackwardCompatibleSessionSerializer(SerializersKt.serializer((KType) null), serializersModule);
    }

    public static /* synthetic */ SessionSerializer KotlinxBackwardCompatibleSessionSerializer$default(KSerializer kSerializer, SerializersModule serializersModule, int i, Object obj) {
        if ((i & 2) != 0) {
            serializersModule = SerializersModuleBuildersKt.EmptySerializersModule();
        }
        return KotlinxBackwardCompatibleSessionSerializer(kSerializer, serializersModule);
    }

    public static final <T> SessionSerializer<T> KotlinxBackwardCompatibleSessionSerializer(KSerializer<T> kSerializer, SerializersModule serializersModule) {
        Intrinsics.checkNotNullParameter(kSerializer, "serializer");
        Intrinsics.checkNotNullParameter(serializersModule, "serializersModule");
        return KotlinxSessionSerializer(kSerializer, new SessionsBackwardCompatibleFormat(serializersModule));
    }
}
