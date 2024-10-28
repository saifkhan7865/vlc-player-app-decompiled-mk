package org.videolan.tools;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineDispatcher;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048VX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\t\u001a\u00020\u00048VX\u0002¢\u0006\f\n\u0004\b\u000b\u0010\b\u001a\u0004\b\n\u0010\u0006R\u001b\u0010\f\u001a\u00020\u00048VX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\b\u001a\u0004\b\r\u0010\u0006¨\u0006\u000f"}, d2 = {"Lorg/videolan/tools/CoroutineContextProvider;", "", "()V", "Default", "Lkotlinx/coroutines/CoroutineDispatcher;", "getDefault", "()Lkotlinx/coroutines/CoroutineDispatcher;", "Default$delegate", "Lkotlin/Lazy;", "IO", "getIO", "IO$delegate", "Main", "getMain", "Main$delegate", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CoroutineContextProvider.kt */
public class CoroutineContextProvider {
    private final Lazy Default$delegate = LazyKt.lazy(CoroutineContextProvider$Default$2.INSTANCE);
    private final Lazy IO$delegate = LazyKt.lazy(CoroutineContextProvider$IO$2.INSTANCE);
    private final Lazy Main$delegate = LazyKt.lazy(CoroutineContextProvider$Main$2.INSTANCE);

    public CoroutineDispatcher getDefault() {
        return (CoroutineDispatcher) this.Default$delegate.getValue();
    }

    public CoroutineDispatcher getIO() {
        return (CoroutineDispatcher) this.IO$delegate.getValue();
    }

    public CoroutineDispatcher getMain() {
        return (CoroutineDispatcher) this.Main$delegate.getValue();
    }
}
