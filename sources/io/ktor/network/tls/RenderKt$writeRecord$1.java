package io.ktor.network.tls;

import io.ktor.utils.io.ByteWriteChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.tls.RenderKt", f = "Render.kt", i = {0, 0, 1, 1, 2, 2, 3, 3, 4}, l = {17, 18, 19, 20, 21}, m = "writeRecord", n = {"$this$writeRecord", "$this$writeRecord_u24lambda_u240", "$this$writeRecord", "$this$writeRecord_u24lambda_u240", "$this$writeRecord", "$this$writeRecord_u24lambda_u240", "$this$writeRecord", "$this$writeRecord_u24lambda_u240", "$this$writeRecord"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$0"})
/* compiled from: Render.kt */
final class RenderKt$writeRecord$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    RenderKt$writeRecord$1(Continuation<? super RenderKt$writeRecord$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return RenderKt.writeRecord((ByteWriteChannel) null, (TLSRecord) null, this);
    }
}
