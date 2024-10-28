package org.videolan.vlc.providers;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.vlc.providers.BrowserProvider$browseImpl$2;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.BrowserProvider$browseImpl$2$1$1", f = "BrowserProvider.kt", i = {0}, l = {196}, m = "emit", n = {"this"}, s = {"L$0"})
/* compiled from: BrowserProvider.kt */
final class BrowserProvider$browseImpl$2$1$1$emit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BrowserProvider$browseImpl$2.AnonymousClass1.AnonymousClass1<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserProvider$browseImpl$2$1$1$emit$1(BrowserProvider$browseImpl$2.AnonymousClass1.AnonymousClass1<? super T> r1, Continuation<? super BrowserProvider$browseImpl$2$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = r1;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit((IMedia) null, (Continuation<? super Unit>) this);
    }
}
