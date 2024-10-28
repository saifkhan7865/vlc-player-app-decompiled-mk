package org.videolan.vlc.providers;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.FileBrowserProvider", f = "FileBrowserProvider.kt", i = {}, l = {153}, m = "browseByUrl", n = {}, s = {})
/* compiled from: FileBrowserProvider.kt */
final class FileBrowserProvider$browseByUrl$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FileBrowserProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileBrowserProvider$browseByUrl$1(FileBrowserProvider fileBrowserProvider, Continuation<? super FileBrowserProvider$browseByUrl$1> continuation) {
        super(continuation);
        this.this$0 = fileBrowserProvider;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.browseByUrl((String) null, this);
    }
}
