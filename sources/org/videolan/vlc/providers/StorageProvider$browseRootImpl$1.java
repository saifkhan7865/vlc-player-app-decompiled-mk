package org.videolan.vlc.providers;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.StorageProvider", f = "StorageProvider.kt", i = {0, 1, 1}, l = {43, 44}, m = "browseRootImpl", n = {"this", "this", "storages"}, s = {"L$0", "L$0", "L$1"})
/* compiled from: StorageProvider.kt */
final class StorageProvider$browseRootImpl$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ StorageProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StorageProvider$browseRootImpl$1(StorageProvider storageProvider, Continuation<? super StorageProvider$browseRootImpl$1> continuation) {
        super(continuation);
        this.this$0 = storageProvider;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.browseRootImpl(this);
    }
}
