package kotlin.reflect.jvm;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\",\u0010\u0002\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u000e¢\u0006\f\u001a\u0004\b\u0002\u0010\u0004\"\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"value", "", "isAccessible", "Lkotlin/reflect/KCallable;", "(Lkotlin/reflect/KCallable;)Z", "setAccessible", "(Lkotlin/reflect/KCallable;Z)V", "kotlin-reflection"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: KCallablesJvm.kt */
public final class KCallablesJvm {
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00bf, code lost:
        r5 = r5.getDefaultCaller();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean isAccessible(kotlin.reflect.KCallable<?> r5) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            boolean r0 = r5 instanceof kotlin.reflect.KMutableProperty
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0039
            r0 = r5
            kotlin.reflect.KProperty r0 = (kotlin.reflect.KProperty) r0
            java.lang.reflect.Field r3 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaField(r0)
            if (r3 == 0) goto L_0x001a
            boolean r3 = r3.isAccessible()
            if (r3 == 0) goto L_0x0036
        L_0x001a:
            java.lang.reflect.Method r0 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaGetter(r0)
            if (r0 == 0) goto L_0x0026
            boolean r0 = r0.isAccessible()
            if (r0 == 0) goto L_0x0036
        L_0x0026:
            kotlin.reflect.KMutableProperty r5 = (kotlin.reflect.KMutableProperty) r5
            java.lang.reflect.Method r5 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaSetter(r5)
            if (r5 == 0) goto L_0x00e6
            boolean r5 = r5.isAccessible()
            if (r5 == 0) goto L_0x0036
            goto L_0x00e6
        L_0x0036:
            r1 = 0
            goto L_0x00e6
        L_0x0039:
            boolean r0 = r5 instanceof kotlin.reflect.KProperty
            if (r0 == 0) goto L_0x0059
            kotlin.reflect.KProperty r5 = (kotlin.reflect.KProperty) r5
            java.lang.reflect.Field r0 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaField(r5)
            if (r0 == 0) goto L_0x004b
            boolean r0 = r0.isAccessible()
            if (r0 == 0) goto L_0x0036
        L_0x004b:
            java.lang.reflect.Method r5 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaGetter(r5)
            if (r5 == 0) goto L_0x00e6
            boolean r5 = r5.isAccessible()
            if (r5 == 0) goto L_0x0036
            goto L_0x00e6
        L_0x0059:
            boolean r0 = r5 instanceof kotlin.reflect.KProperty.Getter
            if (r0 == 0) goto L_0x007f
            r0 = r5
            kotlin.reflect.KProperty$Getter r0 = (kotlin.reflect.KProperty.Getter) r0
            kotlin.reflect.KProperty r0 = r0.getProperty()
            java.lang.reflect.Field r0 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaField(r0)
            if (r0 == 0) goto L_0x0070
            boolean r0 = r0.isAccessible()
            if (r0 == 0) goto L_0x0036
        L_0x0070:
            kotlin.reflect.KFunction r5 = (kotlin.reflect.KFunction) r5
            java.lang.reflect.Method r5 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaMethod(r5)
            if (r5 == 0) goto L_0x00e6
            boolean r5 = r5.isAccessible()
            if (r5 == 0) goto L_0x0036
            goto L_0x00e6
        L_0x007f:
            boolean r0 = r5 instanceof kotlin.reflect.KMutableProperty.Setter
            if (r0 == 0) goto L_0x00a5
            r0 = r5
            kotlin.reflect.KMutableProperty$Setter r0 = (kotlin.reflect.KMutableProperty.Setter) r0
            kotlin.reflect.KProperty r0 = r0.getProperty()
            java.lang.reflect.Field r0 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaField(r0)
            if (r0 == 0) goto L_0x0096
            boolean r0 = r0.isAccessible()
            if (r0 == 0) goto L_0x0036
        L_0x0096:
            kotlin.reflect.KFunction r5 = (kotlin.reflect.KFunction) r5
            java.lang.reflect.Method r5 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaMethod(r5)
            if (r5 == 0) goto L_0x00e6
            boolean r5 = r5.isAccessible()
            if (r5 == 0) goto L_0x0036
            goto L_0x00e6
        L_0x00a5:
            boolean r0 = r5 instanceof kotlin.reflect.KFunction
            if (r0 == 0) goto L_0x00e7
            r0 = r5
            kotlin.reflect.KFunction r0 = (kotlin.reflect.KFunction) r0
            java.lang.reflect.Method r3 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaMethod(r0)
            if (r3 == 0) goto L_0x00b8
            boolean r3 = r3.isAccessible()
            if (r3 == 0) goto L_0x0036
        L_0x00b8:
            kotlin.reflect.jvm.internal.KCallableImpl r5 = kotlin.reflect.jvm.internal.UtilKt.asKCallableImpl(r5)
            r3 = 0
            if (r5 == 0) goto L_0x00ca
            kotlin.reflect.jvm.internal.calls.Caller r5 = r5.getDefaultCaller()
            if (r5 == 0) goto L_0x00ca
            java.lang.reflect.Member r5 = r5.getMember()
            goto L_0x00cb
        L_0x00ca:
            r5 = r3
        L_0x00cb:
            boolean r4 = r5 instanceof java.lang.reflect.AccessibleObject
            if (r4 == 0) goto L_0x00d2
            r3 = r5
            java.lang.reflect.AccessibleObject r3 = (java.lang.reflect.AccessibleObject) r3
        L_0x00d2:
            if (r3 == 0) goto L_0x00da
            boolean r5 = r3.isAccessible()
            if (r5 == 0) goto L_0x0036
        L_0x00da:
            java.lang.reflect.Constructor r5 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaConstructor(r0)
            if (r5 == 0) goto L_0x00e6
            boolean r5 = r5.isAccessible()
            if (r5 == 0) goto L_0x0036
        L_0x00e6:
            return r1
        L_0x00e7:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unknown callable: "
            r1.<init>(r2)
            r1.append(r5)
            java.lang.String r2 = " ("
            r1.append(r2)
            java.lang.Class r5 = r5.getClass()
            r1.append(r5)
            r5 = 41
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r0.<init>(r5)
            goto L_0x010d
        L_0x010c:
            throw r0
        L_0x010d:
            goto L_0x010c
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.KCallablesJvm.isAccessible(kotlin.reflect.KCallable):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00a9, code lost:
        r3 = r3.getDefaultCaller();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void setAccessible(kotlin.reflect.KCallable<?> r3, boolean r4) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            boolean r0 = r3 instanceof kotlin.reflect.KMutableProperty
            if (r0 == 0) goto L_0x002f
            r0 = r3
            kotlin.reflect.KProperty r0 = (kotlin.reflect.KProperty) r0
            java.lang.reflect.Field r1 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaField(r0)
            if (r1 != 0) goto L_0x0013
            goto L_0x0016
        L_0x0013:
            r1.setAccessible(r4)
        L_0x0016:
            java.lang.reflect.Method r0 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaGetter(r0)
            if (r0 != 0) goto L_0x001d
            goto L_0x0020
        L_0x001d:
            r0.setAccessible(r4)
        L_0x0020:
            kotlin.reflect.KMutableProperty r3 = (kotlin.reflect.KMutableProperty) r3
            java.lang.reflect.Method r3 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaSetter(r3)
            if (r3 != 0) goto L_0x002a
            goto L_0x00cd
        L_0x002a:
            r3.setAccessible(r4)
            goto L_0x00cd
        L_0x002f:
            boolean r0 = r3 instanceof kotlin.reflect.KProperty
            if (r0 == 0) goto L_0x004c
            kotlin.reflect.KProperty r3 = (kotlin.reflect.KProperty) r3
            java.lang.reflect.Field r0 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaField(r3)
            if (r0 != 0) goto L_0x003c
            goto L_0x003f
        L_0x003c:
            r0.setAccessible(r4)
        L_0x003f:
            java.lang.reflect.Method r3 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaGetter(r3)
            if (r3 != 0) goto L_0x0047
            goto L_0x00cd
        L_0x0047:
            r3.setAccessible(r4)
            goto L_0x00cd
        L_0x004c:
            boolean r0 = r3 instanceof kotlin.reflect.KProperty.Getter
            if (r0 == 0) goto L_0x006f
            r0 = r3
            kotlin.reflect.KProperty$Getter r0 = (kotlin.reflect.KProperty.Getter) r0
            kotlin.reflect.KProperty r0 = r0.getProperty()
            java.lang.reflect.Field r0 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaField(r0)
            if (r0 != 0) goto L_0x005e
            goto L_0x0061
        L_0x005e:
            r0.setAccessible(r4)
        L_0x0061:
            kotlin.reflect.KFunction r3 = (kotlin.reflect.KFunction) r3
            java.lang.reflect.Method r3 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaMethod(r3)
            if (r3 != 0) goto L_0x006b
            goto L_0x00cd
        L_0x006b:
            r3.setAccessible(r4)
            goto L_0x00cd
        L_0x006f:
            boolean r0 = r3 instanceof kotlin.reflect.KMutableProperty.Setter
            if (r0 == 0) goto L_0x0091
            r0 = r3
            kotlin.reflect.KMutableProperty$Setter r0 = (kotlin.reflect.KMutableProperty.Setter) r0
            kotlin.reflect.KProperty r0 = r0.getProperty()
            java.lang.reflect.Field r0 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaField(r0)
            if (r0 != 0) goto L_0x0081
            goto L_0x0084
        L_0x0081:
            r0.setAccessible(r4)
        L_0x0084:
            kotlin.reflect.KFunction r3 = (kotlin.reflect.KFunction) r3
            java.lang.reflect.Method r3 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaMethod(r3)
            if (r3 != 0) goto L_0x008d
            goto L_0x00cd
        L_0x008d:
            r3.setAccessible(r4)
            goto L_0x00cd
        L_0x0091:
            boolean r0 = r3 instanceof kotlin.reflect.KFunction
            if (r0 == 0) goto L_0x00ce
            r0 = r3
            kotlin.reflect.KFunction r0 = (kotlin.reflect.KFunction) r0
            java.lang.reflect.Method r1 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaMethod(r0)
            if (r1 != 0) goto L_0x009f
            goto L_0x00a2
        L_0x009f:
            r1.setAccessible(r4)
        L_0x00a2:
            kotlin.reflect.jvm.internal.KCallableImpl r3 = kotlin.reflect.jvm.internal.UtilKt.asKCallableImpl(r3)
            r1 = 0
            if (r3 == 0) goto L_0x00b4
            kotlin.reflect.jvm.internal.calls.Caller r3 = r3.getDefaultCaller()
            if (r3 == 0) goto L_0x00b4
            java.lang.reflect.Member r3 = r3.getMember()
            goto L_0x00b5
        L_0x00b4:
            r3 = r1
        L_0x00b5:
            boolean r2 = r3 instanceof java.lang.reflect.AccessibleObject
            if (r2 == 0) goto L_0x00bc
            r1 = r3
            java.lang.reflect.AccessibleObject r1 = (java.lang.reflect.AccessibleObject) r1
        L_0x00bc:
            if (r1 != 0) goto L_0x00bf
            goto L_0x00c3
        L_0x00bf:
            r3 = 1
            r1.setAccessible(r3)
        L_0x00c3:
            java.lang.reflect.Constructor r3 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaConstructor(r0)
            if (r3 != 0) goto L_0x00ca
            goto L_0x00cd
        L_0x00ca:
            r3.setAccessible(r4)
        L_0x00cd:
            return
        L_0x00ce:
            java.lang.UnsupportedOperationException r4 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Unknown callable: "
            r0.<init>(r1)
            r0.append(r3)
            java.lang.String r1 = " ("
            r0.append(r1)
            java.lang.Class r3 = r3.getClass()
            r0.append(r3)
            r3 = 41
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r4.<init>(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.KCallablesJvm.setAccessible(kotlin.reflect.KCallable, boolean):void");
    }
}
