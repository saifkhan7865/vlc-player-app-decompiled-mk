package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import org.videolan.libvlc.RendererDiscoverer;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u000b\u0010\u0000\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.RendererDelegate$start$2", f = "RendererDelegate.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RendererDelegate.kt */
final class RendererDelegate$start$2 extends SuspendLambda implements Function1<Continuation<? super Boolean>, Object> {
    final /* synthetic */ RendererDiscoverer $rd;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RendererDelegate$start$2(RendererDiscoverer rendererDiscoverer, Continuation<? super RendererDelegate$start$2> continuation) {
        super(1, continuation);
        this.$rd = rendererDiscoverer;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new RendererDelegate$start$2(this.$rd, continuation);
    }

    public final Object invoke(Continuation<? super Boolean> continuation) {
        return ((RendererDelegate$start$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxBoolean(!this.$rd.isReleased() ? this.$rd.start() : false);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
