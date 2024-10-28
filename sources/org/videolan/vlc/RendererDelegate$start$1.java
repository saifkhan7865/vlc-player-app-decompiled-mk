package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.RendererDelegate", f = "RendererDelegate.kt", i = {0, 1, 1}, l = {50, 56}, m = "start", n = {"this", "this", "libVlc"}, s = {"L$0", "L$0", "L$1"})
/* compiled from: RendererDelegate.kt */
final class RendererDelegate$start$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RendererDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RendererDelegate$start$1(RendererDelegate rendererDelegate, Continuation<? super RendererDelegate$start$1> continuation) {
        super(continuation);
        this.this$0 = rendererDelegate;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.start(this);
    }
}
