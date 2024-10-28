package io.ktor.server.netty.cio;

import io.ktor.utils.io.ByteWriteChannel;
import io.netty.buffer.ByteBuf;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.netty.cio.RequestBodyHandler", f = "RequestBodyHandler.kt", i = {0}, l = {136}, m = "processContent", n = {"buf"}, s = {"L$0"})
/* compiled from: RequestBodyHandler.kt */
final class RequestBodyHandler$processContent$2 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RequestBodyHandler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RequestBodyHandler$processContent$2(RequestBodyHandler requestBodyHandler, Continuation<? super RequestBodyHandler$processContent$2> continuation) {
        super(continuation);
        this.this$0 = requestBodyHandler;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.processContent((ByteWriteChannel) null, (ByteBuf) null, (Continuation<? super Integer>) this);
    }
}
