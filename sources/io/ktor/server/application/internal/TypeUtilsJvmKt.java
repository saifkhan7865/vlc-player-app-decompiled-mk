package io.ktor.server.application.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClassifiers;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"starProjectedTypeBridge", "Lkotlin/reflect/KType;", "T", "", "klass", "Lkotlin/reflect/KClass;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: TypeUtilsJvm.kt */
public final class TypeUtilsJvmKt {
    public static final <T> KType starProjectedTypeBridge(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "klass");
        return KClassifiers.getStarProjectedType(kClass);
    }
}
