package io.ktor.http.cio;

import io.ktor.utils.io.ByteWriteChannel;
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
@DebugMetadata(c = "io.ktor.http.cio.MultipartKt$parsePartBodyImpl$size$1", f = "Multipart.kt", i = {}, l = {177}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Multipart.kt */
final class MultipartKt$parsePartBodyImpl$size$1 extends SuspendLambda implements Function2<ByteBuffer, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteWriteChannel $output;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MultipartKt$parsePartBodyImpl$size$1(ByteWriteChannel byteWriteChannel, Continuation<? super MultipartKt$parsePartBodyImpl$size$1> continuation) {
        super(2, continuation);
        this.$output = byteWriteChannel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MultipartKt$parsePartBodyImpl$size$1 multipartKt$parsePartBodyImpl$size$1 = new MultipartKt$parsePartBodyImpl$size$1(this.$output, continuation);
        multipartKt$parsePartBodyImpl$size$1.L$0 = obj;
        return multipartKt$parsePartBodyImpl$size$1;
    }

    public final Object invoke(ByteBuffer byteBuffer, Continuation<? super Unit> continuation) {
        return ((MultipartKt$parsePartBodyImpl$size$1) create(byteBuffer, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ByteWriteChannel byteWriteChannel = this.$output;
            this.label = 1;
            if (byteWriteChannel.writeFully((ByteBuffer) this.L$0, (Continuation<? super Unit>) this) == coroutine_suspended) {
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
