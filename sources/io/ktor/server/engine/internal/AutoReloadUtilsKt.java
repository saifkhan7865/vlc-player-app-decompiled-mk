package io.ktor.server.engine.internal;

import io.ktor.http.ContentDisposition;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationEnvironment;
import io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.nio.file.WatchEvent;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.full.KCallables;
import kotlin.reflect.jvm.ReflectJvmMapping;

@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0000\u001a\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000\u001a\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000\u001a\u001c\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0000\u001a&\u0010\u0017\u001a\n\u0012\u0004\u0012\u0002H\u0019\u0018\u00010\u0018\"\u0004\b\u0000\u0010\u0019*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00190\u00180\u001aH\u0000\u001a\u0010\u0010\u001b\u001a\u00020\u0011*\u0006\u0012\u0002\b\u00030\u0018H\u0000\u001a\u001a\u0010\u001c\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0001*\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u000bH\u0000\u001a\u0016\u0010\u001f\u001a\b\u0012\u0002\b\u0003\u0018\u00010 *\u0006\u0012\u0002\b\u00030\u0001H\u0000\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0004\" \u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006!"}, d2 = {"ApplicationClassInstance", "Ljava/lang/Class;", "Lio/ktor/server/application/Application;", "getApplicationClassInstance", "()Ljava/lang/Class;", "ApplicationEnvironmentClassInstance", "Lio/ktor/server/application/ApplicationEnvironment;", "getApplicationEnvironmentClassInstance", "currentStartupModules", "Ljava/lang/ThreadLocal;", "", "", "getCurrentStartupModules", "()Ljava/lang/ThreadLocal;", "get_com_sun_nio_file_SensitivityWatchEventModifier_HIGH", "Ljava/nio/file/WatchEvent$Modifier;", "isApplication", "", "parameter", "Lkotlin/reflect/KParameter;", "isApplicationEnvironment", "isParameterOfType", "type", "bestFunction", "Lkotlin/reflect/KFunction;", "R", "", "isApplicableFunction", "loadClassOrNull", "Ljava/lang/ClassLoader;", "name", "takeIfNotFacade", "Lkotlin/reflect/KClass;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: AutoReloadUtils.kt */
public final class AutoReloadUtilsKt {
    private static final Class<Application> ApplicationClassInstance = Application.class;
    private static final Class<ApplicationEnvironment> ApplicationEnvironmentClassInstance = ApplicationEnvironment.class;
    private static final ThreadLocal<List<String>> currentStartupModules = new ThreadLocal<>();

    public static final ThreadLocal<List<String>> getCurrentStartupModules() {
        return currentStartupModules;
    }

    public static final Class<ApplicationEnvironment> getApplicationEnvironmentClassInstance() {
        return ApplicationEnvironmentClassInstance;
    }

    public static final Class<Application> getApplicationClassInstance() {
        return ApplicationClassInstance;
    }

    public static final boolean isApplicationEnvironment(KParameter kParameter) {
        Intrinsics.checkNotNullParameter(kParameter, "parameter");
        return isParameterOfType(kParameter, ApplicationEnvironmentClassInstance);
    }

    public static final boolean isApplication(KParameter kParameter) {
        Intrinsics.checkNotNullParameter(kParameter, "parameter");
        return isParameterOfType(kParameter, ApplicationClassInstance);
    }

    public static final Class<?> loadClassOrNull(ClassLoader classLoader, String str) {
        Intrinsics.checkNotNullParameter(classLoader, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        try {
            return classLoader.loadClass(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static final boolean isParameterOfType(KParameter kParameter, Class<?> cls) {
        Intrinsics.checkNotNullParameter(kParameter, "parameter");
        Intrinsics.checkNotNullParameter(cls, "type");
        Type javaType = ReflectJvmMapping.getJavaType(kParameter.getType());
        Class cls2 = javaType instanceof Class ? (Class) javaType : null;
        if (cls2 != null) {
            return cls.isAssignableFrom(cls2);
        }
        return false;
    }

    public static final <R> KFunction<R> bestFunction(List<? extends KFunction<? extends R>> list) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        return (KFunction) CollectionsKt.lastOrNull(CollectionsKt.sortedWith(list, ComparisonsKt.compareBy((Function1<? super T, ? extends Comparable<?>>[]) new Function1[]{AutoReloadUtilsKt$bestFunction$1.INSTANCE, AutoReloadUtilsKt$bestFunction$2.INSTANCE, AutoReloadUtilsKt$bestFunction$3.INSTANCE})));
    }

    public static final boolean isApplicableFunction(KFunction<?> kFunction) {
        Intrinsics.checkNotNullParameter(kFunction, "<this>");
        if (kFunction.isOperator() || kFunction.isInfix() || kFunction.isInline() || kFunction.isAbstract() || kFunction.isSuspend()) {
            return false;
        }
        KParameter extensionReceiverParameter = KCallables.getExtensionReceiverParameter(kFunction);
        if (extensionReceiverParameter != null && !isApplication(extensionReceiverParameter) && !isApplicationEnvironment(extensionReceiverParameter)) {
            return false;
        }
        Method javaMethod = ReflectJvmMapping.getJavaMethod(kFunction);
        if (javaMethod != null) {
            if (javaMethod.isSynthetic()) {
                return false;
            }
            if (Modifier.isStatic(javaMethod.getModifiers()) && kFunction.getParameters().isEmpty()) {
                return false;
            }
        }
        Iterable<KParameter> parameters = kFunction.getParameters();
        if (!(parameters instanceof Collection) || !((Collection) parameters).isEmpty()) {
            for (KParameter kParameter : parameters) {
                if (!isApplication(kParameter) && !isApplicationEnvironment(kParameter) && kParameter.getKind() != KParameter.Kind.INSTANCE && !kParameter.isOptional()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static final KClass<?> takeIfNotFacade(Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Metadata metadata = (Metadata) cls.getAnnotation(Metadata.class);
        if (metadata == null || metadata.k() != 1) {
            metadata = null;
        }
        if (metadata != null) {
            return JvmClassMappingKt.getKotlinClass(cls);
        }
        return null;
    }

    public static final WatchEvent.Modifier get_com_sun_nio_file_SensitivityWatchEventModifier_HIGH() {
        if (System.getenv("ANDROID_DATA") != null) {
            return null;
        }
        try {
            Class<?> cls = Class.forName("com.sun.nio.file.SensitivityWatchEventModifier");
            Object obj = cls.getField("HIGH").get(cls);
            if (NioPathKt$$ExternalSyntheticApiModelOutline0.m(obj)) {
                return NioPathKt$$ExternalSyntheticApiModelOutline0.m(obj);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}
