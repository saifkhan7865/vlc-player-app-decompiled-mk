package io.ktor.utils.io;

import io.ktor.utils.io.core.ByteReadPacket;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", i = {0, 0}, l = {186}, m = "writePacket$suspendImpl", n = {"$this", "packet"}, s = {"L$0", "L$1"})
/* compiled from: ByteChannelSequential.kt */
final class ByteChannelSequentialBase$writePacket$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteChannelSequentialBase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ByteChannelSequentialBase$writePacket$1(ByteChannelSequentialBase byteChannelSequentialBase, Continuation<? super ByteChannelSequentialBase$writePacket$1> continuation) {
        super(continuation);
        this.this$0 = byteChannelSequentialBase;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ByteChannelSequentialBase.writePacket$suspendImpl(this.this$0, (ByteReadPacket) null, this);
    }
}
