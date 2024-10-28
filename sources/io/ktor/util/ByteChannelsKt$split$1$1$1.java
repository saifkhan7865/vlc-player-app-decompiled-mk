package io.ktor.util;

import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.core.ByteReadPacket;
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
@DebugMetadata(c = "io.ktor.util.ByteChannelsKt$split$1$1$1", f = "ByteChannels.kt", i = {}, l = {27}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ByteChannels.kt */
final class ByteChannelsKt$split$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteReadPacket $chunk;
    final /* synthetic */ ByteChannel $first;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ByteChannelsKt$split$1$1$1(ByteChannel byteChannel, ByteReadPacket byteReadPacket, Continuation<? super ByteChannelsKt$split$1$1$1> continuation) {
        super(2, continuation);
        this.$first = byteChannel;
        this.$chunk = byteReadPacket;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ByteChannelsKt$split$1$1$1(this.$first, this.$chunk, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ByteChannelsKt$split$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (this.$first.writePacket(this.$chunk.copy(), this) == coroutine_suspended) {
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
