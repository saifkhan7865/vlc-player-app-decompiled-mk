package io.ktor.network.sockets;

import io.ktor.network.selector.SelectInterest;
import io.ktor.network.util.PoolsKt;
import io.ktor.utils.io.pool.ObjectPool;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.sockets.DatagramSendChannel$send$2$1", f = "DatagramSendChannel.kt", i = {0, 0}, l = {86}, m = "invokeSuspend", n = {"$this$useInstance$iv", "instance$iv"}, s = {"L$0", "L$1"})
/* compiled from: DatagramSendChannel.kt */
final class DatagramSendChannel$send$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Datagram $element;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ DatagramSendChannel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DatagramSendChannel$send$2$1(Datagram datagram, DatagramSendChannel datagramSendChannel, Continuation<? super DatagramSendChannel$send$2$1> continuation) {
        super(2, continuation);
        this.$element = datagram;
        this.this$0 = datagramSendChannel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DatagramSendChannel$send$2$1(this.$element, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DatagramSendChannel$send$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        ObjectPool<ByteBuffer> objectPool;
        ByteBuffer byteBuffer;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            objectPool = PoolsKt.getDefaultDatagramByteBufferPool();
            Datagram datagram = this.$element;
            DatagramSendChannel datagramSendChannel = this.this$0;
            ByteBuffer borrow = objectPool.borrow();
            try {
                ByteBuffer byteBuffer2 = borrow;
                DatagramSendChannelKt.writeMessageTo(datagram, byteBuffer2);
                if (datagramSendChannel.getChannel().send(byteBuffer2, JavaSocketAddressUtilsKt.toJavaAddress(datagram.getAddress())) != 0) {
                    datagramSendChannel.getSocket().interestOp(SelectInterest.WRITE, false);
                } else {
                    SocketAddress address = datagram.getAddress();
                    this.L$0 = objectPool;
                    this.L$1 = borrow;
                    this.label = 1;
                    if (datagramSendChannel.sendSuspend(byteBuffer2, address, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                byteBuffer = borrow;
            } catch (Throwable th) {
                th = th;
                byteBuffer = borrow;
                objectPool.recycle(byteBuffer);
                throw th;
            }
        } else if (i == 1) {
            byteBuffer = this.L$1;
            objectPool = (ObjectPool) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th2) {
                th = th2;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        Unit unit = Unit.INSTANCE;
        objectPool.recycle(byteBuffer);
        return Unit.INSTANCE;
    }
}
