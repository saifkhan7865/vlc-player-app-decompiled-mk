package io.ktor.server.netty;

import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u000e\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u0002H\u00020\u00042#\u0012\u0015\u0012\u0013\u0018\u00010\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\n0\u0005j\u0002`\u000bB;\u0012\u0006\u0010\f\u001a\u00028\u0001\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e\u0012\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0011\u0012\u0004\u0012\u00020\n0\u0010¢\u0006\u0002\u0010\u0012J\u0013\u0010\u0014\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0006H\u0002J\u0015\u0010\u0016\u001a\u00020\n2\u0006\u0010\f\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0017R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eX\u0004¢\u0006\u0002\n\u0000R&\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0011\u0012\u0004\u0012\u00020\n0\u0010X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00028\u0001X\u0004¢\u0006\u0004\n\u0002\u0010\u0013¨\u0006\u0018"}, d2 = {"Lio/ktor/server/netty/CoroutineListener;", "T", "F", "Lio/netty/util/concurrent/Future;", "Lio/netty/util/concurrent/GenericFutureListener;", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "cause", "", "Lkotlinx/coroutines/CompletionHandler;", "future", "continuation", "Lkotlinx/coroutines/CancellableContinuation;", "exception", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "(Lio/netty/util/concurrent/Future;Lkotlinx/coroutines/CancellableContinuation;Lkotlin/jvm/functions/Function2;)V", "Lio/netty/util/concurrent/Future;", "invoke", "p1", "operationComplete", "(Lio/netty/util/concurrent/Future;)V", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CIO.kt */
final class CoroutineListener<T, F extends Future<T>> implements GenericFutureListener<F>, Function1<Throwable, Unit> {
    private final CancellableContinuation<T> continuation;
    private final Function2<Throwable, Continuation<? super T>, Unit> exception;
    private final F future;

    public CoroutineListener(F f, CancellableContinuation<? super T> cancellableContinuation, Function2<? super Throwable, ? super Continuation<? super T>, Unit> function2) {
        Intrinsics.checkNotNullParameter(f, "future");
        Intrinsics.checkNotNullParameter(cancellableContinuation, "continuation");
        Intrinsics.checkNotNullParameter(function2, "exception");
        this.future = f;
        this.continuation = cancellableContinuation;
        this.exception = function2;
        cancellableContinuation.invokeOnCancellation(this);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public void operationComplete(F f) {
        Intrinsics.checkNotNullParameter(f, "future");
        try {
            Object obj = f.get();
            Result.Companion companion = Result.Companion;
            this.continuation.resumeWith(Result.m1774constructorimpl(obj));
        } catch (Throwable th) {
            this.exception.invoke(CIOKt.unwrap(th), this.continuation);
        }
    }

    public void invoke(Throwable th) {
        this.future.removeListener(this);
        if (this.continuation.isCancelled()) {
            this.future.cancel(false);
        }
    }
}
