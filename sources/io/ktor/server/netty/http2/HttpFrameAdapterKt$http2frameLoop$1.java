package io.ktor.server.netty.http2;

import io.ktor.utils.io.ByteWriteChannel;
import io.netty.handler.codec.http2.Http2DataFrame;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.channels.ReceiveChannel;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.netty.http2.HttpFrameAdapterKt", f = "HttpFrameAdapter.kt", i = {0, 0, 1, 1, 1, 1}, l = {15, 19}, m = "http2frameLoop", n = {"$this$http2frameLoop", "bc", "$this$http2frameLoop", "bc", "message", "content"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$3"})
/* compiled from: HttpFrameAdapter.kt */
final class HttpFrameAdapterKt$http2frameLoop$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    HttpFrameAdapterKt$http2frameLoop$1(Continuation<? super HttpFrameAdapterKt$http2frameLoop$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return HttpFrameAdapterKt.http2frameLoop((ReceiveChannel<? extends Http2DataFrame>) null, (ByteWriteChannel) null, this);
    }
}
