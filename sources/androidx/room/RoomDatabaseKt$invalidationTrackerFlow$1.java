package androidx.room;

import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ProducerScope;", "", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.room.RoomDatabaseKt$invalidationTrackerFlow$1", f = "RoomDatabaseExt.kt", i = {}, l = {235}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RoomDatabaseExt.kt */
final class RoomDatabaseKt$invalidationTrackerFlow$1 extends SuspendLambda implements Function2<ProducerScope<? super Set<? extends String>>, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $emitInitialState;
    final /* synthetic */ String[] $tables;
    final /* synthetic */ RoomDatabase $this_invalidationTrackerFlow;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RoomDatabaseKt$invalidationTrackerFlow$1(boolean z, RoomDatabase roomDatabase, String[] strArr, Continuation<? super RoomDatabaseKt$invalidationTrackerFlow$1> continuation) {
        super(2, continuation);
        this.$emitInitialState = z;
        this.$this_invalidationTrackerFlow = roomDatabase;
        this.$tables = strArr;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RoomDatabaseKt$invalidationTrackerFlow$1 roomDatabaseKt$invalidationTrackerFlow$1 = new RoomDatabaseKt$invalidationTrackerFlow$1(this.$emitInitialState, this.$this_invalidationTrackerFlow, this.$tables, continuation);
        roomDatabaseKt$invalidationTrackerFlow$1.L$0 = obj;
        return roomDatabaseKt$invalidationTrackerFlow$1;
    }

    public final Object invoke(ProducerScope<? super Set<String>> producerScope, Continuation<? super Unit> continuation) {
        return ((RoomDatabaseKt$invalidationTrackerFlow$1) create(producerScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        ContinuationInterceptor continuationInterceptor;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ProducerScope producerScope = (ProducerScope) this.L$0;
            AtomicBoolean atomicBoolean = new AtomicBoolean(this.$emitInitialState);
            RoomDatabaseKt$invalidationTrackerFlow$1$observer$1 roomDatabaseKt$invalidationTrackerFlow$1$observer$1 = new RoomDatabaseKt$invalidationTrackerFlow$1$observer$1(this.$tables, atomicBoolean, producerScope);
            TransactionElement transactionElement = (TransactionElement) producerScope.getCoroutineContext().get(TransactionElement.Key);
            if (transactionElement == null || (continuationInterceptor = transactionElement.getTransactionDispatcher$room_ktx_release()) == null) {
                continuationInterceptor = CoroutinesRoomKt.getQueryDispatcher(this.$this_invalidationTrackerFlow);
            }
            CoroutineScope coroutineScope = producerScope;
            CoroutineContext coroutineContext = continuationInterceptor;
            final Job launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, coroutineContext, (CoroutineStart) null, new RoomDatabaseKt$invalidationTrackerFlow$1$job$1(this.$this_invalidationTrackerFlow, roomDatabaseKt$invalidationTrackerFlow$1$observer$1, this.$emitInitialState, producerScope, this.$tables, atomicBoolean, (Continuation<? super RoomDatabaseKt$invalidationTrackerFlow$1$job$1>) null), 2, (Object) null);
            this.label = 1;
            if (ProduceKt.awaitClose(producerScope, new Function0<Unit>() {
                public final void invoke() {
                    Job.DefaultImpls.cancel$default(launch$default, (CancellationException) null, 1, (Object) null);
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
