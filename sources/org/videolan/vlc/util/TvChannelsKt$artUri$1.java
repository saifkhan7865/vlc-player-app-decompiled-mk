package org.videolan.vlc.util;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.TvChannelsKt", f = "TvChannels.kt", i = {0}, l = {323}, m = "artUri", n = {"$this$artUri"}, s = {"L$0"})
/* compiled from: TvChannels.kt */
final class TvChannelsKt$artUri$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    TvChannelsKt$artUri$1(Continuation<? super TvChannelsKt$artUri$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return TvChannelsKt.artUri((MediaWrapper) null, this);
    }
}
