package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u00020\u0005H@"}, d2 = {"<anonymous>", "", "K", "", "V", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.ContiguousPagedList$deferBoundaryCallbacks$1", f = "ContiguousPagedList.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ContiguousPagedList.kt */
final class ContiguousPagedList$deferBoundaryCallbacks$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $deferBegin;
    final /* synthetic */ boolean $deferEmpty;
    final /* synthetic */ boolean $deferEnd;
    int label;
    final /* synthetic */ ContiguousPagedList<K, V> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ContiguousPagedList$deferBoundaryCallbacks$1(boolean z, ContiguousPagedList<K, V> contiguousPagedList, boolean z2, boolean z3, Continuation<? super ContiguousPagedList$deferBoundaryCallbacks$1> continuation) {
        super(2, continuation);
        this.$deferEmpty = z;
        this.this$0 = contiguousPagedList;
        this.$deferBegin = z2;
        this.$deferEnd = z3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ContiguousPagedList$deferBoundaryCallbacks$1(this.$deferEmpty, this.this$0, this.$deferBegin, this.$deferEnd, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ContiguousPagedList$deferBoundaryCallbacks$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$deferEmpty) {
                this.this$0.getBoundaryCallback$paging_common().onZeroItemsLoaded();
            }
            if (this.$deferBegin) {
                this.this$0.boundaryCallbackBeginDeferred = true;
            }
            if (this.$deferEnd) {
                this.this$0.boundaryCallbackEndDeferred = true;
            }
            this.this$0.tryDispatchBoundaryCallbacks(false);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
