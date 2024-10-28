package io.ktor.server.http.content;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u00022\u0006\u0010\u0005\u001a\u00020\u0006H@"}, d2 = {"<anonymous>", "", "Resource", "", "<anonymous parameter 0>", "<anonymous parameter 1>", "Lio/ktor/server/application/ApplicationCall;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.http.content.StaticContentConfig$modifier$1", f = "StaticContent.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: StaticContent.kt */
final class StaticContentConfig$modifier$1 extends SuspendLambda implements Function3<Resource, ApplicationCall, Continuation<? super Unit>, Object> {
    int label;

    StaticContentConfig$modifier$1(Continuation<? super StaticContentConfig$modifier$1> continuation) {
        super(3, continuation);
    }

    public final Object invoke(Resource resource, ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
        return new StaticContentConfig$modifier$1(continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
