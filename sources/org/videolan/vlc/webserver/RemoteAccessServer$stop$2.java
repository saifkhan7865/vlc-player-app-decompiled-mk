package org.videolan.vlc.webserver;

import io.ktor.server.engine.ApplicationEngine;
import io.ktor.server.netty.NettyApplicationEngine;
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
import org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessServer$stop$2", f = "RemoteAccessServer.kt", i = {}, l = {226}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessServer.kt */
final class RemoteAccessServer$stop$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ RemoteAccessServer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessServer$stop$2(RemoteAccessServer remoteAccessServer, Continuation<? super RemoteAccessServer$stop$2> continuation) {
        super(2, continuation);
        this.this$0 = remoteAccessServer;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessServer$stop$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteAccessServer$stop$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (RemoteAccessWebSockets.INSTANCE.closeAllSessions(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (this.this$0.engine != null) {
            NettyApplicationEngine access$getEngine$p = this.this$0.engine;
            if (access$getEngine$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("engine");
                access$getEngine$p = null;
            }
            ApplicationEngine.DefaultImpls.stop$default(access$getEngine$p, 0, 0, 3, (Object) null);
        }
        return Unit.INSTANCE;
    }
}
