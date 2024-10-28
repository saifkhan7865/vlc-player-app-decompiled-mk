package io.ktor.utils.io;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import io.ktor.utils.io.internal.ClosedElement;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "ucont", "Lkotlin/coroutines/Continuation;", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ByteBufferChannel.kt */
final class ByteBufferChannel$writeSuspension$1 extends Lambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ ByteBufferChannel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ByteBufferChannel$writeSuspension$1(ByteBufferChannel byteBufferChannel) {
        super(1);
        this.this$0 = byteBufferChannel;
    }

    public final Object invoke(Continuation<? super Unit> continuation) {
        Throwable sendException;
        Intrinsics.checkNotNullParameter(continuation, "ucont");
        int access$getWriteSuspensionSize$p = this.this$0.writeSuspensionSize;
        while (true) {
            ClosedElement access$getClosed = this.this$0.getClosed();
            if (access$getClosed != null && (sendException = access$getClosed.getSendException()) != null) {
                Void unused = ByteBufferChannelKt.rethrowClosed(sendException);
                throw new KotlinNothingValueException();
            } else if (!this.this$0.writeSuspendPredicate(access$getWriteSuspensionSize$p)) {
                Result.Companion companion = Result.Companion;
                continuation.resumeWith(Result.m1774constructorimpl(Unit.INSTANCE));
                break;
            } else {
                ByteBufferChannel byteBufferChannel = this.this$0;
                Continuation<? super Unit> intercepted = IntrinsicsKt.intercepted(continuation);
                ByteBufferChannel byteBufferChannel2 = this.this$0;
                while (byteBufferChannel.getWriteOp() == null) {
                    if (byteBufferChannel2.writeSuspendPredicate(access$getWriteSuspensionSize$p)) {
                        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(ByteBufferChannel._writeOp$FU, byteBufferChannel, (Object) null, intercepted)) {
                            if (byteBufferChannel2.writeSuspendPredicate(access$getWriteSuspensionSize$p) || !AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(ByteBufferChannel._writeOp$FU, byteBufferChannel, intercepted, (Object) null)) {
                                break;
                            }
                        }
                    }
                }
                throw new IllegalStateException("Operation is already in progress".toString());
            }
        }
        this.this$0.flushImpl(access$getWriteSuspensionSize$p);
        if (this.this$0.shouldResumeReadOp()) {
            this.this$0.resumeReadOp();
        }
        return IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }
}
