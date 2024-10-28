package org.videolan.vlc.webserver.websockets;

import android.content.Context;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.tools.AppScope;
import org.videolan.vlc.webserver.RemoteAccessServer;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets$manageIncomingMessages$9$2", f = "RemoteAccessWebSockets.kt", i = {}, l = {198}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessWebSockets.kt */
final class RemoteAccessWebSockets$manageIncomingMessages$9$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessWebSockets$manageIncomingMessages$9$2(Context context, Continuation<? super RemoteAccessWebSockets$manageIncomingMessages$9$2> continuation) {
        super(2, continuation);
        this.$context = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessWebSockets$manageIncomingMessages$9$2(this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteAccessWebSockets$manageIncomingMessages$9$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = ((RemoteAccessServer) RemoteAccessServer.Companion.getInstance(this.$context)).generateNowPlaying(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        RemoteAccessServer.NowPlaying nowPlaying = (RemoteAccessServer.NowPlaying) obj;
        if (nowPlaying != null) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, (CoroutineContext) null, (CoroutineStart) null, new RemoteAccessWebSockets$manageIncomingMessages$9$2$1$1(nowPlaying, (Continuation<? super RemoteAccessWebSockets$manageIncomingMessages$9$2$1$1>) null), 3, (Object) null);
        }
        return Unit.INSTANCE;
    }
}
