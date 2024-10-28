package org.videolan.tools;

import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/tools/IOScopedObject;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "coroutineContext", "Lkotlinx/coroutines/CoroutineDispatcher;", "getCoroutineContext", "()Lkotlinx/coroutines/CoroutineDispatcher;", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IOScopedObject.kt */
public class IOScopedObject implements CoroutineScope {
    private final CoroutineDispatcher coroutineContext = Dispatchers.getIO();

    public CoroutineDispatcher getCoroutineContext() {
        return this.coroutineContext;
    }
}
