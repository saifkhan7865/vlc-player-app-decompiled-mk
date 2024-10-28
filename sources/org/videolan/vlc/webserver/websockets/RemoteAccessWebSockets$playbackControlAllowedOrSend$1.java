package org.videolan.vlc.webserver.websockets;

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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$playbackControlAllowedOrSend$1", f = "RemoteAccessWebSockets.kt", i = {}, l = {325}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessWebSockets.kt */
final class RemoteAccessWebSockets$playbackControlAllowedOrSend$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $message;
    Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessWebSockets$playbackControlAllowedOrSend$1(String str, Continuation<? super RemoteAccessWebSockets$playbackControlAllowedOrSend$1> continuation) {
        super(2, continuation);
        this.$message = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessWebSockets$playbackControlAllowedOrSend$1(this.$message, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteAccessWebSockets$playbackControlAllowedOrSend$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        String str;
        Iterator it;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            str = this.$message;
            it = RemoteAccessWebSockets.websocketSession.iterator();
        } else if (i == 1) {
            it = (Iterator) this.L$1;
            str = (String) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (it.hasNext()) {
            Intrinsics.checkNotNull(str);
            this.L$0 = str;
            this.L$1 = it;
            this.label = 1;
            if (((DefaultWebSocketServerSession) it.next()).send(new Frame.Text(str), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }
}
