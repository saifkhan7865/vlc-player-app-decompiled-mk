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

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "Landroidx/paging/NullPaddedDiffResult;", "T", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.AsyncPagingDataDiffer$differBase$1$presentNewList$diffResult$1", f = "AsyncPagingDataDiffer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AsyncPagingDataDiffer.kt */
final class AsyncPagingDataDiffer$differBase$1$presentNewList$diffResult$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NullPaddedDiffResult>, Object> {
    final /* synthetic */ NullPaddedList<T> $newList;
    final /* synthetic */ NullPaddedList<T> $previousList;
    int label;
    final /* synthetic */ AsyncPagingDataDiffer<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AsyncPagingDataDiffer$differBase$1$presentNewList$diffResult$1(NullPaddedList<T> nullPaddedList, NullPaddedList<T> nullPaddedList2, AsyncPagingDataDiffer<T> asyncPagingDataDiffer, Continuation<? super AsyncPagingDataDiffer$differBase$1$presentNewList$diffResult$1> continuation) {
        super(2, continuation);
        this.$previousList = nullPaddedList;
        this.$newList = nullPaddedList2;
        this.this$0 = asyncPagingDataDiffer;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AsyncPagingDataDiffer$differBase$1$presentNewList$diffResult$1(this.$previousList, this.$newList, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super NullPaddedDiffResult> continuation) {
        return ((AsyncPagingDataDiffer$differBase$1$presentNewList$diffResult$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return NullPaddedListDiffHelperKt.computeDiff(this.$previousList, this.$newList, this.this$0.diffCallback);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
