package org.videolan.vlc.webserver.websockets;

import java.util.Calendar;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$9$1", f = "RemoteAccessWebSockets.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessWebSockets.kt */
final class RemoteAccessWebSockets$manageIncomingMessages$9$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PlaybackService $service;
    final /* synthetic */ Calendar $sleepTime;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessWebSockets$manageIncomingMessages$9$1(PlaybackService playbackService, Calendar calendar, Continuation<? super RemoteAccessWebSockets$manageIncomingMessages$9$1> continuation) {
        super(2, continuation);
        this.$service = playbackService;
        this.$sleepTime = calendar;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessWebSockets$manageIncomingMessages$9$1(this.$service, this.$sleepTime, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteAccessWebSockets$manageIncomingMessages$9$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            PlaybackService playbackService = this.$service;
            if (playbackService != null) {
                playbackService.setSleepTimer(this.$sleepTime);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
