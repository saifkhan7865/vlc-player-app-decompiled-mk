package org.videolan.vlc.providers;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.BrowserProvider", f = "BrowserProvider.kt", i = {0, 0, 1, 1, 2, 2}, l = {195, 198, 198}, m = "browseImpl$suspendImpl", n = {"$this", "url", "$this", "url", "$this", "url"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
/* compiled from: BrowserProvider.kt */
final class BrowserProvider$browseImpl$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BrowserProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserProvider$browseImpl$1(BrowserProvider browserProvider, Continuation<? super BrowserProvider$browseImpl$1> continuation) {
        super(continuation);
        this.this$0 = browserProvider;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return BrowserProvider.browseImpl$suspendImpl(this.this$0, (String) null, this);
    }
}
