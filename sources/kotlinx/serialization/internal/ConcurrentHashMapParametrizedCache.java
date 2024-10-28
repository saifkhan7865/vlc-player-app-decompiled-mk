package kotlinx.serialization.internal;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00028\u00000\u0002B5\u0012,\u0010\t\u001a(\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\b0\u0003¢\u0006\u0004\b\n\u0010\u000bJB\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\b0\u000e2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u000f\u0010\u0010R:\u0010\t\u001a(\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\b0\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u0012R*\u0010\u0016\u001a\u0018\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00150\u00138\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lkotlinx/serialization/internal/ConcurrentHashMapParametrizedCache;", "T", "Lkotlinx/serialization/internal/ParametrizedSerializerCache;", "Lkotlin/Function2;", "Lkotlin/reflect/KClass;", "", "", "Lkotlin/reflect/KType;", "Lkotlinx/serialization/KSerializer;", "compute", "<init>", "(Lkotlin/jvm/functions/Function2;)V", "key", "types", "Lkotlin/Result;", "get-gIAlu-s", "(Lkotlin/reflect/KClass;Ljava/util/List;)Ljava/lang/Object;", "get", "Lkotlin/jvm/functions/Function2;", "j$/util/concurrent/ConcurrentHashMap", "Ljava/lang/Class;", "Lkotlinx/serialization/internal/ParametrizedCacheEntry;", "cache", "Lj$/util/concurrent/ConcurrentHashMap;", "kotlinx-serialization-core"}, k = 1, mv = {1, 8, 0})
/* compiled from: Caching.kt */
final class ConcurrentHashMapParametrizedCache<T> implements ParametrizedSerializerCache<T> {
    private final ConcurrentHashMap<Class<?>, ParametrizedCacheEntry<T>> cache = new ConcurrentHashMap<>();
    private final Function2<KClass<Object>, List<? extends KType>, KSerializer<T>> compute;

    public ConcurrentHashMapParametrizedCache(Function2<? super KClass<Object>, ? super List<? extends KType>, ? extends KSerializer<T>> function2) {
        Intrinsics.checkNotNullParameter(function2, "compute");
        this.compute = function2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0018, code lost:
        r2 = new kotlinx.serialization.internal.ParametrizedCacheEntry();
     */
    /* renamed from: get-gIAlu-s  reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object m2389getgIAlus(kotlin.reflect.KClass<java.lang.Object> r6, java.util.List<? extends kotlin.reflect.KType> r7) {
        /*
            r5 = this;
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "types"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            j$.util.concurrent.ConcurrentHashMap<java.lang.Class<?>, kotlinx.serialization.internal.ParametrizedCacheEntry<T>> r0 = r5.cache
            java.util.concurrent.ConcurrentMap r0 = (java.util.concurrent.ConcurrentMap) r0
            java.lang.Class r1 = kotlin.jvm.JvmClassMappingKt.getJavaClass(r6)
            java.lang.Object r2 = r0.get(r1)
            if (r2 != 0) goto L_0x0025
            kotlinx.serialization.internal.ParametrizedCacheEntry r2 = new kotlinx.serialization.internal.ParametrizedCacheEntry
            r2.<init>()
            java.lang.Object r0 = r0.putIfAbsent(r1, r2)
            if (r0 != 0) goto L_0x0024
            goto L_0x0025
        L_0x0024:
            r2 = r0
        L_0x0025:
            kotlinx.serialization.internal.ParametrizedCacheEntry r2 = (kotlinx.serialization.internal.ParametrizedCacheEntry) r2
            r0 = r7
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r1 = new java.util.ArrayList
            r3 = 10
            int r3 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r3)
            r1.<init>(r3)
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Iterator r0 = r0.iterator()
        L_0x003b:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x0050
            java.lang.Object r3 = r0.next()
            kotlin.reflect.KType r3 = (kotlin.reflect.KType) r3
            kotlinx.serialization.internal.KTypeWrapper r4 = new kotlinx.serialization.internal.KTypeWrapper
            r4.<init>(r3)
            r1.add(r4)
            goto L_0x003b
        L_0x0050:
            java.util.List r1 = (java.util.List) r1
            j$.util.concurrent.ConcurrentHashMap r0 = r2.serializers
            java.util.concurrent.ConcurrentMap r0 = (java.util.concurrent.ConcurrentMap) r0
            java.lang.Object r2 = r0.get(r1)
            if (r2 != 0) goto L_0x0085
            kotlin.Result$Companion r2 = kotlin.Result.Companion     // Catch:{ all -> 0x006d }
            kotlin.jvm.functions.Function2<kotlin.reflect.KClass<java.lang.Object>, java.util.List<? extends kotlin.reflect.KType>, kotlinx.serialization.KSerializer<T>> r2 = r5.compute     // Catch:{ all -> 0x006d }
            java.lang.Object r6 = r2.invoke(r6, r7)     // Catch:{ all -> 0x006d }
            kotlinx.serialization.KSerializer r6 = (kotlinx.serialization.KSerializer) r6     // Catch:{ all -> 0x006d }
            java.lang.Object r6 = kotlin.Result.m1774constructorimpl(r6)     // Catch:{ all -> 0x006d }
            goto L_0x0078
        L_0x006d:
            r6 = move-exception
            kotlin.Result$Companion r7 = kotlin.Result.Companion
            java.lang.Object r6 = kotlin.ResultKt.createFailure(r6)
            java.lang.Object r6 = kotlin.Result.m1774constructorimpl(r6)
        L_0x0078:
            kotlin.Result r6 = kotlin.Result.m1773boximpl(r6)
            java.lang.Object r7 = r0.putIfAbsent(r1, r6)
            if (r7 != 0) goto L_0x0084
            r2 = r6
            goto L_0x0085
        L_0x0084:
            r2 = r7
        L_0x0085:
            java.lang.String r6 = "serializers.getOrPut(wra… { producer() }\n        }"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)
            kotlin.Result r2 = (kotlin.Result) r2
            java.lang.Object r6 = r2.m1783unboximpl()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.internal.ConcurrentHashMapParametrizedCache.m2389getgIAlus(kotlin.reflect.KClass, java.util.List):java.lang.Object");
    }
}
