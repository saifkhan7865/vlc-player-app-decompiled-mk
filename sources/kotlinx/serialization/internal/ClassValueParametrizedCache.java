package kotlinx.serialization.internal;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlinx.serialization.KSerializer;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B3\u0012,\u0010\u0003\u001a(\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\t0\u0004¢\u0006\u0002\u0010\nJB\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\t0\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0016ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0012\u0010\u0013R\u001a\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r0\fX\u0004¢\u0006\u0002\n\u0000R4\u0010\u0003\u001a(\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\t0\u0004X\u0004¢\u0006\u0002\n\u0000\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0014"}, d2 = {"Lkotlinx/serialization/internal/ClassValueParametrizedCache;", "T", "Lkotlinx/serialization/internal/ParametrizedSerializerCache;", "compute", "Lkotlin/Function2;", "Lkotlin/reflect/KClass;", "", "", "Lkotlin/reflect/KType;", "Lkotlinx/serialization/KSerializer;", "(Lkotlin/jvm/functions/Function2;)V", "classValue", "Lkotlinx/serialization/internal/ClassValueReferences;", "Lkotlinx/serialization/internal/ParametrizedCacheEntry;", "get", "Lkotlin/Result;", "key", "types", "get-gIAlu-s", "(Lkotlin/reflect/KClass;Ljava/util/List;)Ljava/lang/Object;", "kotlinx-serialization-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Caching.kt */
final class ClassValueParametrizedCache<T> implements ParametrizedSerializerCache<T> {
    private final ClassValueReferences<ParametrizedCacheEntry<T>> classValue = new ClassValueReferences<>();
    private final Function2<KClass<Object>, List<? extends KType>, KSerializer<T>> compute;

    public ClassValueParametrizedCache(Function2<? super KClass<Object>, ? super List<? extends KType>, ? extends KSerializer<T>> function2) {
        Intrinsics.checkNotNullParameter(function2, "compute");
        this.compute = function2;
    }

    /* renamed from: get-gIAlu-s  reason: not valid java name */
    public Object m2388getgIAlus(KClass<Object> kClass, List<? extends KType> list) {
        Object obj;
        Intrinsics.checkNotNullParameter(kClass, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(list, "types");
        Object m = this.classValue.get(JvmClassMappingKt.getJavaClass(kClass));
        Intrinsics.checkNotNullExpressionValue(m, "get(key)");
        MutableSoftReference mutableSoftReference = (MutableSoftReference) m;
        T t = mutableSoftReference.reference.get();
        if (t == null) {
            t = mutableSoftReference.getOrSetWithLock(new ClassValueParametrizedCache$getgIAlus$$inlined$getOrSet$1());
        }
        ParametrizedCacheEntry parametrizedCacheEntry = (ParametrizedCacheEntry) t;
        Iterable<KType> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (KType kTypeWrapper : iterable) {
            arrayList.add(new KTypeWrapper(kTypeWrapper));
        }
        List list2 = (List) arrayList;
        ConcurrentMap access$getSerializers$p = parametrizedCacheEntry.serializers;
        Object obj2 = access$getSerializers$p.get(list2);
        if (obj2 == null) {
            try {
                Result.Companion companion = Result.Companion;
                obj = Result.m1774constructorimpl(this.compute.invoke(kClass, list));
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj = Result.m1774constructorimpl(ResultKt.createFailure(th));
            }
            Result r6 = Result.m1773boximpl(obj);
            Object putIfAbsent = access$getSerializers$p.putIfAbsent(list2, r6);
            obj2 = putIfAbsent == null ? r6 : putIfAbsent;
        }
        Intrinsics.checkNotNullExpressionValue(obj2, "serializers.getOrPut(wra… { producer() }\n        }");
        return ((Result) obj2).m1783unboximpl();
    }
}
