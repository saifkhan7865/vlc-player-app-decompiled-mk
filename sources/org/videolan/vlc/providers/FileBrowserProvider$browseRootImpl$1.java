package org.videolan.vlc.providers;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.FileBrowserProvider", f = "FileBrowserProvider.kt", i = {0, 0, 0, 0}, l = {66}, m = "browseRootImpl$suspendImpl", n = {"$this", "internalmemoryTitle", "browserStorage", "storageAccess"}, s = {"L$0", "L$1", "L$2", "I$0"})
/* compiled from: FileBrowserProvider.kt */
final class FileBrowserProvider$browseRootImpl$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FileBrowserProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileBrowserProvider$browseRootImpl$1(FileBrowserProvider fileBrowserProvider, Continuation<? super FileBrowserProvider$browseRootImpl$1> continuation) {
        super(continuation);
        this.this$0 = fileBrowserProvider;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return FileBrowserProvider.browseRootImpl$suspendImpl(this.this$0, this);
    }
}
