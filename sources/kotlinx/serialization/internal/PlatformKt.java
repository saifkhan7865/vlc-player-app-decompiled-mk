package kotlinx.serialization.internal;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Polymorphic;
import kotlinx.serialization.PolymorphicSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationException;

@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0018\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aO\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\"\u0010\u0006\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00010\u0007\"\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001H\u0002¢\u0006\u0002\u0010\b\u001a\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\fH\u0000\u001a\u0012\u0010\r\u001a\u0004\u0018\u00010\u0003*\u0006\u0012\u0002\b\u00030\u0005H\u0002\u001a$\u0010\u000e\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\fH\u0000\u001aM\u0010\u000f\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00052\"\u0010\u0006\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00010\u0007\"\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001H\u0000¢\u0006\u0002\u0010\b\u001aM\u0010\u000f\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\f2\"\u0010\u0006\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00010\u0007\"\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001H\u0000¢\u0006\u0002\u0010\u0010\u001a\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0002\u001a$\u0010\u0012\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0002\u001a&\u0010\u0013\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u0015H\b¢\u0006\u0002\u0010\u0016\u001a\u0015\u0010\u0013\u001a\u00020\n*\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0015H\b\u001a$\u0010\u0018\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0002\u001a\u001c\u0010\u0019\u001a\u00020\n\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0002\u001a\u001c\u0010\u001a\u001a\u00020\n\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0002\u001a\u0010\u0010\u001b\u001a\u00020\u001c*\u0006\u0012\u0002\b\u00030\fH\u0000\u001a\u0010\u0010\u001d\u001a\u00020\u001c*\u0006\u0012\u0002\b\u00030\u0005H\u0000\u001aK\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001f0\u0007\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\n\b\u0001\u0010\u001f*\u0004\u0018\u0001H\u0002*\u0012\u0012\u0004\u0012\u0002H\u001f0 j\b\u0012\u0004\u0012\u0002H\u001f`!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u00020\fH\u0000¢\u0006\u0002\u0010#¨\u0006$"}, d2 = {"invokeSerializerOnCompanion", "Lkotlinx/serialization/KSerializer;", "T", "", "jClass", "Ljava/lang/Class;", "args", "", "(Ljava/lang/Class;[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/KSerializer;", "isReferenceArray", "", "rootClass", "Lkotlin/reflect/KClass;", "companionOrNull", "compiledSerializerImpl", "constructSerializerForGivenTypeArgs", "(Lkotlin/reflect/KClass;[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/KSerializer;", "createEnumSerializer", "findObjectSerializer", "getChecked", "index", "", "([Ljava/lang/Object;I)Ljava/lang/Object;", "", "interfaceSerializer", "isNotAnnotated", "isPolymorphicSerializer", "platformSpecificSerializerNotRegistered", "", "serializerNotRegistered", "toNativeArrayImpl", "E", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "eClass", "(Ljava/util/ArrayList;Lkotlin/reflect/KClass;)[Ljava/lang/Object;", "kotlinx-serialization-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Platform.kt */
public final class PlatformKt {
    public static final <T> T getChecked(T[] tArr, int i) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr[i];
    }

    public static final boolean getChecked(boolean[] zArr, int i) {
        Intrinsics.checkNotNullParameter(zArr, "<this>");
        return zArr[i];
    }

    public static final <T> KSerializer<T> compiledSerializerImpl(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        return constructSerializerForGivenTypeArgs(kClass, (KSerializer<Object>[]) new KSerializer[0]);
    }

    public static final <T, E extends T> E[] toNativeArrayImpl(ArrayList<E> arrayList, KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(arrayList, "<this>");
        Intrinsics.checkNotNullParameter(kClass, "eClass");
        Object newInstance = Array.newInstance(JvmClassMappingKt.getJavaClass(kClass), arrayList.size());
        Intrinsics.checkNotNull(newInstance, "null cannot be cast to non-null type kotlin.Array<E of kotlinx.serialization.internal.PlatformKt.toNativeArrayImpl>");
        E[] array = arrayList.toArray((Object[]) newInstance);
        Intrinsics.checkNotNullExpressionValue(array, "toArray(java.lang.reflec….java, size) as Array<E>)");
        return array;
    }

    public static final Void platformSpecificSerializerNotRegistered(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Platform_commonKt.serializerNotRegistered(kClass);
        throw new KotlinNothingValueException();
    }

    public static final Void serializerNotRegistered(Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "<this>");
        throw new SerializationException(Platform_commonKt.notRegisteredMessage(JvmClassMappingKt.getKotlinClass(cls)));
    }

    public static final <T> KSerializer<T> constructSerializerForGivenTypeArgs(KClass<T> kClass, KSerializer<Object>... kSerializerArr) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Intrinsics.checkNotNullParameter(kSerializerArr, "args");
        return constructSerializerForGivenTypeArgs(JvmClassMappingKt.getJavaClass(kClass), (KSerializer<Object>[]) (KSerializer[]) Arrays.copyOf(kSerializerArr, kSerializerArr.length));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0068, code lost:
        if (r3 == false) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006a, code lost:
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x006f, code lost:
        r0 = r4.getField("INSTANCE");
     */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0088 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0089  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> kotlinx.serialization.KSerializer<T> constructSerializerForGivenTypeArgs(java.lang.Class<T> r8, kotlinx.serialization.KSerializer<java.lang.Object>... r9) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "args"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            boolean r0 = r8.isEnum()
            if (r0 == 0) goto L_0x001b
            boolean r0 = isNotAnnotated(r8)
            if (r0 == 0) goto L_0x001b
            kotlinx.serialization.KSerializer r8 = createEnumSerializer(r8)
            return r8
        L_0x001b:
            boolean r0 = r8.isInterface()
            if (r0 == 0) goto L_0x0028
            kotlinx.serialization.KSerializer r0 = interfaceSerializer(r8)
            if (r0 == 0) goto L_0x0028
            return r0
        L_0x0028:
            int r0 = r9.length
            java.lang.Object[] r9 = java.util.Arrays.copyOf(r9, r0)
            kotlinx.serialization.KSerializer[] r9 = (kotlinx.serialization.KSerializer[]) r9
            kotlinx.serialization.KSerializer r9 = invokeSerializerOnCompanion(r8, r9)
            if (r9 == 0) goto L_0x0036
            return r9
        L_0x0036:
            kotlinx.serialization.KSerializer r9 = findObjectSerializer(r8)
            if (r9 == 0) goto L_0x003d
            return r9
        L_0x003d:
            r9 = 0
            java.lang.Class[] r0 = r8.getDeclaredClasses()     // Catch:{ NoSuchFieldException -> 0x0084 }
            java.lang.String r1 = "declaredClasses"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)     // Catch:{ NoSuchFieldException -> 0x0084 }
            java.lang.Object[] r0 = (java.lang.Object[]) r0     // Catch:{ NoSuchFieldException -> 0x0084 }
            int r1 = r0.length     // Catch:{ NoSuchFieldException -> 0x0084 }
            r2 = 0
            r4 = r9
            r3 = 0
        L_0x004d:
            if (r2 >= r1) goto L_0x0068
            r5 = r0[r2]     // Catch:{ NoSuchFieldException -> 0x0084 }
            r6 = r5
            java.lang.Class r6 = (java.lang.Class) r6     // Catch:{ NoSuchFieldException -> 0x0084 }
            java.lang.String r6 = r6.getSimpleName()     // Catch:{ NoSuchFieldException -> 0x0084 }
            java.lang.String r7 = "$serializer"
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)     // Catch:{ NoSuchFieldException -> 0x0084 }
            if (r6 == 0) goto L_0x0065
            if (r3 == 0) goto L_0x0063
            goto L_0x006a
        L_0x0063:
            r3 = 1
            r4 = r5
        L_0x0065:
            int r2 = r2 + 1
            goto L_0x004d
        L_0x0068:
            if (r3 != 0) goto L_0x006b
        L_0x006a:
            r4 = r9
        L_0x006b:
            java.lang.Class r4 = (java.lang.Class) r4     // Catch:{ NoSuchFieldException -> 0x0084 }
            if (r4 == 0) goto L_0x007c
            java.lang.String r0 = "INSTANCE"
            java.lang.reflect.Field r0 = r4.getField(r0)     // Catch:{ NoSuchFieldException -> 0x0084 }
            if (r0 == 0) goto L_0x007c
            java.lang.Object r0 = r0.get(r9)     // Catch:{ NoSuchFieldException -> 0x0084 }
            goto L_0x007d
        L_0x007c:
            r0 = r9
        L_0x007d:
            boolean r1 = r0 instanceof kotlinx.serialization.KSerializer     // Catch:{ NoSuchFieldException -> 0x0084 }
            if (r1 == 0) goto L_0x0085
            kotlinx.serialization.KSerializer r0 = (kotlinx.serialization.KSerializer) r0     // Catch:{ NoSuchFieldException -> 0x0084 }
            goto L_0x0086
        L_0x0084:
        L_0x0085:
            r0 = r9
        L_0x0086:
            if (r0 == 0) goto L_0x0089
            return r0
        L_0x0089:
            boolean r0 = isPolymorphicSerializer(r8)
            if (r0 == 0) goto L_0x009a
            kotlinx.serialization.PolymorphicSerializer r9 = new kotlinx.serialization.PolymorphicSerializer
            kotlin.reflect.KClass r8 = kotlin.jvm.JvmClassMappingKt.getKotlinClass(r8)
            r9.<init>(r8)
            kotlinx.serialization.KSerializer r9 = (kotlinx.serialization.KSerializer) r9
        L_0x009a:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.internal.PlatformKt.constructSerializerForGivenTypeArgs(java.lang.Class, kotlinx.serialization.KSerializer[]):kotlinx.serialization.KSerializer");
    }

    private static final <T> boolean isNotAnnotated(Class<T> cls) {
        return cls.getAnnotation(Serializable.class) == null && cls.getAnnotation(Polymorphic.class) == null;
    }

    private static final <T> boolean isPolymorphicSerializer(Class<T> cls) {
        if (cls.getAnnotation(Polymorphic.class) != null) {
            return true;
        }
        Serializable serializable = (Serializable) cls.getAnnotation(Serializable.class);
        if (serializable == null || !Intrinsics.areEqual((Object) Reflection.getOrCreateKotlinClass(serializable.with()), (Object) Reflection.getOrCreateKotlinClass(PolymorphicSerializer.class))) {
            return false;
        }
        return true;
    }

    private static final <T> KSerializer<T> interfaceSerializer(Class<T> cls) {
        Serializable serializable = (Serializable) cls.getAnnotation(Serializable.class);
        if (serializable == null || Intrinsics.areEqual((Object) Reflection.getOrCreateKotlinClass(serializable.with()), (Object) Reflection.getOrCreateKotlinClass(PolymorphicSerializer.class))) {
            return new PolymorphicSerializer<>(JvmClassMappingKt.getKotlinClass(cls));
        }
        return null;
    }

    private static final <T> KSerializer<T> invokeSerializerOnCompanion(Class<?> cls, KSerializer<Object>... kSerializerArr) {
        Class[] clsArr;
        Object companionOrNull = companionOrNull(cls);
        if (companionOrNull == null) {
            return null;
        }
        try {
            if (kSerializerArr.length == 0) {
                clsArr = new Class[0];
            } else {
                int length = kSerializerArr.length;
                Class[] clsArr2 = new Class[length];
                for (int i = 0; i < length; i++) {
                    clsArr2[i] = KSerializer.class;
                }
                clsArr = clsArr2;
            }
            Object invoke = companionOrNull.getClass().getDeclaredMethod("serializer", (Class[]) Arrays.copyOf(clsArr, clsArr.length)).invoke(companionOrNull, Arrays.copyOf(kSerializerArr, kSerializerArr.length));
            if (invoke instanceof KSerializer) {
                return (KSerializer) invoke;
            }
            return null;
        } catch (NoSuchMethodException unused) {
            return null;
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause != null) {
                String message = cause.getMessage();
                if (message == null) {
                    message = e.getMessage();
                }
                throw new InvocationTargetException(cause, message);
            }
            throw e;
        }
    }

    private static final Object companionOrNull(Class<?> cls) {
        try {
            Field declaredField = cls.getDeclaredField("Companion");
            declaredField.setAccessible(true);
            return declaredField.get((Object) null);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static final <T> KSerializer<T> createEnumSerializer(Class<T> cls) {
        Object[] enumConstants = cls.getEnumConstants();
        String canonicalName = cls.getCanonicalName();
        Intrinsics.checkNotNullExpressionValue(canonicalName, "canonicalName");
        Intrinsics.checkNotNull(enumConstants, "null cannot be cast to non-null type kotlin.Array<out kotlin.Enum<*>>");
        return new EnumSerializer<>(canonicalName, (Enum[]) enumConstants);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0041, code lost:
        if (r5 == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0090, code lost:
        if (r4 == false) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0092, code lost:
        r5 = null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final <T> kotlinx.serialization.KSerializer<T> findObjectSerializer(java.lang.Class<T> r12) {
        /*
            java.lang.reflect.Field[] r0 = r12.getDeclaredFields()
            java.lang.String r1 = "declaredFields"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            int r1 = r0.length
            r2 = 0
            r3 = 0
            r6 = r2
            r4 = 0
            r5 = 0
        L_0x0011:
            r7 = 1
            if (r4 >= r1) goto L_0x0041
            r8 = r0[r4]
            r9 = r8
            java.lang.reflect.Field r9 = (java.lang.reflect.Field) r9
            java.lang.String r10 = r9.getName()
            java.lang.String r11 = "INSTANCE"
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)
            if (r10 == 0) goto L_0x003e
            java.lang.Class r10 = r9.getType()
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r12)
            if (r10 == 0) goto L_0x003e
            int r9 = r9.getModifiers()
            boolean r9 = java.lang.reflect.Modifier.isStatic(r9)
            if (r9 == 0) goto L_0x003e
            if (r5 == 0) goto L_0x003c
            goto L_0x0043
        L_0x003c:
            r6 = r8
            r5 = 1
        L_0x003e:
            int r4 = r4 + 1
            goto L_0x0011
        L_0x0041:
            if (r5 != 0) goto L_0x0044
        L_0x0043:
            r6 = r2
        L_0x0044:
            java.lang.reflect.Field r6 = (java.lang.reflect.Field) r6
            if (r6 != 0) goto L_0x0049
            return r2
        L_0x0049:
            java.lang.Object r0 = r6.get(r2)
            java.lang.reflect.Method[] r12 = r12.getMethods()
            java.lang.String r1 = "methods"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r1)
            java.lang.Object[] r12 = (java.lang.Object[]) r12
            int r1 = r12.length
            r5 = r2
            r4 = 0
        L_0x005b:
            if (r3 >= r1) goto L_0x0090
            r6 = r12[r3]
            r8 = r6
            java.lang.reflect.Method r8 = (java.lang.reflect.Method) r8
            java.lang.String r9 = r8.getName()
            java.lang.String r10 = "serializer"
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r10)
            if (r9 == 0) goto L_0x008d
            java.lang.Class[] r9 = r8.getParameterTypes()
            java.lang.String r10 = "it.parameterTypes"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r10)
            java.lang.Object[] r9 = (java.lang.Object[]) r9
            int r9 = r9.length
            if (r9 != 0) goto L_0x008d
            java.lang.Class r8 = r8.getReturnType()
            java.lang.Class<kotlinx.serialization.KSerializer> r9 = kotlinx.serialization.KSerializer.class
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r9)
            if (r8 == 0) goto L_0x008d
            if (r4 == 0) goto L_0x008b
            goto L_0x0092
        L_0x008b:
            r5 = r6
            r4 = 1
        L_0x008d:
            int r3 = r3 + 1
            goto L_0x005b
        L_0x0090:
            if (r4 != 0) goto L_0x0093
        L_0x0092:
            r5 = r2
        L_0x0093:
            java.lang.reflect.Method r5 = (java.lang.reflect.Method) r5
            if (r5 != 0) goto L_0x0098
            return r2
        L_0x0098:
            java.lang.Object r12 = r5.invoke(r0, r2)
            boolean r0 = r12 instanceof kotlinx.serialization.KSerializer
            if (r0 == 0) goto L_0x00a3
            r2 = r12
            kotlinx.serialization.KSerializer r2 = (kotlinx.serialization.KSerializer) r2
        L_0x00a3:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.internal.PlatformKt.findObjectSerializer(java.lang.Class):kotlinx.serialization.KSerializer");
    }

    public static final boolean isReferenceArray(KClass<Object> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "rootClass");
        return JvmClassMappingKt.getJavaClass(kClass).isArray();
    }
}
