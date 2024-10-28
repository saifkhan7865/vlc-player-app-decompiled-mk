package io.ktor.server.sessions;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClassifiers;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001b\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\n\b\u0000\u0010\u0004\u0018\u0001*\u00020\u0005H\b\u001a&\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\b\b\u0000\u0010\u0004*\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0007H\u0007\u001a\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00040\t\"\b\b\u0000\u0010\u0004*\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b\u001a)\u0010\f\u001a\u0002H\u0004\"\b\b\u0000\u0010\u0004*\u00020\u0005*\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0007H\u0002¢\u0006\u0002\u0010\r\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"TYPE_TOKEN_PARAMETER_NAME", "", "autoSerializerOf", "Lio/ktor/server/sessions/SessionSerializerReflection;", "T", "", "type", "Lkotlin/reflect/KClass;", "defaultSessionSerializer", "Lio/ktor/server/sessions/SessionSerializer;", "typeInfo", "Lkotlin/reflect/KType;", "cast", "(Ljava/lang/Object;Lkotlin/reflect/KClass;)Ljava/lang/Object;", "ktor-server-sessions"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionSerializerReflection.kt */
public final class SessionSerializerReflectionKt {
    private static final String TYPE_TOKEN_PARAMETER_NAME = "$type";

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use defaultSessionSerializer instead.", replaceWith = @ReplaceWith(expression = "defaultSessionSerializer<T>()", imports = {}))
    public static final /* synthetic */ <T> SessionSerializerReflection<T> autoSerializerOf() {
        Intrinsics.reifiedOperationMarker(6, "T");
        SessionSerializer defaultSessionSerializer = defaultSessionSerializer((KType) null);
        Intrinsics.checkNotNull(defaultSessionSerializer, "null cannot be cast to non-null type io.ktor.server.sessions.SessionSerializerReflection<T of io.ktor.server.sessions.SessionSerializerReflectionKt.autoSerializerOf>");
        return (SessionSerializerReflection) defaultSessionSerializer;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use defaultSessionSerializer<T> instead.", replaceWith = @ReplaceWith(expression = "defaultSessionSerializer<T>()", imports = {}))
    public static final <T> SessionSerializerReflection<T> autoSerializerOf(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "type");
        SessionSerializer defaultSessionSerializer = defaultSessionSerializer(KClassifiers.getStarProjectedType(kClass));
        Intrinsics.checkNotNull(defaultSessionSerializer, "null cannot be cast to non-null type io.ktor.server.sessions.SessionSerializerReflection<T of io.ktor.server.sessions.SessionSerializerReflectionKt.autoSerializerOf>");
        return (SessionSerializerReflection) defaultSessionSerializer;
    }

    public static final <T> SessionSerializer<T> defaultSessionSerializer(KType kType) {
        Intrinsics.checkNotNullParameter(kType, "typeInfo");
        return new SessionSerializerReflection<>(kType);
    }

    /* access modifiers changed from: private */
    public static final <T> T cast(Object obj, KClass<T> kClass) {
        if (JvmClassMappingKt.getJavaClass(kClass).isInstance(obj)) {
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type T of io.ktor.server.sessions.SessionSerializerReflectionKt.cast");
            return obj;
        }
        throw new ClassCastException(Reflection.getOrCreateKotlinClass(obj.getClass()) + " couldn't be cast to " + kClass);
    }
}
