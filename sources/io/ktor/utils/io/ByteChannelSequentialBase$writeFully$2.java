package io.ktor.utils.io;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.ByteChannelSequentialBase", f = "ByteChannelSequential.kt", i = {0, 0, 0, 0}, l = {204}, m = "writeFully$suspendImpl", n = {"$this", "src", "currentIndex", "endIndex"}, s = {"L$0", "L$1", "I$0", "I$1"})
/* compiled from: ByteChannelSequential.kt */
final class ByteChannelSequentialBase$writeFully$2 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteChannelSequentialBase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ByteChannelSequentialBase$writeFully$2(ByteChannelSequentialBase byteChannelSequentialBase, Continuation<? super ByteChannelSequentialBase$writeFully$2> continuation) {
        super(continuation);
        this.this$0 = byteChannelSequentialBase;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ByteChannelSequentialBase.writeFully$suspendImpl(this.this$0, (byte[]) null, 0, 0, this);
    }
}
