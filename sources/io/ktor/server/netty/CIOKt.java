package io.ktor.server.netty;

import io.netty.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a!\u0010\t\u001a\u0002H\n\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001aA\u0010\t\u001a\u0002H\n\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u000b2\u001e\u0010\r\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\n0\u0003\u0012\u0004\u0012\u00020\u00040\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a!\u0010\u000f\u001a\u0002H\n\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a\r\u0010\u0010\u001a\u00020\u0002*\u00020\u0002H\u0010\"*\u0010\u0000\u001a\u0018\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u00040\u0001X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0005\u0010\u0006\"*\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0003\u0012\u0004\u0012\u00020\u00040\u0001X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"identityErrorHandler", "Lkotlin/Function2;", "", "Lkotlin/coroutines/Continuation;", "", "getIdentityErrorHandler$annotations", "()V", "wrappingErrorHandler", "getWrappingErrorHandler$annotations", "suspendAwait", "T", "Lio/netty/util/concurrent/Future;", "(Lio/netty/util/concurrent/Future;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "exception", "(Lio/netty/util/concurrent/Future;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "suspendWriteAwait", "unwrap", "ktor-server-netty"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: CIO.kt */
public final class CIOKt {
    private static final Function2<Throwable, Continuation<?>, Unit> identityErrorHandler = CIOKt$identityErrorHandler$1.INSTANCE;
    private static final Function2<Throwable, Continuation<?>, Unit> wrappingErrorHandler = CIOKt$wrappingErrorHandler$1.INSTANCE;

    private static /* synthetic */ void getIdentityErrorHandler$annotations() {
    }

    private static /* synthetic */ void getWrappingErrorHandler$annotations() {
    }

    public static final <T> Object suspendAwait(Future<T> future, Continuation<? super T> continuation) {
        return suspendAwait(future, identityErrorHandler, continuation);
    }

    public static final <T> Object suspendWriteAwait(Future<T> future, Continuation<? super T> continuation) {
        return suspendAwait(future, wrappingErrorHandler, continuation);
    }

    public static final <T> Object suspendAwait(Future<T> future, Function2<? super Throwable, ? super Continuation<? super T>, Unit> function2, Continuation<? super T> continuation) {
        if (future.isDone()) {
            try {
                return future.get();
            } catch (Throwable th) {
                throw unwrap(th);
            }
        } else {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            future.addListener(new CoroutineListener(future, cancellableContinuationImpl, function2));
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result;
        }
    }

    /* access modifiers changed from: private */
    public static final Throwable unwrap(Throwable th) {
        while ((th instanceof ExecutionException) && th.getCause() != null) {
            th = th.getCause();
            Intrinsics.checkNotNull(th);
        }
        return th;
    }
}
