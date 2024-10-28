package androidx.room;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "R", "run"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoomDatabaseExt.kt */
final class RoomDatabaseKt$startTransactionCoroutine$2$1 implements Runnable {
    final /* synthetic */ CoroutineContext $context;
    final /* synthetic */ CancellableContinuation<R> $continuation;
    final /* synthetic */ RoomDatabase $this_startTransactionCoroutine;
    final /* synthetic */ Function2<CoroutineScope, Continuation<? super R>, Object> $transactionBlock;

    RoomDatabaseKt$startTransactionCoroutine$2$1(CoroutineContext coroutineContext, CancellableContinuation<? super R> cancellableContinuation, RoomDatabase roomDatabase, Function2<? super CoroutineScope, ? super Continuation<? super R>, ? extends Object> function2) {
        this.$context = coroutineContext;
        this.$continuation = cancellableContinuation;
        this.$this_startTransactionCoroutine = roomDatabase;
        this.$transactionBlock = function2;
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.RoomDatabaseKt$startTransactionCoroutine$2$1$1", f = "RoomDatabaseExt.kt", i = {}, l = {103}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.room.RoomDatabaseKt$startTransactionCoroutine$2$1$1  reason: invalid class name */
    /* compiled from: RoomDatabaseExt.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(roomDatabase, cancellableContinuation, function2, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Continuation continuation;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineContext.Element element = ((CoroutineScope) this.L$0).getCoroutineContext().get(ContinuationInterceptor.Key);
                Intrinsics.checkNotNull(element);
                CoroutineContext access$createTransactionContext = RoomDatabaseKt.createTransactionContext(roomDatabase, (ContinuationInterceptor) element);
                Continuation continuation2 = cancellableContinuation;
                Result.Companion companion = Result.Companion;
                this.L$0 = continuation2;
                this.label = 1;
                obj = BuildersKt.withContext(access$createTransactionContext, function2, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                continuation = continuation2;
            } else if (i == 1) {
                continuation = (Continuation) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            continuation.resumeWith(Result.m1774constructorimpl(obj));
            return Unit.INSTANCE;
        }
    }

    public final void run() {
        try {
            CoroutineContext minusKey = this.$context.minusKey(ContinuationInterceptor.Key);
            final RoomDatabase roomDatabase = this.$this_startTransactionCoroutine;
            final CancellableContinuation<R> cancellableContinuation = this.$continuation;
            final Function2<CoroutineScope, Continuation<? super R>, Object> function2 = this.$transactionBlock;
            BuildersKt.runBlocking(minusKey, new AnonymousClass1((Continuation<? super AnonymousClass1>) null));
        } catch (Throwable th) {
            this.$continuation.cancel(th);
        }
    }
}
