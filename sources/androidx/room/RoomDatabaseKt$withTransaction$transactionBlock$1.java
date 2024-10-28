package androidx.room;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.room.RoomDatabaseKt$withTransaction$transactionBlock$1", f = "RoomDatabaseExt.kt", i = {0}, l = {62}, m = "invokeSuspend", n = {"transactionElement"}, s = {"L$0"})
/* compiled from: RoomDatabaseExt.kt */
final class RoomDatabaseKt$withTransaction$transactionBlock$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super R>, Object> {
    final /* synthetic */ Function1<Continuation<? super R>, Object> $block;
    final /* synthetic */ RoomDatabase $this_withTransaction;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RoomDatabaseKt$withTransaction$transactionBlock$1(RoomDatabase roomDatabase, Function1<? super Continuation<? super R>, ? extends Object> function1, Continuation<? super RoomDatabaseKt$withTransaction$transactionBlock$1> continuation) {
        super(2, continuation);
        this.$this_withTransaction = roomDatabase;
        this.$block = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RoomDatabaseKt$withTransaction$transactionBlock$1 roomDatabaseKt$withTransaction$transactionBlock$1 = new RoomDatabaseKt$withTransaction$transactionBlock$1(this.$this_withTransaction, this.$block, continuation);
        roomDatabaseKt$withTransaction$transactionBlock$1.L$0 = obj;
        return roomDatabaseKt$withTransaction$transactionBlock$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super R> continuation) {
        return ((RoomDatabaseKt$withTransaction$transactionBlock$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        TransactionElement transactionElement;
        Throwable th;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineContext.Element element = ((CoroutineScope) this.L$0).getCoroutineContext().get(TransactionElement.Key);
            Intrinsics.checkNotNull(element);
            TransactionElement transactionElement2 = (TransactionElement) element;
            transactionElement2.acquire();
            try {
                this.$this_withTransaction.beginTransaction();
                try {
                    Function1<Continuation<? super R>, Object> function1 = this.$block;
                    this.L$0 = transactionElement2;
                    this.label = 1;
                    Object invoke = function1.invoke(this);
                    if (invoke == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    transactionElement = transactionElement2;
                    obj = invoke;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    transactionElement = transactionElement2;
                    th = th3;
                    this.$this_withTransaction.endTransaction();
                    throw th;
                }
            } catch (Throwable th4) {
                Throwable th5 = th4;
                transactionElement = transactionElement2;
                th = th5;
                transactionElement.release();
                throw th;
            }
        } else if (i == 1) {
            transactionElement = (TransactionElement) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th6) {
                th = th6;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.$this_withTransaction.setTransactionSuccessful();
        try {
            this.$this_withTransaction.endTransaction();
            transactionElement.release();
            return obj;
        } catch (Throwable th7) {
            th = th7;
            transactionElement.release();
            throw th;
        }
    }
}
