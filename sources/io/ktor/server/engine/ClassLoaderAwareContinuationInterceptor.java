package io.ktor.server.engine;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\bH\u0016R\u0018\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lio/ktor/server/engine/ClassLoaderAwareContinuationInterceptor;", "Lkotlin/coroutines/ContinuationInterceptor;", "()V", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/CoroutineContext$Key;", "interceptContinuation", "Lkotlin/coroutines/Continuation;", "T", "continuation", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationEngineEnvironmentReloading.kt */
final class ClassLoaderAwareContinuationInterceptor implements ContinuationInterceptor {
    public static final ClassLoaderAwareContinuationInterceptor INSTANCE = new ClassLoaderAwareContinuationInterceptor();
    private static final CoroutineContext.Key<?> key = new ClassLoaderAwareContinuationInterceptor$key$1();

    private ClassLoaderAwareContinuationInterceptor() {
    }

    public <R> R fold(R r, Function2<? super R, ? super CoroutineContext.Element, ? extends R> function2) {
        return ContinuationInterceptor.DefaultImpls.fold(this, r, function2);
    }

    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> key2) {
        return ContinuationInterceptor.DefaultImpls.get(this, key2);
    }

    public CoroutineContext minusKey(CoroutineContext.Key<?> key2) {
        return ContinuationInterceptor.DefaultImpls.minusKey(this, key2);
    }

    public CoroutineContext plus(CoroutineContext coroutineContext) {
        return ContinuationInterceptor.DefaultImpls.plus(this, coroutineContext);
    }

    public void releaseInterceptedContinuation(Continuation<?> continuation) {
        ContinuationInterceptor.DefaultImpls.releaseInterceptedContinuation(this, continuation);
    }

    public CoroutineContext.Key<?> getKey() {
        return key;
    }

    public <T> Continuation<T> interceptContinuation(Continuation<? super T> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        return new ClassLoaderAwareContinuationInterceptor$interceptContinuation$1(continuation, Thread.currentThread().getContextClassLoader());
    }
}
