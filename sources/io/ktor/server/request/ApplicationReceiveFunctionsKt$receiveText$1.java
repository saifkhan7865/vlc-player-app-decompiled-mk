package io.ktor.server.request;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
@DebugMetadata(c = "io.ktor.server.request.ApplicationReceiveFunctionsKt", f = "ApplicationReceiveFunctions.kt", i = {0, 1}, l = {219, 172}, m = "receiveText", n = {"charset", "charset"}, s = {"L$0", "L$0"})
/* compiled from: ApplicationReceiveFunctions.kt */
final class ApplicationReceiveFunctionsKt$receiveText$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    ApplicationReceiveFunctionsKt$receiveText$1(Continuation<? super ApplicationReceiveFunctionsKt$receiveText$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ApplicationReceiveFunctionsKt.receiveText((ApplicationCall) null, this);
    }
}
