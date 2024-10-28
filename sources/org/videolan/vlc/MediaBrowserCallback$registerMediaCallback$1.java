package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.MediaBrowserCallback$registerMediaCallback$1", f = "MediaBrowserCallback.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaBrowserCallback.kt */
final class MediaBrowserCallback$registerMediaCallback$1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0<Unit> $callback;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaBrowserCallback$registerMediaCallback$1(Function0<Unit> function0, Continuation<? super MediaBrowserCallback$registerMediaCallback$1> continuation) {
        super(1, continuation);
        this.$callback = function0;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new MediaBrowserCallback$registerMediaCallback$1(this.$callback, continuation);
    }

    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((MediaBrowserCallback$registerMediaCallback$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.$callback.invoke();
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
