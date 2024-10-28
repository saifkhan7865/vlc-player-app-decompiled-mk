package org.videolan.vlc.webserver.websockets;

import android.content.Context;
import io.ktor.server.websocket.DefaultWebSocketServerSession;
import io.ktor.websocket.Frame;
import java.util.Iterator;
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
@DebugMetadata(c = "org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$4", f = "RemoteAccessWebSockets.kt", i = {}, l = {147}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessWebSockets.kt */
final class RemoteAccessWebSockets$manageIncomingMessages$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ PlaybackService $service;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessWebSockets$manageIncomingMessages$4(Context context, PlaybackService playbackService, Continuation<? super RemoteAccessWebSockets$manageIncomingMessages$4> continuation) {
        super(2, continuation);
        this.$context = context;
        this.$service = playbackService;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessWebSockets$manageIncomingMessages$4(this.$context, this.$service, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteAccessWebSockets$manageIncomingMessages$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Context context;
        PlaybackService playbackService;
        Iterator it;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Context context2 = this.$context;
            playbackService = this.$service;
            context = context2;
            it = RemoteAccessWebSockets.websocketSession.iterator();
        } else if (i == 1) {
            it = (Iterator) this.L$2;
            playbackService = (PlaybackService) this.L$1;
            context = (Context) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (it.hasNext()) {
            this.L$0 = context;
            this.L$1 = playbackService;
            this.L$2 = it;
            this.label = 1;
            if (((DefaultWebSocketServerSession) it.next()).send(new Frame.Text(RemoteAccessWebSockets.INSTANCE.getVolumeMessage(context, playbackService)), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
