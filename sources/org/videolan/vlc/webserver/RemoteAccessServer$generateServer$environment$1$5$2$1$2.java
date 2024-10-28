package org.videolan.vlc.webserver;

import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.SessionChallengeContext;
import io.ktor.server.response.ApplicationResponse;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.server.response.ResponseTypeKt;
import io.ktor.util.reflect.TypeInfoJvmKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H@"}, d2 = {"<anonymous>", "", "Lio/ktor/server/auth/SessionChallengeContext;", "it", "Lorg/videolan/vlc/webserver/UserSession;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessServer$generateServer$environment$1$5$2$1$2", f = "RemoteAccessServer.kt", i = {}, l = {801}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessServer.kt */
final class RemoteAccessServer$generateServer$environment$1$5$2$1$2 extends SuspendLambda implements Function3<SessionChallengeContext, UserSession, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;

    RemoteAccessServer$generateServer$environment$1$5$2$1$2(Continuation<? super RemoteAccessServer$generateServer$environment$1$5$2$1$2> continuation) {
        super(3, continuation);
    }

    public final Object invoke(SessionChallengeContext sessionChallengeContext, UserSession userSession, Continuation<? super Unit> continuation) {
        RemoteAccessServer$generateServer$environment$1$5$2$1$2 remoteAccessServer$generateServer$environment$1$5$2$1$2 = new RemoteAccessServer$generateServer$environment$1$5$2$1$2(continuation);
        remoteAccessServer$generateServer$environment$1$5$2$1$2.L$0 = sessionChallengeContext;
        return remoteAccessServer$generateServer$environment$1$5$2$1$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ApplicationCall call = ((SessionChallengeContext) this.L$0).getCall();
            HttpStatusCode unauthorized = HttpStatusCode.Companion.getUnauthorized();
            if (!(unauthorized instanceof OutgoingContent) && !(unauthorized instanceof byte[])) {
                ApplicationResponse response = call.getResponse();
                KType typeOf = Reflection.typeOf(HttpStatusCode.class);
                ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(HttpStatusCode.class), typeOf));
            }
            ApplicationSendPipeline pipeline = call.getResponse().getPipeline();
            Intrinsics.checkNotNull(unauthorized, "null cannot be cast to non-null type kotlin.Any");
            this.label = 1;
            if (pipeline.execute(call, unauthorized, this) == coroutine_suspended) {
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
