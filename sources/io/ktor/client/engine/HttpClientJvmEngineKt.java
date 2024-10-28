package io.ktor.client.engine;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.InlineMarker;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0011\u0010\u0000\u001a\u00020\u0001HHø\u0001\u0000¢\u0006\u0002\u0010\u0002\u0002\u0004\n\u0002\b\u0019¨\u0006\u0003"}, d2 = {"currentContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpClientJvmEngine.kt */
public final class HttpClientJvmEngineKt {
    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.Throwable, kotlin.coroutines.Continuation] */
    private static final Object currentContext(Continuation<? super CoroutineContext> continuation) {
        InlineMarker.mark(3);
        ? r0 = 0;
        r0.getContext();
        throw r0;
    }
}
