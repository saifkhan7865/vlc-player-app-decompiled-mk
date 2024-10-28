package io.ktor.server.response;

import io.ktor.http.ContentType;
import io.ktor.http.HttpStatusCode;
import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.response.ApplicationResponseFunctionsKt", f = "ApplicationResponseFunctions.kt", i = {0, 0, 0}, l = {139, 224}, m = "respondText", n = {"$this$respondText", "contentType", "status"}, s = {"L$0", "L$1", "L$2"})
/* compiled from: ApplicationResponseFunctions.kt */
final class ApplicationResponseFunctionsKt$respondText$3 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    ApplicationResponseFunctionsKt$respondText$3(Continuation<? super ApplicationResponseFunctionsKt$respondText$3> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ApplicationResponseFunctionsKt.respondText((ApplicationCall) null, (ContentType) null, (HttpStatusCode) null, (Function1<? super Continuation<? super String>, ? extends Object>) null, this);
    }
}
