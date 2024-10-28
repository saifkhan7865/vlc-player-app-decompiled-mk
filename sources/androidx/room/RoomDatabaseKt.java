package androidx.room;

import java.util.Set;
import java.util.concurrent.RejectedExecutionException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.ThreadContextElementKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a9\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006*\u00020\u00022\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\b0\n\"\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\r\u001aL\u0010\u000e\u001a\u0002H\u000f\"\u0004\b\u0000\u0010\u000f*\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00012'\u0010\u0011\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000f0\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0012¢\u0006\u0002\b\u0016H@ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a9\u0010\u0018\u001a\u0002H\u000f\"\u0004\b\u0000\u0010\u000f*\u00020\u00022\u001c\u0010\u0019\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000f0\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u001aH@ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"createTransactionContext", "Lkotlin/coroutines/CoroutineContext;", "Landroidx/room/RoomDatabase;", "dispatcher", "Lkotlin/coroutines/ContinuationInterceptor;", "invalidationTrackerFlow", "Lkotlinx/coroutines/flow/Flow;", "", "", "tables", "", "emitInitialState", "", "(Landroidx/room/RoomDatabase;[Ljava/lang/String;Z)Lkotlinx/coroutines/flow/Flow;", "startTransactionCoroutine", "R", "context", "transactionBlock", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/room/RoomDatabase;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withTransaction", "block", "Lkotlin/Function1;", "(Landroidx/room/RoomDatabase;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "room-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoomDatabaseExt.kt */
public final class RoomDatabaseKt {
    public static final <R> Object withTransaction(RoomDatabase roomDatabase, Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super R> continuation) {
        ContinuationInterceptor continuationInterceptor = null;
        Function2 roomDatabaseKt$withTransaction$transactionBlock$1 = new RoomDatabaseKt$withTransaction$transactionBlock$1(roomDatabase, function1, (Continuation<? super RoomDatabaseKt$withTransaction$transactionBlock$1>) null);
        TransactionElement transactionElement = (TransactionElement) continuation.getContext().get(TransactionElement.Key);
        if (transactionElement != null) {
            continuationInterceptor = transactionElement.getTransactionDispatcher$room_ktx_release();
        }
        if (continuationInterceptor != null) {
            return BuildersKt.withContext(continuationInterceptor, roomDatabaseKt$withTransaction$transactionBlock$1, continuation);
        }
        return startTransactionCoroutine(roomDatabase, continuation.getContext(), roomDatabaseKt$withTransaction$transactionBlock$1, continuation);
    }

    /* access modifiers changed from: private */
    public static final CoroutineContext createTransactionContext(RoomDatabase roomDatabase, ContinuationInterceptor continuationInterceptor) {
        TransactionElement transactionElement = new TransactionElement(continuationInterceptor);
        return continuationInterceptor.plus(transactionElement).plus(ThreadContextElementKt.asContextElement(roomDatabase.getSuspendingTransactionId(), Integer.valueOf(System.identityHashCode(transactionElement))));
    }

    public static /* synthetic */ Flow invalidationTrackerFlow$default(RoomDatabase roomDatabase, String[] strArr, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return invalidationTrackerFlow(roomDatabase, strArr, z);
    }

    public static final Flow<Set<String>> invalidationTrackerFlow(RoomDatabase roomDatabase, String[] strArr, boolean z) {
        return FlowKt.callbackFlow(new RoomDatabaseKt$invalidationTrackerFlow$1(z, roomDatabase, strArr, (Continuation<? super RoomDatabaseKt$invalidationTrackerFlow$1>) null));
    }

    /* access modifiers changed from: private */
    public static final <R> Object startTransactionCoroutine(RoomDatabase roomDatabase, CoroutineContext coroutineContext, Function2<? super CoroutineScope, ? super Continuation<? super R>, ? extends Object> function2, Continuation<? super R> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        try {
            roomDatabase.getTransactionExecutor().execute(new RoomDatabaseKt$startTransactionCoroutine$2$1(coroutineContext, cancellableContinuation, roomDatabase, function2));
        } catch (RejectedExecutionException e) {
            cancellableContinuation.cancel(new IllegalStateException("Unable to acquire a thread to perform the database transaction.", e));
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
