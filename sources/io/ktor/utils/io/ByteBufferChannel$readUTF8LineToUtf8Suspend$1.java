package io.ktor.utils.io;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1}, l = {1960, 2036}, m = "readUTF8LineToUtf8Suspend", n = {"this", "out", "consumed", "required", "caret", "newLine", "output", "transferBuffer", "transferredRemaining", "limit", "this", "consumed", "caret", "newLine"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "I$0", "L$0", "L$1", "L$2", "L$3"})
/* compiled from: ByteBufferChannel.kt */
final class ByteBufferChannel$readUTF8LineToUtf8Suspend$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ByteBufferChannel$readUTF8LineToUtf8Suspend$1(ByteBufferChannel byteBufferChannel, Continuation<? super ByteBufferChannel$readUTF8LineToUtf8Suspend$1> continuation) {
        super(continuation);
        this.this$0 = byteBufferChannel;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.readUTF8LineToUtf8Suspend((Appendable) null, 0, this);
    }
}
