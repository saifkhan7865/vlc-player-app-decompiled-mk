package org.videolan.vlc.gui.browser;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.StorageBrowserAdapter", f = "StorageBrowserAdapter.kt", i = {0}, l = {145}, m = "updateListState", n = {"this"}, s = {"L$0"})
/* compiled from: StorageBrowserAdapter.kt */
final class StorageBrowserAdapter$updateListState$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ StorageBrowserAdapter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StorageBrowserAdapter$updateListState$1(StorageBrowserAdapter storageBrowserAdapter, Continuation<? super StorageBrowserAdapter$updateListState$1> continuation) {
        super(continuation);
        this.this$0 = storageBrowserAdapter;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.updateListState((Context) null, this);
    }
}
