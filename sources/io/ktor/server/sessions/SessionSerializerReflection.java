package io.ktor.server.sessions;

import io.ktor.http.CodecsKt;
import io.ktor.http.HttpUrlEncodedKt;
import io.ktor.http.Parameters;
import io.ktor.http.QueryKt;
import io.ktor.util.StringValues;
import io.ktor.util.StringValuesKt;
import j$.util.Optional;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.full.KClassifiers;
import kotlin.reflect.jvm.KTypesJvm;
import kotlin.reflect.jvm.ReflectJvmMapping;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u001e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0010#\n\u0002\b\u0002\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\b\u0017\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006B\u000f\b\u0000\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ7\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0001\u0010\u00122\u0006\u0010\u0013\u001a\u0002H\u00122\u0010\u0010\u0014\u001a\f\u0012\u0004\u0012\u0002H\u0012\u0012\u0002\b\u00030\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002H\u0002¢\u0006\u0002\u0010\u0017J\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u00022\u0006\u0010\u0004\u001a\u00020\b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002H\u0002J\u0015\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u001bH\u0016¢\u0006\u0002\u0010\u001cJ\u0014\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u001e2\u0006\u0010\u0016\u001a\u00020\u001bH\u0002J\u0018\u0010\u001f\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030 2\u0006\u0010\u0016\u001a\u00020\u001bH\u0002J-\u0010!\u001a\u0002H\"\"\b\b\u0001\u0010\"*\u00020\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\"0\u00052\u0006\u0010#\u001a\u00020\u001bH\u0002¢\u0006\u0002\u0010$J\u001e\u0010%\u001a\u0004\u0018\u00010\u00022\n\u0010&\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0016\u001a\u00020\u001bH\u0002J.\u0010'\u001a\b\u0012\u0004\u0012\u0002H\u00010(\"\b\b\u0001\u0010\u0001*\u00020\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00052\u0006\u0010)\u001a\u00020*H\u0002J0\u0010+\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\u0005\"\b\b\u0001\u0010\u0001*\u00020\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00052\u0006\u0010)\u001a\u00020*H\u0002J\u0016\u0010,\u001a\b\u0012\u0002\b\u0003\u0018\u00010-2\u0006\u0010\u0004\u001a\u00020\bH\u0002J\u0010\u0010.\u001a\u00020/2\u0006\u0010\u0004\u001a\u00020\bH\u0002J\u0010\u00100\u001a\u00020/2\u0006\u0010\u0004\u001a\u00020\bH\u0002J\u0010\u00101\u001a\u00020/2\u0006\u0010\u0004\u001a\u00020\bH\u0002J\u0010\u00102\u001a\u00020/2\u0006\u0010\u0004\u001a\u00020\bH\u0002J-\u00103\u001a\u0002H\u0001\"\b\b\u0001\u0010\u0001*\u00020\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00052\u0006\u0010)\u001a\u00020*H\u0002¢\u0006\u0002\u00104J\u0015\u00105\u001a\u00020\u001b2\u0006\u00106\u001a\u00028\u0000H\u0016¢\u0006\u0002\u00107J\u001f\u00108\u001a\u00020\u001b\"\b\b\u0001\u0010\u0001*\u00020\u00022\u0006\u0010\u0016\u001a\u0002H\u0001H\u0002¢\u0006\u0002\u00107J\u0014\u00109\u001a\u00020\u001b2\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030:H\u0002J\u0018\u0010;\u001a\u00020\u001b2\u000e\u0010\u0016\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030 H\u0002J\u0012\u0010<\u001a\u00020\u001b2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002H\u0002J!\u0010=\u001a\u0002H\u0001\"\b\b\u0001\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u0005H\u0002¢\u0006\u0002\u0010>J6\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00050\u001e\"\b\b\u0001\u0010\u0001*\u00020\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00050\u001e2\u0006\u0010\u0004\u001a\u00020\bH\u0002J*\u0010@\u001a\n\u0012\u0004\u0012\u0002H\u0001\u0018\u00010\u0005\"\b\b\u0001\u0010\u0001*\u00020\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00050\u001eH\u0002J\u0010\u0010A\u001a\u0006\u0012\u0002\b\u00030-*\u00020BH\u0002J\u0010\u0010A\u001a\u0006\u0012\u0002\b\u00030-*\u00020\bH\u0002J,\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00050\u001e\"\b\b\u0001\u0010\u0001*\u00020\u0002*\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u001eH\u0002J=\u0010D\u001a\u0002HE\"\u0004\b\u0001\u0010E*\u0006\u0012\u0002\b\u00030F2\u001f\u0010G\u001a\u001b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020F\u0012\u0004\u0012\u0002HE0H¢\u0006\u0002\bIH\b¢\u0006\u0002\u0010JJI\u0010D\u001a\u0002HE\"\u0004\b\u0001\u0010E*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030K2'\u0010G\u001a#\u0012\u0014\u0012\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020K\u0012\u0004\u0012\u0002HE0H¢\u0006\u0002\bIH\b¢\u0006\u0002\u0010LJ=\u0010D\u001a\u0002HE\"\u0004\b\u0001\u0010E*\u0006\u0012\u0002\b\u00030M2\u001f\u0010G\u001a\u001b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020M\u0012\u0004\u0012\u0002HE0H¢\u0006\u0002\bIH\b¢\u0006\u0002\u0010NR\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u000e\n\u0000\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006O"}, d2 = {"Lio/ktor/server/sessions/SessionSerializerReflection;", "T", "", "Lio/ktor/server/sessions/SessionSerializer;", "type", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)V", "typeInfo", "Lkotlin/reflect/KType;", "(Lkotlin/reflect/KType;)V", "getType$annotations", "()V", "getType", "()Lkotlin/reflect/KClass;", "getTypeInfo$ktor_server_sessions", "()Lkotlin/reflect/KType;", "assignValue", "", "X", "instance", "p", "Lkotlin/reflect/KProperty1;", "value", "(Ljava/lang/Object;Lkotlin/reflect/KProperty1;Ljava/lang/Object;)V", "coerceType", "deserialize", "text", "", "(Ljava/lang/String;)Ljava/lang/Object;", "deserializeCollection", "", "deserializeMap", "", "deserializeObject", "Y", "encoded", "(Lkotlin/reflect/KClass;Ljava/lang/String;)Ljava/lang/Object;", "deserializeValue", "owner", "findConstructor", "Lkotlin/reflect/KFunction;", "bundle", "Lio/ktor/util/StringValues;", "findParticularType", "getRawType", "Ljava/lang/Class;", "isEnumType", "", "isListType", "isMapType", "isSetType", "newInstance", "(Lkotlin/reflect/KClass;Lio/ktor/util/StringValues;)Ljava/lang/Object;", "serialize", "session", "(Ljava/lang/Object;)Ljava/lang/String;", "serializeClassInstance", "serializeCollection", "", "serializeMap", "serializeValue", "callNoArgConstructor", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "filterAssignable", "firstHasNoArgConstructor", "toJavaClass", "Ljava/lang/reflect/Type;", "toTypedList", "withUnsafe", "R", "", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "", "(Ljava/util/Set;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.ERROR, message = "Don't refer to the implementation class directly. Use interface type if possible or use defaultSessionSerializer function to create.")
/* compiled from: SessionSerializerReflection.kt */
public final class SessionSerializerReflection<T> implements SessionSerializer<T> {
    private final KClass<T> type;
    private final KType typeInfo;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SessionSerializerReflection.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                kotlin.reflect.KParameter$Kind[] r0 = kotlin.reflect.KParameter.Kind.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                kotlin.reflect.KParameter$Kind r1 = kotlin.reflect.KParameter.Kind.INSTANCE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                kotlin.reflect.KParameter$Kind r1 = kotlin.reflect.KParameter.Kind.EXTENSION_RECEIVER     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                kotlin.reflect.KParameter$Kind r1 = kotlin.reflect.KParameter.Kind.VALUE     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.sessions.SessionSerializerReflection.WhenMappings.<clinit>():void");
        }
    }

    public static /* synthetic */ void getType$annotations() {
    }

    public SessionSerializerReflection(KType kType) {
        Intrinsics.checkNotNullParameter(kType, "typeInfo");
        this.typeInfo = kType;
        KClass<?> jvmErasure = KTypesJvm.getJvmErasure(kType);
        Intrinsics.checkNotNull(jvmErasure, "null cannot be cast to non-null type kotlin.reflect.KClass<T of io.ktor.server.sessions.SessionSerializerReflection>");
        this.type = jvmErasure;
    }

    public final KType getTypeInfo$ktor_server_sessions() {
        return this.typeInfo;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Use defaultSessionSerializer() function instead")
    public SessionSerializerReflection(KClass<T> kClass) {
        this(KClassifiers.getStarProjectedType(kClass));
        Intrinsics.checkNotNullParameter(kClass, "type");
    }

    public final KClass<T> getType() {
        return this.type;
    }

    public T deserialize(String str) {
        Intrinsics.checkNotNullParameter(str, "text");
        T parseQueryString$default = QueryKt.parseQueryString$default(str, 0, 0, false, 14, (Object) null);
        if (!Intrinsics.areEqual((Object) this.type, (Object) Reflection.getOrCreateKotlinClass(Parameters.class))) {
            return deserializeObject(this.type, str);
        }
        Intrinsics.checkNotNull(parseQueryString$default, "null cannot be cast to non-null type T of io.ktor.server.sessions.SessionSerializerReflection");
        return (Object) parseQueryString$default;
    }

    public String serialize(T t) {
        Intrinsics.checkNotNullParameter(t, "session");
        if (Intrinsics.areEqual((Object) this.type, (Object) Reflection.getOrCreateKotlinClass(Parameters.class))) {
            return HttpUrlEncodedKt.formUrlEncode((Parameters) t);
        }
        return serializeClassInstance(SessionSerializerReflectionKt.cast(t, this.type));
    }

    /* JADX WARNING: type inference failed for: r8v0, types: [kotlin.reflect.KClass, kotlin.reflect.KClass<T>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <T> T newInstance(kotlin.reflect.KClass<T> r8, io.ktor.util.StringValues r9) {
        /*
            r7 = this;
            java.lang.Object r0 = r8.getObjectInstance()
            if (r0 == 0) goto L_0x0007
            return r0
        L_0x0007:
            kotlin.reflect.KFunction r0 = r7.findConstructor(r8, r9)
            java.util.List r1 = r0.getParameters()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            r2 = 10
            int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r1, r2)
            int r2 = kotlin.collections.MapsKt.mapCapacity(r2)
            r3 = 16
            int r2 = kotlin.ranges.RangesKt.coerceAtLeast((int) r2, (int) r3)
            java.util.LinkedHashMap r3 = new java.util.LinkedHashMap
            r3.<init>(r2)
            java.util.Map r3 = (java.util.Map) r3
            java.util.Iterator r1 = r1.iterator()
        L_0x002c:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x007e
            java.lang.Object r2 = r1.next()
            kotlin.reflect.KParameter r2 = (kotlin.reflect.KParameter) r2
            kotlin.reflect.KParameter$Kind r4 = r2.getKind()
            int[] r5 = io.ktor.server.sessions.SessionSerializerReflection.WhenMappings.$EnumSwitchMapping$0
            int r4 = r4.ordinal()
            r4 = r5[r4]
            r5 = 1
            if (r4 == r5) goto L_0x0076
            r5 = 2
            if (r4 == r5) goto L_0x0076
            r5 = 3
            if (r4 != r5) goto L_0x0070
            kotlin.reflect.KType r4 = r2.getType()
            kotlin.reflect.KType r5 = r2.getType()
            kotlin.reflect.KClass r5 = kotlin.reflect.jvm.KTypesJvm.getJvmErasure((kotlin.reflect.KType) r5)
            java.lang.String r6 = r2.getName()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            java.lang.String r6 = r9.get(r6)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            java.lang.Object r5 = r7.deserializeValue(r5, r6)
            java.lang.Object r4 = r7.coerceType(r4, r5)
            goto L_0x007a
        L_0x0070:
            kotlin.NoWhenBranchMatchedException r8 = new kotlin.NoWhenBranchMatchedException
            r8.<init>()
            throw r8
        L_0x0076:
            kotlin.reflect.KClass r4 = r7.findParticularType(r8, r9)
        L_0x007a:
            r3.put(r2, r4)
            goto L_0x002c
        L_0x007e:
            java.lang.Object r8 = r0.callBy(r3)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.sessions.SessionSerializerReflection.newInstance(kotlin.reflect.KClass, io.ktor.util.StringValues):java.lang.Object");
    }

    private final <T> KClass<? extends T> findParticularType(KClass<T> kClass, StringValues stringValues) {
        Object obj;
        if (kClass.isSealed()) {
            String str = stringValues.get("$type");
            if (str != null) {
                Iterator it = kClass.getSealedSubclasses().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (Intrinsics.areEqual((Object) ((KClass) obj).getSimpleName(), (Object) str)) {
                        break;
                    }
                }
                KClass<? extends T> kClass2 = (KClass) obj;
                if (kClass2 != null) {
                    return kClass2;
                }
                throw new IllegalStateException(("No sealed subclass " + str + " found in " + kClass).toString());
            }
            throw new IllegalStateException(("No typeToken found for sealed " + kClass).toString());
        } else if (!kClass.isAbstract()) {
            return kClass;
        } else {
            throw new IllegalStateException(("Abstract types are not supported: " + kClass).toString());
        }
    }

    private final <T> KFunction<T> findConstructor(KClass<T> kClass, StringValues stringValues) {
        Object obj = null;
        if (kClass.isSealed()) {
            return findConstructor(findParticularType(kClass, stringValues), StringValuesKt.filter$default(stringValues, false, SessionSerializerReflection$findConstructor$filtered$1.INSTANCE, 1, (Object) null));
        }
        if (!kClass.isAbstract()) {
            String str = stringValues.get("$type");
            if (str != null && !Intrinsics.areEqual((Object) kClass.getSimpleName(), (Object) str)) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            } else if (kClass.getObjectInstance() != null) {
                KProperty1.Getter getter = SessionSerializerReflection$findConstructor$getter$1.INSTANCE.getGetter();
                Intrinsics.checkNotNull(getter, "null cannot be cast to non-null type kotlin.reflect.KFunction<T of io.ktor.server.sessions.SessionSerializerReflection.findConstructor>");
                return getter;
            } else {
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
                            if (kParameter.getName() == null) {
                                break;
                            }
                            String name = kParameter.getName();
                            Intrinsics.checkNotNull(name);
                            if (!stringValues.contains(name)) {
                                break;
                            }
                        }
                    }
                    arrayList.add(next);
                }
                Iterator it2 = ((List) arrayList).iterator();
                if (it2.hasNext()) {
                    obj = it2.next();
                    if (it2.hasNext()) {
                        int size = ((KFunction) obj).getParameters().size();
                        do {
                            Object next2 = it2.next();
                            int size2 = ((KFunction) next2).getParameters().size();
                            if (size < size2) {
                                obj = next2;
                                size = size2;
                            }
                        } while (it2.hasNext());
                    }
                }
                KFunction<T> kFunction = (KFunction) obj;
                if (kFunction != null) {
                    return kFunction;
                }
                throw new IllegalArgumentException("Couldn't instantiate " + kClass + " for parameters " + stringValues.names());
            }
        } else {
            throw new IllegalStateException(("Abstract types are not supported: " + kClass).toString());
        }
    }

    private final <X> void assignValue(X x, KProperty1<X, ?> kProperty1, Object obj) {
        Object obj2 = kProperty1.get(x);
        if (isListType(kProperty1.getReturnType())) {
            if (!(obj instanceof List)) {
                assignValue(x, kProperty1, coerceType(kProperty1.getReturnType(), obj));
            } else if (kProperty1 instanceof KMutableProperty1) {
                ((KMutableProperty1) kProperty1).getSetter().call(x, coerceType(kProperty1.getReturnType(), obj));
            } else if (TypeIntrinsics.isMutableList(obj2)) {
                List list = (List) obj2;
                Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.MutableList<kotlin.Any?>");
                List asMutableList = TypeIntrinsics.asMutableList(list);
                asMutableList.clear();
                asMutableList.addAll((Collection) obj);
            } else {
                throw new IllegalStateException("Couldn't inject property " + kProperty1.getName() + " from value " + obj);
            }
        } else if (isSetType(kProperty1.getReturnType())) {
            if (!(obj instanceof Set)) {
                assignValue(x, kProperty1, coerceType(kProperty1.getReturnType(), obj));
            } else if (kProperty1 instanceof KMutableProperty1) {
                ((KMutableProperty1) kProperty1).getSetter().call(x, coerceType(kProperty1.getReturnType(), obj));
            } else if (TypeIntrinsics.isMutableSet(obj2)) {
                Set set = (Set) obj2;
                Intrinsics.checkNotNull(set, "null cannot be cast to non-null type kotlin.collections.MutableSet<kotlin.Any?>");
                Set asMutableSet = TypeIntrinsics.asMutableSet(set);
                asMutableSet.clear();
                asMutableSet.addAll((Collection) obj);
            } else {
                throw new IllegalStateException("Couldn't inject property " + kProperty1.getName() + " from value " + obj);
            }
        } else if (isMapType(kProperty1.getReturnType())) {
            if (!(obj instanceof Map)) {
                assignValue(x, kProperty1, coerceType(kProperty1.getReturnType(), obj));
            } else if (kProperty1 instanceof KMutableProperty1) {
                ((KMutableProperty1) kProperty1).getSetter().call(x, coerceType(kProperty1.getReturnType(), obj));
            } else if (TypeIntrinsics.isMutableMap(obj2)) {
                Map map = (Map) obj2;
                Intrinsics.checkNotNull(map, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.Any?, kotlin.Any?>");
                Map asMutableMap = TypeIntrinsics.asMutableMap(map);
                asMutableMap.clear();
                asMutableMap.putAll((Map) obj);
            } else {
                throw new IllegalStateException("Couldn't inject property " + kProperty1.getName() + " from value " + obj);
            }
        } else if (!(kProperty1 instanceof KMutableProperty1)) {
        } else {
            if (obj != null || kProperty1.getReturnType().isMarkedNullable()) {
                ((KMutableProperty1) kProperty1).getSetter().call(x, coerceType(kProperty1.getReturnType(), obj));
                return;
            }
            throw new IllegalArgumentException("Couldn't inject null to property " + kProperty1.getName());
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v27, resolved type: java.util.Set} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v38, resolved type: java.util.List} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v17, resolved type: java.util.Map} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v40, resolved type: java.util.Set} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v41, resolved type: java.util.Set} */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0098, code lost:
        if (r1 != 0) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x018c, code lost:
        if (r1 != null) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x02cb, code lost:
        if (r2 != null) goto L_0x0373;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.Object coerceType(kotlin.reflect.KType r12, java.lang.Object r13) {
        /*
            r11 = this;
            r0 = 0
            if (r13 != 0) goto L_0x0006
            r13 = r0
            goto L_0x03b1
        L_0x0006:
            boolean r1 = r11.isListType(r12)
            r2 = 10
            r3 = 2
            r4 = 1
            java.lang.String r5 = " to "
            java.lang.String r6 = "Couldn't coerce type "
            r7 = 0
            if (r1 == 0) goto L_0x00f1
            boolean r0 = r13 instanceof java.util.List
            if (r0 != 0) goto L_0x0029
            boolean r1 = r13 instanceof java.lang.Iterable
            if (r1 == 0) goto L_0x0029
            java.lang.Iterable r13 = (java.lang.Iterable) r13
            java.util.List r13 = kotlin.collections.CollectionsKt.toList(r13)
            java.lang.Object r13 = r11.coerceType(r12, r13)
            goto L_0x03b1
        L_0x0029:
            if (r0 == 0) goto L_0x00d5
            java.util.List r0 = r12.getArguments()
            java.lang.Object r0 = kotlin.collections.CollectionsKt.single(r0)
            kotlin.reflect.KTypeProjection r0 = (kotlin.reflect.KTypeProjection) r0
            kotlin.reflect.KType r0 = r0.getType()
            if (r0 == 0) goto L_0x00b9
            kotlin.reflect.KClass[] r1 = new kotlin.reflect.KClass[r3]
            java.lang.Class r3 = r11.toJavaClass((kotlin.reflect.KType) r12)
            kotlin.reflect.KClass r3 = kotlin.jvm.JvmClassMappingKt.getKotlinClass(r3)
            r1[r7] = r3
            java.lang.Class<java.util.ArrayList> r3 = java.util.ArrayList.class
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            r1[r4] = r3
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            java.util.List r1 = r11.toTypedList(r1)
            java.util.List r1 = r11.filterAssignable(r1, r12)
            kotlin.reflect.KClass r1 = r11.firstHasNoArgConstructor(r1)
            if (r1 == 0) goto L_0x009d
            java.lang.Object r1 = r11.callNoArgConstructor(r1)
            java.util.List r1 = (java.util.List) r1
            if (r1 == 0) goto L_0x009d
            java.util.List r1 = kotlin.jvm.internal.TypeIntrinsics.asMutableList(r1)
            r3 = r13
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.ArrayList r4 = new java.util.ArrayList
            int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r3, r2)
            r4.<init>(r2)
            java.util.Collection r4 = (java.util.Collection) r4
            java.util.Iterator r2 = r3.iterator()
        L_0x007f:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0091
            java.lang.Object r3 = r2.next()
            java.lang.Object r3 = r11.coerceType(r0, r3)
            r4.add(r3)
            goto L_0x007f
        L_0x0091:
            java.util.List r4 = (java.util.List) r4
            java.util.Collection r4 = (java.util.Collection) r4
            r1.addAll(r4)
            if (r1 == 0) goto L_0x009d
        L_0x009a:
            r13 = r1
            goto L_0x03b1
        L_0x009d:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r6)
            java.lang.Class r13 = r13.getClass()
            r1.append(r13)
            r1.append(r5)
            r1.append(r12)
            java.lang.String r12 = r1.toString()
            r0.<init>(r12)
            throw r0
        L_0x00b9:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Star projections are not supported for list element: "
            r0.<init>(r1)
            java.util.List r12 = r12.getArguments()
            java.lang.Object r12 = r12.get(r7)
            r0.append(r12)
            java.lang.String r12 = r0.toString()
            r13.<init>(r12)
            throw r13
        L_0x00d5:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r6)
            java.lang.Class r13 = r13.getClass()
            r1.append(r13)
            r1.append(r5)
            r1.append(r12)
            java.lang.String r12 = r1.toString()
            r0.<init>(r12)
            throw r0
        L_0x00f1:
            boolean r1 = r11.isSetType(r12)
            r8 = 3
            r9 = 4
            if (r1 == 0) goto L_0x01e4
            boolean r0 = r13 instanceof java.util.Set
            if (r0 != 0) goto L_0x010d
            boolean r1 = r13 instanceof java.lang.Iterable
            if (r1 == 0) goto L_0x010d
            java.lang.Iterable r13 = (java.lang.Iterable) r13
            java.util.Set r13 = kotlin.collections.CollectionsKt.toSet(r13)
            java.lang.Object r13 = r11.coerceType(r12, r13)
            goto L_0x03b1
        L_0x010d:
            if (r0 == 0) goto L_0x01c8
            java.util.List r0 = r12.getArguments()
            java.lang.Object r0 = kotlin.collections.CollectionsKt.single(r0)
            kotlin.reflect.KTypeProjection r0 = (kotlin.reflect.KTypeProjection) r0
            kotlin.reflect.KType r0 = r0.getType()
            if (r0 == 0) goto L_0x01ac
            kotlin.reflect.KClass[] r1 = new kotlin.reflect.KClass[r9]
            java.lang.Class r9 = r11.toJavaClass((kotlin.reflect.KType) r12)
            kotlin.reflect.KClass r9 = kotlin.jvm.JvmClassMappingKt.getKotlinClass(r9)
            r1[r7] = r9
            java.lang.Class<java.util.LinkedHashSet> r7 = java.util.LinkedHashSet.class
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)
            r1[r4] = r7
            java.lang.Class<java.util.HashSet> r4 = java.util.HashSet.class
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)
            r1[r3] = r4
            java.lang.Class<java.util.TreeSet> r3 = java.util.TreeSet.class
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            r1[r8] = r3
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            java.util.List r1 = r11.toTypedList(r1)
            java.util.List r1 = r11.filterAssignable(r1, r12)
            kotlin.reflect.KClass r1 = r11.firstHasNoArgConstructor(r1)
            if (r1 == 0) goto L_0x0190
            java.lang.Object r1 = r11.callNoArgConstructor(r1)
            java.util.Set r1 = (java.util.Set) r1
            if (r1 == 0) goto L_0x0190
            java.util.Set r1 = kotlin.jvm.internal.TypeIntrinsics.asMutableSet(r1)
            r3 = r13
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.ArrayList r4 = new java.util.ArrayList
            int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r3, r2)
            r4.<init>(r2)
            java.util.Collection r4 = (java.util.Collection) r4
            java.util.Iterator r2 = r3.iterator()
        L_0x0173:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0185
            java.lang.Object r3 = r2.next()
            java.lang.Object r3 = r11.coerceType(r0, r3)
            r4.add(r3)
            goto L_0x0173
        L_0x0185:
            java.util.List r4 = (java.util.List) r4
            java.util.Collection r4 = (java.util.Collection) r4
            r1.addAll(r4)
            if (r1 == 0) goto L_0x0190
            goto L_0x009a
        L_0x0190:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r6)
            java.lang.Class r13 = r13.getClass()
            r1.append(r13)
            r1.append(r5)
            r1.append(r12)
            java.lang.String r12 = r1.toString()
            r0.<init>(r12)
            throw r0
        L_0x01ac:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Star projections are not supported for set element: "
            r0.<init>(r1)
            java.util.List r12 = r12.getArguments()
            java.lang.Object r12 = r12.get(r7)
            r0.append(r12)
            java.lang.String r12 = r0.toString()
            r13.<init>(r12)
            throw r13
        L_0x01c8:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r6)
            java.lang.Class r13 = r13.getClass()
            r1.append(r13)
            r1.append(r5)
            r1.append(r12)
            java.lang.String r12 = r1.toString()
            r0.<init>(r12)
            throw r0
        L_0x01e4:
            boolean r1 = r11.isMapType(r12)
            if (r1 == 0) goto L_0x033f
            boolean r0 = r13 instanceof java.util.Map
            if (r0 == 0) goto L_0x0323
            java.util.List r0 = r12.getArguments()
            java.lang.Object r0 = r0.get(r7)
            kotlin.reflect.KTypeProjection r0 = (kotlin.reflect.KTypeProjection) r0
            kotlin.reflect.KType r0 = r0.getType()
            if (r0 == 0) goto L_0x0307
            java.util.List r1 = r12.getArguments()
            java.lang.Object r1 = r1.get(r4)
            kotlin.reflect.KTypeProjection r1 = (kotlin.reflect.KTypeProjection) r1
            kotlin.reflect.KType r1 = r1.getType()
            if (r1 == 0) goto L_0x02eb
            r2 = 5
            kotlin.reflect.KClass[] r2 = new kotlin.reflect.KClass[r2]
            java.lang.Class r10 = r11.toJavaClass((kotlin.reflect.KType) r12)
            kotlin.reflect.KClass r10 = kotlin.jvm.JvmClassMappingKt.getKotlinClass(r10)
            r2[r7] = r10
            java.lang.Class<java.util.LinkedHashMap> r7 = java.util.LinkedHashMap.class
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)
            r2[r4] = r7
            java.lang.Class<java.util.HashMap> r4 = java.util.HashMap.class
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)
            r2[r3] = r4
            java.lang.Class<java.util.TreeMap> r3 = java.util.TreeMap.class
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            r2[r8] = r3
            java.lang.Class<j$.util.concurrent.ConcurrentHashMap> r3 = j$.util.concurrent.ConcurrentHashMap.class
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            r2[r9] = r3
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            java.util.List r2 = r11.toTypedList(r2)
            java.util.List r2 = r11.filterAssignable(r2, r12)
            kotlin.reflect.KClass r2 = r11.firstHasNoArgConstructor(r2)
            if (r2 == 0) goto L_0x02cf
            java.lang.Object r2 = r11.callNoArgConstructor(r2)
            java.util.Map r2 = (java.util.Map) r2
            if (r2 == 0) goto L_0x02cf
            java.util.Map r2 = kotlin.jvm.internal.TypeIntrinsics.asMutableMap(r2)
            r3 = r13
            java.util.Map r3 = (java.util.Map) r3
            java.util.LinkedHashMap r4 = new java.util.LinkedHashMap
            int r7 = r3.size()
            int r7 = kotlin.collections.MapsKt.mapCapacity(r7)
            r4.<init>(r7)
            java.util.Map r4 = (java.util.Map) r4
            java.util.Set r3 = r3.entrySet()
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.Iterator r3 = r3.iterator()
        L_0x0275:
            boolean r7 = r3.hasNext()
            if (r7 == 0) goto L_0x0291
            java.lang.Object r7 = r3.next()
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7
            java.lang.Object r8 = r7.getKey()
            java.lang.Object r8 = r11.coerceType(r0, r8)
            java.lang.Object r7 = r7.getValue()
            r4.put(r8, r7)
            goto L_0x0275
        L_0x0291:
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            int r3 = r4.size()
            int r3 = kotlin.collections.MapsKt.mapCapacity(r3)
            r0.<init>(r3)
            java.util.Map r0 = (java.util.Map) r0
            java.util.Set r3 = r4.entrySet()
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.Iterator r3 = r3.iterator()
        L_0x02aa:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x02c8
            java.lang.Object r4 = r3.next()
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r7 = r4.getKey()
            java.lang.Object r4 = r4.getValue()
            java.lang.Object r4 = r11.coerceType(r1, r4)     // Catch:{ all -> 0x02c6 }
            r0.put(r7, r4)
            goto L_0x02aa
        L_0x02c6:
            r12 = move-exception
            throw r12
        L_0x02c8:
            r2.putAll(r0)
            if (r2 == 0) goto L_0x02cf
            goto L_0x0373
        L_0x02cf:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r6)
            java.lang.Class r13 = r13.getClass()
            r1.append(r13)
            r1.append(r5)
            r1.append(r12)
            java.lang.String r12 = r1.toString()
            r0.<init>(r12)
            throw r0
        L_0x02eb:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Star projections are not supported for map value "
            r0.<init>(r1)
            java.util.List r12 = r12.getArguments()
            java.lang.Object r12 = r12.get(r4)
            r0.append(r12)
            java.lang.String r12 = r0.toString()
            r13.<init>(r12)
            throw r13
        L_0x0307:
            java.lang.IllegalArgumentException r13 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Star projections are not supported for map key: "
            r0.<init>(r1)
            java.util.List r12 = r12.getArguments()
            java.lang.Object r12 = r12.get(r7)
            r0.append(r12)
            java.lang.String r12 = r0.toString()
            r13.<init>(r12)
            throw r13
        L_0x0323:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r6)
            java.lang.Class r13 = r13.getClass()
            r1.append(r13)
            r1.append(r5)
            r1.append(r12)
            java.lang.String r12 = r1.toString()
            r0.<init>(r12)
            throw r0
        L_0x033f:
            boolean r1 = r11.isEnumType(r12)
            if (r1 == 0) goto L_0x0380
            java.lang.reflect.Type r12 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaType(r12)
            java.lang.Class r12 = r11.toJavaClass((java.lang.reflect.Type) r12)
            java.lang.Object[] r12 = r12.getEnumConstants()
            java.lang.String r1 = "type.javaType.toJavaClass().enumConstants"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r1)
            int r1 = r12.length
        L_0x0358:
            if (r7 >= r1) goto L_0x0378
            r2 = r12[r7]
            boolean r3 = r2 instanceof java.lang.Enum
            if (r3 == 0) goto L_0x0364
            r3 = r2
            java.lang.Enum r3 = (java.lang.Enum) r3
            goto L_0x0365
        L_0x0364:
            r3 = r0
        L_0x0365:
            if (r3 == 0) goto L_0x036c
            java.lang.String r3 = r3.name()
            goto L_0x036d
        L_0x036c:
            r3 = r0
        L_0x036d:
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r13)
            if (r3 == 0) goto L_0x0375
        L_0x0373:
            r13 = r2
            goto L_0x03b1
        L_0x0375:
            int r7 = r7 + 1
            goto L_0x0358
        L_0x0378:
            java.util.NoSuchElementException r12 = new java.util.NoSuchElementException
            java.lang.String r13 = "Array contains no element matching the predicate."
            r12.<init>(r13)
            throw r12
        L_0x0380:
            java.lang.Class r0 = r11.toJavaClass((kotlin.reflect.KType) r12)
            java.lang.Class r1 = java.lang.Float.TYPE
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)
            if (r0 == 0) goto L_0x039b
            boolean r0 = r13 instanceof java.lang.Number
            if (r0 == 0) goto L_0x039b
            java.lang.Number r13 = (java.lang.Number) r13
            float r12 = r13.floatValue()
            java.lang.Float r13 = java.lang.Float.valueOf(r12)
            goto L_0x03b1
        L_0x039b:
            java.lang.Class r12 = r11.toJavaClass((kotlin.reflect.KType) r12)
            java.lang.Class<java.util.UUID> r0 = java.util.UUID.class
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r0)
            if (r12 == 0) goto L_0x03b1
            boolean r12 = r13 instanceof java.lang.String
            if (r12 == 0) goto L_0x03b1
            java.lang.String r13 = (java.lang.String) r13
            java.util.UUID r13 = java.util.UUID.fromString(r13)
        L_0x03b1:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.sessions.SessionSerializerReflection.coerceType(kotlin.reflect.KType, java.lang.Object):java.lang.Object");
    }

    private final <R> R withUnsafe(List<?> list, Function1<? super List<Object>, ? extends R> function1) {
        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.MutableList<kotlin.Any?>");
        return function1.invoke(TypeIntrinsics.asMutableList(list));
    }

    private final <R> R withUnsafe(Set<?> set, Function1<? super Set<Object>, ? extends R> function1) {
        Intrinsics.checkNotNull(set, "null cannot be cast to non-null type kotlin.collections.MutableSet<kotlin.Any?>");
        return function1.invoke(TypeIntrinsics.asMutableSet(set));
    }

    private final <R> R withUnsafe(Map<?, ?> map, Function1<? super Map<Object, Object>, ? extends R> function1) {
        Intrinsics.checkNotNull(map, "null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.Any?, kotlin.Any?>");
        return function1.invoke(TypeIntrinsics.asMutableMap(map));
    }

    private final <T> List<KClass<T>> toTypedList(List<? extends KClass<?>> list) {
        Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<kotlin.reflect.KClass<T of io.ktor.server.sessions.SessionSerializerReflection.toTypedList>>");
        return list;
    }

    private final Class<?> toJavaClass(KType kType) {
        return toJavaClass(ReflectJvmMapping.getJavaType(kType));
    }

    private final Class<?> toJavaClass(Type type2) {
        if (type2 instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) type2).getRawType();
            Intrinsics.checkNotNullExpressionValue(rawType, "this.rawType");
            return toJavaClass(rawType);
        } else if (type2 instanceof Class) {
            return (Class) type2;
        } else {
            throw new IllegalArgumentException("Bad type " + type2);
        }
    }

    private final <T> List<KClass<T>> filterAssignable(List<? extends KClass<T>> list, KType kType) {
        Collection arrayList = new ArrayList();
        for (Object next : list) {
            if (toJavaClass(kType).isAssignableFrom(JvmClassMappingKt.getJavaClass((KClass) next))) {
                arrayList.add(next);
            }
        }
        return (List) arrayList;
    }

    private final <T> KClass<T> firstHasNoArgConstructor(List<? extends KClass<T>> list) {
        Object obj;
        Iterator it = list.iterator();
        loop0:
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            Iterable<KFunction> constructors = ((KClass) obj).getConstructors();
            if (!(constructors instanceof Collection) || !((Collection) constructors).isEmpty()) {
                for (KFunction parameters : constructors) {
                    if (parameters.getParameters().isEmpty()) {
                        break loop0;
                    }
                }
                continue;
            }
        }
        return (KClass) obj;
    }

    private final <T> T callNoArgConstructor(KClass<T> kClass) {
        for (KFunction kFunction : kClass.getConstructors()) {
            if (kFunction.getParameters().isEmpty()) {
                return kFunction.call(new Object[0]);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    private final Object deserializeValue(KClass<?> kClass, String str) {
        boolean z = false;
        if (StringsKt.startsWith$default(str, "#", false, 2, (Object) null)) {
            CharSequence charSequence = str;
            Character orNull = StringsKt.getOrNull(charSequence, 1);
            if (orNull == null || orNull.charValue() == 'n') {
                return null;
            }
            if (orNull != null && orNull.charValue() == 'i') {
                return Integer.valueOf(Integer.parseInt(StringsKt.drop(str, 2)));
            }
            if (orNull != null && orNull.charValue() == 'l') {
                return Long.valueOf(Long.parseLong(StringsKt.drop(str, 2)));
            }
            if (orNull != null && orNull.charValue() == 'f') {
                return Double.valueOf(Double.parseDouble(StringsKt.drop(str, 2)));
            }
            if (orNull != null && orNull.charValue() == 'b') {
                Character orNull2 = StringsKt.getOrNull(charSequence, 2);
                if (orNull2 != null && orNull2.charValue() == 'o') {
                    Character orNull3 = StringsKt.getOrNull(charSequence, 3);
                    if (orNull3 != null && orNull3.charValue() == 't') {
                        z = true;
                    } else if (orNull3 == null || orNull3.charValue() != 'f') {
                        throw new IllegalArgumentException("Unsupported bo-value " + StringsKt.take(str, 4));
                    }
                    return Boolean.valueOf(z);
                } else if (orNull2 != null && orNull2.charValue() == 'd') {
                    return new BigDecimal(StringsKt.drop(str, 3));
                } else {
                    if (orNull2 != null && orNull2.charValue() == 'i') {
                        return new BigInteger(StringsKt.drop(str, 3));
                    }
                    throw new IllegalArgumentException("Unsupported b-type " + StringsKt.take(str, 3));
                }
            } else if (orNull != null && orNull.charValue() == 'o') {
                Character orNull4 = StringsKt.getOrNull(charSequence, 2);
                if (orNull4 != null && orNull4.charValue() == 'm') {
                    return Optional.empty();
                }
                if (orNull4 != null && orNull4.charValue() == 'p') {
                    return Optional.ofNullable(deserializeValue(kClass, StringsKt.drop(str, 3)));
                }
                throw new IllegalArgumentException("Unsupported o-value " + StringsKt.take(str, 3));
            } else if (orNull != null && orNull.charValue() == 's') {
                return StringsKt.drop(str, 2);
            } else {
                if (orNull != null && orNull.charValue() == 'c') {
                    Character orNull5 = StringsKt.getOrNull(charSequence, 2);
                    if (orNull5 != null && orNull5.charValue() == 'l') {
                        return deserializeCollection(StringsKt.drop(str, 3));
                    }
                    if (orNull5 != null && orNull5.charValue() == 's') {
                        return CollectionsKt.toSet(deserializeCollection(StringsKt.drop(str, 3)));
                    }
                    if (orNull5 != null && orNull5.charValue() == 'h') {
                        return Character.valueOf(StringsKt.first(StringsKt.drop(str, 3)));
                    }
                    throw new IllegalArgumentException("Unsupported c-type " + StringsKt.take(str, 3));
                } else if (orNull != null && orNull.charValue() == 'm') {
                    return deserializeMap(StringsKt.drop(str, 2));
                } else {
                    if (orNull != null && orNull.charValue() == '#') {
                        return deserializeObject(kClass, StringsKt.drop(str, 2));
                    }
                    throw new IllegalArgumentException("Unsupported type " + StringsKt.take(str, 2));
                }
            }
        } else {
            throw new IllegalArgumentException("Bad serialized value");
        }
    }

    /* access modifiers changed from: private */
    public final String serializeValue(Object obj) {
        if (obj == null) {
            return "#n";
        }
        if (obj instanceof Integer) {
            return "#i" + obj;
        } else if (obj instanceof Long) {
            return "#l" + obj;
        } else if (obj instanceof Float) {
            return "#f" + obj;
        } else if (obj instanceof Double) {
            return "#f" + obj;
        } else if (obj instanceof Boolean) {
            return "#bo" + StringsKt.first(String.valueOf(((Boolean) obj).booleanValue()));
        } else if (obj instanceof Character) {
            return "#ch" + obj;
        } else if (obj instanceof BigDecimal) {
            return "#bd" + obj;
        } else if (obj instanceof BigInteger) {
            return "#bi" + obj;
        } else if (obj instanceof Optional) {
            Optional optional = (Optional) obj;
            if (!optional.isPresent()) {
                return "#om";
            }
            return "#op" + serializeValue(optional.get());
        } else if (obj instanceof String) {
            return "#s" + obj;
        } else if (obj instanceof List) {
            return "#cl" + serializeCollection((Collection) obj);
        } else if (obj instanceof Set) {
            return "#cs" + serializeCollection((Collection) obj);
        } else if (obj instanceof Map) {
            return "#m" + serializeMap((Map) obj);
        } else if (obj instanceof Enum) {
            return "#s" + ((Enum) obj).name();
        } else if (obj instanceof UUID) {
            return "#s" + obj;
        } else {
            return "##" + serializeClassInstance(obj);
        }
    }

    private final <T> String serializeClassInstance(T t) {
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(t.getClass());
        Iterable<KProperty1> sortedWith = CollectionsKt.sortedWith(KClasses.getMemberProperties(orCreateKotlinClass), new SessionSerializerReflection$serializeClassInstance$$inlined$sortedBy$1());
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(sortedWith, 10));
        for (KProperty1 kProperty1 : sortedWith) {
            arrayList.add(TuplesKt.to(kProperty1.getName(), serializeValue(kProperty1.get(t))));
        }
        List list = (List) arrayList;
        if (orCreateKotlinClass.getSimpleName() != null) {
            Iterable superclasses = KClasses.getSuperclasses(orCreateKotlinClass);
            if (!(superclasses instanceof Collection) || !((Collection) superclasses).isEmpty()) {
                Iterator it = superclasses.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (((KClass) it.next()).isSealed()) {
                            String simpleName = orCreateKotlinClass.getSimpleName();
                            Intrinsics.checkNotNull(simpleName);
                            list = CollectionsKt.plus(list, new Pair("$type", simpleName));
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return HttpUrlEncodedKt.formUrlEncode((List<Pair<String, String>>) list);
    }

    private final <Y> Y deserializeObject(KClass<Y> kClass, String str) {
        Parameters parseQueryString$default = QueryKt.parseQueryString$default(str, 0, 0, false, 14, (Object) null);
        Y newInstance = newInstance(kClass, parseQueryString$default);
        for (KProperty1 next : KClasses.getMemberProperties(kClass)) {
            String str2 = parseQueryString$default.get(next.getName());
            if (str2 != null) {
                assignValue(newInstance, next, coerceType(next.getReturnType(), deserializeValue(KTypesJvm.getJvmErasure(next.getReturnType()), str2)));
            }
        }
        return newInstance;
    }

    private final List<?> deserializeCollection(String str) {
        Collection arrayList = new ArrayList();
        for (Object next : StringsKt.split$default((CharSequence) CodecsKt.decodeURLQueryComponent$default(str, 0, 0, false, (Charset) null, 15, (Object) null), new String[]{"&"}, false, 0, 6, (Object) null)) {
            if (((String) next).length() > 0) {
                arrayList.add(next);
            }
        }
        Iterable<String> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (String decodeURLQueryComponent$default : iterable) {
            arrayList2.add(deserializeValue(Reflection.getOrCreateKotlinClass(Object.class), CodecsKt.decodeURLQueryComponent$default(decodeURLQueryComponent$default, 0, 0, false, (Charset) null, 15, (Object) null)));
        }
        return (List) arrayList2;
    }

    private final String serializeCollection(Collection<?> collection) {
        return CodecsKt.encodeURLQueryComponent$default(CollectionsKt.joinToString$default(collection, "&", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new SessionSerializerReflection$serializeCollection$1(this), 30, (Object) null), false, false, (Charset) null, 7, (Object) null);
    }

    private final Map<?, ?> deserializeMap(String str) {
        Collection arrayList = new ArrayList();
        for (Object next : StringsKt.split$default((CharSequence) CodecsKt.decodeURLQueryComponent$default(str, 0, 0, false, (Charset) null, 15, (Object) null), new String[]{"&"}, false, 0, 6, (Object) null)) {
            if (((String) next).length() > 0) {
                arrayList.add(next);
            }
        }
        Iterable<String> iterable = (List) arrayList;
        Map<?, ?> linkedHashMap = new LinkedHashMap<>(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable, 10)), 16));
        for (String str2 : iterable) {
            linkedHashMap.put(deserializeValue(Reflection.getOrCreateKotlinClass(Object.class), CodecsKt.decodeURLQueryComponent$default(StringsKt.substringBefore$default(str2, '=', (String) null, 2, (Object) null), 0, 0, false, (Charset) null, 15, (Object) null)), deserializeValue(Reflection.getOrCreateKotlinClass(Object.class), CodecsKt.decodeURLQueryComponent$default(StringsKt.substringAfter$default(str2, '=', (String) null, 2, (Object) null), 0, 0, false, (Charset) null, 15, (Object) null)));
        }
        return linkedHashMap;
    }

    private final boolean isListType(KType kType) {
        Class<?> rawType = getRawType(kType);
        if (rawType != null) {
            return List.class.isAssignableFrom(rawType);
        }
        return false;
    }

    private final boolean isSetType(KType kType) {
        Class<?> rawType = getRawType(kType);
        if (rawType != null) {
            return Set.class.isAssignableFrom(rawType);
        }
        return false;
    }

    private final boolean isEnumType(KType kType) {
        Class<?> rawType = getRawType(kType);
        if (rawType != null) {
            return Enum.class.isAssignableFrom(rawType);
        }
        return false;
    }

    private final boolean isMapType(KType kType) {
        Class<?> rawType = getRawType(kType);
        if (rawType != null) {
            return Map.class.isAssignableFrom(rawType);
        }
        return false;
    }

    private final Class<?> getRawType(KType kType) {
        Type javaType = ReflectJvmMapping.getJavaType(kType);
        if (javaType instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) javaType).getRawType();
            if (rawType instanceof Class) {
                return (Class) rawType;
            }
            return null;
        } else if (javaType instanceof Class) {
            return (Class) javaType;
        } else {
            return null;
        }
    }

    private final String serializeMap(Map<?, ?> map) {
        Collection arrayList = new ArrayList(map.size());
        for (Map.Entry next : map.entrySet()) {
            arrayList.add(CodecsKt.encodeURLQueryComponent$default(serializeValue(next.getKey()), false, false, (Charset) null, 7, (Object) null) + '=' + CodecsKt.encodeURLQueryComponent$default(serializeValue(next.getValue()), false, false, (Charset) null, 7, (Object) null));
        }
        return CodecsKt.encodeURLQueryComponent$default(CollectionsKt.joinToString$default((List) arrayList, "&", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), false, false, (Charset) null, 7, (Object) null);
    }
}
