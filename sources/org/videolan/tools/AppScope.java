package org.videolan.tools;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/tools/AppScope;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Workers.kt */
public final class AppScope implements CoroutineScope {
    public static final AppScope INSTANCE = new AppScope();
    private static final CoroutineContext coroutineContext = Dispatchers.getMain().getImmediate().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null));

    private AppScope() {
    }

    public CoroutineContext getCoroutineContext() {
        return coroutineContext;
    }
}
