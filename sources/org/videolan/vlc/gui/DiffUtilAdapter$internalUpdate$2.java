package org.videolan.vlc.gui;

import androidx.recyclerview.widget.DiffUtil;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.gui.DiffUtilAdapter;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u0002\u0012\u0004\u0012\u00020\u00040\u0001\"\u0004\b\u0000\u0010\u0003\"\b\b\u0001\u0010\u0005*\u00020\u0006*\u00020\u0007H@"}, d2 = {"<anonymous>", "Lkotlin/Pair;", "", "D", "Landroidx/recyclerview/widget/DiffUtil$DiffResult;", "VH", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.DiffUtilAdapter$internalUpdate$2", f = "DiffUtilAdapter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: DiffUtilAdapter.kt */
final class DiffUtilAdapter$internalUpdate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Pair<? extends List<? extends D>, ? extends DiffUtil.DiffResult>>, Object> {
    final /* synthetic */ List<D> $list;
    int label;
    final /* synthetic */ DiffUtilAdapter<D, VH> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DiffUtilAdapter$internalUpdate$2(DiffUtilAdapter<D, VH> diffUtilAdapter, List<? extends D> list, Continuation<? super DiffUtilAdapter$internalUpdate$2> continuation) {
        super(2, continuation);
        this.this$0 = diffUtilAdapter;
        this.$list = list;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DiffUtilAdapter$internalUpdate$2(this.this$0, this.$list, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Pair<? extends List<? extends D>, ? extends DiffUtil.DiffResult>> continuation) {
        return ((DiffUtilAdapter$internalUpdate$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            List<D> prepareList = this.this$0.prepareList(this.$list);
            DiffUtilAdapter.DiffCallback access$getDiffCallback = this.this$0.getDiffCallback();
            access$getDiffCallback.update(this.this$0.getDataset(), prepareList);
            DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(access$getDiffCallback, this.this$0.detectMoves());
            Intrinsics.checkNotNullExpressionValue(calculateDiff, "calculateDiff(...)");
            return new Pair(prepareList, calculateDiff);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
