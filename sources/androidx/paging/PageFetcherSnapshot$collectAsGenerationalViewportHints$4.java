package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H@¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "", "Key", "", "Value", "generationalHint", "Landroidx/paging/GenerationalViewportHint;", "emit", "(Landroidx/paging/GenerationalViewportHint;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PageFetcherSnapshot.kt */
final class PageFetcherSnapshot$collectAsGenerationalViewportHints$4<T> implements FlowCollector {
    final /* synthetic */ LoadType $loadType;
    final /* synthetic */ PageFetcherSnapshot<Key, Value> this$0;

    PageFetcherSnapshot$collectAsGenerationalViewportHints$4(PageFetcherSnapshot<Key, Value> pageFetcherSnapshot, LoadType loadType) {
        this.this$0 = pageFetcherSnapshot;
        this.$loadType = loadType;
    }

    public final Object emit(GenerationalViewportHint generationalViewportHint, Continuation<? super Unit> continuation) {
        Object access$doLoad = this.this$0.doLoad(this.$loadType, generationalViewportHint, continuation);
        return access$doLoad == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? access$doLoad : Unit.INSTANCE;
    }
}
