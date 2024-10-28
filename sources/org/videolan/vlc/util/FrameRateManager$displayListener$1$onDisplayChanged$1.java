package org.videolan.vlc.util;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import org.videolan.vlc.VersionDependantKt;
import org.videolan.vlc.gui.dialogs.adapters.VlcTrack;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.FrameRateManager$displayListener$1$onDisplayChanged$1", f = "FrameRateManager.kt", i = {}, l = {38}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FrameRateManager.kt */
final class FrameRateManager$displayListener$1$onDisplayChanged$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ FrameRateManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FrameRateManager$displayListener$1$onDisplayChanged$1(FrameRateManager frameRateManager, Continuation<? super FrameRateManager$displayListener$1$onDisplayChanged$1> continuation) {
        super(2, continuation);
        this.this$0 = frameRateManager;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FrameRateManager$displayListener$1$onDisplayChanged$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FrameRateManager$displayListener$1$onDisplayChanged$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        VlcTrack vlcTrack;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (DelayKt.delay(2000, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        try {
            vlcTrack = VersionDependantKt.getSelectedVideoTrack(this.this$0.getService().getMediaplayer());
        } catch (IllegalStateException unused) {
            vlcTrack = null;
        }
        if (vlcTrack != null) {
            this.this$0.getService().play();
        }
        return Unit.INSTANCE;
    }
}
