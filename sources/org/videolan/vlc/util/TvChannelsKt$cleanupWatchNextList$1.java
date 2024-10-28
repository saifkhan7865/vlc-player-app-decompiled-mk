package org.videolan.vlc.util;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.TvChannelsKt", f = "TvChannels.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5}, l = {335, 202, 337, 339, 341, 223}, m = "cleanupWatchNextList", n = {"context", "cursor", "it", "context", "cursor", "it", "context", "cursor", "it", "contentId", "watchNextProgramId", "mediaId", "context", "cursor", "it", "contentId", "watchNextProgramId", "context", "cursor", "it", "context", "cursor", "it"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "J$0", "J$1", "L$0", "L$1", "L$2", "L$3", "J$0", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2"})
/* compiled from: TvChannels.kt */
final class TvChannelsKt$cleanupWatchNextList$1 extends ContinuationImpl {
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    TvChannelsKt$cleanupWatchNextList$1(Continuation<? super TvChannelsKt$cleanupWatchNextList$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return TvChannelsKt.cleanupWatchNextList((Context) null, this);
    }
}
