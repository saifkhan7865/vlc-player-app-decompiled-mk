package io.ktor.server.engine;

import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.BaseApplicationResponse", f = "BaseApplicationResponse.kt", i = {0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, l = {208, 210, 215}, m = "respondFromChannel$suspendImpl", n = {"$this", "readChannel", "$this", "readChannel", "$this$use$iv", "length", "$this", "$this$use$iv", "length", "copied"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "J$0"})
/* compiled from: BaseApplicationResponse.kt */
final class BaseApplicationResponse$respondFromChannel$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BaseApplicationResponse this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseApplicationResponse$respondFromChannel$1(BaseApplicationResponse baseApplicationResponse, Continuation<? super BaseApplicationResponse$respondFromChannel$1> continuation) {
        super(continuation);
        this.this$0 = baseApplicationResponse;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return BaseApplicationResponse.respondFromChannel$suspendImpl(this.this$0, (ByteReadChannel) null, this);
    }
}
