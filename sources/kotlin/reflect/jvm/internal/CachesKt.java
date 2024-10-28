package kotlin.reflect.jvm.internal;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;
import kotlin.reflect.full.KClassifiers;

@Metadata(d1 = {"\u0000Z\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a-\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\"\b\b\u0000\u0010\u0001*\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a'\u0010\b\u001a\u00020\u0007\"\b\b\u0000\u0010\u0001*\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u000f\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a=\u0010\u0013\u001a\u00020\u0012\"\b\b\u0000\u0010\u0001*\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0011\u001a\u00020\u0010H\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a=\u0010\u0015\u001a\u00020\u0012\"\b\b\u0000\u0010\u0001*\u00020\u00002\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0011\u001a\u00020\u0010H\u0002¢\u0006\u0004\b\u0015\u0010\u0014\"*\u0010\u0018\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u000e\b\u0001\u0012\n \u0017*\u0004\u0018\u00010\u00000\u00000\u00040\u00168\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019\"\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00168\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001b\u0010\u0019\"\u001a\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00120\u00168\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u0019\"\u001a\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00120\u00168\u0002X\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010\u0019\"<\u0010!\u001a*\u0012&\u0012$\u0012\u001a\u0012\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\u00100\u001fj\u0002` \u0012\u0004\u0012\u00020\u00120\u001e0\u00168\u0002X\u0004¢\u0006\u0006\n\u0004\b!\u0010\u0019*0\b\u0002\u0010\"\"\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\u00100\u001f2\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\u00100\u001f¨\u0006#"}, d2 = {"", "T", "Ljava/lang/Class;", "jClass", "Lkotlin/reflect/jvm/internal/KClassImpl;", "getOrCreateKotlinClass", "(Ljava/lang/Class;)Lkotlin/reflect/jvm/internal/KClassImpl;", "Lkotlin/reflect/KDeclarationContainer;", "getOrCreateKotlinPackage", "(Ljava/lang/Class;)Lkotlin/reflect/KDeclarationContainer;", "", "clearCaches", "()V", "", "Lkotlin/reflect/KTypeProjection;", "arguments", "", "isMarkedNullable", "Lkotlin/reflect/KType;", "getOrCreateKType", "(Ljava/lang/Class;Ljava/util/List;Z)Lkotlin/reflect/KType;", "getOrCreateKTypeWithTypeArguments", "Lkotlin/reflect/jvm/internal/CacheByClass;", "kotlin.jvm.PlatformType", "K_CLASS_CACHE", "Lkotlin/reflect/jvm/internal/CacheByClass;", "Lkotlin/reflect/jvm/internal/KPackageImpl;", "K_PACKAGE_CACHE", "CACHE_FOR_BASE_CLASSIFIERS", "CACHE_FOR_NULLABLE_BASE_CLASSIFIERS", "j$/util/concurrent/ConcurrentHashMap", "Lkotlin/Pair;", "Lkotlin/reflect/jvm/internal/Key;", "CACHE_FOR_GENERIC_CLASSIFIERS", "Key", "kotlin-reflection"}, k = 2, mv = {1, 9, 0})
/* compiled from: caches.kt */
public final class CachesKt {
    private static final CacheByClass<KType> CACHE_FOR_BASE_CLASSIFIERS = CacheByClassKt.createCache(CachesKt$CACHE_FOR_BASE_CLASSIFIERS$1.INSTANCE);
    private static final CacheByClass<ConcurrentHashMap<Pair<List<KTypeProjection>, Boolean>, KType>> CACHE_FOR_GENERIC_CLASSIFIERS = CacheByClassKt.createCache(CachesKt$CACHE_FOR_GENERIC_CLASSIFIERS$1.INSTANCE);
    private static final CacheByClass<KType> CACHE_FOR_NULLABLE_BASE_CLASSIFIERS = CacheByClassKt.createCache(CachesKt$CACHE_FOR_NULLABLE_BASE_CLASSIFIERS$1.INSTANCE);
    private static final CacheByClass<KClassImpl<? extends Object>> K_CLASS_CACHE = CacheByClassKt.createCache(CachesKt$K_CLASS_CACHE$1.INSTANCE);
    private static final CacheByClass<KPackageImpl> K_PACKAGE_CACHE = CacheByClassKt.createCache(CachesKt$K_PACKAGE_CACHE$1.INSTANCE);

    public static final <T> KClassImpl<T> getOrCreateKotlinClass(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "jClass");
        KClassImpl<? extends Object> kClassImpl = K_CLASS_CACHE.get(cls);
        Intrinsics.checkNotNull(kClassImpl, "null cannot be cast to non-null type kotlin.reflect.jvm.internal.KClassImpl<T of kotlin.reflect.jvm.internal.CachesKt.getOrCreateKotlinClass>");
        return kClassImpl;
    }

    public static final <T> KDeclarationContainer getOrCreateKotlinPackage(Class<T> cls) {
        Intrinsics.checkNotNullParameter(cls, "jClass");
        return K_PACKAGE_CACHE.get(cls);
    }

    public static final void clearCaches() {
        K_CLASS_CACHE.clear();
        K_PACKAGE_CACHE.clear();
        CACHE_FOR_BASE_CLASSIFIERS.clear();
        CACHE_FOR_NULLABLE_BASE_CLASSIFIERS.clear();
        CACHE_FOR_GENERIC_CLASSIFIERS.clear();
    }

    public static final <T> KType getOrCreateKType(Class<T> cls, List<KTypeProjection> list, boolean z) {
        Intrinsics.checkNotNullParameter(cls, "jClass");
        Intrinsics.checkNotNullParameter(list, "arguments");
        if (!list.isEmpty()) {
            return getOrCreateKTypeWithTypeArguments(cls, list, z);
        }
        if (z) {
            return CACHE_FOR_NULLABLE_BASE_CLASSIFIERS.get(cls);
        }
        return CACHE_FOR_BASE_CLASSIFIERS.get(cls);
    }

    private static final <T> KType getOrCreateKTypeWithTypeArguments(Class<T> cls, List<KTypeProjection> list, boolean z) {
        ConcurrentMap concurrentMap = CACHE_FOR_GENERIC_CLASSIFIERS.get(cls);
        Pair<A, B> pair = TuplesKt.to(list, Boolean.valueOf(z));
        Object obj = concurrentMap.get(pair);
        if (obj == null) {
            KType createType = KClassifiers.createType(getOrCreateKotlinClass(cls), list, z, CollectionsKt.emptyList());
            Object putIfAbsent = concurrentMap.putIfAbsent(pair, createType);
            obj = putIfAbsent == null ? createType : putIfAbsent;
        }
        Intrinsics.checkNotNullExpressionValue(obj, "getOrPut(...)");
        return (KType) obj;
    }
}
