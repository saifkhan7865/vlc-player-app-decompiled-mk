package io.ktor.network.sockets;

import io.ktor.utils.io.ByteChannel;
import java.net.SocketTimeoutException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.sockets.CIOWriterKt$attachForWritingImpl$1$timeout$1", f = "CIOWriter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CIOWriter.kt */
final class CIOWriterKt$attachForWritingImpl$1$timeout$1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteChannel $channel;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CIOWriterKt$attachForWritingImpl$1$timeout$1(ByteChannel byteChannel, Continuation<? super CIOWriterKt$attachForWritingImpl$1$timeout$1> continuation) {
        super(1, continuation);
        this.$channel = byteChannel;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new CIOWriterKt$attachForWritingImpl$1$timeout$1(this.$channel, continuation);
    }

    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((CIOWriterKt$attachForWritingImpl$1$timeout$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.$channel.close(new SocketTimeoutException());
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
