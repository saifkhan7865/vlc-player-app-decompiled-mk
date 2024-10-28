package io.ktor.server.engine;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;

@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001e\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\nR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u000b"}, d2 = {"io/ktor/server/engine/ClassLoaderAwareContinuationInterceptor$interceptContinuation$1", "Lkotlin/coroutines/Continuation;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "resumeWith", "", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationEngineEnvironmentReloading.kt */
public final class ClassLoaderAwareContinuationInterceptor$interceptContinuation$1 implements Continuation<T> {
    final /* synthetic */ ClassLoader $classLoader;
    final /* synthetic */ Continuation<T> $continuation;
    private final CoroutineContext context;

    ClassLoaderAwareContinuationInterceptor$interceptContinuation$1(Continuation<? super T> continuation, ClassLoader classLoader) {
        this.$continuation = continuation;
        this.$classLoader = classLoader;
        this.context = continuation.getContext();
    }

    public CoroutineContext getContext() {
        return this.context;
    }

    public void resumeWith(Object obj) {
        Thread.currentThread().setContextClassLoader(this.$classLoader);
        this.$continuation.resumeWith(obj);
    }
}
