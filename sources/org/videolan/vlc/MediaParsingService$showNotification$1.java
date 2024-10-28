package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.MediaParsingService", f = "MediaParsingService.kt", i = {0, 0}, l = {380}, m = "showNotification", n = {"this", "parsing"}, s = {"L$0", "F$0"})
/* compiled from: MediaParsingService.kt */
final class MediaParsingService$showNotification$1 extends ContinuationImpl {
    float F$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MediaParsingService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaParsingService$showNotification$1(MediaParsingService mediaParsingService, Continuation<? super MediaParsingService$showNotification$1> continuation) {
        super(continuation);
        this.this$0 = mediaParsingService;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.showNotification(0, 0, this);
    }
}
