package io.ktor.server.engine;

import java.lang.reflect.Method;
import kotlin.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlin.reflect.jvm.ReflectJvmMapping;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002H\u0000¨\u0006\u0003"}, d2 = {"methodName", "", "Lkotlin/Function;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ServerHostUtils.kt */
public final class ServerHostUtilsKt {
    public static final String methodName(Function<?> function) {
        Method javaMethod;
        Intrinsics.checkNotNullParameter(function, "<this>");
        KFunction kFunction = function instanceof KFunction ? (KFunction) function : null;
        if (kFunction == null || (javaMethod = ReflectJvmMapping.getJavaMethod(kFunction)) == null) {
            return function.getClass().getName() + ".invoke";
        }
        Class<?> declaringClass = javaMethod.getDeclaringClass();
        String name = javaMethod.getName();
        return declaringClass.getName() + '.' + name;
    }
}
