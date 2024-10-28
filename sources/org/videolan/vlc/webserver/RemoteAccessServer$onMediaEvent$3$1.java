package org.videolan.vlc.webserver;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.webserver.RemoteAccessServer;
import org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessServer$onMediaEvent$3$1", f = "RemoteAccessServer.kt", i = {}, l = {556}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessServer.kt */
final class RemoteAccessServer$onMediaEvent$3$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ RemoteAccessServer.PlayQueue $playQueue;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessServer$onMediaEvent$3$1(RemoteAccessServer.PlayQueue playQueue, Continuation<? super RemoteAccessServer$onMediaEvent$3$1> continuation) {
        super(2, continuation);
        this.$playQueue = playQueue;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessServer$onMediaEvent$3$1(this.$playQueue, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteAccessServer$onMediaEvent$3$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (RemoteAccessWebSockets.INSTANCE.sendToAll(this.$playQueue, this) == coroutine_suspended) {
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
