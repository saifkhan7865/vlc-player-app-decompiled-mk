package org.videolan.vlc.util;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.TvChannelsKt", f = "TvChannels.kt", i = {0, 0, 0, 1, 1, 1, 1, 1, 1, 2}, l = {335, 165, 174}, m = "setResumeProgram", n = {"context", "cursor", "isProgramPresent", "context", "cursor", "isProgramPresent", "mw", "existingProgram", "watchNextProgramId", "cursor"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$4", "J$0", "L$0"})
/* compiled from: TvChannels.kt */
final class TvChannelsKt$setResumeProgram$1 extends ContinuationImpl {
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

    TvChannelsKt$setResumeProgram$1(Continuation<? super TvChannelsKt$setResumeProgram$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return TvChannelsKt.setResumeProgram((Context) null, (MediaWrapper) null, this);
    }
}
