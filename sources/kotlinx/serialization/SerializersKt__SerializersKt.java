package kotlinx.serialization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.Triple;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.HashMapSerializer;
import kotlinx.serialization.internal.HashSetSerializer;
import kotlinx.serialization.internal.LinkedHashMapSerializer;
import kotlinx.serialization.internal.LinkedHashSetSerializer;
import kotlinx.serialization.internal.PlatformKt;
import kotlinx.serialization.internal.Platform_commonKt;
import kotlinx.serialization.internal.PrimitivesKt;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleBuildersKt;

@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\u001a\u0014\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001\u001a \u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0001\u001a7\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0010\u0010\b\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\tH\u0001¢\u0006\u0002\u0010\n\u001a\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\f0\u0001\"\u0006\b\u0000\u0010\f\u0018\u0001H\b\u001a6\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007\u001a\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00012\u0006\u0010\u0012\u001a\u00020\u0013\u001a\u0018\u0010\u0014\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u00012\u0006\u0010\u0012\u001a\u00020\u0013\u001aG\u0010\u0015\u001a\f\u0012\u0006\b\u0001\u0012\u00020\r\u0018\u00010\u0001*\b\u0012\u0004\u0012\u00020\r0\u00072\u0014\u0010\u0016\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00010\u000f2\u000e\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0018H\u0002¢\u0006\u0002\b\u001a\u001a7\u0010\u001b\u001a\f\u0012\u0006\b\u0001\u0012\u00020\r\u0018\u00010\u0001*\b\u0012\u0004\u0012\u00020\r0\u00072\u0014\u0010\u0016\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00010\u000fH\u0002¢\u0006\u0002\b\u001c\u001a1\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\f0\u0001\"\b\b\u0000\u0010\f*\u00020\r*\b\u0012\u0004\u0012\u0002H\f0\u00012\u0006\u0010\u001e\u001a\u00020\u0011H\u0002¢\u0006\u0002\b\u001f\u001aB\u0010 \u001a\f\u0012\u0006\b\u0001\u0012\u00020\r\u0018\u00010\u0001*\b\u0012\u0004\u0012\u00020\r0\u00072\u0014\u0010\u0016\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00010\u000f2\u000e\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u0018H\u0000\u001a\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\f0\u0001\"\b\b\u0000\u0010\f*\u00020\r*\b\u0012\u0004\u0012\u0002H\f0\u0007H\u0007\u001a\u001b\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\f0\u0001\"\u0006\b\u0000\u0010\f\u0018\u0001*\u00020\u0005H\b\u001a:\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0001*\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0010\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007\u001a\u001a\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0001*\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013\u001aG\u0010!\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u0001*\u00020\u00052\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\r0\u00072\u0014\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002¢\u0006\u0002\b#\u001a+\u0010$\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u0001*\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u0011H\u0002¢\u0006\u0002\b&\u001a$\u0010\u0014\u001a\n\u0012\u0004\u0012\u0002H\f\u0018\u00010\u0001\"\b\b\u0000\u0010\f*\u00020\r*\b\u0012\u0004\u0012\u0002H\f0\u0007H\u0007\u001a\u001c\u0010\u0014\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\r\u0018\u00010\u0001*\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013\u001a2\u0010'\u001a\u0012\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0001\u0018\u00010\u000f*\u00020\u00052\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00130\u000f2\u0006\u0010%\u001a\u00020\u0011H\u0000¨\u0006)"}, d2 = {"noCompiledSerializer", "Lkotlinx/serialization/KSerializer;", "forClass", "", "module", "Lkotlinx/serialization/modules/SerializersModule;", "kClass", "Lkotlin/reflect/KClass;", "argSerializers", "", "(Lkotlinx/serialization/modules/SerializersModule;Lkotlin/reflect/KClass;[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/KSerializer;", "serializer", "T", "", "typeArgumentsSerializers", "", "isNullable", "", "type", "Lkotlin/reflect/KType;", "serializerOrNull", "builtinParametrizedSerializer", "serializers", "elementClassifierIfArray", "Lkotlin/Function0;", "Lkotlin/reflect/KClassifier;", "builtinParametrizedSerializer$SerializersKt__SerializersKt", "compiledParametrizedSerializer", "compiledParametrizedSerializer$SerializersKt__SerializersKt", "nullable", "shouldBeNullable", "nullable$SerializersKt__SerializersKt", "parametrizedSerializerOrNull", "serializerByKClassImpl", "rootClass", "serializerByKClassImpl$SerializersKt__SerializersKt", "serializerByKTypeImpl", "failOnMissingTypeArgSerializer", "serializerByKTypeImpl$SerializersKt__SerializersKt", "serializersForParameters", "typeArguments", "kotlinx-serialization-core"}, k = 5, mv = {1, 8, 0}, xi = 48, xs = "kotlinx/serialization/SerializersKt")
/* compiled from: Serializers.kt */
final /* synthetic */ class SerializersKt__SerializersKt {
    public static final /* synthetic */ <T> KSerializer<T> serializer() {
        Intrinsics.reifiedOperationMarker(6, "T");
        KSerializer<Object> serializer = SerializersKt.serializer((KType) null);
        Intrinsics.checkNotNull(serializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        KSerializer kSerializer = serializer;
        return serializer;
    }

    public static final /* synthetic */ <T> KSerializer<T> serializer(SerializersModule serializersModule) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.reifiedOperationMarker(6, "T");
        KSerializer<Object> serializer = SerializersKt.serializer(serializersModule, (KType) null);
        Intrinsics.checkNotNull(serializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.internal.Platform_commonKt.cast>");
        KSerializer kSerializer = serializer;
        return serializer;
    }

    public static final KSerializer<Object> serializer(KType kType) {
        Intrinsics.checkNotNullParameter(kType, "type");
        return SerializersKt.serializer(SerializersModuleBuildersKt.EmptySerializersModule(), kType);
    }

    @ExperimentalSerializationApi
    public static final KSerializer<Object> serializer(KClass<?> kClass, List<? extends KSerializer<?>> list, boolean z) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(list, "typeArgumentsSerializers");
        return SerializersKt.serializer(SerializersModuleBuildersKt.EmptySerializersModule(), kClass, list, z);
    }

    public static final KSerializer<Object> serializerOrNull(KType kType) {
        Intrinsics.checkNotNullParameter(kType, "type");
        return SerializersKt.serializerOrNull(SerializersModuleBuildersKt.EmptySerializersModule(), kType);
    }

    public static final KSerializer<Object> serializer(SerializersModule serializersModule, KType kType) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(kType, "type");
        KSerializer<Object> serializerByKTypeImpl$SerializersKt__SerializersKt = serializerByKTypeImpl$SerializersKt__SerializersKt(serializersModule, kType, true);
        if (serializerByKTypeImpl$SerializersKt__SerializersKt != null) {
            return serializerByKTypeImpl$SerializersKt__SerializersKt;
        }
        PlatformKt.platformSpecificSerializerNotRegistered(Platform_commonKt.kclass(kType));
        throw new KotlinNothingValueException();
    }

    @ExperimentalSerializationApi
    public static final KSerializer<Object> serializer(SerializersModule serializersModule, KClass<?> kClass, List<? extends KSerializer<?>> list, boolean z) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(list, "typeArgumentsSerializers");
        KSerializer<Object> serializerByKClassImpl$SerializersKt__SerializersKt = serializerByKClassImpl$SerializersKt__SerializersKt(serializersModule, kClass, list, z);
        if (serializerByKClassImpl$SerializersKt__SerializersKt != null) {
            return serializerByKClassImpl$SerializersKt__SerializersKt;
        }
        PlatformKt.platformSpecificSerializerNotRegistered(kClass);
        throw new KotlinNothingValueException();
    }

    public static final KSerializer<Object> serializerOrNull(SerializersModule serializersModule, KType kType) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(kType, "type");
        return serializerByKTypeImpl$SerializersKt__SerializersKt(serializersModule, kType, false);
    }

    private static final KSerializer<Object> serializerByKTypeImpl$SerializersKt__SerializersKt(SerializersModule serializersModule, KType kType, boolean z) {
        KSerializer<Object> kSerializer;
        KSerializer<? extends Object> kSerializer2;
        KClass<Object> kclass = Platform_commonKt.kclass(kType);
        boolean isMarkedNullable = kType.isMarkedNullable();
        Iterable<KTypeProjection> arguments = kType.getArguments();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
        for (KTypeProjection type : arguments) {
            KType type2 = type.getType();
            if (type2 != null) {
                arrayList.add(type2);
            } else {
                throw new IllegalArgumentException(("Star projections in type arguments are not allowed, but had " + kType).toString());
            }
        }
        List list = (List) arrayList;
        if (list.isEmpty()) {
            kSerializer = SerializersCacheKt.findCachedSerializer(kclass, isMarkedNullable);
        } else {
            Object findParametrizedCachedSerializer = SerializersCacheKt.findParametrizedCachedSerializer(kclass, list, isMarkedNullable);
            if (z) {
                if (Result.m1780isFailureimpl(findParametrizedCachedSerializer)) {
                    findParametrizedCachedSerializer = null;
                }
                kSerializer = (KSerializer) findParametrizedCachedSerializer;
            } else if (Result.m1777exceptionOrNullimpl(findParametrizedCachedSerializer) != null) {
                return null;
            } else {
                kSerializer = (KSerializer) findParametrizedCachedSerializer;
            }
        }
        if (kSerializer != null) {
            return kSerializer;
        }
        if (list.isEmpty()) {
            kSerializer2 = SerializersModule.getContextual$default(serializersModule, kclass, (List) null, 2, (Object) null);
        } else {
            List serializersForParameters = SerializersKt.serializersForParameters(serializersModule, list, z);
            if (serializersForParameters == null) {
                return null;
            }
            KSerializer<? extends Object> parametrizedSerializerOrNull = SerializersKt.parametrizedSerializerOrNull(kclass, serializersForParameters, new SerializersKt__SerializersKt$serializerByKTypeImpl$contextualSerializer$1(list));
            kSerializer2 = parametrizedSerializerOrNull == null ? serializersModule.getContextual(kclass, serializersForParameters) : parametrizedSerializerOrNull;
        }
        if (kSerializer2 != null) {
            return nullable$SerializersKt__SerializersKt(kSerializer2, isMarkedNullable);
        }
        return null;
    }

    private static final KSerializer<Object> serializerByKClassImpl$SerializersKt__SerializersKt(SerializersModule serializersModule, KClass<Object> kClass, List<? extends KSerializer<Object>> list, boolean z) {
        KSerializer<T> kSerializer;
        if (list.isEmpty()) {
            kSerializer = SerializersKt.serializerOrNull(kClass);
            if (kSerializer == null) {
                kSerializer = SerializersModule.getContextual$default(serializersModule, kClass, (List) null, 2, (Object) null);
            }
        } else {
            try {
                KSerializer<? extends Object> parametrizedSerializerOrNull = SerializersKt.parametrizedSerializerOrNull(kClass, list, SerializersKt__SerializersKt$serializerByKClassImpl$serializer$1.INSTANCE);
                kSerializer = parametrizedSerializerOrNull == null ? serializersModule.getContextual(kClass, list) : parametrizedSerializerOrNull;
            } catch (IndexOutOfBoundsException e) {
                throw new SerializationException("Unable to retrieve a serializer, the number of passed type serializers differs from the actual number of generic parameters", e);
            }
        }
        if (kSerializer != null) {
            return nullable$SerializersKt__SerializersKt(kSerializer, z);
        }
        return null;
    }

    public static final List<KSerializer<Object>> serializersForParameters(SerializersModule serializersModule, List<? extends KType> list, boolean z) {
        Intrinsics.checkNotNullParameter(serializersModule, "<this>");
        Intrinsics.checkNotNullParameter(list, "typeArguments");
        if (z) {
            Iterable<KType> iterable = list;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (KType serializer : iterable) {
                arrayList.add(SerializersKt.serializer(serializersModule, serializer));
            }
            return (List) arrayList;
        }
        Iterable<KType> iterable2 = list;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
        for (KType serializerOrNull : iterable2) {
            KSerializer<Object> serializerOrNull2 = SerializersKt.serializerOrNull(serializersModule, serializerOrNull);
            if (serializerOrNull2 == null) {
                return null;
            }
            arrayList2.add(serializerOrNull2);
        }
        return (List) arrayList2;
    }

    @InternalSerializationApi
    public static final <T> KSerializer<T> serializer(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        KSerializer<T> serializerOrNull = SerializersKt.serializerOrNull(kClass);
        if (serializerOrNull != null) {
            return serializerOrNull;
        }
        Platform_commonKt.serializerNotRegistered(kClass);
        throw new KotlinNothingValueException();
    }

    @InternalSerializationApi
    public static final <T> KSerializer<T> serializerOrNull(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        KSerializer<T> compiledSerializerImpl = PlatformKt.compiledSerializerImpl(kClass);
        return compiledSerializerImpl == null ? PrimitivesKt.builtinSerializerOrNull(kClass) : compiledSerializerImpl;
    }

    public static final KSerializer<? extends Object> parametrizedSerializerOrNull(KClass<Object> kClass, List<? extends KSerializer<Object>> list, Function0<? extends KClassifier> function0) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Intrinsics.checkNotNullParameter(list, "serializers");
        Intrinsics.checkNotNullParameter(function0, "elementClassifierIfArray");
        KSerializer<? extends Object> builtinParametrizedSerializer$SerializersKt__SerializersKt = builtinParametrizedSerializer$SerializersKt__SerializersKt(kClass, list, function0);
        return builtinParametrizedSerializer$SerializersKt__SerializersKt == null ? compiledParametrizedSerializer$SerializersKt__SerializersKt(kClass, list) : builtinParametrizedSerializer$SerializersKt__SerializersKt;
    }

    private static final KSerializer<? extends Object> compiledParametrizedSerializer$SerializersKt__SerializersKt(KClass<Object> kClass, List<? extends KSerializer<Object>> list) {
        KSerializer[] kSerializerArr = (KSerializer[]) list.toArray(new KSerializer[0]);
        return PlatformKt.constructSerializerForGivenTypeArgs(kClass, (KSerializer<Object>[]) (KSerializer[]) Arrays.copyOf(kSerializerArr, kSerializerArr.length));
    }

    private static final KSerializer<? extends Object> builtinParametrizedSerializer$SerializersKt__SerializersKt(KClass<Object> kClass, List<? extends KSerializer<Object>> list, Function0<? extends KClassifier> function0) {
        if (Intrinsics.areEqual((Object) kClass, (Object) Reflection.getOrCreateKotlinClass(Collection.class)) || Intrinsics.areEqual((Object) kClass, (Object) Reflection.getOrCreateKotlinClass(List.class)) || Intrinsics.areEqual((Object) kClass, (Object) Reflection.getOrCreateKotlinClass(List.class)) || Intrinsics.areEqual((Object) kClass, (Object) Reflection.getOrCreateKotlinClass(ArrayList.class))) {
            return new ArrayListSerializer<>((KSerializer) list.get(0));
        }
        if (Intrinsics.areEqual((Object) kClass, (Object) Reflection.getOrCreateKotlinClass(HashSet.class))) {
            return new HashSetSerializer<>((KSerializer) list.get(0));
        }
        if (Intrinsics.areEqual((Object) kClass, (Object) Reflection.getOrCreateKotlinClass(Set.class)) || Intrinsics.areEqual((Object) kClass, (Object) Reflection.getOrCreateKotlinClass(Set.class)) || Intrinsics.areEqual((Object) kClass, (Object) Reflection.getOrCreateKotlinClass(LinkedHashSet.class))) {
            return new LinkedHashSetSerializer<>((KSerializer) list.get(0));
        }
        if (Intrinsics.areEqual((Object) kClass, (Object) Reflection.getOrCreateKotlinClass(HashMap.class))) {
            return new HashMapSerializer<>((KSerializer) list.get(0), (KSerializer) list.get(1));
        }
        if (Intrinsics.areEqual((Object) kClass, (Object) Reflection.getOrCreateKotlinClass(Map.class)) || Intrinsics.areEqual((Object) kClass, (Object) Reflection.getOrCreateKotlinClass(Map.class)) || Intrinsics.areEqual((Object) kClass, (Object) Reflection.getOrCreateKotlinClass(LinkedHashMap.class))) {
            return new LinkedHashMapSerializer<>((KSerializer) list.get(0), (KSerializer) list.get(1));
        }
        if (Intrinsics.areEqual((Object) kClass, (Object) Reflection.getOrCreateKotlinClass(Map.Entry.class))) {
            return BuiltinSerializersKt.MapEntrySerializer((KSerializer) list.get(0), (KSerializer) list.get(1));
        }
        if (Intrinsics.areEqual((Object) kClass, (Object) Reflection.getOrCreateKotlinClass(Pair.class))) {
            return BuiltinSerializersKt.PairSerializer((KSerializer) list.get(0), (KSerializer) list.get(1));
        }
        if (Intrinsics.areEqual((Object) kClass, (Object) Reflection.getOrCreateKotlinClass(Triple.class))) {
            return BuiltinSerializersKt.TripleSerializer((KSerializer) list.get(0), (KSerializer) list.get(1), (KSerializer) list.get(2));
        }
        if (!PlatformKt.isReferenceArray(kClass)) {
            return null;
        }
        Object invoke = function0.invoke();
        Intrinsics.checkNotNull(invoke, "null cannot be cast to non-null type kotlin.reflect.KClass<kotlin.Any>");
        return BuiltinSerializersKt.ArraySerializer((KClass) invoke, (KSerializer) list.get(0));
    }

    private static final <T> KSerializer<T> nullable$SerializersKt__SerializersKt(KSerializer<T> kSerializer, boolean z) {
        if (z) {
            return BuiltinSerializersKt.getNullable(kSerializer);
        }
        Intrinsics.checkNotNull(kSerializer, "null cannot be cast to non-null type kotlinx.serialization.KSerializer<T of kotlinx.serialization.SerializersKt__SerializersKt.nullable?>");
        return kSerializer;
    }

    public static final KSerializer<?> noCompiledSerializer(String str) {
        Intrinsics.checkNotNullParameter(str, "forClass");
        throw new SerializationException(Platform_commonKt.notRegisteredMessage(str));
    }

    public static final KSerializer<?> noCompiledSerializer(SerializersModule serializersModule, KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(serializersModule, "module");
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        KSerializer<?> contextual$default = SerializersModule.getContextual$default(serializersModule, kClass, (List) null, 2, (Object) null);
        if (contextual$default != null) {
            return contextual$default;
        }
        Platform_commonKt.serializerNotRegistered(kClass);
        throw new KotlinNothingValueException();
    }

    public static final KSerializer<?> noCompiledSerializer(SerializersModule serializersModule, KClass<?> kClass, KSerializer<?>[] kSerializerArr) {
        Intrinsics.checkNotNullParameter(serializersModule, "module");
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(kSerializerArr, "argSerializers");
        KSerializer<?> contextual = serializersModule.getContextual(kClass, ArraysKt.asList((T[]) kSerializerArr));
        if (contextual != null) {
            return contextual;
        }
        Platform_commonKt.serializerNotRegistered(kClass);
        throw new KotlinNothingValueException();
    }
}
