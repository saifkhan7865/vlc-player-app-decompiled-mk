package io.ktor.utils.io;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.utils.io.ByteBufferChannel", f = "ByteBufferChannel.kt", i = {0}, l = {2374}, m = "peekTo-lBXzO7A$suspendImpl", n = {"bytesCopied"}, s = {"L$0"})
/* compiled from: ByteBufferChannel.kt */
final class ByteBufferChannel$peekTo$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ByteBufferChannel$peekTo$1(ByteBufferChannel byteBufferChannel, Continuation<? super ByteBufferChannel$peekTo$1> continuation) {
        super(continuation);
        this.this$0 = byteBufferChannel;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ByteBufferChannel.m146peekTolBXzO7A$suspendImpl(this.this$0, (ByteBuffer) null, 0, 0, 0, 0, this);
    }
}
