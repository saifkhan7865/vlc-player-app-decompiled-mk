package org.videolan.vlc.util;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.TvChannelsKt", f = "TvChannels.kt", i = {0, 0, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4}, l = {335, 63, 75, 80, 89}, m = "updatePrograms", n = {"context", "channelId", "context", "videoList", "channelId", "context", "programs", "cn", "mw", "channelId", "count", "context", "programs", "cn", "mw", "channelId", "count", "context"}, s = {"L$0", "J$0", "L$0", "L$1", "J$0", "L$0", "L$1", "L$2", "L$4", "J$0", "I$2", "L$0", "L$1", "L$2", "L$4", "J$0", "I$2", "L$0"})
/* compiled from: TvChannels.kt */
final class TvChannelsKt$updatePrograms$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    long J$0;
    long J$1;
    long J$2;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;

    TvChannelsKt$updatePrograms$1(Continuation<? super TvChannelsKt$updatePrograms$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return TvChannelsKt.updatePrograms((Context) null, 0, this);
    }
}
