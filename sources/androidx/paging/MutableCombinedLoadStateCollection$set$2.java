package androidx.paging;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroidx/paging/CombinedLoadStates;", "currState", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: MutableCombinedLoadStateCollection.kt */
final class MutableCombinedLoadStateCollection$set$2 extends Lambda implements Function1<CombinedLoadStates, CombinedLoadStates> {
    final /* synthetic */ boolean $remote;
    final /* synthetic */ LoadState $state;
    final /* synthetic */ LoadType $type;
    final /* synthetic */ MutableCombinedLoadStateCollection this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MutableCombinedLoadStateCollection$set$2(boolean z, LoadType loadType, LoadState loadState, MutableCombinedLoadStateCollection mutableCombinedLoadStateCollection) {
        super(1);
        this.$remote = z;
        this.$type = loadType;
        this.$state = loadState;
        this.this$0 = mutableCombinedLoadStateCollection;
    }

    public final CombinedLoadStates invoke(CombinedLoadStates combinedLoadStates) {
        LoadStates loadStates;
        LoadStates loadStates2;
        if (combinedLoadStates == null || (loadStates = combinedLoadStates.getSource()) == null) {
            loadStates = LoadStates.Companion.getIDLE();
        }
        if (combinedLoadStates == null || (loadStates2 = combinedLoadStates.getMediator()) == null) {
            loadStates2 = LoadStates.Companion.getIDLE();
        }
        if (this.$remote) {
            loadStates2 = loadStates2.modifyState$paging_common(this.$type, this.$state);
        } else {
            loadStates = loadStates.modifyState$paging_common(this.$type, this.$state);
        }
        return this.this$0.computeNewState(combinedLoadStates, loadStates, loadStates2);
    }
}
