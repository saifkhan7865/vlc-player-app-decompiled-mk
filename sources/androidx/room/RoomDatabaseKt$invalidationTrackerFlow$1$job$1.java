package androidx.room;

import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.channels.ProducerScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.room.RoomDatabaseKt$invalidationTrackerFlow$1$job$1", f = "RoomDatabaseExt.kt", i = {}, l = {230}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RoomDatabaseExt.kt */
final class RoomDatabaseKt$invalidationTrackerFlow$1$job$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ProducerScope<Set<String>> $$this$callbackFlow;
    final /* synthetic */ boolean $emitInitialState;
    final /* synthetic */ AtomicBoolean $ignoreInvalidation;
    final /* synthetic */ RoomDatabaseKt$invalidationTrackerFlow$1$observer$1 $observer;
    final /* synthetic */ String[] $tables;
    final /* synthetic */ RoomDatabase $this_invalidationTrackerFlow;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RoomDatabaseKt$invalidationTrackerFlow$1$job$1(RoomDatabase roomDatabase, RoomDatabaseKt$invalidationTrackerFlow$1$observer$1 roomDatabaseKt$invalidationTrackerFlow$1$observer$1, boolean z, ProducerScope<? super Set<String>> producerScope, String[] strArr, AtomicBoolean atomicBoolean, Continuation<? super RoomDatabaseKt$invalidationTrackerFlow$1$job$1> continuation) {
        super(2, continuation);
        this.$this_invalidationTrackerFlow = roomDatabase;
        this.$observer = roomDatabaseKt$invalidationTrackerFlow$1$observer$1;
        this.$emitInitialState = z;
        this.$$this$callbackFlow = producerScope;
        this.$tables = strArr;
        this.$ignoreInvalidation = atomicBoolean;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RoomDatabaseKt$invalidationTrackerFlow$1$job$1(this.$this_invalidationTrackerFlow, this.$observer, this.$emitInitialState, this.$$this$callbackFlow, this.$tables, this.$ignoreInvalidation, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RoomDatabaseKt$invalidationTrackerFlow$1$job$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.$this_invalidationTrackerFlow.getInvalidationTracker().addObserver(this.$observer);
            if (this.$emitInitialState) {
                this.$$this$callbackFlow.m1139trySendJP2dKIU(ArraysKt.toSet((T[]) this.$tables));
            }
            this.$ignoreInvalidation.set(false);
            this.label = 1;
            if (DelayKt.awaitCancellation(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th) {
                this.$this_invalidationTrackerFlow.getInvalidationTracker().removeObserver(this.$observer);
                throw th;
            }
        }
        throw new KotlinNothingValueException();
    }
}
