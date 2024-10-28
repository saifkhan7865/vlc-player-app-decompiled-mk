package org.videolan.vlc.webserver;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.Principal;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H@"}, d2 = {"<anonymous>", "Lio/ktor/server/auth/Principal;", "Lio/ktor/server/application/ApplicationCall;", "session", "Lorg/videolan/vlc/webserver/UserSession;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessServer$generateServer$environment$1$5$2$1$1", f = "RemoteAccessServer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessServer.kt */
final class RemoteAccessServer$generateServer$environment$1$5$2$1$1 extends SuspendLambda implements Function3<ApplicationCall, UserSession, Continuation<? super Principal>, Object> {
    /* synthetic */ Object L$0;
    int label;

    RemoteAccessServer$generateServer$environment$1$5$2$1$1(Continuation<? super RemoteAccessServer$generateServer$environment$1$5$2$1$1> continuation) {
        super(3, continuation);
    }

    public final Object invoke(ApplicationCall applicationCall, UserSession userSession, Continuation<? super Principal> continuation) {
        RemoteAccessServer$generateServer$environment$1$5$2$1$1 remoteAccessServer$generateServer$environment$1$5$2$1$1 = new RemoteAccessServer$generateServer$environment$1$5$2$1$1(continuation);
        remoteAccessServer$generateServer$environment$1$5$2$1$1.L$0 = userSession;
        return remoteAccessServer$generateServer$environment$1$5$2$1$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return (UserSession) this.L$0;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
