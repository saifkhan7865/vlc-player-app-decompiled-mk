package io.ktor.client.plugins;

import io.ktor.client.statement.HttpResponse;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.HttpCallValidator", f = "HttpCallValidator.kt", i = {0}, l = {51}, m = "validateResponse", n = {"response"}, s = {"L$0"})
/* compiled from: HttpCallValidator.kt */
final class HttpCallValidator$validateResponse$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HttpCallValidator this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HttpCallValidator$validateResponse$1(HttpCallValidator httpCallValidator, Continuation<? super HttpCallValidator$validateResponse$1> continuation) {
        super(continuation);
        this.this$0 = httpCallValidator;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.validateResponse((HttpResponse) null, this);
    }
}
