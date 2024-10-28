package io.ktor.server.engine.internal;

import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationEnvironment;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.jvm.ReflectJvmMapping;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a7\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002¢\u0006\u0002\u0010\t\u001a \u0010\n\u001a\u00020\u0004*\u00020\u00022\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a$\u0010\r\u001a\u00020\u000e*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0007\u001a\u00020\bH\u0000¨\u0006\u0013"}, d2 = {"callFunctionWithInjection", "R", "Lio/ktor/server/application/ApplicationEnvironment;", "instance", "", "entryPoint", "Lkotlin/reflect/KFunction;", "application", "Lio/ktor/server/application/Application;", "(Lio/ktor/server/application/ApplicationEnvironment;Ljava/lang/Object;Lkotlin/reflect/KFunction;Lio/ktor/server/application/Application;)Ljava/lang/Object;", "createModuleContainer", "applicationEntryClass", "Lkotlin/reflect/KClass;", "executeModuleFunction", "", "classLoader", "Ljava/lang/ClassLoader;", "fqName", "", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: CallableUtils.kt */
public final class CallableUtilsKt {
    public static final void executeModuleFunction(ApplicationEnvironment applicationEnvironment, ClassLoader classLoader, String str, Application application) {
        Intrinsics.checkNotNullParameter(applicationEnvironment, "<this>");
        Intrinsics.checkNotNullParameter(classLoader, "classLoader");
        Intrinsics.checkNotNullParameter(str, "fqName");
        Intrinsics.checkNotNullParameter(application, "application");
        char[] charArray = ".#".toCharArray();
        Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
        int lastIndexOfAny$default = StringsKt.lastIndexOfAny$default((CharSequence) str, charArray, 0, false, 6, (Object) null);
        if (lastIndexOfAny$default != -1) {
            String substring = str.substring(0, lastIndexOfAny$default);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            String substring2 = str.substring(lastIndexOfAny$default + 1);
            Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
            Class<?> loadClassOrNull = AutoReloadUtilsKt.loadClassOrNull(classLoader, substring);
            if (loadClassOrNull != null) {
                Method[] methods = loadClassOrNull.getMethods();
                Intrinsics.checkNotNullExpressionValue(methods, "clazz.methods");
                Collection arrayList = new ArrayList();
                for (Object obj : (Object[]) methods) {
                    Method method = (Method) obj;
                    if (Intrinsics.areEqual((Object) method.getName(), (Object) substring2) && Modifier.isStatic(method.getModifiers())) {
                        arrayList.add(obj);
                    }
                }
                Collection arrayList2 = new ArrayList();
                for (Method method2 : (List) arrayList) {
                    Intrinsics.checkNotNullExpressionValue(method2, "it");
                    KFunction<?> kotlinFunction = ReflectJvmMapping.getKotlinFunction(method2);
                    if (kotlinFunction != null) {
                        arrayList2.add(kotlinFunction);
                    }
                }
                Collection arrayList3 = new ArrayList();
                for (Object next : (List) arrayList2) {
                    if (AutoReloadUtilsKt.isApplicableFunction((KFunction) next)) {
                        arrayList3.add(next);
                    }
                }
                KFunction bestFunction = AutoReloadUtilsKt.bestFunction((List) arrayList3);
                if (bestFunction != null) {
                    Iterable<KParameter> parameters = bestFunction.getParameters();
                    if (!(parameters instanceof Collection) || !((Collection) parameters).isEmpty()) {
                        for (KParameter kind : parameters) {
                            if (kind.getKind() == KParameter.Kind.INSTANCE) {
                            }
                        }
                    }
                    callFunctionWithInjection(applicationEnvironment, (Object) null, bestFunction, application);
                    return;
                }
                try {
                    if (Function1.class.isAssignableFrom(loadClassOrNull)) {
                        Constructor[] declaredConstructors = loadClassOrNull.getDeclaredConstructors();
                        Intrinsics.checkNotNullExpressionValue(declaredConstructors, "clazz.declaredConstructors");
                        Constructor constructor = (Constructor) ArraysKt.single((T[]) (Object[]) declaredConstructors);
                        if (constructor.getParameterCount() == 0) {
                            constructor.setAccessible(true);
                            Object newInstance = constructor.newInstance((Object[]) null);
                            Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Function1<io.ktor.server.application.Application, kotlin.Unit>");
                            ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(newInstance, 1)).invoke(application);
                            return;
                        }
                        throw new ReloadingException("Module function with captured variables cannot be instantiated '" + str + '\'');
                    }
                } catch (NoSuchMethodError unused) {
                }
                KClass<?> takeIfNotFacade = AutoReloadUtilsKt.takeIfNotFacade(loadClassOrNull);
                if (takeIfNotFacade != null) {
                    Collection arrayList4 = new ArrayList();
                    for (Object next2 : KClasses.getFunctions(takeIfNotFacade)) {
                        KFunction kFunction = (KFunction) next2;
                        if (Intrinsics.areEqual((Object) kFunction.getName(), (Object) substring2) && AutoReloadUtilsKt.isApplicableFunction(kFunction)) {
                            arrayList4.add(next2);
                        }
                    }
                    KFunction bestFunction2 = AutoReloadUtilsKt.bestFunction((List) arrayList4);
                    if (bestFunction2 != null) {
                        callFunctionWithInjection(applicationEnvironment, createModuleContainer(applicationEnvironment, takeIfNotFacade, application), bestFunction2, application);
                        return;
                    }
                    throw new ClassNotFoundException("Module function cannot be found for the fully qualified name '" + str + '\'');
                }
                throw new ReloadingException("Module function cannot be found for the fully qualified name '" + str + '\'');
            }
            throw new ReloadingException("Module function cannot be found for the fully qualified name '" + str + '\'');
        }
        throw new ReloadingException("Module function cannot be found for the fully qualified name '" + str + '\'');
    }

    private static final Object createModuleContainer(ApplicationEnvironment applicationEnvironment, KClass<?> kClass, Application application) {
        Object objectInstance = kClass.getObjectInstance();
        if (objectInstance != null) {
            return objectInstance;
        }
        Collection arrayList = new ArrayList();
        for (Object next : kClass.getConstructors()) {
            Iterable parameters = ((KFunction) next).getParameters();
            if (!(parameters instanceof Collection) || !((Collection) parameters).isEmpty()) {
                Iterator it = parameters.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    KParameter kParameter = (KParameter) it.next();
                    if (!kParameter.isOptional() && !AutoReloadUtilsKt.isApplicationEnvironment(kParameter) && !AutoReloadUtilsKt.isApplication(kParameter)) {
                        break;
                    }
                }
            }
            arrayList.add(next);
        }
        KFunction bestFunction = AutoReloadUtilsKt.bestFunction((List) arrayList);
        if (bestFunction != null) {
            return callFunctionWithInjection(applicationEnvironment, (Object) null, bestFunction, application);
        }
        throw new RuntimeException("There are no applicable constructors found in class " + kClass);
    }

    /* JADX WARNING: type inference failed for: r6v0, types: [java.lang.Object] */
    /* JADX WARNING: type inference failed for: r8v0, types: [io.ktor.server.application.Application] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final <R> R callFunctionWithInjection(io.ktor.server.application.ApplicationEnvironment r5, java.lang.Object r6, kotlin.reflect.KFunction<? extends R> r7, io.ktor.server.application.Application r8) {
        /*
            java.util.List r0 = r7.getParameters()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Iterator r0 = r0.iterator()
        L_0x0011:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0028
            java.lang.Object r2 = r0.next()
            r3 = r2
            kotlin.reflect.KParameter r3 = (kotlin.reflect.KParameter) r3
            boolean r3 = r3.isOptional()
            if (r3 != 0) goto L_0x0011
            r1.add(r2)
            goto L_0x0011
        L_0x0028:
            java.util.List r1 = (java.util.List) r1
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            r0 = 10
            int r0 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r1, r0)
            int r0 = kotlin.collections.MapsKt.mapCapacity(r0)
            r2 = 16
            int r0 = kotlin.ranges.RangesKt.coerceAtLeast((int) r0, (int) r2)
            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
            r2.<init>(r0)
            java.util.Map r2 = (java.util.Map) r2
            java.util.Iterator r0 = r1.iterator()
        L_0x0047:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0109
            java.lang.Object r1 = r0.next()
            kotlin.reflect.KParameter r1 = (kotlin.reflect.KParameter) r1
            kotlin.reflect.KParameter$Kind r3 = r1.getKind()
            kotlin.reflect.KParameter$Kind r4 = kotlin.reflect.KParameter.Kind.INSTANCE
            if (r3 != r4) goto L_0x005d
            r3 = r6
            goto L_0x006c
        L_0x005d:
            boolean r3 = io.ktor.server.engine.internal.AutoReloadUtilsKt.isApplicationEnvironment(r1)
            if (r3 == 0) goto L_0x0065
            r3 = r5
            goto L_0x006c
        L_0x0065:
            boolean r3 = io.ktor.server.engine.internal.AutoReloadUtilsKt.isApplication(r1)
            if (r3 == 0) goto L_0x0070
            r3 = r8
        L_0x006c:
            r2.put(r1, r3)
            goto L_0x0047
        L_0x0070:
            kotlin.reflect.KType r5 = r1.getType()
            java.lang.String r5 = r5.toString()
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            java.lang.String r6 = "Application"
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r7 = 0
            r8 = 2
            r0 = 0
            boolean r5 = kotlin.text.StringsKt.contains$default((java.lang.CharSequence) r5, (java.lang.CharSequence) r6, (boolean) r7, (int) r8, (java.lang.Object) r0)
            if (r5 == 0) goto L_0x00dc
            kotlin.reflect.KType r5 = r1.getType()
            java.lang.reflect.Type r5 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaType(r5)
            boolean r6 = r5 instanceof java.lang.Class
            if (r6 == 0) goto L_0x0096
            java.lang.Class r5 = (java.lang.Class) r5
            goto L_0x0097
        L_0x0096:
            r5 = r0
        L_0x0097:
            if (r5 == 0) goto L_0x009d
            java.lang.ClassLoader r0 = r5.getClassLoader()
        L_0x009d:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Parameter type "
            r6.<init>(r7)
            kotlin.reflect.KType r7 = r1.getType()
            r6.append(r7)
            java.lang.String r7 = ":{"
            r6.append(r7)
            r6.append(r0)
            java.lang.String r8 = "} is not supported.Application is loaded as "
            r6.append(r8)
            java.lang.Class r8 = io.ktor.server.engine.internal.AutoReloadUtilsKt.getApplicationClassInstance()
            r6.append(r8)
            r6.append(r7)
            java.lang.Class r7 = io.ktor.server.engine.internal.AutoReloadUtilsKt.getApplicationClassInstance()
            java.lang.ClassLoader r7 = r7.getClassLoader()
            r6.append(r7)
            r7 = 125(0x7d, float:1.75E-43)
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L_0x00dc:
            java.lang.IllegalArgumentException r5 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r7 = "Parameter type '"
            r6.<init>(r7)
            kotlin.reflect.KType r7 = r1.getType()
            r6.append(r7)
            java.lang.String r7 = "' of parameter '"
            r6.append(r7)
            java.lang.String r7 = r1.getName()
            if (r7 != 0) goto L_0x00f9
            java.lang.String r7 = "<receiver>"
        L_0x00f9:
            r6.append(r7)
            java.lang.String r7 = "' is not supported"
            r6.append(r7)
            java.lang.String r6 = r6.toString()
            r5.<init>(r6)
            throw r5
        L_0x0109:
            java.lang.Object r5 = r7.callBy(r2)     // Catch:{ InvocationTargetException -> 0x010e }
            return r5
        L_0x010e:
            r5 = move-exception
            java.lang.Throwable r6 = r5.getCause()
            if (r6 != 0) goto L_0x0118
            r6 = r5
            java.lang.Throwable r6 = (java.lang.Throwable) r6
        L_0x0118:
            goto L_0x011a
        L_0x0119:
            throw r6
        L_0x011a:
            goto L_0x0119
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.internal.CallableUtilsKt.callFunctionWithInjection(io.ktor.server.application.ApplicationEnvironment, java.lang.Object, kotlin.reflect.KFunction, io.ktor.server.application.Application):java.lang.Object");
    }
}
