package io.ktor.server.netty.cio;

import io.ktor.utils.io.ByteWriteChannel;
import io.netty.buffer.ByteBuf;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.netty.cio.RequestBodyHandler", f = "RequestBodyHandler.kt", i = {0}, l = {170}, m = "copy", n = {"length"}, s = {"I$0"})
/* compiled from: RequestBodyHandler.kt */
final class RequestBodyHandler$copy$1 extends ContinuationImpl {
    int I$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RequestBodyHandler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RequestBodyHandler$copy$1(RequestBodyHandler requestBodyHandler, Continuation<? super RequestBodyHandler$copy$1> continuation) {
        super(continuation);
        this.this$0 = requestBodyHandler;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.copy((ByteBuf) null, (ByteWriteChannel) null, this);
    }
}
