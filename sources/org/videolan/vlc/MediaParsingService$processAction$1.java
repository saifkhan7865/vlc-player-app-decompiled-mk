package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.channels.ActorScope;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.MediaParsingService", f = "MediaParsingService.kt", i = {0, 1, 1, 1, 2, 2, 2, 2, 3, 4, 4, 5}, l = {490, 504, 510, 518, 525, 528}, m = "processAction", n = {"this", "this", "action", "context", "this", "action", "context", "shouldInit", "this", "this", "action", "this"}, s = {"L$0", "L$0", "L$2", "L$3", "L$0", "L$2", "L$3", "I$0", "L$0", "L$0", "L$2", "L$0"})
/* compiled from: MediaParsingService.kt */
final class MediaParsingService$processAction$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MediaParsingService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaParsingService$processAction$1(MediaParsingService mediaParsingService, Continuation<? super MediaParsingService$processAction$1> continuation) {
        super(continuation);
        this.this$0 = mediaParsingService;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.processAction((ActorScope<MLAction>) null, this);
    }
}
