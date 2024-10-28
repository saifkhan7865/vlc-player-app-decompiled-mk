package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import org.videolan.medialibrary.interfaces.Medialibrary;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.MediaParsingService$onCreate$1$1", f = "MediaParsingService.kt", i = {}, l = {145}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaParsingService.kt */
final class MediaParsingService$onCreate$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Boolean $running;
    int label;
    final /* synthetic */ MediaParsingService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaParsingService$onCreate$1$1(Boolean bool, MediaParsingService mediaParsingService, Continuation<? super MediaParsingService$onCreate$1$1> continuation) {
        super(2, continuation);
        this.$running = bool;
        this.this$0 = mediaParsingService;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaParsingService$onCreate$1$1(this.$running, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaParsingService$onCreate$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (!this.$running.booleanValue()) {
                this.label = 1;
                if (DelayKt.delay(1000, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            return Unit.INSTANCE;
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Medialibrary access$getMedialibrary$p = this.this$0.medialibrary;
        if (access$getMedialibrary$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("medialibrary");
            access$getMedialibrary$p = null;
        }
        if (!access$getMedialibrary$p.isWorking()) {
            this.this$0.exitCommand();
        }
        return Unit.INSTANCE;
    }
}
