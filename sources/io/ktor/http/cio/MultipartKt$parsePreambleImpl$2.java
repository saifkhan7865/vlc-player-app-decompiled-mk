package io.ktor.http.cio;

import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.OutputArraysJVMKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "it", "Ljava/nio/ByteBuffer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.http.cio.MultipartKt$parsePreambleImpl$2", f = "Multipart.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Multipart.kt */
final class MultipartKt$parsePreambleImpl$2 extends SuspendLambda implements Function2<ByteBuffer, Continuation<? super Unit>, Object> {
    final /* synthetic */ BytePacketBuilder $output;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MultipartKt$parsePreambleImpl$2(BytePacketBuilder bytePacketBuilder, Continuation<? super MultipartKt$parsePreambleImpl$2> continuation) {
        super(2, continuation);
        this.$output = bytePacketBuilder;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MultipartKt$parsePreambleImpl$2 multipartKt$parsePreambleImpl$2 = new MultipartKt$parsePreambleImpl$2(this.$output, continuation);
        multipartKt$parsePreambleImpl$2.L$0 = obj;
        return multipartKt$parsePreambleImpl$2;
    }

    public final Object invoke(ByteBuffer byteBuffer, Continuation<? super Unit> continuation) {
        return ((MultipartKt$parsePreambleImpl$2) create(byteBuffer, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            OutputArraysJVMKt.writeFully(this.$output, (ByteBuffer) this.L$0);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
