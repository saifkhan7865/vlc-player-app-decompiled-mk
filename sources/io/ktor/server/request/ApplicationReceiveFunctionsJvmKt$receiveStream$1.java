package io.ktor.server.request;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
@DebugMetadata(c = "io.ktor.server.request.ApplicationReceiveFunctionsJvmKt", f = "ApplicationReceiveFunctionsJvm.kt", i = {}, l = {18}, m = "receiveStream", n = {}, s = {})
/* compiled from: ApplicationReceiveFunctionsJvm.kt */
final class ApplicationReceiveFunctionsJvmKt$receiveStream$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    ApplicationReceiveFunctionsJvmKt$receiveStream$1(Continuation<? super ApplicationReceiveFunctionsJvmKt$receiveStream$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ApplicationReceiveFunctionsJvmKt.receiveStream((ApplicationCall) null, this);
    }
}
