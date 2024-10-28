package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.vlc.PreviewVideoInputService;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.PreviewVideoInputService$PreviewSession", f = "PreviewVideoInputService.kt", i = {0}, l = {118}, m = "awaitSurface", n = {"this"}, s = {"L$0"})
/* compiled from: PreviewVideoInputService.kt */
final class PreviewVideoInputService$PreviewSession$awaitSurface$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PreviewVideoInputService.PreviewSession this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreviewVideoInputService$PreviewSession$awaitSurface$1(PreviewVideoInputService.PreviewSession previewSession, Continuation<? super PreviewVideoInputService$PreviewSession$awaitSurface$1> continuation) {
        super(continuation);
        this.this$0 = previewSession;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.awaitSurface(this);
    }
}
