package io.ktor.utils.io;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", i = {0, 0, 0, 0}, l = {218}, m = "writeFully-JT6ljtQ$suspendImpl", n = {"$this", "memory", "endIndex", "currentIndex"}, s = {"L$0", "L$1", "I$0", "I$1"})
/* compiled from: ByteChannelSequential.kt */
final class ByteChannelSequentialBase$writeFully$3 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteChannelSequentialBase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ByteChannelSequentialBase$writeFully$3(ByteChannelSequentialBase byteChannelSequentialBase, Continuation<? super ByteChannelSequentialBase$writeFully$3> continuation) {
        super(continuation);
        this.this$0 = byteChannelSequentialBase;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ByteChannelSequentialBase.m150writeFullyJT6ljtQ$suspendImpl(this.this$0, (ByteBuffer) null, 0, 0, this);
    }
}
