package org.videolan.vlc.util;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.TvChannelsKt", f = "TvChannels.kt", i = {0, 0}, l = {100}, m = "insertWatchNext", n = {"context", "mw"}, s = {"L$0", "L$1"})
/* compiled from: TvChannels.kt */
final class TvChannelsKt$insertWatchNext$1 extends ContinuationImpl {
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    TvChannelsKt$insertWatchNext$1(Continuation<? super TvChannelsKt$insertWatchNext$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return TvChannelsKt.insertWatchNext((Context) null, (MediaWrapper) null, this);
    }
}
