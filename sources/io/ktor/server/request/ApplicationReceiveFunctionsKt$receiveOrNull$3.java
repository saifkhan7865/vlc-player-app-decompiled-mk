package io.ktor.server.request;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.reflect.KClass;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.request.ApplicationReceiveFunctionsKt", f = "ApplicationReceiveFunctions.kt", i = {0}, l = {155}, m = "receiveOrNull", n = {"$this$receiveOrNull"}, s = {"L$0"})
/* compiled from: ApplicationReceiveFunctions.kt */
final class ApplicationReceiveFunctionsKt$receiveOrNull$3<T> extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    ApplicationReceiveFunctionsKt$receiveOrNull$3(Continuation<? super ApplicationReceiveFunctionsKt$receiveOrNull$3> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ApplicationReceiveFunctionsKt.receiveOrNull((ApplicationCall) null, (KClass) null, this);
    }
}
