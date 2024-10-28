package org.videolan.vlc.util;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.TvChannelsKt", f = "TvChannels.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2}, l = {335, 337, 339}, m = "checkWatchNextId", n = {"context", "cursor", "it", "contentId", "id", "watchNextProgramId", "mediaId", "context", "cursor", "contentId", "id", "watchNextProgramId", "cursor", "id"}, s = {"L$0", "L$1", "L$2", "L$3", "J$0", "J$1", "J$2", "L$0", "L$1", "L$2", "J$0", "J$1", "L$0", "J$0"})
/* compiled from: TvChannels.kt */
final class TvChannelsKt$checkWatchNextId$1 extends ContinuationImpl {
    long J$0;
    long J$1;
    long J$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    TvChannelsKt$checkWatchNextId$1(Continuation<? super TvChannelsKt$checkWatchNextId$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return TvChannelsKt.checkWatchNextId((Context) null, 0, this);
    }
}
