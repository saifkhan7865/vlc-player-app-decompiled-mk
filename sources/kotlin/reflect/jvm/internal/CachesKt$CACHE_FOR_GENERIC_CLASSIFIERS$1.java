package kotlin.reflect.jvm.internal;

import j$.util.concurrent.ConcurrentHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeProjection;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u000b\u001a$\u0012\u001a\u0012\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00060\u0003j\u0002`\u0007\u0012\u0004\u0012\u00020\b0\u00022\n\u0010\u0001\u001a\u0006\u0012\u0002\b\u00030\u0000H\n¢\u0006\u0004\b\t\u0010\n"}, d2 = {"Ljava/lang/Class;", "it", "j$/util/concurrent/ConcurrentHashMap", "Lkotlin/Pair;", "", "Lkotlin/reflect/KTypeProjection;", "", "Lkotlin/reflect/jvm/internal/Key;", "Lkotlin/reflect/KType;", "invoke", "(Ljava/lang/Class;)Lj$/util/concurrent/ConcurrentHashMap;", "<anonymous>"}, k = 3, mv = {1, 9, 0})
/* compiled from: caches.kt */
final class CachesKt$CACHE_FOR_GENERIC_CLASSIFIERS$1 extends Lambda implements Function1<Class<?>, ConcurrentHashMap<Pair<? extends List<? extends KTypeProjection>, ? extends Boolean>, KType>> {
    public static final CachesKt$CACHE_FOR_GENERIC_CLASSIFIERS$1 INSTANCE = new CachesKt$CACHE_FOR_GENERIC_CLASSIFIERS$1();

    CachesKt$CACHE_FOR_GENERIC_CLASSIFIERS$1() {
        super(1);
    }

    public final ConcurrentHashMap<Pair<List<KTypeProjection>, Boolean>, KType> invoke(Class<?> cls) {
        Intrinsics.checkNotNullParameter(cls, "it");
        return new ConcurrentHashMap<>();
    }
}
