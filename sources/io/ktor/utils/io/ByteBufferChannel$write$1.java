package io.ktor.utils.io;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", i = {0, 0, 0}, l = {1507}, m = "write$suspendImpl", n = {"$this", "block", "min"}, s = {"L$0", "L$1", "I$0"})
/* compiled from: ByteBufferChannel.kt */
final class ByteBufferChannel$write$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ByteBufferChannel$write$1(ByteBufferChannel byteBufferChannel, Continuation<? super ByteBufferChannel$write$1> continuation) {
        super(continuation);
        this.this$0 = byteBufferChannel;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ByteBufferChannel.write$suspendImpl(this.this$0, 0, (Function1<? super ByteBuffer, Unit>) null, this);
    }
}
