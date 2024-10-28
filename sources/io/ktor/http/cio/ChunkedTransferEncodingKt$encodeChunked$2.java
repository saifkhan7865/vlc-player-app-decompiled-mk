package io.ktor.http.cio;

import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.ReaderScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lio/ktor/utils/io/ReaderScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.http.cio.ChunkedTransferEncodingKt$encodeChunked$2", f = "ChunkedTransferEncoding.kt", i = {}, l = {126}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ChunkedTransferEncoding.kt */
final class ChunkedTransferEncodingKt$encodeChunked$2 extends SuspendLambda implements Function2<ReaderScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteWriteChannel $output;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ChunkedTransferEncodingKt$encodeChunked$2(ByteWriteChannel byteWriteChannel, Continuation<? super ChunkedTransferEncodingKt$encodeChunked$2> continuation) {
        super(2, continuation);
        this.$output = byteWriteChannel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ChunkedTransferEncodingKt$encodeChunked$2 chunkedTransferEncodingKt$encodeChunked$2 = new ChunkedTransferEncodingKt$encodeChunked$2(this.$output, continuation);
        chunkedTransferEncodingKt$encodeChunked$2.L$0 = obj;
        return chunkedTransferEncodingKt$encodeChunked$2;
    }

    public final Object invoke(ReaderScope readerScope, Continuation<? super Unit> continuation) {
        return ((ChunkedTransferEncodingKt$encodeChunked$2) create(readerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (ChunkedTransferEncodingKt.encodeChunked(this.$output, ((ReaderScope) this.L$0).getChannel(), (Continuation<? super Unit>) this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
