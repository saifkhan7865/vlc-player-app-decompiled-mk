package io.ktor.server.request;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
@DebugMetadata(c = "io.ktor.server.request.ApplicationReceiveFunctionsKt", f = "ApplicationReceiveFunctions.kt", i = {}, l = {218}, m = "receiveMultipart", n = {}, s = {})
/* compiled from: ApplicationReceiveFunctions.kt */
final class ApplicationReceiveFunctionsKt$receiveMultipart$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    ApplicationReceiveFunctionsKt$receiveMultipart$1(Continuation<? super ApplicationReceiveFunctionsKt$receiveMultipart$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ApplicationReceiveFunctionsKt.receiveMultipart((ApplicationCall) null, this);
    }
}