package io.ktor.server.engine;

import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.BaseApplicationResponse", f = "BaseApplicationResponse.kt", i = {0, 1}, l = {169, 173}, m = "respondWriteChannelContent$suspendImpl", n = {"content", "$this$use$iv"}, s = {"L$0", "L$0"})
/* compiled from: BaseApplicationResponse.kt */
final class BaseApplicationResponse$respondWriteChannelContent$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BaseApplicationResponse this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseApplicationResponse$respondWriteChannelContent$1(BaseApplicationResponse baseApplicationResponse, Continuation<? super BaseApplicationResponse$respondWriteChannelContent$1> continuation) {
        super(continuation);
        this.this$0 = baseApplicationResponse;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return BaseApplicationResponse.respondWriteChannelContent$suspendImpl(this.this$0, (OutgoingContent.WriteChannelContent) null, this);
    }
}
