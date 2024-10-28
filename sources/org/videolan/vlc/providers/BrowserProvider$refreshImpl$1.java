package org.videolan.vlc.providers;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.BrowserProvider", f = "BrowserProvider.kt", i = {0, 1}, l = {240, 240}, m = "refreshImpl$suspendImpl", n = {"$this", "$this"}, s = {"L$0", "L$0"})
/* compiled from: BrowserProvider.kt */
final class BrowserProvider$refreshImpl$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BrowserProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserProvider$refreshImpl$1(BrowserProvider browserProvider, Continuation<? super BrowserProvider$refreshImpl$1> continuation) {
        super(continuation);
        this.this$0 = browserProvider;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return BrowserProvider.refreshImpl$suspendImpl(this.this$0, this);
    }
}
