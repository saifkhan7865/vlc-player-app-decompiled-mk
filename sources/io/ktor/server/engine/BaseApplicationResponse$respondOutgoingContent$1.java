package io.ktor.server.engine;

import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.BaseApplicationResponse", f = "BaseApplicationResponse.kt", i = {0, 1, 2, 3, 3, 4}, l = {115, 124, 132, 142, 151}, m = "respondOutgoingContent$suspendImpl", n = {"$this", "$this", "$this", "$this", "readChannel", "$this"}, s = {"L$0", "L$0", "L$0", "L$0", "L$1", "L$0"})
/* compiled from: BaseApplicationResponse.kt */
final class BaseApplicationResponse$respondOutgoingContent$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BaseApplicationResponse this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseApplicationResponse$respondOutgoingContent$1(BaseApplicationResponse baseApplicationResponse, Continuation<? super BaseApplicationResponse$respondOutgoingContent$1> continuation) {
        super(continuation);
        this.this$0 = baseApplicationResponse;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return BaseApplicationResponse.respondOutgoingContent$suspendImpl(this.this$0, (OutgoingContent) null, this);
    }
}
