package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.libvlc.interfaces.ILibVLC;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.VLCInstance;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Lorg/videolan/libvlc/interfaces/ILibVLC;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.RendererDelegate$start$libVlc$1", f = "RendererDelegate.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RendererDelegate.kt */
final class RendererDelegate$start$libVlc$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ILibVLC>, Object> {
    int label;

    RendererDelegate$start$libVlc$1(Continuation<? super RendererDelegate$start$libVlc$1> continuation) {
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RendererDelegate$start$libVlc$1(continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ILibVLC> continuation) {
        return ((RendererDelegate$start$libVlc$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return VLCInstance.INSTANCE.getInstance(AppContextProvider.INSTANCE.getAppContext());
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
