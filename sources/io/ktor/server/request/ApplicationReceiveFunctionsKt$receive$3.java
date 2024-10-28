package io.ktor.server.request;

import io.ktor.server.application.ApplicationCall;
import io.ktor.util.reflect.TypeInfo;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.request.ApplicationReceiveFunctionsKt", f = "ApplicationReceiveFunctions.kt", i = {}, l = {121}, m = "receive", n = {}, s = {})
/* compiled from: ApplicationReceiveFunctions.kt */
final class ApplicationReceiveFunctionsKt$receive$3<T> extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    ApplicationReceiveFunctionsKt$receive$3(Continuation<? super ApplicationReceiveFunctionsKt$receive$3> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ApplicationReceiveFunctionsKt.receive((ApplicationCall) null, (TypeInfo) null, this);
    }
}
