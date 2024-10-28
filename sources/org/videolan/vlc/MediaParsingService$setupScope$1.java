package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;", "Lorg/videolan/vlc/MLAction;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.MediaParsingService$setupScope$1", f = "MediaParsingService.kt", i = {}, l = {157}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaParsingService.kt */
final class MediaParsingService$setupScope$1 extends SuspendLambda implements Function2<ActorScope<MLAction>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MediaParsingService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaParsingService$setupScope$1(MediaParsingService mediaParsingService, Continuation<? super MediaParsingService$setupScope$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaParsingService;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MediaParsingService$setupScope$1 mediaParsingService$setupScope$1 = new MediaParsingService$setupScope$1(this.this$0, continuation);
        mediaParsingService$setupScope$1.L$0 = obj;
        return mediaParsingService$setupScope$1;
    }

    public final Object invoke(ActorScope<MLAction> actorScope, Continuation<? super Unit> continuation) {
        return ((MediaParsingService$setupScope$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MediaParsingService mediaParsingService = this.this$0;
            this.label = 1;
            if (mediaParsingService.processAction((ActorScope) this.L$0, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
